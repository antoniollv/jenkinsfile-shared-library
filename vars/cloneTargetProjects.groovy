#!/usr/bin/env groovy
def call (REPOSITORIOS) {
  sh "repos=(${REPOSITORIOS})"
  sh '''
for repon in "${repos}[@]}"
  do
    echo $repo
done
'''
}