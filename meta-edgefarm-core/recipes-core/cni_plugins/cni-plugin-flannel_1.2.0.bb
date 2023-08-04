SUMMARY = "Install CNI Plugin Flannel"
HOMEPAGE = "https://github.com/flannel-io/cni-plugin"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

COMPATIBLE_HOST = "(amd64|aarch64|arm).*-linux"

SRC_URI_x86-64 = "https://github.com/flannel-io/cni-plugin/releases/download/v${PV}/${BPN}-linux-amd64-v${PV}.tgz;name=amd64"
SRC_URI_arm = "https://github.com/flannel-io/cni-plugin/releases/download/v${PV}/${BPN}-linux-arm-v${PV}.tgz;name=arm"
SRC_URI_aarch64 = "https://github.com/flannel-io/cni-plugin/releases/download/v${PV}/${BPN}-linux-arm64-v${PV}.tgz;name=arm64"


SRC_URI[amd64.sha256sum] = "f24fb5b3002b228361c5b45bb56b58353a53d2ed6fc2701a01f90b2152363602"
SRC_URI[arm.sha256sum] = "3aae96f27731e84ac0c1db44c21b3256c420b95757f45ff1f89d94f3e9aa8522"
SRC_URI[arm64.sha256sum] = "a90e43bfc6fa729446b271b128bdee8c87853cea3215c4923b220443b5cf8184"

do_install() {
    install -d ${D}/opt/cni/bin
    install -m 0755 ${WORKDIR}/flannel* ${D}/opt/cni/bin/flannel
}

FILES_${PN} += "/opt/cni/bin/flannel"
