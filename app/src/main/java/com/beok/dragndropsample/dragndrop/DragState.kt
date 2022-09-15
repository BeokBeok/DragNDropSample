package com.beok.dragndropsample.dragndrop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

class DragState {
    var isDragging: Boolean by mutableStateOf(false)
    var dragPosition: Offset by mutableStateOf(Offset.Zero)
    var dragOffset: Offset by mutableStateOf(Offset.Zero)
    var draggableContent by mutableStateOf<(@Composable () -> Unit)?>(null)
    var dragData: Any? by mutableStateOf(null)
}
