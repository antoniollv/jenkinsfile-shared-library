#!/usr/bin/env groovy
def call(String resourceToCopy = '/test/touch.txt' String pathToCopy = '.') {
    try {
      def tnsnames = libraryResource "com/moradores$resourceToCopy"
      sh(returnStatus: false, script: "cd $pathToCopy;echo \"$tnsnames\" > tnsnames.ora")

    } catch (Exception e){
      println("ERROR -- en executeSHOracle debido a ${e}")
      error("${env.STAGE_NAME} ERROR -- en executeSHOracle debido a ${e}")
    }
}
