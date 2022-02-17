SUMMARY = "edgefarm specific package groups"
DESCRIPTION = "This provides edgefarm specific package groups to enable edgefarm functionality on a device"

inherit packagegroup

PACKAGES = "\
    packagegroup-edgefarm-base \
    "

RDEPENDSf_packagegroup-edgefarm-base = "\
    edgecore \
    persistent-edgecore \
    "
