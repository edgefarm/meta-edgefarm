SUMMARY = "Install kubeedge"
DESCRIPTION = "Extend native containerized application orchestration and device management to hosts at the Edge"
HOMEPAGE = "https://github.com/kubeedge/kubeedge"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE-${BPN}-${PV};md5=86d3f3a95c324c9479bd8986968f4327"

COMPATIBLE_HOST = "(x86_64|aarch64|arm).*-linux"

DIRECTORY_x86-64 = "kubeedge-v${PV}-linux-amd64"
DIRECTORY_arm = "kubeedge-v${PV}-linux-arm"
DIRECTORY_aarch64 = "kubeedge-v${PV}-linux-arm64"

SRC_URI_x86-64 = "https://github.com/kubeedge/kubeedge/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=amd64"
SRC_URI_arm = "https://github.com/kubeedge/kubeedge/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm"
SRC_URI_aarch64 = "https://github.com/kubeedge/kubeedge/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm64"

SRC_URI[amd64.md5sum] = "adcd1058402973369a7de987b0f15ee2"
SRC_URI[amd64.sha256sum] = "3288ca7e5560df2607d817f039fd3a049abc22a871a179d68835373b8619c53c"
SRC_URI[arm.md5sum] = "d4da60c72aa9a718f9af077fc4c8b031"
SRC_URI[arm.sha256sum] = "0a11c8b47d7614aeccc6be3a97d72bebc10f37f358ebe6f420aeb291d0e9650c"
SRC_URI[arm64.md5sum] = "585b6acdbe9ae53b7b0fa31403750cd3"
SRC_URI[arm64.sha256sum] = "63e469e9851d84df0ad3b475b832512ea7359905f15a751adebf17ec6ffa5eec"

SRC_URI_append = " https://raw.githubusercontent.com/kubeedge/kubeedge/v${PV}/LICENSE;name=license;downloadfilename=LICENSE-${BPN}-${PV}"
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
SRC_URI[config.md5sum] = "fd576e4974f7ff6682717f86241eab8f"
SRC_URI[config.sha256sum] = "dadd2fe19a25c06b508a73e06010deb521dbdb4946e46f5fb2ac3faa52924358"
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
