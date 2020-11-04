plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("android-target")
    id("space-maven")
}

group = "tz.co.asoft"
version = versions.asoft

repositories {
    publicRepos()
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release")
    }

    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    js {
        useCommonJs()
    }
}