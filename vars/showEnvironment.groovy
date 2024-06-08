#!/usr/bin/env groovy
def call() {
    def guiones = 50
    def pad     = 20
    println "Running Job:".padRight(pad) + "${env.JOB_NAME}"
    println       "Build:".padRight(pad) + "${env.BUILD_ID} on server ${env.JENKINS_URL}"
    println " HOSTNAME ".center(guiones,"-")
    sh "hostname"
    println "".center(guiones,"-")
    println " GLOBALS VARIABLES ".center(guiones,"-")
    println      "PATH:".padRight(pad) + "${env.PATH}"
    println "WORKSPACE:".padRight(pad) + "${env.WORKSPACE}"
    println "".center(guiones,"-")
    println " ALL ENVIRONMENT VARIABLES ".center(guiones,"-")
    sh 'printenv'
    println "".center(guiones,"-")
    println " ALL POM VARIABLES ".center(guiones,"-")
    println "".center(guiones,"-")
    println " ALL BITBUCKET VARIABLES ".center(guiones,"-")
    println "".center(guiones,"-")
}
