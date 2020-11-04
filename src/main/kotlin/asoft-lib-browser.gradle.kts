plugins {
    kotlin("js")
    kotlin("plugin.serialization")
    id("space-maven")
}

group = "tz.co.asoft"
version = versions.asoft

repositories {
    publicRepos()
}

kotlin {
    target {
        useCommonJs()
    }
}

publishing.publications {
    create<MavenPublication>("kotlin-js") {
        from(components["kotlin"])
        groupId = project.group.toString()
        artifactId = project.name
        version = versions.asoft
        artifact(tasks.getByName<Zip>("JsSourcesJar"))
    }
}