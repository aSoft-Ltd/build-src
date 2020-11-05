plugins {
    `kotlin-dsl`
    id("maven-publish")
}

repositories {
    mavenLocal()
    google()
    jcenter()
    mavenCentral()
}

object versions {
    const val kotlin = "1.3.72"
    const val gradle = "6.1.1"
    const val android_build_tools = "4.0.0"
}

group = "tz.co.asoft"
version = "1.0.0"

defaultTasks("jar")

val sourcesJar by tasks.creating(org.gradle.jvm.tasks.Jar::class) {
    archiveClassifier.value("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.creating(org.gradle.jvm.tasks.Jar::class) {
    archiveClassifier.value("javadoc")
}

artifacts {
    archives(sourcesJar)
    archives(javadocJar)
}

dependencies {
    api("com.android.tools.build:gradle:${versions.android_build_tools}")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
    api("org.jetbrains.kotlin:kotlin-serialization:${versions.kotlin}")
}

tasks.getByName<Wrapper>("wrapper") {
    gradleVersion = versions.gradle
    distributionType = Wrapper.DistributionType.ALL
}