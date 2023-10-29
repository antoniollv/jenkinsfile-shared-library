#!/usr/bin/env groovy
@Grab(group='org.yaml', module='snakeyaml', version='1.25')
import org.yaml.snakeyaml.Yaml

// Método que lanza los repositorio en el orden que se indica en el yml de orden de despliegue.
def call(repositories, country = 'URUGUAY') {
    def fileYaml = 'deployment-order.yml'
    getLibraryResource("mapfre/${fileYaml}", fileYaml)
    def deploymentOrder = readYaml  file: fileYaml
    def map = [:]
    
    if(country == 'URUGUAY') {
        for(repos in deploymentOrder.deployment_order){
            if(repositories.contains(repos.name)){
                def reposToDeploy = "${repos.name}"
                def repoAndDependencies = repos
                map << ["${repoAndDependencies.name}" : { launchJobs("${repoAndDependencies.name}", repoAndDependencies, repositories) }]
            }
            for (repository in repositories){
                if(repos.name!=repository && repos.dependencies != null && !repositories.contains(repos.name) && repos.dependencies.contains(repository)){
                    def reposToDeploy = "${repository}"
                    map << ["${repository}" : { launchJobs("${reposToDeploy}", null, null) }]
                }
            }
        }
        echo "parallel ${map}"
    }else {
        def repositoryList= []
        repositoryList = repositories.split(',')
        for(repos in deploymentOrder.deployment_order){
            if(repositoryList.contains(repos.name)){
                def reposToDeploy = "${repos.name}"
                def repoAndDependencies = repos
                map << ["${repoAndDependencies.name}" : { launchJobs("${repoAndDependencies.name}", repoAndDependencies, repositoryList) }]
            }
            for (repository in repositoryList){
                if(repos.name!=repository && repos.dependencies != null && !repositoryList.contains(repos.name) && repos.dependencies.contains(repository)){
                    def reposToDeploy = "${repository}"
                    map << ["${repository}" : { launchJobs("${reposToDeploy}", null, null) }]
                }
            }
        }
        echo "parallel ${map}"
    }
}

// Lanza el job y si tiene repositorios dependendientes, lo mandan a un metodo para evaluar si se tiene que lanzar
def private launchJobs (repository, repos, repositoryList){
    def jobResult = prepareJob (repository)
    if (jobResult == Constants.SUCCESS && ((repos != null && repos.dependencies != null) || repositoryList != null)){
        launchDependenciesJobs(repos, repositoryList)
    }
}

// Metodo que lanza los jobs
def private prepareJob (repository){
    def repositoryJob = PIPELINE_REPOSITORY_BRANCH.replace(":repository","${repository}").replace(":branch","development")
    echo "build job: "${repositoryJob}", parameters: [booleanParam(name:'MANUAL_BUILD', value: true)], propagate: false"
    //def job = build job: "${repositoryJob}", parameters: [booleanParam(name:'MANUAL_BUILD', value: true)], propagate: false
    // if(job.result != Constants.SUCCESS) {
    //   unstable "WARNING: Update LB job result is ${job.result}"
    // }
    // echo "JOB RESULT: ${repository} - ${job.result}"
    // return job.result
}

// Método que evalua los que dependen de un repositoprio que ya se ha lanzado en el pase. 
// Si alguno de los repositorios tambien se tiene que desplegar en este pase, se lanza el job
def private launchDependenciesJobs(repos, repositoryList) {
    def mapDependencies = [:]
    if(repos.name != null && repos.dependencies != null) {
        for (repository in repositoryList){
            if (repos.dependencies.contains(repository)){
                def reposToDeploy = "${repository}"
                mapDependencies << ["${reposToDeploy}" : { prepareJob ("${reposToDeploy}") }]
            }
        }
    }
    echo "parallel ${mapDependencies}"
}
