plugins {
    kotlin("js")
    kotlin("plugin.serialization")
    id("maven-publish")
}

group = "tz.co.asoft"

repositories {
    publicRepos()
}

kotlin {
    target {
        useCommonJs()
    }
}

configurePublishing()