@Grab('org.yaml:snakeyaml:1.17')
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.DumperOptions
import static org.yaml.snakeyaml.DumperOptions.FlowStyle.BLOCK

def call(String ruta, String yamlName, String pais) {
    getLibraryResource("${ruta}/${yamlName}", yamlName)
    def dataYaml = readYaml  file: yamlName
    def projectConfig = dataYaml.project_config
    def traductionPais = new traductionLanzador().getTraductions(pais)
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
    private Map traductionsPais01 = [  
        'clave0': 'valorClave0Pais01',
        'clave1': 'valorClave1Pais01'                      
    ]

    private Map traductionsPais02 = [  
        'clave0': 'valorClave0Pais02',
        'clave1': 'valorClave1Pais02'                      
    ]

    def getTraductions( String pais) {
        switch (pais) {
        case 'Pais01':
            return traductionsPais01
            break
        case 'Pais02':
            return traductionsPais02
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
