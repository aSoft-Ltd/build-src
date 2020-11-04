import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("plugin.serialization")
    kotlin("jvm")
    id("space-maven")
}

group = "tz.co.asoft"
version = versions.asoft

repositories {
    publicRepos()
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions.jvmTarget = "1.8"
}

publishing.publications {
    create<MavenPublication>("kotlin-jvm") {
        from(components["kotlin"])
        groupId = project.group.toString()
        artifactId = project.name
        version = versions.asoft
        artifact(tasks.getByName<Zip>("kotlinSourcesJar"))
    }
}