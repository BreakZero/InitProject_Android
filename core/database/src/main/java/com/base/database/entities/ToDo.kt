package com.base.database.entities

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class ToDo(
  val isFinished: Boolean,
  val accentColor: Long,
  val name: String,
  val level: Int,
  val date: LocalDate,
  val alarm: LocalTime,
  val timeDuring: LocalTime
)
