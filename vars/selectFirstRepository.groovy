def call(def ymlConfig, def deployConfig) {
  def respuestaTech = [:]
  def mensaje = "Select repositories order"
  def listExtendedChoices = []  
  for(technology in deployConfig.tech_repositories.keySet()) {
    if(stageConditionalApp(technology, ymlConfig, deployConfig)) {
      def ordedList = deployConfig.deployment_order."${technology}"
      def list = []
      try {
        // crear los diferentes tipos de inputs. el tipo depende de si es consecutivo o paralelo el despliegue
        if(ordedList.type == "consec") {
          list = getReposListConsec(ymlConfig, ordedList.repositories)
        //   listConsec["${technology}"]= list
          list.add("none")
          listExtendedChoices.add(choice(choices: list, description: "", name: "${technology}"))
        } else {
          list = getReposListParallel(ymlConfig, ordedList.repositories, [])
          listExtendedChoices.add(extendedChoice(defaultValue: list.join(','), value: list.join(','), type: 'PT_CHECKBOX', name: "${technology}"))
        }
      } catch(Exception err) {
        println("RESPUESTA TIMEOUT: ${respuestaTech}")
        error("lanzadorAPI - selectFirstRepository\n${err}")
      }
    }
  }
    // Crea la pagina de input y se recoge la respuesta
    respuestaTech = makeQuestionMultichoice(mensaje, listExtendedChoices)
    if(respuestaTech != "none") {
      // Cuando solo hay un input de tecnologia, el response devuelve un listado con el resultado, 
      // y no devuelve el nombre de la tecnologia. 
      // Cuando hay mas un input, el response es un map con key la tecnologia y value lo seleccionado
      if(listExtendedChoices.size() <= 1) {
        for(technology in deployConfig.tech_repositories) {
            if(technology.value.contains(respuestaTech)) {
                respuestaTech = [(technology.key): "${respuestaTech}"]
            }
        }
      }
      //Procesado del resultado de los input para saber que repositorios se despliegan
      reposToDeploy = processInputResponses(respuestaTech, ymlConfig, deployConfig)
    }
    echo "REPOS TO DEPLOY: ${reposToDeploy}"
  return reposToDeploy
}

def stageConditionalApp(technology, ymlConfig, deployConfig) {
  for(repository in deployConfig.tech_repositories."${technology}") {
    def project = getProject(repository, ymlConfig)
    if(project != "none") {
      return true
    }
  }
  return false
}

def getReposListConsec(def ymlConfig, def ordedList) {
  def list = []
  for(repository in ordedList) {
    if(getProject(repository, ymlConfig) != "none") {
      list << repository
    }
  }
  return list 
}