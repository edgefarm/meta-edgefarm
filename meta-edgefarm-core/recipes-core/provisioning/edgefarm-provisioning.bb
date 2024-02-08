SUMMARY = "Provisioning scripts for edgefarm"
DESCRIPTION = "Installation of provisioning and unprovisioning scripts for edgefarm"
HOMEPAGE = "https://ci4rail.com"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append = " file://edgefarm-join.sh \
                   file://edgefarm-unprovision.sh \
"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/edgefarm-join.sh ${D}${bindir}
    install -m 0755 ${WORKDIR}/edgefarm-unprovision.sh ${D}${bindir}
}

RDEPENDS_${PN} += "bash"

FILES_${PN} += "${bindir}/edgefarm-join.sh \
                ${bindir}/edgefarm-unprovision.sh \
"