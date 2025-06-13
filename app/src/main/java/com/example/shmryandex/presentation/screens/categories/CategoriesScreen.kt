package com.example.shmryandex.presentation.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shmryandex.R
import com.example.shmryandex.ui.theme.DividerGrey
import com.example.shmryandex.ui.theme.SecondaryGreen

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }

    Column {
        Search(
            query = query,
            onQueryChange = { query = it }
        )

        LazyColumn {
            items(uiState.value.categoriesList) { category ->
                CategoryCard(
                    emoji = category.emoji,
                    name = category.name
                )
            }
        }
    }
}

@Composable
fun Search(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFFECE6F0))
            .drawBehind {
                val strokeWidth = 0.7.dp.toPx()
                drawLine(
                    color = Color(0xFFCAC4D0),
                    start = Offset(0f, size.height - strokeWidth / 2),
                    end = Offset(size.width, size.height - strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .padding( horizontal = 8.dp)
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Найти статью",
                    fontSize = 16.sp,
                    color = Color(0xFF49454F),
                    style  = MaterialTheme.typography.bodyLarge
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF49454F),
                unfocusedTextColor = Color(0xFFECE6F0),
                focusedIndicatorColor = Color(0xFFCAC4D0),
                unfocusedIndicatorColor = Color(0xFFCAC4D0),
                focusedPlaceholderColor = Color(0xFFECE6F0),
                unfocusedPlaceholderColor = Color(0xFFECE6F0),
                focusedContainerColor = Color(0xFFECE6F0),
                unfocusedContainerColor = Color(0xFFECE6F0),
            ),
            singleLine = true
        )

        Image(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd)
                .padding(end = 10.dp)
                .clickable{}
        )
    }
}

@Composable
private fun CategoryCard(emoji: String, name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .drawBehind {
                val strokeWidth = 0.7.dp.toPx()
                drawLine(
                    color = DividerGrey,
                    start = Offset(0f, size.height - strokeWidth / 2),
                    end = Offset(size.width, size.height - strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(SecondaryGreen, CircleShape)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = emoji
            )
        }

        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}