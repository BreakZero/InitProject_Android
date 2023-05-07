plugins {
  id("easy.android.library")
  id("easy.android.library.jacoco")
  id("easy.android.hilt")
  id("kotlinx-serialization")
}

android {
  namespace = ModuleNameSpaces.Data
}

dependencies {
  implementation(project(":core:common"))
  implementation(project(":core:model"))
  implementation(project(":core:database"))
  implementation(project(":core:network"))
  implementation(project(":core:datastore"))

  testImplementation(project(":core:testing"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.compose.paging)

  implementation(libs.kotlinx.datetime)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.serialization.json)
}

