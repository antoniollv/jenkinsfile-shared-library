#!/usr/bin/env groovy
def call (REPOSITORIOS) {
    String[] repos
    repos = repos.split(',')
    for(String repo : repos) {
        println(repo)
    }
}