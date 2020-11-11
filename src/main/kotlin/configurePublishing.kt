import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.external.javadoc.StandardJavadocDocletOptions
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.withType
import java.io.File

fun Project.configurePublishing(config: PublishingExtension.() -> Unit) {
    val android = extensions.findByType(com.android.build.gradle.BaseExtension::class.java)

    /**
     * Javadoc
     */
    var javadocTask = tasks.findByName("javadoc") as Javadoc?
    var javadocJarTaskProvider: TaskProvider<Jar>? = null

    if (javadocTask == null && android != null) {
        // create the Android javadoc if needed
        javadocTask = tasks.create("javadoc", Javadoc::class.java) {
            source = android.sourceSets["main"].java.getSourceFiles()
            classpath += project.files(android.bootClasspath.joinToString(File.pathSeparator))

            (android as? com.android.build.gradle.LibraryExtension)?.libraryVariants?.configureEach {
                if (name != "release") {
                    return@configureEach
                }
                classpath += getCompileClasspath(null)
            }
        }
    }

    javadocJarTaskProvider = tasks.register("javadocJar", Jar::class.java) {
        archiveClassifier.set("javadoc")
        if (javadocTask != null) {
            dependsOn(javadocTask)
            from(javadocTask.destinationDir)
        }
    }

    val javaPluginConvention = project.convention.findPlugin(JavaPluginConvention::class.java)
    val sourcesJarTaskProvider = tasks.register("sourcesJar", Jar::class.java) {
        archiveClassifier.set("sources")
        when {
            javaPluginConvention != null && android == null -> {
                from(javaPluginConvention.sourceSets["main"].allSource)
            }
            android != null -> {
                from(android.sourceSets["main"].java.getSourceFiles())
            }
        }
    }

    tasks.withType(Javadoc::class.java) {
        // TODO: fix the javadoc warnings
        (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    }

    configure<PublishingExtension> {
        publications {
            when {
                plugins.hasPlugin("org.jetbrains.kotlin.multiplatform") -> {
                    withType<MavenPublication> {
                        // multiplatform doesn't add javadoc by default so add it here
                        artifact(javadocJarTaskProvider.get())
                        if (name == "kotlinMultiplatform") {
                            // sources are added for each platform but not for the common module
                            artifact(sourcesJarTaskProvider.get())
                        }
                    }
                }

                plugins.hasPlugin("java-gradle-plugin") -> {
                    // java-gradle-plugin doesn't add javadoc/sources by default so add it here
                    withType<MavenPublication> {
                        artifact(javadocJarTaskProvider.get())
                        artifact(sourcesJarTaskProvider.get())
                    }
                }

                else -> {
                    create("default", MavenPublication::class.java) {
                        val javaComponent = components.findByName("java")
                        if (javaComponent != null) {
                            from(javaComponent)
                        } else if (android != null) {
                            afterEvaluate {
                                from(components.findByName("release"))
                            }
                        }

                        artifact(javadocJarTaskProvider.get())
                        artifact(sourcesJarTaskProvider.get())

                        pom {
                            artifactId = null // findProperty("POM_ARTIFACT_ID") as String?
                        }
                    }
                }
            }

            withType<MavenPublication> {
                setDefaultPomFields(this)
            }
        }
        config()
        repositories {
            maven {
                name = "buildDir"
                url = uri("file://${rootProject.buildDir}/localMaven")
            }
        }
    }
}

fun Project.setDefaultPomFields(mavenPublication: MavenPublication) {
    mavenPublication.groupId = group.toString()
    mavenPublication.version = version.toString()

    mavenPublication.pom {
        name.set(this@setDefaultPomFields.name)
        (findProperty("POM_PACKAGING") as String?)?.let {
            // Do not overwrite packaging if set by the multiplatform plugin
            packaging = it
        }

        description.set(this@setDefaultPomFields.description)
        url.set(this@setDefaultPomFields.url)

        scm {
            url.set(this@setDefaultPomFields.scmUrl)
            connection.set(this@setDefaultPomFields.scmConnection)
            developerConnection.set(this@setDefaultPomFields.scmDeveloperConnection)
        }

        licenses {
            license {
                name.set(this@setDefaultPomFields.licenseName)
                url.set(this@setDefaultPomFields.licenseUrl)
            }
        }

        developers {
            developer {
                id.set(this@setDefaultPomFields.developerId)
                name.set(this@setDefaultPomFields.developerName)
            }
        }
    }
}