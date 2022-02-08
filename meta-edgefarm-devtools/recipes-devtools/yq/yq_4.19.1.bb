SUMMARY = "Install yq"
DESCRIPTION = "Lightweight and portable command-line YAML, JSON and XML processor."

HOMEPAGE = "https://github.com/mikefarah/yq"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=e40a0dcd62f8269b9bff37fe9aa7dcc2"

COMPATIBLE_HOST = "(i.86|x86_64|aarch64|arm).*-linux"

# Binary URLs:
#   https://github.com/mikefarah/yq/releases/download/v4.19.1/yq_linux_amd64
#   https://github.com/mikefarah/yq/releases/download/v4.19.1/yq_linux_arm
#   https://github.com/mikefarah/yq/releases/download/v4.19.1/yq_linux_arm64
#

BINARY_x86-64 = "${BPN}_linux_amd64"
BINARY_arm = "${BPN}_linux_arm"
BINARY_aarch64 = "${BPN}_linux_arm64"

SRC_URI_x86-64 = "https://github.com/mikefarah/yq/releases/download/v${PV}/${BINARY};name=amd64"
SRC_URI_arm = "https://github.com/mikefarah/yq/releases/download/v${PV}/${BINARY};name=arm"
SRC_URI_aarch64 = "https://github.com/mikefarah/yq/releases/download/v${PV}/${BINARY};name=arm64"

SRC_URI[amd64.md5sum] = "403ced7d7c86f186d0084f573673b7c5"
SRC_URI[amd64.sha256sum] = "6b8f8cfc0aaa180121057b63c8d5c60b1567eb34ca38ac2e8e7e2d3b77bbba9f"
SRC_URI[arm.md5sum] = "62c0a952804358153facaea5b416b714"
SRC_URI[arm.sha256sum] = "6101d9377c4684a2227189329c1d6e34c6b54d78b113e1d626a71edcf2322a55"
SRC_URI[arm64.md5sum] = "d930e7f0ef2d61e715cdde265766182f"
SRC_URI[arm64.sha256sum] = "970c6e099f9d3d59d5d67e23f741243ee1c43e49daf2ca27cc7d96838e84556f"

SRC_URI_append = " https://raw.githubusercontent.com/mikefarah/yq/v${PV}/LICENSE;name=license"
SRC_URI[license.md5sum] = "e40a0dcd62f8269b9bff37fe9aa7dcc2"
SRC_URI[license.sha256sum] = "697db34dabb21562fe84487a2ccd031fbd45382b89c2cbdec8ef31682c486040"

do_install() {
    install -d ${D}/${bindir}
    install ${WORKDIR}/${BINARY} ${D}/${bindir}/yq
}

FILES_${PN} += "${bindir}/yq"
