import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Zip
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get

fun Project.configurePublishing() = configure<PublishingExtension> {
    publications {
        afterEvaluate {
            create("kotlin", MavenPublication::class.java) {
                try {
                    from(components["kotlin"])
                } catch (_: Exception) {
                    from(components["release"])
                }
                groupId = project.group.toString()
                artifactId = project.name
                version = project.version.toString()
                listOf("JsSourcesJar", "kotlinSourcesJar", "androidSourcesJar").mapNotNull {
                    tasks.findByName(it) as? Zip
                }.firstOrNull()?.let {
                    println(it.name)
                    artifact(it)
                }
            }
        }
    }
}