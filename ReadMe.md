# build-src

This library comes packed with ready to use default gradle-plugins and configurations for Kotlin Multiplatform projects

## Gradle Plugins

### mpp-lib
Adds the `kotlin("multiplatform")` plugin and `maven-central` to help you write and publish your libs
#### Usage
```kotlin
plugins {
  id("tz.co.asoft.gradle-plugin.lib-multiplatform")
}
```

### [android,jvm,js]-only-libs
If you wan't to create a platform specific platform library, these will help you.
All of these plugins already applied the `maven publish`. So you can focus on your code

#### Usage
```kotlin
plugins {
  id("tz.co.asoft.gradle-plugin.android-only-lib") // if you need to publish for android only
  id("tz.co.asoft.gradle-plugin.jvm-only-lib") // if you need to publish for jvm only
  id("tz.co.asoft.gradle-plugin.js-only-lib") // if you need to publish for js only
}
```

### android-lib
If you need to target android libs in your `mpp-lib`. You should add `id("android-lib")` below the `mpp-lib` plugin.
Note: Do not confuse this with the `android-only-lib`

## Useful Methods
A bunch of methods are also available

### `fun RepositoryHandler.publicRepos()`

Adds the repositories below
```kotlin
    mavenLocal()
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    mavenCentral()
```

### `fun Project.configurePublishing()`
This one helps in adding default configurations for targets that do not have default publishing configs.
You mostly won't need to use this as it is applied in all places required.
