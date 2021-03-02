package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.DemoDataProvider
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.viewmodel.NavViewModel

@Composable
fun HomeScreen(viewModel: NavViewModel) = VerticalListView(viewModel = viewModel)

@Composable
private fun VerticalListView(viewModel: NavViewModel) {
    val list = remember { DemoDataProvider.puppyList }
    val bannerList = remember { DemoDataProvider.banner }
    val bannerListState = rememberLazyListState()
    val onclickItem: (Puppy) -> Unit = remember { { viewModel.navigateToDetail(it) } }
    LazyColumn {
        item {
            BannerView(bannerListState, bannerList, onclickItem)
        }
        items(list.size) { index ->
            val puppy = list[index]
            VerticalListItem(puppy = puppy, onCLick = onclickItem)
            ListItemDivider()
        }
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    )
}

