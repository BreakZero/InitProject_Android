package com.base.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Light default theme color scheme
 */
@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
  primary = Purple40,
  onPrimary = Color.White,
  primaryContainer = Purple90,
  onPrimaryContainer = Purple10,
  secondary = Orange40,
  onSecondary = Color.White,
  secondaryContainer = Orange90,
  onSecondaryContainer = Orange10,
  tertiary = Blue40,
  onTertiary = Color.White,
  tertiaryContainer = Blue90,
  onTertiaryContainer = Blue10,
  error = Red40,
  onError = Color.White,
  errorContainer = Red90,
  onErrorContainer = Red10,
  background = DarkPurpleGray99,
  onBackground = DarkPurpleGray10,
  surface = DarkPurpleGray99,
  onSurface = DarkPurpleGray10,
  surfaceVariant = PurpleGray90,
  onSurfaceVariant = PurpleGray30,
  outline = PurpleGray50
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
  primary = Purple80,
  onPrimary = Purple20,
  primaryContainer = Purple30,
  onPrimaryContainer = Purple90,
  secondary = Orange80,
  onSecondary = Orange20,
  secondaryContainer = Orange30,
  onSecondaryContainer = Orange90,
  tertiary = Blue80,
  onTertiary = Blue20,
  tertiaryContainer = Blue30,
  onTertiaryContainer = Blue90,
  error = Red80,
  onError = Red20,
  errorContainer = Red30,
  onErrorContainer = Red90,
  background = DarkPurpleGray10,
  onBackground = DarkPurpleGray90,
  surface = DarkPurpleGray10,
  onSurface = DarkPurpleGray90,
  surfaceVariant = PurpleGray30,
  onSurfaceVariant = PurpleGray80,
  outline = PurpleGray60
)

/**
 * Light Android theme color scheme
 */
@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
  primary = Green40,
  onPrimary = Color.White,
  primaryContainer = Green90,
  onPrimaryContainer = Green10,
  secondary = DarkGreen40,
  onSecondary = Color.White,
  secondaryContainer = DarkGreen90,
  onSecondaryContainer = DarkGreen10,
  tertiary = Teal40,
  onTertiary = Color.White,
  tertiaryContainer = Teal90,
  onTertiaryContainer = Teal10,
  error = Red40,
  onError = Color.White,
  errorContainer = Red90,
  onErrorContainer = Red10,
  background = DarkGreenGray99,
  onBackground = DarkGreenGray10,
  surface = DarkGreenGray99,
  onSurface = DarkGreenGray10,
  surfaceVariant = GreenGray90,
  onSurfaceVariant = GreenGray30,
  outline = GreenGray50
)

/**
 * Dark Android theme color scheme
 */
@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
  primary = Green80,
  onPrimary = Green20,
  primaryContainer = Green30,
  onPrimaryContainer = Green90,
  secondary = DarkGreen80,
  onSecondary = DarkGreen20,
  secondaryContainer = DarkGreen30,
  onSecondaryContainer = DarkGreen90,
  tertiary = Teal80,
  onTertiary = Teal20,
  tertiaryContainer = Teal30,
  onTertiaryContainer = Teal90,
  error = Red80,
  onError = Red20,
  errorContainer = Red30,
  onErrorContainer = Red90,
  background = DarkGreenGray10,
  onBackground = DarkGreenGray90,
  surface = DarkGreenGray10,
  onSurface = DarkGreenGray90,
  surfaceVariant = GreenGray30,
  onSurfaceVariant = GreenGray80,
  outline = GreenGray60
)

/**
 * Light default gradient colors
 */
val LightDefaultGradientColors = GradientColors(
  primary = Purple95,
  secondary = Orange95,
  tertiary = Blue95,
  neutral = DarkPurpleGray95
)

/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = DarkGreenGray95)

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)

/**
 * Now in Android theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme. If this is `false`, then dynamic theming will be used when supported.
 */
@Composable
fun DefaultTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  androidTheme: Boolean = false,
  content: @Composable () -> Unit
) = DefaultTheme(
  darkTheme = darkTheme,
  androidTheme = androidTheme,
  disableDynamicTheming = false,
  content = content
)

/**
 * Now in Android theme. This is an internal only version, to allow disabling dynamic theming
 * in tests.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme.
 * @param disableDynamicTheming If `true`, disables the use of dynamic theming, even when it is
 *        supported. This parameter has no effect if [androidTheme] is `true`.
 */
@Composable
internal fun DefaultTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  androidTheme: Boolean = false,
  disableDynamicTheming: Boolean,
  content: @Composable () -> Unit
) {
  val colorScheme = if (androidTheme) {
    if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
  } else if (!disableDynamicTheming && supportsDynamicTheming()) {
    val context = LocalContext.current
    if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
  } else {
    if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
  }

  val defaultGradientColors = GradientColors()
  val gradientColors = if (androidTheme || (!disableDynamicTheming && supportsDynamicTheming())) {
    defaultGradientColors
  } else {
    if (darkTheme) defaultGradientColors else LightDefaultGradientColors
  }

  val defaultBackgroundTheme = BackgroundTheme(
    color = colorScheme.surface,
    tonalElevation = 2.dp
  )
  val backgroundTheme = if (androidTheme) {
    if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
  } else {
    defaultBackgroundTheme
  }

  CompositionLocalProvider(
    LocalGradientColors provides gradientColors,
    LocalBackgroundTheme provides backgroundTheme,
    LocalSpacing provides Dimensions()
  ) {
    MaterialTheme(
      colorScheme = colorScheme,
      typography = DefaultTypography,
      content = content
    )
  }
}

@Immutable
data class Dimensions(
  val default: Dp = 0.dp,
  val extraSmall: Dp = 4.dp,
  val small: Dp = 8.dp,
  val medium: Dp = 16.dp,
  val large: Dp = 32.dp,
  val extraLarge: Dp = 64.dp,

  val spacing12: Dp = 12.dp,
  val spacing24: Dp = 24.dp,
  val spacing48: Dp = 48.dp,
  val spacing56: Dp = 56.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }

val MaterialTheme.Spacings: Dimensions
  @Composable
  @ReadOnlyComposable
  get() = LocalSpacing.current

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
private fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
