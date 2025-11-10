package com.bangkok.app.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkok.app.ui.components.BangkokButton
import com.bangkok.app.ui.components.BangkokTextField
import com.bangkok.app.ui.components.BangkokTopBar
import com.bangkok.app.ui.theme.BangkokTheme
import org.koin.compose.koinInject
import androidx.compose.runtime.LaunchedEffect

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AuthViewModel = koinInject()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    
    // Navegar automáticamente cuando el registro sea exitoso
    LaunchedEffect(uiState.registerSuccess) {
        if (uiState.registerSuccess) {
            onRegisterSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BangkokTopBar(
            title = "Crear Cuenta",
            showBackButton = true,
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "¡Únete a Bangkok!",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Crea tu cuenta para empezar a comprar",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Error Message
            uiState.errorMessage?.let { errorMessage ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = errorMessage,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            // Full Name Field
            BangkokTextField(
                value = uiState.fullName,
                onValueChange = viewModel::updateFullName,
                label = "Nombre completo",
                placeholder = "Tu nombre completo",
                isError = uiState.fullNameError != null,
                errorMessage = uiState.fullNameError,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            // Email Field
            BangkokTextField(
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                label = "Correo electrónico",
                placeholder = "ejemplo@email.com",
                isError = uiState.emailError != null,
                errorMessage = uiState.emailError,
                keyboardType = KeyboardType.Email,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            // Phone Field
            BangkokTextField(
                value = uiState.phone,
                onValueChange = viewModel::updatePhone,
                label = "Teléfono",
                placeholder = "+52 55 1234 5678",
                isError = uiState.phoneError != null,
                errorMessage = uiState.phoneError,
                keyboardType = KeyboardType.Phone,
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )

            // Password Field
            OutlinedTextField(
                value = uiState.password,
                onValueChange = viewModel::updatePassword,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Contraseña") },
                placeholder = { Text("Mínimo 6 caracteres") },
                isError = uiState.passwordError != null,
                supportingText = uiState.passwordError?.let { { Text(it) } },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    errorBorderColor = MaterialTheme.colorScheme.error
                )
            )

            // Confirm Password Field
            OutlinedTextField(
                value = uiState.confirmPassword,
                onValueChange = viewModel::updateConfirmPassword,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Confirmar contraseña") },
                placeholder = { Text("Repite tu contraseña") },
                isError = uiState.confirmPasswordError != null,
                supportingText = uiState.confirmPasswordError?.let { { Text(it) } },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { 
                        focusManager.clearFocus()
                        viewModel.register()
                    }
                ),
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (confirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    errorBorderColor = MaterialTheme.colorScheme.error
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Register Button
            BangkokButton(
                text = if (uiState.isLoading) "Creando cuenta..." else "Crear Cuenta",
                onClick = viewModel::register,
                enabled = !uiState.isLoading
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Login Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿Ya tienes cuenta? ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
                TextButton(onClick = onLoginClick) {
                    Text(
                        text = "Inicia sesión",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    BangkokTheme {
        RegisterScreen(
            onRegisterSuccess = {},
            onLoginClick = {},
            onBackClick = {}
        )
    }
}
