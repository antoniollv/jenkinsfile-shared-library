#!/usr/bin/env groovy
def call (REPOSITORIOS) {
    sh """
for repositorio in \"\${${REPOSITORIOS}[@]}\"
        do
  echo \$repositorio
done
"""
}