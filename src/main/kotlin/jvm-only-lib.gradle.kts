plugins {
    kotlin("jvm")
    id("maven-publish")
}

group = "tz.co.asoft"

repositories {
    publicRepos()
}

targetJava("1.8")
configurePublishing()