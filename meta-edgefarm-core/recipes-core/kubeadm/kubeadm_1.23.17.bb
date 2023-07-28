SUMMARY = "Install kubeadm"
DESCRIPTION = "TODO"

inherit systemd

LICENSE = "Apache-2.0"
# TODO solve LICENSE Problem
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

COMPATIBLE_HOST = "(amd64|aarch64|arm).*-linux"

SRC_URI_x86-64 = "https://dl.k8s.io/release/v${PV}/bin/linux/amd64/kubeadm;name=kubeadm_amd64 \
                  https://dl.k8s.io/release/v${PV}/bin/linux/amd64/kubelet;name=kubelet_amd64"
SRC_URI_arm = "https://dl.k8s.io/release/v${PV}/bin/linux/arm/kubeadm;name=kubeadm_arm \
               https://dl.k8s.io/release/v${PV}/bin/linux/arm/kubelet;name=kubelet_arm"
SRC_URI_aarch64 = "https://dl.k8s.io/release/v${PV}/bin/linux/arm64/kubeadm;name=kubeadm_arm64 \
                   https://dl.k8s.io/release/v${PV}/bin/linux/arm64/kubelet;name=kubelet_arm64"

SRC_URI[kubeadm_amd64.sha256sum] = "6d3f732fe1eabd91c98ff0ee66c6c8b4fcbdee9e99c2c8606f0fa5ff57b4ea65"
SRC_URI[kubeadm_arm.sha256sum] = "28de42463de52cb9f81f4c0d74db996cc58eebb1eaa89fb88115283ad9af077c"
SRC_URI[kubeadm_arm64.sha256sum] = "cf1bca6b464f30ea078a9cf4d902033fb80527b03c2f39409e19fb8b3886c75e"

SRC_URI[kubelet_amd64.sha256sum] = "588dde06e2515601380787cb5fcb07ae3d3403130e1a5556b013b7b7fb4ab230"
SRC_URI[kubelet_arm.sha256sum] = "fc22039729512786e39621ee5d8c2d71e6f65f388b2a69e07be799639925a808"
SRC_URI[kubelet_arm64.sha256sum] = "95e25ee4d2f34f628ba42685c6ae1ba6efdf86f2e76b5cceb0b48de4d66522a7"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

SYSTEMD_SERVICE_${PN} = "kubelet.service"
SYSTEMD_AUTO_ENABLE = "enable"

SRC_URI_x86-64 += "https://raw.githubusercontent.com/kubernetes/release/v0.15.1/cmd/kubepkg/templates/latest/deb/kubelet/lib/systemd/system/kubelet.service;name=service"
SRC_URI_arm += "https://raw.githubusercontent.com/kubernetes/release/v0.15.1/cmd/kubepkg/templates/latest/deb/kubelet/lib/systemd/system/kubelet.service;name=service"
SRC_URI_aarch64 += "https://raw.githubusercontent.com/kubernetes/release/v0.15.1/cmd/kubepkg/templates/latest/deb/kubelet/lib/systemd/system/kubelet.service;name=service"
SRC_URI_x86-64 += "https://raw.githubusercontent.com/kubernetes/release/v0.15.1/cmd/kubepkg/templates/latest/deb/kubeadm/10-kubeadm.conf;name=config"
SRC_URI_arm += "https://raw.githubusercontent.com/kubernetes/release/v0.15.1/cmd/kubepkg/templates/latest/deb/kubeadm/10-kubeadm.conf;name=config"
SRC_URI_aarch64 += "https://raw.githubusercontent.com/kubernetes/release/v0.15.1/cmd/kubepkg/templates/latest/deb/kubeadm/10-kubeadm.conf;name=config"

SRC_URI[service.sha256sum] = "48c8f5a6a13d4179fe156f4813167a590177cfdda73c1b9681fec7e3d159b709"
SRC_URI[config.sha256sum] = "3b6e4cb3ac34a6df6dd60ce45c9ebf035fc3ec160dc001b28afb7cca1c11ef06"

do_install() {
    install -d ${D}/usr/local/bin
    install -d ${D}${systemd_system_unitdir}
    install -d ${D}${sysconfdir}/systemd/system/kubelet.service.d
    install -m 0755 ${WORKDIR}/kubeadm ${D}/usr/local/bin
    install -m 0755 ${WORKDIR}/kubelet ${D}/usr/local/bin
    install -m 0644 ${WORKDIR}/kubelet.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/10-kubeadm.conf ${D}${sysconfdir}/systemd/system/kubelet.service.d
    sed -i "s:/usr/bin:/usr/local/bin:g" ${D}${systemd_system_unitdir}/kubelet.service
    sed -i "s:/usr/bin:/usr/local/bin:g" ${D}${sysconfdir}/systemd/system/kubelet.service.d/10-kubeadm.conf
}

FILES_${PN} = "/usr/local/bin/kubeadm \
                /usr/local/bin/kubelet \
                ${systemd_system_unitdir}/kubelet.service \
                ${sysconfdir}/systemd/system/kubelet.service.d/10-kubeadm.conf"
