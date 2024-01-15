- name: podman
  image: quay.io/podman/stable
  command:
    - sleep
  args:
    - infinity
  securityContext:
    runAsUser: 0
    privileged: true