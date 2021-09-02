pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
  }
}

rootProject.name = "compose-anime-tv"
include(":app", ":core")
include(":compiler:assistedFactory")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
