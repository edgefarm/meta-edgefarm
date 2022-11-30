MACHINE_NAME ?= "${MACHINE}"
GIT_VERSION := "${@d.getVar('BB_ORIGENV',False).getVar('IMAGE_GIT_VERSION', False) or 'NoVersion'}"
NAME_SUFFIX := "${@d.getVar('BB_ORIGENV',False).getVar('IMAGE_NAME_SUFFIX', False) or ''}"
IMAGE_NAME = "${MACHINE_NAME}_${IMAGE_BASENAME}_${GIT_VERSION}${NAME_SUFFIX}"
