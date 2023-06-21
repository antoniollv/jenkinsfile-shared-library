#!/usr/bin/env groovy
def call (REPOSITORIOS) {
  sh "repos=(${REPOSITORIOS})"
  sh '''
for repo in "${repos}[@]}"
  do
    echo $repo
done
'''
}