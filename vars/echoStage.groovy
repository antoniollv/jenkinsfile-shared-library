#!/usr/bin/env groovy
def call() {
    def guiones = 50
    def pad     = 20
    println ""
    println " HOSTNAME ".center(guiones,"-")
    println println " ${STAGE_NAME} ".center(guiones,"-")
    println " HOSTNAME ".center(guiones,"-")
    println ""
}
