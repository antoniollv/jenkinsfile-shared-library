import Render.*

def call(Map config = [:]) {
    String cloud = 'kubernetes'

    def ret = [:]
    def template = 'templates/agentTemplates.tpl'
    def containers = ''
    def listContainers = config.listContainers
    listContainers.each { item ->
        containers = containers + libraryResource("templates/${item}.tpl") + "\n"
    }

    def agentCreator = renderTemplateText(templatePath:template,
        tokens: [
            acrName: config.acrName,
            credentialSecret: config.credentialSecret,
            nodeSelectorValue: config.nodeSelectorValue == null ? 'jenkins-worker' : config.nodeSelectorValue,
            nodeTaintKey: config.nodeTaintKey == null ? 'ndop.jenkins.worker' : config.nodeTaintKey,
            containers: containers.padLeft(4)
        ])

    ret['cloud'] = cloud
    ret['yaml'] = agentCreator
    return ret
}
