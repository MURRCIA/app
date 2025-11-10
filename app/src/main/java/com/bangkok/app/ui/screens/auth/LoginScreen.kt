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
import androidx.compose.runtime.LaunchedEffect
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
import com.bangkok.app.ui.theme.BangkokTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkok.app.ui.components.BangkokButton
import com.bangkok.app.ui.components.BangkokTextField
import com.bangkok.app.ui.components.BangkokTopBar
import com.bangkok.app.ui.theme.BangkokGrey50
import com.bangkok.app.ui.theme.BangkokTheme
import org.koin.compose.koinInject

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AuthViewModel = koinInject()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    var passwordVisible by remember { mutableStateOf(false) }
    
    // Navegar automáticamente cuando el login sea exitoso
    LaunchedEffect(uiState.loginSuccess) {
        if (uiState.loginSuccess) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BangkokGrey50)
    ) {
        BangkokTopBar(
            title = "Iniciar Sesión",
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
                    text = "¡Bienvenido de nuevo!",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Inicia sesión para continuar",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.7f),
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

            // Email Field
            BangkokTextField(
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                label = "Correo",
                placeholder = "ejemplo@email.com",
                isError = uiState.emailError != null,
                errorMessage = uiState.emailError,
                keyboardType = KeyboardType.Email,
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
                placeholder = { Text("Ingresa tu contraseña") },
                isError = uiState.passwordError != null,
                supportingText = uiState.passwordError?.let { { Text(it) } },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { 
                        focusManager.clearFocus()
                        viewModel.login()
                    }
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

            // Remember Me
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = uiState.rememberMe,
                    onCheckedChange = viewModel::updateRememberMe,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = "Recordarme",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                TextButton(onClick = onForgotPasswordClick) {
                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            BangkokButton(
                text = if (uiState.isLoading) "Iniciando sesión..." else "Iniciar Sesión",
                onClick = viewModel::login,
                enabled = !uiState.isLoading
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Register Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿No tienes cuenta? ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                TextButton(onClick = onRegisterClick) {
                    Text(
                        text = "Crear cuenta",
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
fun LoginScreenPreview() {
    BangkokTheme {
        LoginScreen(
            onLoginSuccess = {},
            onRegisterClick = {},
            onForgotPasswordClick = {},
            onBackClick = {}
        )
    }
}
