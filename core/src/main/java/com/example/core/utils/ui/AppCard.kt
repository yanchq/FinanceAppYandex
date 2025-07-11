package com.example.core.utils.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.utils.toCurrencyString
import com.example.core.R

/**
 * Универсальная карточка приложения.
 * Отображает информацию в виде карточки с возможностью настройки заголовка,
 * подзаголовка, суммы, дополнительной суммы и эмодзи-аватара.
 *
 * @param title Основной заголовок карточки
 * @param subtitle Подзаголовок (опционально)
 * @param amount Числовое значение суммы (опционально)
 * @param subAmount Дополнительная текстовая информация о сумме (опционально)
 * @param avatarEmoji Эмодзи для аватара (опционально)
 * @param canNavigate Флаг, указывающий возможность навигации
 * @param onNavigateClick Callback для обработки нажатия при навигации
 * @param isSetting Флаг, указывающий является ли карточка элементом настроек
 */
@JvmOverloads
@Composable
fun AppCard(
    title: String,
    subtitle: String? = null,
    amount: Double? = null,
    stringDate: String? = null,
    subAmount: String? = null,
    avatarEmoji: String? = null,
    canNavigate: Boolean = false,
    onNavigateClick: (() -> Unit)? = null,
    isSetting: Boolean = false,
    currency: String? = null,
) {
    val borderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .then(
                if (onNavigateClick != null) {
                    Modifier.clickable { onNavigateClick() }
                } else {
                    Modifier
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp,
            draggedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height - strokeWidth / 2

                    drawLine(
                        color = borderColor,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (avatarEmoji != null) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(avatarEmoji, fontSize = 19.sp)
                }
                Spacer(modifier = Modifier.width(12.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,

                )
                if (!subtitle.isNullOrEmpty()) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            if (amount != null) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = amount.toCurrencyString(currency = currency ?: "₽"),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 16.sp,
                    )
                    if (!subAmount.isNullOrEmpty()) {
                        Text(
                            text = subAmount,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.End,
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
            }

            if (stringDate != null) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = stringDate,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 16.sp,
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }

            if (canNavigate) {
                if (isSetting) {
                    Image(
                        painter = painterResource(R.drawable.ic_category_more),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.ic_more),
                        contentDescription = "Подробнее",
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
        }
    }
}
