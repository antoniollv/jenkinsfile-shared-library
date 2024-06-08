apiVersion: v1
kind: Pod
spec:
  securityContext:
    runAsUser: 1001
  containers:
  <% if (jdkBlocked==false || jdkBlocked==null) { %>
    - name: jdk
      image: docker.io/eclipse-temurin:8u362-b09-jre-alpine
      resources:
        requests:
          memory: "512Mi"
        limits:
          memory: "512Mi"
      command:
        - sleep
      args:
        - infinity
  <% } %>
  <% if (jdk17Blocked==false || jdk17Blocked==null) { %>
    - name: jdk17
      image: docker.io/eclipse-temurin:17.0.9_9-jdk
      resources:
        requests:
          memory: "512Mi"
        limits:
          memory: "512Mi"
      command:
        - sleep
      args:
        - infinity
  <% } %>
  <% if (podmanBlocked==false || podmanBlocked==null) { %>
    - name: podman
      image: quay.io/podman/stable
      resources:
        requests:
          memory: "512Mi"
        limits:
          memory: "512Mi"
      command:
        - sleep
      args:
        - infinity
      securityContext:
        runAsUser: 0
        privileged: true
  <% } %>
  <% if (aksBuilderBlocked==false || aksBuilderBlocked==null) { %>
    - name: aks-builder
      image: ${imageName}/ndop_aks_builder:latest
      resources:
        requests:
          memory: "2048Mi"
          cpu: "2000m"
        limits:
          memory: "2048Mi"
          cpu: "2000m"
      imagePullPolicy: Always
      command:
        - sleep
      args:
        - infinity
  <% } %>
  <% if (lighthouseBuilderBlocked==false || lighthouseBuilderBlocked==null) { %>
    - name: lighthouse-builder
      image: ${imageName}/ndop_lighthouse_builder:latest
      resources:
        requests:
          memory: "512Mi"
        limits:
          memory: "512Mi"
      imagePullPolicy: Always
      command:
        - sleep
      args:
        - infinity
      securityContext:
        runAsUser: 0
        privileged: true
  <% } %>
  <% if (aksBuilderBlocked==true) { %>
    - name: azure-cli
      image: bitnami/azure-cli:latest
      resources:
        requests:
          memory: "256Mi"
        limits:
          memory: "256Mi"
      imagePullPolicy: Always
      command:
        - sleep
      args:
        - infinity
      securityContext:
        runAsUser: 0
        privileged: true
    - name: curl
      image: curlimages/curl:latest
      resources:
        requests:
          memory: "256Mi"
        limits:
          memory: "256Mi"
      imagePullPolicy: Always
      command:
        - sleep
      args:
        - infinity
      securityContext:
        runAsUser: 0
        privileged: true
  <% } %>
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