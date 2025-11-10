package com.bangkok.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bangkok.app.data.models.ProductSize
import com.bangkok.app.ui.theme.BangkokPrimary

@Composable
fun SizeSelector(
    availableSizes: List<ProductSize>,
    selectedSize: ProductSize?,
    onSizeSelected: (ProductSize) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Talla",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            availableSizes.forEach { size ->
                SizeChip(
                    size = size,
                    isSelected = selectedSize == size,
                    isAvailable = true,
                    onClick = { onSizeSelected(size) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun SizeChip(
    size: ProductSize,
    isSelected: Boolean,
    isAvailable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        isSelected -> BangkokPrimary
        !isAvailable -> Color.Gray.copy(alpha = 0.2f)
        else -> Color.White
    }
    
    val contentColor = when {
        isSelected -> Color.White
        !isAvailable -> Color.Gray
        else -> Color.Black
    }
    
    val borderColor = when {
        isSelected -> BangkokPrimary
        !isAvailable -> Color.Gray.copy(alpha = 0.3f)
        else -> Color.Gray.copy(alpha = 0.5f)
    }
    
    Surface(
        modifier = modifier
            .height(48.dp)
            .clickable(enabled = isAvailable, onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        color = backgroundColor,
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = borderColor
        ),
        tonalElevation = if (isSelected) 4.dp else 0.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = size.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                ),
                color = contentColor
            )
        }
    }
}

