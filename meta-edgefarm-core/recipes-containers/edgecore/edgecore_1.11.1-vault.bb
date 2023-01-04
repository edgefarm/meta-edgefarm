SUMMARY = "Install kubeedge edgecore"
DESCRIPTION = "Extend native containerized application orchestration and device management to hosts at the Edge"
HOMEPAGE = "https://github.com/edgefarm/kubeedge"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE-${BPN}-${PV};md5=86d3f3a95c324c9479bd8986968f4327"

COMPATIBLE_HOST = "(x86_64|aarch64).*-linux"

DIRECTORY_x86-64 = "kubedge-v${PV}-linux-amd64"
DIRECTORY_arm = "kubeedge-v${PV}-linux-arm"
DIRECTORY_aarch64 = "kubeedge-v${PV}-linux-arm64"

SRC_URI_x86-64 = "https://github.com/edgefarm/kubeedge/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=amd64"
SRC_URI_arm = "https://github.com/edgefarm/kubeedge/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm"
SRC_URI_aarch64 = "https://github.com/edgefarm/kubeedge/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm64"

# Use yocto-images script to update
# e.g.  scripts/github-release-checksums.sh https://github.com/edgefarm/kubeedge v1.11.1-vault "kubeedge*"
SRC_URI[amd64.md5sum] = "ddc37238b546ba25656a0ac3a645f3f6"
SRC_URI[amd64.sha256sum] = "25935c64cdf8544484cc72939bddf504a7bdc111d2fd682197039d788df150e4"
SRC_URI[arm64.md5sum] = "c54ab01c6a16d7066a03d10fee248b19"
SRC_URI[arm64.sha256sum] = "9a0215d340adcad7ebe6cc12e204057a296a5799b3079a536f400f920b209432"
SRC_URI[arm.md5sum] = "02faab0ff4e13f1a8a85d39bcdddc6da"
SRC_URI[arm.sha256sum] = "508d7145215f842b0f8b5deb7d90e5c00ba7ee7c688e7483e938f35b09d71065"

SRC_URI_append = " https://raw.githubusercontent.com/edgefarm/kubeedge/v${PV}/LICENSE;name=license;downloadfilename=LICENSE-${BPN}-${PV}"
SRC_URI[license.md5sum] = "86d3f3a95c324c9479bd8986968f4327"
SRC_URI[license.sha256sum] = "c71d239df91726fc519c6eb72d318ec65820627232b2f796219e87dcf35d0ab4"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " \
                   file://edgecore.service;name=service \
                   "
#SRC_URI[service.md5sum] = "8396cbead9608589822cb2e3ee711923"
#SRC_URI[service.sha256sum] = "c6008d6612e7aca28e467ada2dd2d802da9fb32a614784b8a7b728ee50dbba06"

inherit systemd

SYSTEMD_SERVICE_${PN} = "edgecore.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${systemd_system_unitdir}
    install -d ${D}${sysconfdir}/kubeedge/config
    install -d ${D}${sysconfdir}/kubeedge/certs/
    install -m 0755 ${WORKDIR}/${DIRECTORY}/edge/edgecore ${D}${bindir}/edgecore
    install -m 0644 ${WORKDIR}/edgecore.service ${D}${systemd_system_unitdir}/edgecore.service
}

FILES_${PN} += "${bindir}/edgecore"
FILES_${PN} += "${systemd_system_unitdir}/edgecore.service"
