#!/usr/bin/env groovy

def call(String projects) {

    String[] repos
    repos = projects.split(',')
    for(int i = 0; i < repos.size(); ++i) {
        repos[i] = getDirectoryFromProject(repos[i])
    }
    return repos
}