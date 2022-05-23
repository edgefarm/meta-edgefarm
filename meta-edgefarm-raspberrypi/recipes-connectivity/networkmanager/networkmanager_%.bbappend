PACKAGECONFIG_remove = "ifupdown dnsmasq"

RDEPENDS_${PN} += "bash"
RPROVIDES_${PN} = "network-configuration"
