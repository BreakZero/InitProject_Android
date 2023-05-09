package com.easy.sample.home

data class FeatureInfo(
  val id: Long,
  val icon: Int,
  val name: String,
  val route: String
)

val features = listOf(
  FeatureInfo(
    id = 0L,
    icon = R.drawable.baseline_today_24,
    name = "ToDo List",
    route = ""
  )
)
