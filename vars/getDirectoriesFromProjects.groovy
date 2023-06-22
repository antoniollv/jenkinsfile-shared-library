#!/usr/bin/env groovy

def call(String projects) {

    def repos = []
    String[] reposTmp
    reposTmp = projects.split(',')
    for(int i = 0; i < reposTmp.size(); ++i) {
        println(reposTmp[i])
        directory = getDirectoryFromProject(reposTmp[i])
        repos.add(directory)
        println(repos[i])
    }
    return repos
}