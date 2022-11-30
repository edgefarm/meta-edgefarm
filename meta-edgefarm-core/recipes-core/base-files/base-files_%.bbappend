do_install_append () {
        # Add alias for docker ps to profile
        if [ "${@bb.utils.contains('DISTRO_FEATURES', 'virtualization', 'virtualization', '', d)}" = "virtualization" ]; then
                echo "alias dps=\"docker ps --format 'table {{.Image}}\\\t{{.Status}}\\\t{{.RunningFor}}\\\t{{.Names}}'\"" >> ${D}${sysconfdir}/profile
        fi
        # Link to make kubeedge state persistent
        ln -s /data/edgecore ${D}/${localstatedir}/lib/kubeedge
}
