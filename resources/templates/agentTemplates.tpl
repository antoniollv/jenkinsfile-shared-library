apiVersion: v1
kind: Pod
spec:
  securityContext:
    runAsUser: 1001
  containers:
${containers.porLaDerecha(10)}
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