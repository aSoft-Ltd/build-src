import io.codearte.gradle.nexus.NexusStagingExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.aSoftLibrary(
    version: String,
    description: String,
    url: String = "https://github.com/aSoft-Ltd/$name",
    scmConnection: String = "scm:git:git://github.com/aSoft-Ltd/$name.git",
    scmDeveloperConnection: String = "scm:git:https://github.com/aSoft-Ltd/$name.git",
    licenseName: String = "MIT License",
    licenseUrl: String = "https://github.com/aSoft-Ltd/$name/blob/master/LICENSE",
    developerId: String = "andylamax",
    developerName: String = "Anderson Lameck"
) {
    this.group = "tz.co.asoft"
    this.version = version
    this.description = description
    this.url = url
    this.scmConnection = scmConnection
    this.scmDeveloperConnection = scmDeveloperConnection
    this.licenseName = licenseName
    this.licenseUrl = licenseUrl
    this.developerId = developerId
    this.developerName = developerName

    val nexusUsername = System.getenv("ASOFT_NEXUS_USERNAME") ?: "null"
    val nexusPassword = System.getenv("ASOFT_NEXUS_PASSWORD") ?: "null"
    val pgpPrivateKey = System.getenv("ASOFT_MAVEN_PGP_PRIVATE_KEY") ?: ""
    val pgpPassword = System.getenv("ASOFT_MAVEN_PGP_PASSWORD") ?: ""


    configure<NexusStagingExtension> {
        username = nexusUsername
        password = nexusPassword
    }

    configureSigning(privateKey = pgpPrivateKey, password = pgpPassword)

    configurePublishing {
        repositories {
            configureMavenCentralRepos(username = nexusUsername, password = nexusPassword)
        }
    }
}