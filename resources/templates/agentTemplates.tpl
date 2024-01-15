apiVersion: v1
kind: Pod
spec:
  securityContext:
    runAsUser: 1001
  containers:
<% containers = containers.padRight(4) %>
$containers
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