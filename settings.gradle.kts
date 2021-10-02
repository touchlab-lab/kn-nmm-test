
rootProject.name = "kotlin_native_memory_issues"

pluginManagement {
  repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
  }
  val KOTLIN_VERSION: String by settings
  plugins {
    kotlin("multiplatform") version KOTLIN_VERSION
  }
}
