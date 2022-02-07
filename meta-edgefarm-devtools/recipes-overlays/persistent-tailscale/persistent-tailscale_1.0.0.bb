inherit systemd
inherit features_check
require persistent-tailscale.inc

SUMMARY = "Creates and mounts overlay directories"
DESCRIPTION = "Creates and mounts overlay directories for tailscale state."
HOMEPAGE = "https://ci4rail.com"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://persistent-tailscale.service"
SYSTEMD_SERVICE_${PN} = "persistent-tailscale.service"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# ${bindir} expands to `/usr/bin/`
CREATE_OVERLAY_DIRECTORIES_SCRIPT ?= "${bindir}/persistent-tailscale.sh"

SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${systemd_system_unitdir}

    cat <<EOF > ${D}${CREATE_OVERLAY_DIRECTORIES_SCRIPT}
#!/bin/sh
# etc
mkdir -p ${TAILSCALE_OVERLAY_CONFIG_ROOT_DIR}
mkdir -p ${TAILSCALE_OVERLAY_CONFIG_LOWER_DIR}
mkdir -p ${TAILSCALE_OVERLAY_CONFIG_UPPER_DIR}
mkdir -p ${TAILSCALE_OVERLAY_CONFIG_WORK_DIR}

# mount overlays
mount -t overlay overlay -o lowerdir=${TAILSCALE_OVERLAY_CONFIG_LOWER_DIR},upperdir=${TAILSCALE_OVERLAY_CONFIG_UPPER_DIR},workdir=${TAILSCALE_OVERLAY_CONFIG_WORK_DIR} ${TAILSCALE_OVERLAY_CONFIG_LOWER_DIR}

EOF
    chmod +x ${D}${CREATE_OVERLAY_DIRECTORIES_SCRIPT}
    install -m 0644 ${WORKDIR}/persistent-tailscale.service ${D}${systemd_system_unitdir}
}

FILES_${PN} += "${CREATE_OVERLAY_DIRECTORIES_SCRIPT}"
FILES_${PN} += "${systemd_system_unitdir}/persistent-tailscale.service"

REQUIRED_DISTRO_FEATURES = "systemd"
