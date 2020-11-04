import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("plugin.serialization")
    id("android-target")
    kotlin("android")
    id("space-maven")
}

group = "tz.co.asoft"
version = versions.asoft

repositories {
    publicRepos()
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions.jvmTarget = "1.8"
}