package com.bangkok.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkok.app.ui.theme.BangkokTheme

@Composable
fun BangkokTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
) {
    val colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        errorBorderColor = MaterialTheme.colorScheme.error,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorLabelColor = MaterialTheme.colorScheme.error,
        focusedTextColor = MaterialTheme.colorScheme.primary,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorTextColor = MaterialTheme.colorScheme.error,
        cursorColor = MaterialTheme.colorScheme.primary
    )
    
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = label?.let { { Text(text = it) } },
        placeholder = placeholder?.let { { Text(text = it) } },
        isError = isError,
        supportingText = errorMessage?.let { { Text(text = it) } },
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        colors = colors,
        singleLine = true,
        shape = RoundedCornerShape(8.dp)
    )


}

@Preview(showBackground = true)
@Composable
fun BangkokTextFieldPreview() {
    BangkokTheme {
        BangkokTextField(
            value = "",
            onValueChange = { },
            label = "Correo electrónico",
            placeholder = "ejemplo@email.com"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BangkokTextFieldErrorPreview() {
    BangkokTheme {
        BangkokTextField(
            value = "invalid-email",
            onValueChange = { },
            label = "Correo electrónico",
            isError = true,
            errorMessage = "Ingresa un correo válido"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BangkokTextFieldPasswordPreview() {
    BangkokTheme {
        BangkokTextField(
            value = "password123",
            onValueChange = { },
            label = "Contraseña",
            isPassword = true
        )
    }
}
