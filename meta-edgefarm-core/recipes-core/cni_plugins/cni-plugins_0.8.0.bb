SUMMARY = "Install CNI Plugins"
HOMEPAGE = "https://github.com/containernetworking/plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# COMPATIBLE_HOST = "(amd64|aarch64|arm).*-linux"
COMPATIBLE_HOST = "(aarch64).*-linux"

# SRC_URI_x86-64 = "https://github.com/containernetworking/plugins/releases/download/v${PV}/${BPN}-linux-amd64-v${PV}.tgz;name=amd64"
# SRC_URI_arm = "https://github.com/containernetworking/plugins/releases/download/v${PV}/${BPN}-linux-arm-v${PV}.tgz;name=arm"
SRC_URI_aarch64 = "https://github.com/edgefarm/edgefarm/releases/download/cni-0.8.0/cni-plugins-linux-arm64-v0.8.0.tgz;name=arm64"

# SRC_URI[amd64.sha256sum] = "754a71ed60a4bd08726c3af705a7d55ee3df03122b12e389fdba4bea35d7dd7e"
# SRC_URI[arm.sha256sum] = "86c4c866a01a8073ad14f6feec74de1fd63669786850c7be47521433f9570902"
SRC_URI[arm64.sha256sum] = "8ea2cc9cbf5bc514b8bf9168fe11ac6c8a414685db3aca186467d40823ecb4ac"


do_install() {
    install -d ${D}/opt/cni/bin
    install -m 0755 ${WORKDIR}/._bandwidth ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._bridge ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._dhcp ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._firewall ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._flannel ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._host-device ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._host-local ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._ipvlan ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._loopback ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._macvlan ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._portmap ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._ptp ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._sbr ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._static ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._tuning ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/._vlan ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/bandwidth ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/bridge ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/dhcp ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/firewall ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/flannel ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/host-device ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/host-local ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/ipvlan ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/loopback ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/macvlan ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/portmap ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/ptp ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/sbr ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/static ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/tuning ${D}/opt/cni/bin/
    install -m 0755 ${WORKDIR}/vlan ${D}/opt/cni/bin/
}

FILES_${PN} += "/opt/cni/bin/._bandwidth"
FILES_${PN} += "/opt/cni/bin/._bridge"
FILES_${PN} += "/opt/cni/bin/._dhcp"
FILES_${PN} += "/opt/cni/bin/._firewall"
FILES_${PN} += "/opt/cni/bin/._flannel"
FILES_${PN} += "/opt/cni/bin/._host-device"
FILES_${PN} += "/opt/cni/bin/._host-local"
FILES_${PN} += "/opt/cni/bin/._ipvlan"
FILES_${PN} += "/opt/cni/bin/._loopback"
FILES_${PN} += "/opt/cni/bin/._macvlan"
FILES_${PN} += "/opt/cni/bin/._portmap"
FILES_${PN} += "/opt/cni/bin/._ptp"
FILES_${PN} += "/opt/cni/bin/._sbr"
FILES_${PN} += "/opt/cni/bin/._static"
FILES_${PN} += "/opt/cni/bin/._tuning"
FILES_${PN} += "/opt/cni/bin/._vlan"
FILES_${PN} += "/opt/cni/bin/bandwidth"
FILES_${PN} += "/opt/cni/bin/bridge"
FILES_${PN} += "/opt/cni/bin/dhcp"
FILES_${PN} += "/opt/cni/bin/firewall"
FILES_${PN} += "/opt/cni/bin/flannel"
FILES_${PN} += "/opt/cni/bin/host-device"
FILES_${PN} += "/opt/cni/bin/host-local"
FILES_${PN} += "/opt/cni/bin/ipvlan"
FILES_${PN} += "/opt/cni/bin/loopback"
FILES_${PN} += "/opt/cni/bin/macvlan"
FILES_${PN} += "/opt/cni/bin/portmap"
FILES_${PN} += "/opt/cni/bin/ptp"
FILES_${PN} += "/opt/cni/bin/sbr"
FILES_${PN} += "/opt/cni/bin/static"
FILES_${PN} += "/opt/cni/bin/tuning"
FILES_${PN} += "/opt/cni/bin/vlan"

