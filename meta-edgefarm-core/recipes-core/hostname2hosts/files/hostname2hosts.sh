#!/bin/bash

# This script is used to update the /etc/hosts: The hostname found in /etc/hostname shall be added to the localhost IPv4 address (127.0.0.1).
#hostname_file="/etc/hostname"
hostname_file="hostname"
ip_address="127.0.0.1"
#hosts_file="/etc/hosts"
hosts_file="hosts"


# Check if the hostname file exists
if [ ! -f "$hostname_file" ]; then
    echo "Hostname file not found."
    exit 1
fi

# Read the hostname from the file
hostname=$(cat $hostname_file)

# test if hostname is empty
if [ -z "$hostname" ]; then
    echo "Hostname file is empty."
    exit 1
fi

# Check if the IP address is already in the /etc/hosts file
local_host_entry=$(grep ${ip_address} ${hosts_file})

if [ -z "$local_host_entry" ]; then
    # no entry with the IP address found. Create a new one.
    echo "Adding hostname to ${hosts_file} file: ${ip_address} ${hostname}"
    # add the hostname to the /etc/hosts file
    echo "${ip_address} ${hostname}" >> ${hosts_file}
else
    # check if hostname is part of the local_host_entry string
    if ! [[ "$local_host_entry" == *"$hostname"* ]]; then
        local_host_entry=$(echo "${local_host_entry} $hostname")
        echo "Adding hostname to ${hosts_file} file: ${local_host_entry}"
        # replace the line in /etc/hosts file
        sed -i "s/.*$ip_address.*/$local_host_entry/g" ${hosts_file}
    fi
fi
