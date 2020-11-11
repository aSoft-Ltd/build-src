import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.universalLib() {
    android {
        targetJava("1.8")
        publishLibraryVariants("release")
    }
    jvm { targetJava("1.8") }
    js(IR) {
        browser {}
    }
}