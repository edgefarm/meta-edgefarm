SUMMARY = "edgefarm specific package groups"
DESCRIPTION = "This provides edgefarm specific package groups to enable edgefarm functionality on a device"

inherit packagegroup

PACKAGES = "\
    packagegroup-edgefarm-base \
    "

RDEPENDS_packagegroup-edgefarm-base = "\
    docker-prune \
    conntrack-tools \
    ebtables \
    socat \
    iproute2 \
    iproute2-tc \
    cni-plugins \
    crictl \
    k8s \
    hostname2hosts \
    "
