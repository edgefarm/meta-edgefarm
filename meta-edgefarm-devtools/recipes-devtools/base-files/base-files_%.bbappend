do_install_append () {
        # Link to make tailscale state persistent
        ln -s /data/tailscale ${D}/${localstatedir}/lib/tailscale
}
