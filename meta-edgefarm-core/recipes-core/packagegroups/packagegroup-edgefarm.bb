SUMMARY = "edgefarm specific package groups"
DESCRIPTION = "This provides edgefarm specific package groups to enable edgefarm functionality on a device"

inherit packagegroup

PACKAGES = "\
    packagegroup-edgefarm-base \
    "

RDEPENDS_packagegroup-edgefarm-base = "\
    conntrack-tools \
    ebtables \
    socat \
    iproute2 \
    iproute2-tc \
    k8s \
    docker-prune \
    netbird \
    edge-netif-forward \
    hostname2hosts \
    "
