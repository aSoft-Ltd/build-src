package tz.co.asoft

import applyKotlin
import configurePublishing
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension
import publicRepos

open class LibOnlyJsPlugin : Plugin<Project> {
    private fun Project.setupPlugins() = with(plugins) {
        applyKotlin("js")
        apply("maven-publish")
    }

    override fun apply(project: Project) = with(project) {
        setupPlugins()
        repositories { publicRepos() }
        configure<KotlinJsProjectExtension> {
            target {
                useCommonJs()
            }
        }
        configurePublishing()
    }
}