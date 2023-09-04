/**
*  Método para obtener un recurso desde librería como archivo en el sistema de ficheros del nodo.
*
*  @param resource    Recuros en la librería
*  @param nodePath    Path donde se creara el recurso como archivo
*/
def call(resource, nodePath) {
    def resourceFile = libraryResource "com/moradores/${resource}"
    sh(returnStatus: false, script: "echo \"$resourceFile\" > \"$nodePath\"")
}