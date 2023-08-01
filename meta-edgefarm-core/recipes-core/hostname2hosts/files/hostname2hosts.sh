#!/bin/bash

# This script is used to update the /etc/hosts: The hostname found in /etc/hostname shall be added to the localhost IPv4 address (127.0.0.1).
hostname_file="/etc/hostname"
ip_address="127.0.0.1"
hosts_file="/etc/hosts"
hostname_marker="# hostname marker - DO NOT EDIT THIS LINE!"


# Check if the hostname file exists
if [ ! -f "$hostname_file" ]; then
    echo "Hostname file not found."
    exit 0
fi

# Read the hostname from the file
hostname=$(cat $hostname_file)

# test if hostname is empty
if [ -z "$hostname" ]; then
    echo "Hostname file is empty."
    exit 0
fi

# Check if the hostname marker is already in the /etc/hosts file
hostname_marker_line=$(grep "${hostname_marker}" ${hosts_file})

if [ -z "$hostname_marker_line" ]; then
    # no entry with the hostname marker found. Create a new one.
    echo "Adding hostname marker to ${hosts_file} file"
    # add the hostname marker to the /etc/hosts file
    echo -e "${ip_address}       ${hostname}       ${hostname_marker}\n$(cat ${hosts_file})" > ${hosts_file}
else
    # check if hostname is part of the hostname_marker_line string
    prev_hostname=$(echo ${hostname_marker_line} | awk '{print $2}')
    if [ -z "$prev_hostname" ] ||  [ "$prev_hostname" != "$hostname" ]; then
        echo "Replacing hostname in ${hosts_file} file"
        # replace the hostname in the hostname marker line
        sed -i "s/${hostname_marker_line}/${ip_address}       ${hostname}       ${hostname_marker}/g" ${hosts_file}
    fi
fi
