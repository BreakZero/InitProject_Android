package com.base.ui

sealed interface UiEvent {
  data class Navigate(val route: String) : UiEvent
  data class Error(val message: UiText) : UiEvent
}
