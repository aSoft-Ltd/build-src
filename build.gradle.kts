plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "0.12.0"
}

repositories {
    mavenLocal()
    google()
    jcenter()
    mavenCentral()
}

object versions {
    const val kotlin = "1.4.10"
    const val gradle = "6.7"
    const val asoft = "0.0.4"
    const val android_build_tools = "4.1.0"
}

gradlePlugin {
    plugins {
        val gradlePluginMaker by creating {
            id = "tz.co.asoft.plugin.maker"
            description = "A gradle plugin that easises in creating gradle plugins"
            implementationClass = "tz.co.asoft.GradlePluginMaker"
        }

        val library by creating {
            id = "tz.co.asoft.library"
            description = "A kotlin library plugin"
            implementationClass = "tz.co.asoft.LibraryPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/aSoft-Ltd/build-src"
    vcsUrl = website
    description = "Simple Plugins to Ease Library Development"

    plugins {
        val gradlePluginMaker by getting {
            displayName = "Gradle Plugin Maker"
            tags = listOf("kotlin")
        }

        val library by getting {
            displayName = "A Kotlin Library Plugin"
            tags = listOf("kotlin", "library")
        }
    }
}

group = "tz.co.asoft"
version = versions.asoft

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