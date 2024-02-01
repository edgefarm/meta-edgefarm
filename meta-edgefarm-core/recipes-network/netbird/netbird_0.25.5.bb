SUMMARY = "Install netbird"
DESCRIPTION = "Netbird client Linux from pre-built binaries"

HOMEPAGE = "https://github.com/netbirdio/netbird"
SECTION = "net"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=31f22fa533c3258d02c8896e546593f0"

# skip already-stripped check for now (we download a pre-built binary)
INSANE_SKIP_${PN} += "already-stripped"

COMPATIBLE_HOST = "(aarch64).*-linux"

DIRECTORY_aarch64 = "${BPN}_${PV}_linux_arm64"

SRC_URI_append = " file://netbird.service \
                   file://netbird.sysconfig"
SRC_URI_aarch64 = "https://github.com/netbirdio/netbird/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm64"

SRC_URI[arm64.md5sum] = "2b761282ce43f936299596728332e09b"
SRC_URI[arm64.sha256sum] = "0f1ee9660da2b1e3de7e6101fdc2bf435a12887ee1daa807ad17589a2a6e4e1f"

inherit systemd


do_install() {
    install -d ${D}${bindir}
    install -d ${D}${systemd_unitdir}/system/
    install -d ${D}${sysconfdir}/sysconfig

    install ${WORKDIR}/netbird ${D}/${bindir}/netbird
    install -m 0644 ${WORKDIR}/${PN}.service ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/netbird.sysconfig ${D}/${sysconfdir}/sysconfig/netbird
}

FILES_${PN} += "${bindir}/netbird"
FILES_${PN} += "${systemd_unitdir}/system/netbird.service"
FILES_${PN} += "${bindir}/netbird.sysconfig"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "netbird.service"
SYSTEMD_AUTO_ENABLE = "enable"