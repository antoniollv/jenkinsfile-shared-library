#!/usr/bin/env groovy
def call (REPOSITORIOS, RAMA, GIT_USER) {
    String[] repos
    repos = REPOSITORIOS.split(',')
    for(String repo : repos) {
        cloneTargetProject(repo, RAMA, GIT_USER)
    }
}