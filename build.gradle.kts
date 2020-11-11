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
            id = "tz.co.asoft.gradle-plugin.maker"
            description = "A gradle plugin that easises in creating gradle plugins"
            implementationClass = "tz.co.asoft.GradlePluginMaker"
        }

        val libOnlyJs by creating {
            id = "tz.co.asoft.gradle-plugin.lib-only-js"
            description = "Kotlin/JS Only Library"
            implementationClass = "tz.co.asoft.LibOnlyJsPlugin"
        }

        val libOnlyJvm by creating {
            id = "tz.co.asoft.gradle-plugin.lib-only-jvm"
            description = "Kotlin/JVM Only Library"
            implementationClass = "tz.co.asoft.LibOnlyJvmPlugin"
        }

        val libOnlyAndroid by creating {
            id = "tz.co.asoft.gradle-plugin.lib-only-android"
            description = "Kotlin/Android Only Library"
            implementationClass = "tz.co.asoft.LibOnlyAndroidPlugin"
        }

        val libAndroid by creating {
            id = "tz.co.asoft.gradle-plugin.lib-android"
            description = "Library target for multiplatform"
            implementationClass = "tz.co.asoft.LibAndroidPlugin"
        }

        val libMutiplatform by creating {
            id = "tz.co.asoft.gradle-plugin.lib-multiplatform"
            description = "For Multiplatform Libs"
            implementationClass = "tz.co.asoft.LibMultiplatformPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/aSoft-Ltd/build-src"
    vcsUrl = website
    description = "Simple Plugins to Ease Library Development"
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