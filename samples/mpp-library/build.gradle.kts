plugins {
    id("mpp-lib")
    id("android-lib")
}

group = "tz.co.asoft"
version = "0.0.1"


kotlin {
    android {
        targetJava("1.8")
        publishLibraryVariants("release")
    }

    jvm { targetJava("1.8") }

    js { useCommonJs() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
    }
}