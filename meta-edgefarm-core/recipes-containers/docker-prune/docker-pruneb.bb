SUMMARY = "Docker Prune - disabled services"
DESCRIPTION = "Service to clean up docker system - triggered by docker-prune.timer"
HOMEPAGE = "https://ci4rail.com"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI_append = " file://${BPN}.service"


SYSTEMD_AUTO_ENABLE = "disable"
SYSTEMD_SERVICE_${PN} = "\
    ${BPN}.service \
"
FILES_${PN} += "${systemd_system_unitdir}/${BPN}.service"

do_install() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/${BPN}.service ${D}/${systemd_unitdir}/system
}

inherit systemd
