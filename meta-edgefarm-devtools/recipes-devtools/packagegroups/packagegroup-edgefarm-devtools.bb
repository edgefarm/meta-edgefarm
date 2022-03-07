SUMMARY = "edgefarm development tools package group"
DESCRIPTION = "This provides edgefarm tooling required for development purposes"

inherit packagegroup

RDEPENDS_${PN} = "\
                  bind-utils \
                  jq \
                  nats \
                  persistent-tailscale \
                  tailscale \
                  termshark \
                  yq \
                  "
