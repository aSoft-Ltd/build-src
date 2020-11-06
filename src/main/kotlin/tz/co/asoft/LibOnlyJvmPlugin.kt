package tz.co.asoft

import applyKotlin
import configurePublishing
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories
import publicRepos
import targetJava

open class LibOnlyJvmPlugin : Plugin<Project> {
    private fun Project.applyPlugins() = with(plugins) {
        applyKotlin("jvm")
        apply("maven-publish")
    }

    override fun apply(project: Project) = with(project) {
        applyPlugins()
        repositories { publicRepos() }
        targetJava("1.8")
        configurePublishing()
    }
}