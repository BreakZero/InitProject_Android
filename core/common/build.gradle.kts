

plugins {
  id("easy.android.library")
  id("easy.android.library.jacoco")
  id("easy.android.hilt")
}

android {
  namespace = ModuleNameSpaces.Common
}

dependencies {
  testImplementation(project(":core:testing"))

  api(libs.timber)

  implementation(libs.androidx.security.crypto)
  implementation(libs.androidx.dataStore.core)
  implementation(libs.androidx.dataStore.preferences)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.datetime)
}
