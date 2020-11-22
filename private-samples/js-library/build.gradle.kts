plugins {
    kotlin("js") version "1.4.10"
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging") version "0.22.0"
    signing
}

val lib_version = "0.0.1"
group = "tz.co.asoft"
version = lib_version

aSoftLibrary(
    version = lib_version,
    description = "A test lib"
)