package com.example.shmryandex.app.presentation.options.maincolor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.presentation.LocalViewModelFactory
import com.example.core.utils.ui.MainColorType
import com.example.core.utils.ui.theme.PrimaryGreen
import com.example.core.utils.ui.theme.PrimaryRed
import com.example.core.utils.ui.theme.PrimaryYellow

@Composable
fun ChangeMainColorScreen(
    viewModel: MainColorViewModel = viewModel(factory = LocalViewModelFactory.current)
) {

    val mainColor = viewModel.mainColor.collectAsStateWithLifecycle()

    ColorRadioButtonsGrid(
        selected = mainColor.value,
        onSelectedChange = {
            viewModel.changeMainColor(it)
        }
    )
}

@Composable
fun ColorRadioButtonsGrid(
    selected: MainColorType,
    onSelectedChange: (MainColorType) -> Unit,
    buttonSize: Dp = 80.dp,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(MainColorType.entries) {
            val isSelected = it == selected

            Box(
                modifier = Modifier
                    .size(buttonSize) // квадрат
                    .border(
                        width = if (isSelected) 3.dp else 0.dp,
                        color = if (isSelected) Color.Black else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(
                        color = when (it) {
                            MainColorType.GREEN -> PrimaryGreen
                            MainColorType.RED -> PrimaryRed
                            MainColorType.YELLOW -> PrimaryYellow
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { onSelectedChange(it) }
            )
        }
    }
}