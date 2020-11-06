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

/**
 * Plugin should be used with the multiplatform plugin
 */
open class LibAndroidPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        plugins.apply("com.android.library")
        repositories { publicRepos() }
        configure<LibraryExtension> {
            configureAndroid(dir = "src/androidMain")

            tasks.create("androidSourcesJar", Jar::class.java) {
                archiveClassifier.value("sources")
                from(sourceSets["main"].java.srcDirs)
            }
        }
        targetJava("1.8")
        configurePublishing()
    }
}