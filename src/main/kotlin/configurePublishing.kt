import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Zip
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.delegateClosureOf
import org.gradle.kotlin.dsl.get

fun Project.configurePublishing() = configure<PublishingExtension> {
    publications {
        create("kotlin", delegateClosureOf<MavenPublication> {
            from(components["kotlin"])
            groupId = project.group.toString()
            artifactId = project.name
            version = versions.asoft
            val artifact = listOf("JsSourcesJar", "kotlinSourcesJar").mapNotNull {
                tasks.findByName(it) as? Zip
            }.first()
            artifact(artifact)
        })
    }
}