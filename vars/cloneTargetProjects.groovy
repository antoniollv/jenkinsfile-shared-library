#!/usr/bin/env groovy
def call (REPOSITORIOS) {
    String[] repos
    repos = REPOSITORIOS.split(',')
    for(String repo : repos) {
        println(repo)
    }
}