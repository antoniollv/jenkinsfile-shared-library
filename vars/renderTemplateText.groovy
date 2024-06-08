import Render.*

def call(Map config = [:]) {
    /*
    config.templatePath  : Path inside lib resources of the template
    config.tokens        : tokens as key,value to render the template
    */
    // Renders a template text.
    def template = libraryResource config.templatePath
    def bind = config.tokens

    def render = new Render(input: template, binding: bind)

    def result = render.Rendering()

    return result
}