SUMMARY = "cri-tools"
DESCRIPTION = "CLI and validation tools for Kubelet Container Runtime Interface (CRI) ."
HOMEPAGE = "https://github.com/kubernetes-sigs/cri-tools"

LICENSE = "Apache-2.0"
# TODO solve LICENSE Problem
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

COMPATIBLE_HOST = "(amd64|aarch64|arm).*-linux"

SRC_URI_x86-64 = "https://github.com/kubernetes-sigs/cri-tools/releases/download/v${PV}/${BPN}-v${PV}-linux-amd64.tar.gz;name=amd64"
SRC_URI_arm = "https://github.com/kubernetes-sigs/cri-tools/releases/download/v${PV}/${BPN}-v${PV}-linux-arm.tar.gz;name=arm"
SRC_URI_aarch64 = "https://github.com/kubernetes-sigs/cri-tools/releases/download/v${PV}/${BPN}-v${PV}-linux-arm64.tar.gz;name=arm64"

SRC_URI[amd64.sha256sum] = "d335d6e16c309fbc3ff1a29a7e49bb253b5c9b4b030990bf7c6b48687f985cee"
SRC_URI[amd64.sha512sum] = "aa622325bf05520939f9e020d7a28ab48ac23e2fae6f47d5a4e52174c88c1ebc31b464853e4fd65bd8f5331f330a6ca96fd370d247d3eeaed042da4ee2d1219a"
SRC_URI[arm.sha256sum] = "0b6983195cc62bfc98de1f3fc2ee297a7274fb79ccabf413b8a20765f12d522a"
SRC_URI[arm.sha512sum] = "c5f580e59a7802d40e3db0badad1ca5efdd241e772dd0a536685bd0cc80277a54c59a3a077d62626b260719a5680307fbe9ca6b48b4c2c55e1dd2351e5caf0cb"
SRC_URI[arm64.sha256sum] = "9317560069ded8e7bf8b9488fdb110d9e62f0fbc0e33ed09fe972768b47752bd"
SRC_URI[arm64.sha512sum] = "db062e43351a63347871e7094115be2ae3853afcd346d47f7b51141da8c3202c2df58d2e17359322f632abcb37474fd7fdb3b7aadbc5cfd5cf6d3bad040b6251"


do_install() {
    install -d ${D}/usr/local/bin
    install -m 0755 ${WORKDIR}/crictl ${D}/usr/local/bin
}

FILES_${PN} += "/usr/local/bin/crictl"
