#!/bin/bash

green='\033[0;32m'
red='\033[0;31m'
yellow='\033[0;33m'
yellowBold='\033[1;33m'
nc='\033[0m'

if [ "$EUID" -ne 0 ]; then
  echo "Please run this script as root."
  exit 1
fi

# Function to check reachability of API server
check_reachability() {
    code=$(curl -k -I -w "%{http_code}\n" -s -o /dev/null https://$1)
    # if code is 200 or 403, it means that the server is reachable
    if [ $code -eq 403 ]; then
        return 0
    fi
    if [ $code -eq 200 ]; then
        return 0
    fi
    return 1
}

is_valid_format() {
    input=$1

    # Use a regular expression to check the format
    if [[ $input =~ ^[a-zA-Z0-9.-]+:[0-9]+$ ]]; then
        return 0  # Valid format
    else
        return 1  # Invalid format
    fi
}


###### PARAMETERS HANDLING
NAME=${NAME:-$(hostname)}
TOKEN=${TOKEN:-}
ADDRESS=${ADDRESS:-}
JOIN=${JOIN:-true}
PRECHECKS_ONLY=${PRECHECKS_ONLY:-false}
NODE_IP=${NODE_IP:-}
CONVERT=${CONVERT:-true}

options=$(getopt -o "" -l "address:,token:,help" -- "$@")

if [ $? -ne 0 ]; then
 echo "Invalid arguments."
 exit 1
fi

eval set -- "$options"
Help() {
 echo "Usage: edgefarm-join --address ADDRESS --token TOKEN [--help]"
 echo
 echo "Options:"
 echo "--address ADDRESS   Set the API server address as host:port. This option is mandatory."
 echo "--token TOKEN       Set the edgefarm join token. This option is mandatory."
 echo "--help              Display this help message."
 echo
}

while [ $# -gt 0 ]; do
 case "$1" in
  --address) ADDRESS="$2"; shift;;
  --token) TOKEN="$2"; shift;;
  --help) Help; exit;;
  --version) VERSION="$2"; shift;;
  --) shift;;
 esac
 shift
done

if [ -z "$TOKEN" ]; then
 echo -e "${red}Token must be set.${nc}"
 exit 1
fi

if [ -z "$ADDRESS" ]; then
 echo -e "${red}Address must be set.${nc}"
 exit 1
fi

# Check if the address format is valid
if ! is_valid_format "$ADDRESS"; then
    echo "$ADDRESS is invalid. Maybe you forgot to add the port?"
    exit 1
fi

###### PRECHECKS
PRECHECK_ERRORS=0
echo "Running prechecks..."
INTERFACE="wt0"
if ip link show "$INTERFACE" | grep -qs "state UP"; then
   echo -e "  ${red}Interface $INTERFACE is not up or does not exist. Make sure that netbird is installed, up and running.${nc}"
   PRECHECK_ERRORS=$((PRECHECK_ERRORS+1))
else
    echo -e "  ${green}Interface $INTERFACE is up. Netbird seems to be up.${nc}"
fi

# check if /usr/local/etc/wt0.ip exists
if [ ! -f /usr/local/etc/wt0.ip ]; then
    echo -e "  ${red}File /usr/local/etc/wt0.ip does not exist. Something is wrong with udev rules${nc}"
    PRECHECK_ERRORS=$((PRECHECK_ERRORS+1))
else
    echo -e "  ${green}File /usr/local/etc/wt0.ip exists.${nc}"
fi


# Check reachability
if ! check_reachability "$ADDRESS"; then
    echo -e "  ${red}$ADDRESS is not reachable. Maybe you made a typo? Format must be 'host:port'${nc}"
    PRECHECK_ERRORS=$((PRECHECK_ERRORS+1))
else
    echo -e "  ${green}API server is reachable.${nc}"
fi


# Try to run a Docker command
docker info > /dev/null 2>&1
# Check the exit status
if [ $? -eq 0 ]; then
   echo -e "  ${green}Docker is running${nc}"
else
   echo -e "  ${red}Docker is not running${nc}"
   PRECHECK_ERRORS=$((PRECHECK_ERRORS+1))
fi


if [ $PRECHECK_ERRORS -gt 0 ]; then
    echo -e "${red}Prechecks failed.${nc}"
    exit 1
fi

echo "Prechecks passed."
###### INSTALLATION

if $PRECHECKS_ONLY == "true" ; then
    exit 0
fi

TMP=$(mktemp -d)


LABELSAPPEND=""
if $CONVERT ; then
  LABELSAPPEND="node.edgefarm.io/to-be-converted=true"
else
  LABELSAPPEND="node.edgefarm.io/to-be-converted=false"
fi

cp /etc/edgefarm/kubeadm-join.conf.template ${TMP}/kubeadm-join.conf
sed -i "s#LABELSAPPEND#$LABELSAPPEND#g" ${TMP}/kubeadm-join.conf
sed -i "s/ADDRESS/$ADDRESS/g" ${TMP}/kubeadm-join.conf
sed -i "s/NODE_NAME/$NAME/g" ${TMP}/kubeadm-join.conf
sed -i "s/BOOTSTRAP_TOKEN/$TOKEN/g" ${TMP}/kubeadm-join.conf

if [ -z "${NODE_IP}" ]; then
  NODE_IP=$(cat /usr/local/etc/wt0.ip)
fi
echo "    node-ip: ${NODE_IP}" >> ${TMP}/kubeadm-join.conf

cp ${TMP}/kubeadm-join.conf /etc/edgefarm/
rm -rf ${TMP}

###### JOIN CLUSTER
if $JOIN ; then
  echo -e "${green}Joining the cluster...${nc}"
  kubeadm join --config /etc/edgefarm/kubeadm-join.conf -v5
else
  echo -e "${green}Everything is set up. Run the following command to join the cluster:${nc}"
  echo kubeadm join --config /etc/edgefarm/kubeadm-join.conf -v5
fi
