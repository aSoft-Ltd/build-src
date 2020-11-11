# build-src

This library comes packed with ready to use default gradle-plugins and configurations for Kotlin Multiplatform projects

## Gradle Plugins

### lib-multiplatform
Adds the `kotlin("multiplatform")` plugin and `maven-central` to help you write and publish your libs
#### Usage
```kotlin
plugins {
  id("tz.co.asoft.lib.multiplatform")
}
```

### lib-only-[android|jvm|js]
If you wan't to create a platform specific platform library, these will help you.
All of these plugins already applied the `maven publish`. So you can focus on your code

#### Usage
```kotlin
plugins {
  id("tz.co.asoft.lib.only.android") // if you need to publish for android only
  id("tz.co.asoft.lib.only.jvm") // if you need to publish for jvm only
  id("tz.co.asoft.lib.only.js") // if you need to publish for js only
}
```

### lib-android
If you need to target android libs in your `lib.multiplatform`. You should add `id("tz.co.asoft.lib.android")` below the `lib.multiplatform` plugin.
Note: Do not confuse this with the `lib.android.only`

## Useful Methods
A bunch of methods are also available

### `publicRepos()`
To use this method, just do `repositories { publicRepos() }`, Add it will add repositories below
```kotlin
    mavenLocal()
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    mavenCentral()
```

### `configurePublishing()`
This one helps in adding default configurations for targets that do not have default publishing configs.
You mostly won't need to use this as it is applied in all places required.
