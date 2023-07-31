SUMMARY = "Docker Prune - enabled services"
DESCRIPTION = "Systemd timer and at-boot-service to clean up docker containers"
HOMEPAGE = "https://ci4rail.com"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append = " file://hostname2hosts.service \
                   file://hostname2hosts.timer \
                   file://hostname2hosts.sh \
"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit systemd
inherit features_check

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "hostname2hosts.service hostname2hosts.timer"
SYSTEMD_AUTO_ENABLE = "enable"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/hostname2hosts.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/hostname2hosts.timer ${D}${systemd_unitdir}/system/
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/hostname2hosts.sh ${D}${bindir}
}

REQUIRED_DISTRO_FEATURES= "systemd"

RDEPENDS_${PN} += "bash"