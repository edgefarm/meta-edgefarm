require ../../../meta-ci4rail-bsp/recipes-images/images/ci4rail-base-image.bb

SUMMARY = "Ci4Rail Embedded Linux Image for Ci4Rail Edgefarm"
DESCRIPTION = "Image for the Ci4Rail EdgeFarm Services prepared for the Ci4Rail supported Hardware"

LICENSE = "MIT"

#Prefix to the resulting deployable tarball name
export IMAGE_BASENAME = "EdgeFarm-Image"

IMAGE_INSTALL += "\
                  edgecore\
                  persistent-edgecore"
