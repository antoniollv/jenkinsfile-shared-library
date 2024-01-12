import Render.*

def call(Map config = [:]) {
    String cloud = 'kubernetes'

    def ret = [:]
    def template = 'templates/agentTemplates.tpl'
    def contenedores = ''
    def listContainers = config.listContainers
    listContainers.each { item ->
        contenedores += libraryResource "templates/${item}.tpl"
    }

    agentCreator += renderTemplateText(templatePath:template,
        tokens: [
            acrName: config.acrName,
            credentialSecret: config.credentialSecret,
            nodeSelectorValue: config.nodeSelectorValue == null ? 'jenkins-worker' : config.nodeSelectorValue,
            nodeTaintKey: config.nodeTaintKey == null ? 'ndop.jenkins.worker' : config.nodeTaintKey
        ])

    ret['cloud'] = cloud
    ret['yaml'] = agentCreator
    return ret
}
