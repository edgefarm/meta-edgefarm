# Add alias for docker ps to profile
do_install_append () {
        if [ "${@bb.utils.contains('DISTRO_FEATURES', 'virtualization',
'virtualization', '', d)}" = "virtualization" ]; then
                echo "alias dps=\"docker ps --format 'table {{.Image}}\t{{.Status}}\t{{.RunningFor}}\t{{.Names}}'\"\n" >>
${D}${sysconfdir}/profile
        fi
}
