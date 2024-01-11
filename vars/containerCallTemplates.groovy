import Render.*

def call(Map config = [:]) {
    String cloud = 'kubernetes'

    def ret = [:]

    def listContainers = config.listContainers
    def agentCreator = ""
    listContainers.each { item -> 
        agentCreator += renderTemplateText(templatePath:"./templates/java/${item}.tpl",
        tokens: [
            imageName: config.imageName,
            credentialSecret: config.credentialSecret,
            nodeSelectorValue: config.nodeSelectorValue == null ? "jenkins-worker" : config.nodeSelectorValue,
            nodeTaintKey: config.nodeTaintKey == null ? "ndop.jenkins.worker" : config.nodeTaintKey
        ])
    }
    
    ret['cloud'] = cloud
    ret['yaml'] = agentCreator

    return ret
}