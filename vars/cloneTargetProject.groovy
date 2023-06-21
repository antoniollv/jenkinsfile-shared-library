#!/usr/bin/env groovy
def call(REPOSITORIO, RAMA, GIT_USER) {
    dir = makeDirectoryWhereClone(REPOSITORIO)
    sh "cd $dir"
    git branch: "$RAMA",
        credentialsId: "$GIT_USER",
        url: "$REPOSITORIO"
    sh "cd .."
}