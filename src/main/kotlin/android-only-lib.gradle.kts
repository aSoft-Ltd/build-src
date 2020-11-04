import com.android.build.gradle.LibraryExtension

plugins {
    kotlin("plugin.serialization")
    kotlin("android")
    id("com.android.library")
}

group = "tz.co.asoft"

repositories {
    publicRepos()
}

targetJava("1.8")

configure<LibraryExtension> {
    configureAndroid()
}
configurePublishing()