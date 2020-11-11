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

        val libOnlyJs by creating {
            id = "tz.co.asoft.lib.only.js"
            description = "Kotlin/JS Only Library"
            implementationClass = "tz.co.asoft.LibOnlyJsPlugin"
        }

        val libOnlyJvm by creating {
            id = "tz.co.asoft.lib.only.jvm"
            description = "Kotlin/JVM Only Library"
            implementationClass = "tz.co.asoft.LibOnlyJvmPlugin"
        }

        val libOnlyAndroid by creating {
            id = "tz.co.asoft.lib.only.android"
            description = "Kotlin/Android Only Library"
            implementationClass = "tz.co.asoft.LibOnlyAndroidPlugin"
        }

        val libAndroid by creating {
            id = "tz.co.asoft.lib.android"
            description = "Library target for multiplatform"
            implementationClass = "tz.co.asoft.LibAndroidPlugin"
        }

        val libMultiplatform by creating {
            id = "tz.co.asoft.lib.multiplatform"
            description = "For Multiplatform Libs"
            implementationClass = "tz.co.asoft.LibMultiplatformPlugin"
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

        val libOnlyJs by getting {
            displayName = "Js Only Library"
            tags = listOf("kotlin", "js")
        }

        val libOnlyJvm by getting {
            displayName = "JVM Only Library"
            tags = listOf("kotlin", "jvm")
        }

        val libOnlyAndroid by getting {
            displayName = "Android Only Library"
            tags = listOf("kotlin", "android")
        }

        val libAndroid by getting {
            displayName = "Android Multiplatform Target"
            tags = listOf("kotlin", "multiplatform", "android")
        }

        val libMultiplatform by getting {
            displayName = "Kotlin Multiplatform Plugin"
            tags = listOf("kotlin", "multiplatform")
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