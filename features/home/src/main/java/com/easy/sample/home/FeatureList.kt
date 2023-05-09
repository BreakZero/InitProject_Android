package com.easy.sample.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.base.designsystem.theme.DefaultTheme
import com.base.designsystem.theme.Spacings
import com.base.ui.UiEvent
import com.base.ui.extensions.clickableSingle

@Composable
fun FeatureListScreen(
  state: FeatureState,
  handleEvent: (UiEvent) -> Unit
) {
  Scaffold(
    modifier = Modifier
  ) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
      items(state.features, key = { it.id }) { feature ->
        FeatureItem(feature = feature, onItemClick = {
          handleEvent.invoke(UiEvent.Navigate(feature.route))
        })
      }
    }
  }
}

@Composable
private fun FeatureItem(
  feature: FeatureInfo,
  onItemClick: (FeatureInfo) -> Unit
) {
  Column(
    modifier = Modifier.clickableSingle {
      onItemClick(feature)
    },
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(painter = painterResource(id = feature.icon), contentDescription = null)
    Spacer(modifier = Modifier.height(MaterialTheme.Spacings.extraSmall))
    Text(text = feature.name)
  }
}

@Preview(showBackground = true)
@Composable
fun FeaturePreview() {
  DefaultTheme {
    FeatureItem(feature = features.first()) {
    }
  }
}
