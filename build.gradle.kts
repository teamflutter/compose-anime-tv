plugins {
  id("com.android.application").apply(false)
  id("com.android.library").apply(false)
  kotlin("android").apply(false)
  id("com.diffplug.spotless").version(Versions.spotless)
}

allprojects {
  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = Versions.Java.jvmTarget
      allWarningsAsErrors = false
      freeCompilerArgs = listOf(
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xallow-unstable-dependencies"
      )
    }
  }

  apply(plugin = "com.diffplug.spotless")
  spotless {
    kotlin {
      target("**/*.kt")
      targetExclude(
        "$buildDir/**/*.kt",
        "bin/**/*.kt",
        "buildSrc/**/*.kt",
        "**/*Response.kt",
      )
      ktlint(Versions.ktlint).userData(
        mapOf(
          "indent_size" to "2",
          "continuation_indent_size" to "2"
        )
      )
      // licenseHeaderFile(rootProject.file("spotless/license"))
    }
    kotlinGradle {
      target("*.gradle.kts")
      targetExclude(
        "feature/focuskit/**"
      )
      ktlint(Versions.ktlint).userData(
        mapOf(
          "indent_size" to "2",
          "continuation_indent_size" to "2"
        )
      )
    }
  }

  // 剔除livedata，使用flow代替
  configurations.all {
    exclude(group = "androidx.lifecycle", module = "lifecycle-livedata")
  }
}
