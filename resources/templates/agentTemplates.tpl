<% [
    'groovy.text.*',
].each { importClass -> %>
    <% "import ${importClass}" %>
<% } %>
apiVersion: v1
kind: Pod
spec:
  securityContext:
    runAsUser: 1001
  containers:
$containers.padLeft(4)
  imagePullSecrets:
    - name: $credentialSecret
<% if (nodeSelectorValue!="--__UNDEFINED__--") { %>
  tolerations:
  - key: '$nodeTaintKey'
    value: "true"
    effect: "NoSchedule"
  nodeSelector:
    workload: '$nodeSelectorValue'
<% } %>