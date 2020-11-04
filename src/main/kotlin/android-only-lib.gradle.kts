import com.android.build.gradle.LibraryExtension

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

group = "tz.co.asoft"

repositories {
    publicRepos()
}

targetJava("1.8")

configure<LibraryExtension> {
    configureAndroid(dir = "src/main")
}

val androidSourcesJar by tasks.creating(org.gradle.jvm.tasks.Jar::class) {
    archiveClassifier.value("sources")
    from(android.sourceSets["main"].java.srcDirs)
}

configurePublishing()
