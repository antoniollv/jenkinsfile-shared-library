#!/usr/bin/env groovy

def call(String project) {
    
    def cutChar = '/'

    project  = project - ".git"    
    lastChar = project.lastIndexOf(cutChar)
    project  = project.substring(lastChar + 1)

    return project
}