package com.easy.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EasyApplication : Application() {
  override fun onCreate() {
    super.onCreate()
  }
}
