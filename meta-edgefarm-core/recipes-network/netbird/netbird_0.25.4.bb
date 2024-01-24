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

SRC_URI_append = " file://netbird.service"
SRC_URI_aarch64 = "https://github.com/netbirdio/netbird/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm64"

SRC_URI[arm64.md5sum] = "ae2c09851da36fd826f74f94883e9d11"
SRC_URI[arm64.sha256sum] = "c3b91a01b56c9425376d6da72e4690d86050b1faa7bf2fd746a664ebdc72aad4"

inherit systemd


do_install() {
    install -d ${D}/${bindir}
    install -d ${D}${systemd_unitdir}/system/

    install ${WORKDIR}/netbird ${D}/${bindir}/netbird
    install -m 0644 ${WORKDIR}/${PN}.service ${D}/${systemd_unitdir}/system
}

FILES_${PN} += "${bindir}/netbird"
FILES_${PN} += "${systemd_unitdir}/system/netbird.service"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "netbird.service"
SYSTEMD_AUTO_ENABLE = "enable"