plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("space-maven")
}

group = "tz.co.asoft"
version = versions.asoft

repositories {
    publicRepos()
}

kotlin {
    js {
        useCommonJs()
    }
}