#!/usr/bin/env groovy

def call(String projects) {

    String[] reposTmp
    String[] repos
    reposTmp = projects.split(',')
    for(int i = 0; i < reposTmp.size(); ++i) {
        println(reposTmp[i])
        repos[i] = getDirectoryFromProject(reposTmp[i])
        println(repos[i])
    }
    return repos
}