#!/usr/bin/env groovy

def call(String projects) {

    String[] repos
    repos = projects.split(',')
    for(String repo : repos) {
        repo = getDirectoryFromProject(repo)
    }
    return repos
}