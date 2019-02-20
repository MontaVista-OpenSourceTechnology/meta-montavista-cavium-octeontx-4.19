# Cavium ThunderX defaults

require conf/machine/include/soc-family.inc
require conf/machine/include/tune-thunderx.inc
include conf/machine/include/arm/arch-multilib-ilp32.inc

SOC_FAMILY = "cavium-octeontx"

# used by sysvinit_2

SERIAL_CONSOLE ?= "115200 ttyAMA0"

