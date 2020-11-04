import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension

plugins {
    kotlin("js")
    id("maven-publish")
}

group = "tz.co.asoft"

repositories {
    publicRepos()
}

configure<KotlinJsProjectExtension> {
    target {
        useCommonJs()
    }
}

configurePublishing()