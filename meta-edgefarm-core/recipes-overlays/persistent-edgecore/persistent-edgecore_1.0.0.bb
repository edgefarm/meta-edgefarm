SUMMARY = "Creates and mounts overlay directories"
DESCRIPTION = "Creates and mounts overlay directories for edgecore state."
HOMEPAGE = "https://ci4rail.com"

inherit systemd
inherit features_check
require persistent-edgecore.inc

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://persistent-edgecore.service"
SYSTEMD_SERVICE_${PN} = "persistent-edgecore.service"

# ${bindir} expands to `/usr/bin/`
CREATE_OVERLAY_DIRECTORIES_SCRIPT ?= "${bindir}/persistent-edgecore.sh"

SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${systemd_system_unitdir}

    cat <<EOF > ${D}${CREATE_OVERLAY_DIRECTORIES_SCRIPT}
#!/bin/sh
# etc
mkdir -p ${EDGECORE_OVERLAY_CONFIG_ROOT_DIR}
mkdir -p ${EDGECORE_OVERLAY_CONFIG_LOWER_DIR}
mkdir -p ${EDGECORE_OVERLAY_CONFIG_UPPER_DIR}
mkdir -p ${EDGECORE_OVERLAY_CONFIG_WORK_DIR}

# mount overlays
mount -t overlay overlay -o lowerdir=${EDGECORE_OVERLAY_CONFIG_LOWER_DIR},upperdir=${EDGECORE_OVERLAY_CONFIG_UPPER_DIR},workdir=${EDGECORE_OVERLAY_CONFIG_WORK_DIR} ${EDGECORE_OVERLAY_CONFIG_LOWER_DIR}

EOF
    chmod +x ${D}${CREATE_OVERLAY_DIRECTORIES_SCRIPT}
    install -m 0644 ${WORKDIR}/persistent-edgecore.service ${D}${systemd_system_unitdir}
}

FILES_${PN} += "${CREATE_OVERLAY_DIRECTORIES_SCRIPT}"
FILES_${PN} += "${systemd_system_unitdir}/persistent-edgecore.service"

REQUIRED_DISTRO_FEATURES = "systemd"
