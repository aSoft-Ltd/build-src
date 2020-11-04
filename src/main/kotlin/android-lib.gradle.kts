import com.android.build.gradle.LibraryExtension

plugins {
    id("com.android.library")
}

group = "tz.co.asoft"

repositories {
    publicRepos()
}

targetJava("1.8")

configure<LibraryExtension> {
    configureAndroid()
}

