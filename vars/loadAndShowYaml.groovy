@Grab('org.yaml:snakeyaml:1.17')
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.DumperOptions
import static org.yaml.snakeyaml.DumperOptions.FlowStyle.BLOCK

def call() {
    getLibraryResource('mapfre/tron-pipeline-release.ORIGEN.yml', 'tron-pipeline-release.ORIGEN.yml')
    def dataYaml = readYaml  file: 'tron-pipeline-release.ORIGEN.yml'
    def projectConfig = dataYaml.project_config
    def traductionPais = new traductionLanzador().getTraductions('Panama')
    def projects = []
    projectConfig.each { project ->        
        projects.add(loadMap(project, traductionPais))
    }
    def dataYamlPais = ['planId':dataYaml.planId, 'project_config':projects, 'global_parameter':dataYaml.global_parameter]
    echo "dataYamlPais: ${dataYamlPais}"
    writeFile file:'pais.yaml', text:yamlToString(dataYamlPais)
    sh 'cat pais.yaml'
}

def loadMap(project, traductionPais) {
    def projectPais = [:]
    name = traductionPais.get(project.get('name')).toString()
    projectPais.put('name', name)
    name_stage = traductionPais.get(project.get('name_stage').toString())
    projectPais.put('name_stage', name_stage)
    name_repo = traductionPais.get(project.get('name_repo').toString())
    projectPais.put('name_repo', name_repo)
    projectPais.put('version', 'Alfalfa')
    projectPais.put('source_branch', 'Development')
    projectPais.put('fase1', '0')
    return projectPais
}

class traductionLanzador {
    private Map traductionsPanama = [  
        'core_tron_be_nwt': 'ca_tron_be_nwt',
        'core_tron_be_nwt': 'ca_tron_be_nwt',
        'core_tron_cmn_api': 'ca_tron_cmn_api',
        'core_tron_fw_tw': 'ca_tron_fw_tw',
        'core_tron_client_tw': 'ca_tron_client_tw',
        'core_tron_client_tst_tw': 'ca_tron_client_tst_tw',
        'core_tron_bom_tw': 'ca_tron_bom_tw',
        'core_tron_btc_api': 'ca_tron_btc_api'                      
    ]

    def getTraductions( String pais) {
        switch (pais) {
        case 'Panama':
            return traductionsPanama
            break
        }
    }
}

@NonCPS
String yamlToString(Object data){
    def opts = new DumperOptions()
    opts.setDefaultFlowStyle(BLOCK)
    return new Yaml(opts).dump(data)
}
