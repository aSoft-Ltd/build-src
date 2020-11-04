plugins {
    id("maven-publish")
}

publishing {
    repositories {
        maven("https://maven.jetbrains.space/asofttz/kotlin") {
            credentials {
                username = System.getenv("SPACE_USERNAME")
                password = System.getenv("SPACE_PASSWORD")
            }
        }
    }
}