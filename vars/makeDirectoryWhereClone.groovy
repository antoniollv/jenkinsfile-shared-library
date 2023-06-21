#!/usr/bin/env groovy
def call(REPOSITORIO) {
    delimitador = '/'
    REPOSITORIO = REPOSITORIO - ".git"
    //Quitar el final .git
    ultimoDelimitador = REPOSITORIO.lastIndexOf(delimitador)
    REPOSITORIO = REPOSITORIO.substring(ultimoDelimitador + 1)
    sh "mkdir -f $REPOSITORIO"
    return REPOSITORIO
}