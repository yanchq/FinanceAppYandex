package com.example.history.impl.presentation.analytics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.toCurrencyString
import com.example.core.utils.ui.theme.DividerGrey
import com.example.core.utils.ui.theme.SecondaryGreen
import com.example.history.impl.presentation.analytics.contract.GroupedHistory

@Composable
fun AnalyticsCard(
    analyticsItem: GroupedHistory,
    color: Color?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
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
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(SecondaryGreen, CircleShape)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = analyticsItem.emoji
            )
        }

        Row(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = analyticsItem.name,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )

            color?.let {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(it, CircleShape)
                )
            }
        }


        Column {

            Text(
                modifier = Modifier.align(Alignment.End),
                text = analyticsItem.percentage.toString() + " %",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                modifier = Modifier
                    .align(Alignment.End),
                textAlign = TextAlign.End,
                text = analyticsItem.amount.toCurrencyString("₽"),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}