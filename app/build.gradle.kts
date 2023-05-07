import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("easy.android.application")
  id("easy.android.application.compose")
  id("easy.android.application.jacoco")
  id("easy.android.hilt")
  id("jacoco")
}

android {
  namespace = "com.easy.base"

  defaultConfig {
    applicationId = "com.easy.base"
    versionCode = 1
    versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

    testInstrumentationRunner = "com.base.testing.BaseTestRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  packagingOptions {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
  }
}

dependencies {
  implementation(project(":core:common"))
  implementation(project(":core:designsystem"))
  implementation(project(":core:ui"))
  implementation(project(":core:data"))
  implementation(project(":core:model"))

  implementation(project(":features:home"))

  androidTestImplementation(project(":core:testing"))
  androidTestImplementation(libs.androidx.navigation.testing)
  androidTestImplementation(kotlin("test"))
  debugImplementation(libs.androidx.compose.ui.testManifest)

}

tasks.register("installGitHook", Copy::class.java) {
  from(File(rootProject.rootDir, "scripts/pre-commit-macos"))
  into(File(rootProject.rootDir, ".git/hooks"))
  rename("pre-commit-macos", "pre-commit")
  fileMode = 509
}

tasks.getByPath(":app:preBuild").dependsOn("installGitHook")

tasks.withType<KotlinCompile> {
  compilerOptions {
//    freeCompilerArgs.set(listOf("-Xuse-k2"))
    jvmTarget.set(JvmTarget.JVM_17)
//    languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
  }
}