import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget

/**
 * Configures the android plugin
 */
fun BaseExtension.configureAndroid(dir: String = "src/androidMain") {

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
            java.srcDir("$dir/kotlin")
            manifest.srcFile("$dir/AndroidManifest.xml")
            res.srcDirs("$dir/resources")
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
            setMatchingFallbacks("release")
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