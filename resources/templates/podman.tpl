apiVersion: v1
kind: Pod
spec:
  securityContext:
    runAsUser: 1001
  containers:
    - name: podman
      image: quay.io/podman/stable
      command:
        - sleep
      args:
        - infinity
      securityContext:
        runAsUser: 0
        privileged: true
  imagePullSecrets:
    - name: $credentialSecret
  // tolerations:
// - key: "ndop.jenkins.worker"
//   value: "true"
//   effect: "NoSchedule"
// nodeSelector:
//   workload: jenkins-worker