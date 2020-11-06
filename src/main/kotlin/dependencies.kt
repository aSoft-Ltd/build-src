import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun PluginDependenciesSpec.asoftPlugin(name: String, version: String? = null) = when (version) {
    null -> id("tz.co.asoft.gradle-plugin.$name")
    else -> id("tz.co.asoft.gradle-plugin.$name") version (version)
}

fun KotlinDependencyHandler.asoft(module: String, version: String) = "tz.co.asoft:$module:$version"