

plugins {
  id("easy.android.library")
  id("easy.android.library.jacoco")
  id("easy.android.hilt")
  id("kotlinx-serialization")
}

android {
  namespace = ModuleNameSpaces.Network
}

dependencies {
  implementation(project(":core:common"))
  implementation(project(":core:model"))

  testImplementation(project(":core:testing"))

  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.kotlinx.datetime)

  implementation(libs.ktor.core)
  implementation(libs.ktor.client.okhttp)
  implementation(libs.ktor.negotiation)
  implementation(libs.ktor.logging)
  implementation(libs.ktor.json)

  debugImplementation(libs.chucker.debug)
  releaseImplementation(libs.chucker.release)
}

