MV_KERNEL_BRANCH ?= "mvl-4.19/msd.cgx"
MV_KERNEL_TREE ?= "git://github.com/MontaVista-OpenSourceTechnology/linux-mvista-2.6.git;protocol=https"
MV_KERNELCACHE_BRANCH ?= "yocto-4.19"
MV_KERNELCACHE_TREE ?= "git://github.com/MontaVista-OpenSourceTechnology/yocto-kernel-cache;protocol=https"

require recipes-kernel/linux/linux-yocto.inc
NO_SOURCE_MIRROR="1"
BB_GENERATE_MIRROR_TARBALLS = "0"
SRCREV_machine ?= "${MV_KERNEL_BRANCH}"
SRCREV_meta ?= "${MV_KERNELCACHE_BRANCH}"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"


S = "${WORKDIR}/git"

LINUX_VERSION = "4.19"
KERNEL_VERSION_SANITY_SKIP="1"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI = "${MV_KERNEL_TREE};branch=${MV_KERNEL_BRANCH};name=machine \
           ${MV_KERNELCACHE_TREE};type=kmeta;name=meta;branch=${MV_KERNELCACHE_BRANCH};destsuffix=${KMETA}"
SRC_URI += "file://defconfig"

DEPENDS += "elfutils-native"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "0"
COMPATIBLE_MACHINE_cavium-octeontx = "cavium-octeontx"

# Signing applications require openssl-native
DEPENDS += "openssl-native"
EXTRA_OEMAKE += "HOSTCC='gcc -I${STAGING_INCDIR_NATIVE} -L${STAGING_DIR_NATIVE}/lib -Wl,-rpath,${STAGING_DIR_NATIVE}/lib'"
