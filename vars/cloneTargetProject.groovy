#!/usr/bin/env groovy
def call(REPOSITORIO, RAMA, GIT_USER) {
    directorio = makeDirectoryWhereClone(REPOSITORIO)
    dir("$directorio") {
        git branch: "$RAMA",
            credentialsId: "$GIT_USER",
            url: "$REPOSITORIO"
    }
}