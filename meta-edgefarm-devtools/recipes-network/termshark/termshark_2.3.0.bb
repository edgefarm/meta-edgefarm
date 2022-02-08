SUMMARY = "Install termshark"
DESCRIPTION = "A terminal user-interface for tshark, inspired by Wireshark."

HOMEPAGE = "https://github.com/gcla/termshark"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE-${BPN}-${PV};md5=078881bfb263eb2e92e6c338186db5d7"

COMPATIBLE_HOST = "(x86_64|aarch64|arm).*-linux"

# Archive URLs:
#   https://github.com/gcla/termshark/releases/download/v2.3.0/termshark_2.3.0_linux_arm64.tar.gz
#   https://github.com/gcla/termshark/releases/download/v2.3.0/termshark_2.3.0_linux_armv6.tar.gz
#   https://github.com/gcla/termshark/releases/download/v2.3.0/termshark_2.3.0_linux_x64.tar.gz
#

DIRECTORY_x86-64 = "${BPN}_${PV}_linux_x64"
DIRECTORY_arm = "${BPN}_${PV}_linux_armv6"
DIRECTORY_aarch64 = "${BPN}_${PV}_linux_arm64"

SRC_URI_x86-64 = "https://github.com/gcla/termshark/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=amd64"
SRC_URI_arm = "https://github.com/gcla/termshark/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm"
SRC_URI_aarch64 = "https://github.com/gcla/termshark/releases/download/v${PV}/${DIRECTORY}.tar.gz;name=arm64"

SRC_URI[amd64.md5sum] = "9203210dea201a7c3339149931098e6e"
SRC_URI[amd64.sha256sum] = "a8dd2ea848100757b83bd58178cc593d002ae3eeae4491715118d22744985ecd"
SRC_URI[arm.md5sum] = "22d4179f3c285f9f9b6d6742a2de0a5f"
SRC_URI[arm.sha256sum] = "be183090c117f9bd45e896c229faa28a42f95dda386d7da13689657854bc8c15"
SRC_URI[arm64.md5sum] = "711e31e93e8f8595700150642f789b65"
SRC_URI[arm64.sha256sum] = "3031073aebf22b29aaaf3bad7adfabe499cf70468763aa1505be40955acf63e2"

SRC_URI_append = " https://raw.githubusercontent.com/gcla/termshark/v${PV}/LICENSE;name=license;downloadfilename=LICENSE-${BPN}-${PV}"
SRC_URI[license.md5sum] = "078881bfb263eb2e92e6c338186db5d7"
SRC_URI[license.sha256sum] = "e96db14e21841660831e2964551da502438c1d1942cb70ab362792e8b977bbf5"

do_install() {
    install -d ${D}/${bindir}
    install ${WORKDIR}/${DIRECTORY}/termshark ${D}/${bindir}/termshark
}

FILES_${PN} += "${bindir}/termshark"

INSANE_SKIP_${PN}_append = " already-stripped"
