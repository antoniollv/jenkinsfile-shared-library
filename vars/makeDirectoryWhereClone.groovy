#!/usr/bin/env groovy
def call(project) {
    project = getDirectoryFromProject(project)
    sh "mkdir -p $project"
    return project
}