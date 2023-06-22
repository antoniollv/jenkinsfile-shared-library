#!/usr/bin/env groovy
def call(env){
    sh '''
apt-get update
git  --version || apt-get install -y git
diff --version || apt-get install -y diff
rm -rf /var/lib/apt/lists/* 
'''    
}