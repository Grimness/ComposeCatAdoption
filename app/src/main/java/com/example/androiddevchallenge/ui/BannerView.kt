package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Puppy

@Composable
fun BannerView(
    listState: LazyListState,
    list: List<Puppy>,
    onClickItem: (puppy: Puppy) -> Unit
) {
    Column {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Featured Puppies",
            style = MaterialTheme.typography.subtitle1
        )
        LazyRow(state = listState) {
            items(count = list.size,
                itemContent = {
                    BannerItem(list[it], Modifier.padding(16.dp, bottom = 16.dp, top = 8.dp)) {
                        onClickItem.invoke(list[it])
                    }
                })
        }
    }
}

@Composable
private fun BannerItem(
    puppy: Puppy,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.size(250.dp, 200.dp).shadow(20.dp)
    ) {
        Box(modifier = Modifier.clickable { onClick() }) {
            val image = ImageBitmap.imageResource(puppy.images.first())
            Image(
                bitmap = image,
                contentDescription = puppy.story,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxHeight().fillMaxWidth()
            )
            Column(
                modifier = Modifier.background(Color.White.copy(alpha = 0.5f))
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            ) {
                Row {
                    Text(
                        text = puppy.name,
                        style = MaterialTheme.typography.h6,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.padding(1.dp))
                    Image(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.CenterVertically),
                        colorFilter = ColorFilter.tint(puppy.sex.color),
                        imageVector = puppy.sex.label,
                        contentDescription = puppy.story
                    )
                }
                Text(
                    text = puppy.breed,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = puppy.location,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}