package tz.co.asoft

import applyKotlin
import com.android.build.gradle.LibraryExtension
import configureAndroid
import configurePublishing
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.repositories
import publicRepos
import targetJava

open class LibOnlyAndroidPlugin : Plugin<Project> {
    private fun Project.applyPlugins() = with(plugins) {
        apply("com.android.library")
        applyKotlin("android")
        apply("maven-publish")
    }

    override fun apply(project: Project) = with(project) {
        applyPlugins()
        repositories { publicRepos() }
        configure<LibraryExtension> {
            configureAndroid(dir = "src/main")

            project.tasks.create("androidSourcesJar", Jar::class.java) {
                archiveClassifier.value("sources")
                from(sourceSets["main"].java.srcDirs)
            }
        }
        targetJava("1.8")
        configurePublishing()
    }
}