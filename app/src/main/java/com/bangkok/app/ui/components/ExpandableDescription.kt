package com.bangkok.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableDescription(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    maxLinesCollapsed: Int = 5
) {
    var isExpanded by remember { mutableStateOf(false) }
    val showExpandButton = description.length > 200 // Show expand if description is longer than 200 chars
    
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
        
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black.copy(alpha = 0.7f),
            maxLines = if (isExpanded) Int.MAX_VALUE else maxLinesCollapsed
        )
        
        if (showExpandButton) {
            TextButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier.padding(start = 0.dp, top = 0.dp)
            ) {
                Text(
                    text = if (isExpanded) "Ver menos" else "Ver más",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

