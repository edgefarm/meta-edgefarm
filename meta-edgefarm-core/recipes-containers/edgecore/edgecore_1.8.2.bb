SUMMARY = "Install kubeedge"
DESCRIPTION = "Extend native containerized application orchestration and device management to hosts at the Edge"
HOMEPAGE = "https://github.com/kubeedge/kubeedge"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

COMPATIBLE_HOST = "(x86_64|aarch64|arm).*-linux"

DIRECTORY_x86-64 = "kubeedge-v${PV}-linux-amd64"
DIRECTORY_arm = "kubeedge-v${PV}-linux-arm"
DIRECTORY_aarch64 = "kubeedge-v${PV}-linux-arm64"

SRC_URI_x86-64 = "https://github.com/kubeedge/kubeedge/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=amd64"
SRC_URI_arm = "https://github.com/kubeedge/kubeedge/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm"
SRC_URI_aarch64 = "https://github.com/kubeedge/kubeedge/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm64"

SRC_URI[amd64.md5sum] = "16dd6262c8157eeae04b086adcccd104"
SRC_URI[amd64.sha256sum] = "07e1ffe3d82c16522bdb56fb06b5df4e7a97142e2bb439f45380cce9f250e467"
SRC_URI[arm.md5sum] = "67bd2bdccfcadacba2a5d4aabf631f0e"
SRC_URI[arm.sha256sum] = "a6346d3bd4710b3fe458a5cc8a22a1e2260dbfa77b5d97a4169af82596c18302"
SRC_URI[arm64.md5sum] = "3eca3c2ca5356fc6978c38f2d2629957"
SRC_URI[arm64.sha256sum] = "3314d4429074fbdf2caa54bf7de0a4f3b3823a34feec14d9a95618844edaf6fe"

SRC_URI_append = " https://raw.githubusercontent.com/kubeedge/kubeedge/v${PV}/LICENSE;name=license"
SRC_URI[license.md5sum] = "86d3f3a95c324c9479bd8986968f4327"
SRC_URI[license.sha256sum] = "c71d239df91726fc519c6eb72d318ec65820627232b2f796219e87dcf35d0ab4"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " \
                   file://edgecore.service;name=service \
                   file://edgecore.yaml.TEMPLATE;name=config \
                   file://configure_edgecore.sh;name=script \
                   "
SRC_URI[service.md5sum] = "8396cbead9608589822cb2e3ee711923"
SRC_URI[service.sha256sum] = "c6008d6612e7aca28e467ada2dd2d802da9fb32a614784b8a7b728ee50dbba06"
SRC_URI[config.md5sum] = "a48f04a4e85f5ca02d991c891cc4fd94"
SRC_URI[config.sha256sum] = "07f518ad67e811a15ef3e1bb245d376ea6a941bb179be00d4b467f9329fa4ce1"
SRC_URI[script.md5sum] = "b88e8a7e4fd2e20cd945013681784e7e"
SRC_URI[script.sha256sum] = "e984d305525698dacbf1456fbb01d527827c1607b7762419069ba8f11f0a7da2"

inherit systemd

SYSTEMD_SERVICE_${PN} = "edgecore.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${systemd_system_unitdir}
    install -d ${D}${sysconfdir}/kubeedge/config
    install -d ${D}${sysconfdir}/kubeedge/certs/
    install -m 0755 ${WORKDIR}/${DIRECTORY}/edge/edgecore ${D}${bindir}/edgecore
    install -m 0755 ${WORKDIR}/configure_edgecore.sh ${D}${bindir}/configure_edgecore.sh
    install -m 0644 ${WORKDIR}/edgecore.service ${D}${systemd_system_unitdir}/edgecore.service
    install -m 0644 ${WORKDIR}/edgecore.yaml.TEMPLATE ${D}${sysconfdir}/kubeedge/config/edgecore.yaml.TEMPLATE

}

FILES_${PN} += "${bindir}/edgecore"
FILES_${PN} += "${bindir}/configure_edgecore.sh"
FILES_${PN} += "${systemd_system_unitdir}/edgecore.service"
FILES_${PN} += "${sysconfdir}/kubeedge/config/edgecore.yaml.TEMPLATE"
