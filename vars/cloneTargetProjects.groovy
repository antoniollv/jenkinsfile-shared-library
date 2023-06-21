#!/usr/bin/env groovy
def call (REPOSITORIOS) {
  sh """IFS="," read -a repos <<< ${REPOSITORIOS}
for repo in "${repos}[@]}"
  do
    echo $repo
done
"""
}