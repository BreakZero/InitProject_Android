package com.easy.sample.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor() : ViewModel() {
  private val _featureState = MutableStateFlow(FeatureState())
  val featureState = _featureState.asStateFlow()

  init {
    _featureState.update {
      FeatureState(features)
    }
  }
}

data class FeatureState(
  val features: List<FeatureInfo> = emptyList()
)
