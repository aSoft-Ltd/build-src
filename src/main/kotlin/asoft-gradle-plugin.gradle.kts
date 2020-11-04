plugins {
    kotlin("jvm")
    id("java")
    id("java-gradle-plugin")
}

repositories {
    publicRepos()
}

group = "tz.co.asoft"

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
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
}
