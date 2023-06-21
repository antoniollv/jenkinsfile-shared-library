#!/usr/bin/env groovy
def call (REPOSITORIO, RAMA, GIT_USER) {
    git branch: "$RAMA",
        credentialsId: "git-user",
        url: "$REPOSITORIO"
}