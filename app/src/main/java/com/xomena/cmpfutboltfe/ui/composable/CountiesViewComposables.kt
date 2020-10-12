package com.xomena.cmpfutboltfe.ui.composable

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.xomena.cmpfutboltfe.R

enum class CountiesViewsTabs(val titleResId: Int) {
    Counties(R.string.tabCounties),
    Search(R.string.tabSearch),
    Map(R.string.tabMap)
}

@Composable
fun CountiesViewsTabsCompose() {
    var selectedTab: CountiesViewsTabs = CountiesViewsTabs.Counties
    val tabsList = CountiesViewsTabs.values().asList()
    TabRow(
        selectedTabIndex = selectedTab.ordinal,
        modifier = Modifier.background(Color.Blue)
    ) {
        tabsList.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedTab.ordinal,
                onClick = {
                    selectedTab = CountiesViewsTabs.values()[index]
                },
                text = {
                    Text(
                        text = stringResource(category.titleResId),
                        style = MaterialTheme.typography.body2
                    )
                }
            )
        }
    }
    when (selectedTab) {
        CountiesViewsTabs.Counties -> countiesListCompose()
        CountiesViewsTabs.Search  -> searchPitchesCompose()
        CountiesViewsTabs.Map -> mapPitchesCompose()
    }
}

@Composable
fun countiesListCompose() {
    Text(text = "Counties list")
}

@Composable
fun searchPitchesCompose() {
    Text(text = "Search pitch")
}

@Composable
fun mapPitchesCompose() {
    Text(text = "Map view")
}

