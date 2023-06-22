#!/usr/bin/env groovy

def call(String projects) {

    String[] repos
    repos = projects.split(',')
    for(int i = 0; i < repos.size(); ++i) {
        repo[i] = getDirectoryFromProject(repo[i])
    }
    return repos
}