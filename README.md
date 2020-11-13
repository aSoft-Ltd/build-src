# build-src

This library comes packed with ready to use default gradle-plugins and configurations for Kotlin Multiplatform projects
and authoring gradle plugins. Supports
- Kotlin Multiplatform libraries
- Kotlin Javascript libraries
- Kotlin Android libraries
- Kotlin JVM libraries

## Usage
Configuring a library becomes as easy as just, and all publications will be set
```kotlin
plugins {                                    
    kotlin("js") // [kotlin("jvm")|kotlin("multiplatform")|kotlin("android")|kotlin("js)]
    id("tz.co.asoft.library")
}
```
After that you can just run `./gradlew publish` and you are all set
