SUMMARY = "Install k8s"
DESCRIPTION = "Installation of kubeadm, kubelet and kubectl together with service and config files"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

COMPATIBLE_HOST = "(amd64|aarch64|arm).*-linux"

SRC_URI_x86-64 = "https://dl.k8s.io/release/v${PV}/bin/linux/amd64/kubeadm;name=kubeadm_amd64 \
                  https://dl.k8s.io/release/v${PV}/bin/linux/amd64/kubelet;name=kubelet_amd64 \
                  https://dl.k8s.io/release/v${PV}/bin/linux/amd64/kubectl;name=kubectl_amd64"
SRC_URI_arm = "https://dl.k8s.io/release/v${PV}/bin/linux/arm/kubeadm;name=kubeadm_arm \
               https://dl.k8s.io/release/v${PV}/bin/linux/arm/kubelet;name=kubelet_arm \
               https://dl.k8s.io/release/v${PV}/bin/linux/arm/kubectl;name=kubectl_arm"
SRC_URI_aarch64 = "https://dl.k8s.io/release/v${PV}/bin/linux/arm64/kubeadm;name=kubeadm_arm64 \
                   https://dl.k8s.io/release/v${PV}/bin/linux/arm64/kubelet;name=kubelet_arm64 \
                    https://dl.k8s.io/release/v${PV}/bin/linux/arm64/kubectl;name=kubectl_arm64"

#SRC_URI[kubeadm_amd64.sha256sum] = "6d3f732fe1eabd91c98ff0ee66c6c8b4fcbdee9e99c2c8606f0fa5ff57b4ea65"
#SRC_URI[kubeadm_arm.sha256sum] = "28de42463de52cb9f81f4c0d74db996cc58eebb1eaa89fb88115283ad9af077c"
SRC_URI[kubeadm_arm64.sha256sum] = "90a1e3c98fabaf4c6dfca5cef4d33a3212a9d624ec2e6249e7ac747f10dcad67"

#SRC_URI[kubelet_amd64.sha256sum] = "588dde06e2515601380787cb5fcb07ae3d3403130e1a5556b013b7b7fb4ab230"
#SRC_URI[kubelet_arm.sha256sum] = "fc22039729512786e39621ee5d8c2d71e6f65f388b2a69e07be799639925a808"
SRC_URI[kubelet_arm64.sha256sum] = "dd76d33b2a72693b9a88614cef72367dd737d74433fd9e65129684086719d632"

#SRC_URI[kubectl_amd64.sha256sum] = "f09f7338b5a677f17a9443796c648d2b80feaec9d6a094ab79a77c8a01fde941"
#SRC_URI[kubectl_arm.sha256sum] = "9a7186543be0b952261537d7754b8a381b8b0d0a8a0cc3629fd5280b50a8be77"
SRC_URI[kubectl_arm64.sha256sum] = "8fc2f8d5c80a6bf60be06f8cf28679a05ce565ce0bc81e70aaac38e0f7da6259"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"


do_install() {
    install -d ${D}${bindir}
    install -d ${D}${sysconfdir}/systemd/system/kubelet.service.d
    install -m 0755 ${WORKDIR}/kubeadm ${D}${bindir}
    install -m 0755 ${WORKDIR}/kubelet ${D}${bindir}
    install -m 0755 ${WORKDIR}/kubectl ${D}${bindir}
}

FILES_${PN} = "${bindir}/kubeadm \
               ${bindir}/kubelet \
               ${bindir}/kubectl \
               ${sysconfdir}/systemd/system/kubelet.service.d"