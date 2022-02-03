#!/bin/sh
#
# Execute this script for provisioning edgefarm services.
#
# To be called with arguments: <cloudcore address> <node name>
#
# Generate and apply edgecore config file.
# Do not forget to provide /etc/kubeedge/certs/rootCa.pem and /etc/kubeedge/certs/node.pem for a successful connection.

if [ "$#" -ne 2 ] ; then
    echo "Usage: ${0} <cloudcore address> <node name>"
    exit 1
fi

template=/etc/kubeedge/config/edgecore.yaml.TEMPLATE

export CLOUDCORE_ADDRESS=${1}
export  NODE_NAME=${2}

eval "echo \"$(cat "${template}")\"" > /etc/kubeedge/config/edgecore.yaml

systemctl restart edgecore
