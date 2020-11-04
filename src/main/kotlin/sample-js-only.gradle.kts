plugins {
    kotlin("js")
}

repositories {
    publicRepos()
}

kotlin {
    target {
        useCommonJs()
    }
}