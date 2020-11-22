package tz.co.asoft

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.repositories
import publicRepos
import versions

open class GradlePluginMaker : Plugin<Project> {
    private fun Project.applyPlugins() = with(plugins) {
        apply("org.jetbrains.kotlin.jvm")
        apply("java-gradle-plugin")
        apply("org.gradle.signing")
    }

    override fun apply(project: Project) = with(project) {
        applyPlugins()
        repositories { publicRepos() }
        defaultTasks("Jar")

        val sourcesJar = tasks.create("sourcesJar", Jar::class.java) {
            archiveClassifier.value("sources")
//            from(sourceSets["main"].allSource)
        }

        val javadocJar = tasks.create("javadocJar", Jar::class.java) {
            archiveClassifier.value("javadoc")
        }

        artifacts {
//            archives(sourcesJar)
//            archives(javadocJar)
        }

        dependencies {
            "api"("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
        }
    }
}