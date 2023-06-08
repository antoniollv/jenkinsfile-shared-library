def call() {
    echo "Running Job: ${env.JOB_NAME} Build: ${env.BUILD_ID} on ${env.JENKINS_URL}"
    echo "----------- HOSTNAME --------------"
    sh "hostname"
    echo "-----------------------------------"
    echo "-------- GLOBALS VARIABLES --------"
    echo "PATH = ${env.PATH}"
    echo "WORKSPACE= ${env.WORKSPACE}"
    echo "-----------------------------------"
    echo "---- ALL ENVIRONMENT VARIABLES ----"
    sh 'printenv'
    echo "-----------------------------------"
    echo "-------- ALL POM VARIABLES --------"
    echo "-----------------------------------"
    echo "----- ALL BITBUCKET VARIABLES -----"
    echo "-----------------------------------"
}
