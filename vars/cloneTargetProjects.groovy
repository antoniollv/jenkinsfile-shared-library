#!/usr/bin/env groovy
def call (REPOSITORIOS) {
  sh """repos=(${REPOSITORIOS})
for repo in "\${repos}[@]}"
  do
    echo \$repo
done
"""
}