require edgefarm-image.bb

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "EdgeFarm-Devtools-Image"

IMAGE_FEATURES += "debug-tweaks"

IMAGE_INSTALL += "\
                   tailscale \
                   persistent-tailscale \
                   jq \
                   bind-utils \
                   "
