SUMMARY = "Install netbird"
DESCRIPTION = "Netbird client Linux from pre-built binaries"

HOMEPAGE = "https://github.com/netbirdio/netbird"
SECTION = "net"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=31f22fa533c3258d02c8896e546593f0"

#COMPATIBLE_HOST = "(i.86|x86_64|aarch64|arm).*-linux"
COMPATIBLE_HOST = "(aarch64).*-linux"
# Archive URLs:
#   https://pkgs.tailscale.com/stable/tailscale_1.20.2_386.tgz
#   https://pkgs.tailscale.com/stable/tailscale_1.20.2_amd64.tgz
#   https://pkgs.tailscale.com/stable/tailscale_1.20.2_arm.tgz
#   https://pkgs.tailscale.com/stable/tailscale_1.20.2_arm64.tgz
#

# DIRECTORY_x86-64 = "${BPN}_${PV}_amd64"
# DIRECTORY_arm = "${BPN}_${PV}_arm"
DIRECTORY_aarch64 = "${BPN}_${PV}_linux_arm64"
# DIRECTORY_i586 = "${BPN}_${PV}_386"
# DIRECTORY_i686 = "${BPN}_${PV}_386"



# SRC_URI_x86-64 = "https://pkgs.tailscale.com/stable/${DIRECTORY}.tgz;name=amd64"
# SRC_URI_arm = "https://pkgs.tailscale.com/stable/${DIRECTORY}.tgz;name=arm"
# SRC_URI_i586 = "https://pkgs.tailscale.com/stable/${DIRECTORY}.tgz;name=386"
# SRC_URI_i686 = "https://pkgs.tailscale.com/stable/${DIRECTORY}.tgz;name=386"
SRC_URI_aarch64 = "https://github.com/netbirdio/netbird/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm64"

# SRC_URI[amd64.md5sum] = "78448258bc6ff8834eaa1bac33ef114a"
# SRC_URI[amd64.sha256sum] = "b759e0185a4a2c5a927e71d3b2f6aa6c58aa293ca968397e783572a1b82da4d5"
# SRC_URI[arm.md5sum] = "d0442170446b38006433a3c9bc459053"
# SRC_URI[arm.sha256sum] = "315f105f007dde15374dda00f702215dd1b598e61b57911e01ee605f714b47bb"
SRC_URI[arm64.md5sum] = "ae2c09851da36fd826f74f94883e9d11"
SRC_URI[arm64.sha256sum] = "c3b91a01b56c9425376d6da72e4690d86050b1faa7bf2fd746a664ebdc72aad4"
# SRC_URI[386.md5sum] = "41dfb5b8305d7e4c2b8a2efdf465931e"
# SRC_URI[386.sha256sum] = "9400aa65dc4f7f6d2c2adcdfb1b9c991fa419e833f528964aa8a65021a5f2a2b"

#inherit systemd


do_install() {
    install -d ${D}/${bindir}
    install -d ${D}${sysconfdir}/default/
    install -d ${D}${systemd_unitdir}/system/
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/

    install ${WORKDIR}/netbird ${D}/${bindir}/netbird
    # ${D}/${bindir}/netbird service install
    # ${D}/${bindir}/netbird service start
}

FILES_${PN} += "${bindir}/netbird"
# FILES_${PN} += "${sbindir}/tailscaled"
# FILES_${PN} += "${sysconfdir}/default/tailscaled"
# FILES_${PN} += "${systemd_unitdir}/system/tailscaled.service"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "tailscaled.service"
SYSTEMD_AUTO_ENABLE = "enable"