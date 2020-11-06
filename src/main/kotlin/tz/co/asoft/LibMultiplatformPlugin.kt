package tz.co.asoft

import applyKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.repositories
import publicRepos

open class LibMultiplatformPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        with(plugins) {
            applyKotlin("multiplatform")
            apply("maven-publish")
        }
        repositories { publicRepos() }
    }
}