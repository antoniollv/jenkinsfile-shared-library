#!/usr/bin/env groovy
def call (REPOSITORIO, RAMA, GIT_USER) {
    git branch: "$RAMA",
        credentialsId: "%GIT_USER%",
        url: "$REPOSITORIO"
}