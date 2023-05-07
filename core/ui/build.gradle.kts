plugins {
  id("easy.android.library")
  id("easy.android.library.compose")
  id("easy.android.library.jacoco")
}

android {
  namespace = ModuleNameSpaces.UI
}

dependencies {
  api(project(":core:designsystem"))
  implementation(project(":core:model"))
  implementation(project(":core:domain"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.coil.kt)
  implementation(libs.coil.kt.compose)
  implementation(libs.kotlinx.datetime)

  implementation(libs.zxing)
  implementation(libs.androidx.camera.camera2)
  implementation(libs.androidx.camera.lifecycle)
  implementation(libs.androidx.camera.view)

  implementation(libs.androidx.navigation.compose)

  api(libs.androidx.compose.runtime.livedata)
  api(libs.androidx.metrics)
  api(libs.androidx.tracing.ktx)
}
