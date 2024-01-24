SUMMARY = "Edge Node Traffic Forwarder"
DESCRIPTION = "Scripts to forward network traffic of edge node from dummy network interface to real network interface"
HOMEPAGE = "https://ci4rail.com"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://90-wt0.rules \
                   file://add-edge0.sh \
                   file://add-wt0.sh \
                   file://remove-wt0.sh \
                   file://edge0-device.service"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "edge0-device.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/edge0-device.service ${D}${systemd_unitdir}/system/
    install -d ${D}/usr/local/bin
    install -m 0755 ${WORKDIR}/add-edge0.sh ${D}/usr/local/bin/
    install -m 0755 ${WORKDIR}/add-wt0.sh ${D}/usr/local/bin/
    install -m 0755 ${WORKDIR}/remove-wt0.sh ${D}/usr/local/bin/
    install -d ${D}/etc/udev/rules.d
    install -m 0755 ${WORKDIR}/90-wt0.rules ${D}/etc/udev/rules.d
}

RDEPENDS_${PN} += "bash"

FILES_${PN} += "/usr/local/bin/add-edge0.sh"
FILES_${PN} += "/usr/local/bin/add-wt0.sh"
FILES_${PN} += "/usr/local/bin/remove-wt0.sh"
FILES_${PN} += "/etc/udev/rules.d/90-wt0.rules"
