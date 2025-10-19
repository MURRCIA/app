package com.bangkok.app.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkok.app.ui.theme.BangkokTheme

@Composable
fun BangkokButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    size: ButtonSize = ButtonSize.Medium
) {
    val buttonColors = when (variant) {
        ButtonVariant.Primary -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        ButtonVariant.Secondary -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
        ButtonVariant.Outline -> ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
    
    val buttonHeight = when (size) {
        ButtonSize.Small -> 40.dp
        ButtonSize.Medium -> 48.dp
        ButtonSize.Large -> 56.dp
    }
    
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(buttonHeight),
        enabled = enabled,
        colors = buttonColors,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        elevation = if (variant == ButtonVariant.Outline) {
            ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        } else {
            ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
        }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

enum class ButtonVariant {
    Primary,
    Secondary,
    Outline
}

sealed class ButtonSize {
    object Small : ButtonSize()
    object Medium : ButtonSize()
    object Large : ButtonSize()
}

@Preview(showBackground = true)
@Composable
fun BangkokButtonPreview() {
    BangkokTheme {
        BangkokButton(
            text = "Continuar",
            onClick = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BangkokButtonSecondaryPreview() {
    BangkokTheme {
        BangkokButton(
            text = "Cancelar",
            onClick = { },
            variant = ButtonVariant.Secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BangkokButtonOutlinePreview() {
    BangkokTheme {
        BangkokButton(
            text = "Ver más",
            onClick = { },
            variant = ButtonVariant.Outline
        )
    }
}
