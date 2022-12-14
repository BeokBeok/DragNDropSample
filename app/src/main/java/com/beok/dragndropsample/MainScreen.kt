package com.beok.dragndropsample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.beok.dragndropsample.dragndrop.DragTarget
import com.beok.dragndropsample.dragndrop.DropContainer

@Composable
fun RunePane(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        RuneImageBox(modifier = Modifier.size(100.dp, 100.dp))
    }
}

@Composable
fun RuneImageBox(modifier: Modifier = Modifier) {
    val dragImage = painterResource(id = R.drawable.rune_spirit)

    DragTarget(modifier = modifier, dragData = dragImage) {
        Image(
            painter = dragImage,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun RuneWordPane(modifier: Modifier = Modifier) {
    var dragImage by remember { mutableStateOf<Painter?>(null) }
    val updateDragImage: (Painter?) -> Unit = { newValue -> dragImage = newValue }
    var isDroppingItem by remember { mutableStateOf(false) }
    var isItemInBounds by remember { mutableStateOf(false) }

    DropContainer(
        modifier = modifier,
        onDrag = { inBounds, isDragging ->
            isDroppingItem = isDragging
            isItemInBounds = inBounds
        }
    ) { dragData ->
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            dragData?.let {
                if (isDroppingItem) return@let
                dragImage = dragData as Painter
            }
            Image(
                modifier = Modifier.size(150.dp, 150.dp),
                painter = painterResource(id = R.drawable.monarch),
                contentDescription = null
            )
            if (dragImage != null) {
                Image(
                    modifier = Modifier.size(100.dp, 100.dp),
                    painter = dragImage!!,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Remover(updateDragImage = updateDragImage)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        if (dragImage != null) {
            RuneWordOption()
        }
    }
}

@Composable
fun Remover(updateDragImage: (Painter?) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(onClick = { updateDragImage(null) }) {
            Image(
                modifier = Modifier.size(80.dp, 40.dp),
                painter = painterResource(id = R.drawable.remover),
                contentDescription = null
            )
        }
    }
}

@Composable
fun RuneWordOption(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text= "?????? ?????? +2")
        Text(text= "?????? ?????? +${(25..35).random()}%", color = Color.Red)
        Text(text= "?????? ?????? ?????? +55%")
        Text(text= "?????? +22")
        Text(text= "?????? +${(89..112).random()}", color = Color.Red)
        Text(text= "?????? ?????? +${(3..8).random()}", color = Color.Red)
        Text(text= "????????? ?????? ????????? +250")
        Text(text= "?????? ?????? +35%")
        Text(text= "?????? ?????? +35%")
        Text(text= "??? ?????? +35%")
        Text(text= "???????????? ????????? 14 ??????")
    }
}
