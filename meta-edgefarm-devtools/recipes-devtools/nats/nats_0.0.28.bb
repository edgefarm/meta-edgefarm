SUMMARY = "Install NATS cli"
DESCRIPTION = "A command line utility to interact with and manage NATS."
HOMEPAGE = "https://github.com/nats-io/natscli"

# Binary URLs:
#   https://github.com/nats-io/natscli/releases/download/v0.0.28/nats-0.0.28-linux-arm64.zip
#   https://github.com/nats-io/natscli/releases/download/v0.0.28/nats-0.0.28-linux-arm6.zip
#   https://github.com/nats-io/natscli/releases/download/v0.0.28/nats-0.0.28-linux-amd64.zip
#
COMPATIBLE_HOST = "(x86_64|aarch64|arm).*-linux"

DIRECTORY_x86-64 = "${BPN}-${PV}-linux-amd64"
DIRECTORY_arm = "${BPN}-${PV}-linux-arm6"
DIRECTORY_aarch64 = "${BPN}-${PV}-linux-arm64"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/${DIRECTORY}/LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI_x86-64 = "https://github.com/nats-io/natscli/releases/download/v${PV}/${DIRECTORY}.zip;name=amd64"
SRC_URI_arm = "https://github.com/nats-io/natscli/releases/download/v${PV}/${DIRECTORY}.zip;name=arm"
SRC_URI_aarch64 = "https://github.com/nats-io/natscli/releases/download/v${PV}/${DIRECTORY}.zip;name=arm64"

SRC_URI[amd64.md5sum] = "02a26629bc2397a98f18ae0ec90b5e10"
SRC_URI[amd64.sha256sum] = "0501cabd5da181a263bd2ac4d8bb35bddd022dd08fa2e2d537eae711997811a3"
SRC_URI[arm64.md5sum] = "d04c4b63f0e36c23c287e7f6ea10d2cf"
SRC_URI[arm64.sha256sum] = "7255ef21f5f93507bc883fa755c721e81f8a3d5a4a288a20f76792de3e3b0bc4"
SRC_URI[arm.md5sum] = "7c0167d5d139d39e4b31386ee21bd310"
SRC_URI[arm.sha256sum] = "87ea2ef29755ffdc0068ca53705d93fb4064cc0e7c9d03850449d199ba7f2cee"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/${DIRECTORY}/nats ${D}${bindir}/nats
}

FILES_${PN} += "${bindir}/nats"

INSANE_SKIP_${PN}_append = " already-stripped"
