SUMMARY = "Docker Prune - enabled services"
DESCRIPTION = "Systemd timer and at-boot-service to clean up docker containers"
HOMEPAGE = "https://ci4rail.com"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI_append = " file://${BPN}-at-boot.service"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "\
    ${BPN}-at-boot.service \
"
FILES_${PN} += "${systemd_system_unitdir}/${BPN}-at-boot.service"

do_install() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/${BPN}-at-boot.service ${D}/${systemd_unitdir}/system
}

inherit systemd
