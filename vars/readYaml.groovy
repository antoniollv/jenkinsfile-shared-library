
@Grab(group='org.yaml', module='snakeyaml', version='1.25')
import org.yaml.snakeyaml.Yaml
def call(String path){
        
        def pathYml = path
        
        def request = libraryResource pathYml
        def yamlObject = new Yaml()
        def yml = yamlObject.load(request)

        return yml
}