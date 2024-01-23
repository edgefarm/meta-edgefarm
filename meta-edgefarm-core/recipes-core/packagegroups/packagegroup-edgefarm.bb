SUMMARY = "edgefarm specific package groups"
DESCRIPTION = "This provides edgefarm specific package groups to enable edgefarm functionality on a device"

inherit packagegroup

PACKAGES = "\
    packagegroup-edgefarm-base \
    "

RDEPENDS_packagegroup-edgefarm-base = "\
    conntrack \
    conntrack-tools \
    ebtables \
    socat \
    iproute2 \
    iproute2-tc \
    crictl \
    k8s \
    hostname2hosts \
    docker-prune \
    netbird \
    "
