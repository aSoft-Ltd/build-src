package tz.co.asoft

import com.android.build.gradle.LibraryExtension
import configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension
import publicRepos
import targetJava

open class LibraryPlugin : Plugin<Project> {
    fun Project.setupAndroidLib(dir: String) {
        configure<LibraryExtension> {
            configureAndroid(dir)
        }
        targetJava("1.8")
    }

    fun Project.setupJvmLib() {
        targetJava("1.8")
    }

    fun Project.setupJsLib() = configure<KotlinJsProjectExtension> {
        js(IR) {
            browser {}
        }
    }

    override fun apply(project: Project) = with(project) {
        when {
            plugins.hasPlugin("com.android.library") -> when {
                plugins.hasPlugin("org.jetbrains.kotlin.multiplatform") -> {
                    setupAndroidLib("src/androidMain")
                }
                else -> setupAndroidLib("src/main")
            }

            plugins.hasPlugin("org.jetbrains.kotlin.multiplatform") -> {

            }

            plugins.hasPlugin("org.jetbrains.kotlin.jvm") -> {
                setupJvmLib()
            }

            plugins.hasPlugin("org.jetbrains.kotlin.js") -> {
                setupJsLib()
            }
        }

        plugins.apply("maven-publish")

        repositories { publicRepos() }
    }
}