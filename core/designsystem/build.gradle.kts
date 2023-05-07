plugins {
  id("easy.android.library")
  id("easy.android.library.compose")
  id("easy.android.library.jacoco")
}

android {
  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  namespace = ModuleNameSpaces.DesignSystem
}

dependencies {
  implementation(libs.androidx.core.ktx)

  api(libs.accompanist.navigation.animation)

  api(platform(libs.androidx.compose.bom))
  api(libs.androidx.compose.foundation)
  api(libs.androidx.compose.foundation.layout)
  api(libs.androidx.compose.material.iconsExtended)
  api(libs.androidx.compose.material3)
  debugApi(libs.androidx.compose.ui.tooling)
  api(libs.androidx.compose.ui.tooling.preview)
  api(libs.androidx.compose.ui.util)
  api(libs.androidx.compose.runtime)

  androidTestImplementation(project(":core:testing"))
}
