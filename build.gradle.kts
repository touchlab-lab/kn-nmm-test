plugins {
    kotlin("multiplatform")
}

group = "me.kgalligan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    macosX64("native")

    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
    }
}
