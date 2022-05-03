#!/bin/sh
#
# Execute this script for provisioning edgefarm services.
#
# To be called with arguments: <cloudcore address> <node name>
#
# Generate and apply edgecore config file.
# Set host name to <node name>.
# Do not forget to provide /etc/kubeedge/certs/rootCa.pem and /etc/kubeedge/certs/node.pem for a successful connection.

if [ "$#" -ne 2 ] ; then
    echo "Usage: ${0} <cloudcore address> <node name>"
    exit 1
fi

export NODE_NAME=${2}

# Update hostname
oldHostname=$(cat /etc/hostname)
hostnamectl set-hostname $NODE_NAME
sed -i "s/$oldHostname/$NODE_NAME/g" /etc/hosts

# Generate edgecore config
template=/etc/kubeedge/config/edgecore.yaml.TEMPLATE

case "${1}" in
*.nip.io*) export CLOUDCORE_ADDRESS=$(echo ${1} | sed -e 's/\./\-/g' -e 's/-nip-io/.nip.io/g') ;;
*        ) export CLOUDCORE_ADDRESS=${1} ;;
esac

eval "echo \"$(cat "${template}")\"" > /etc/kubeedge/config/edgecore.yaml

systemctl restart edgecore
