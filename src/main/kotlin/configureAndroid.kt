import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget

fun BaseExtension.configureAndroid() {

    compileSdkVersion(28)

    buildFeatures.apply {
        buildConfig = false
    }

    defaultConfig {
        minSdkVersion(1)
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/resources")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            matchingFallbacks = listOf("release")
        }
    }

    lintOptions {
        isAbortOnError = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

fun KotlinAndroidTarget.targetJava(version: String = "1.8") = compilations.all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}