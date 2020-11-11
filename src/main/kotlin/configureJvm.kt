import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun KotlinJvmTarget.targetJava(version: String = "1.8") = compilations.all {
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
}

fun Project.targetJava(version: String = "1.8") = tasks.withType(KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = version
        useIR = true
    }
}