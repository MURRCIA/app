package com.bangkok.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkok.app.ui.components.BangkokTopBar
import com.bangkok.app.ui.theme.BangkokTheme
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(
    onEditProfileClick: () -> Unit,
    onOrdersClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPaymentMethodsClick: () -> Unit,
    onAdminClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit
) {
    val viewModel: ProfileViewModel = koinInject()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isAdmin by viewModel.isAdmin.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BangkokTopBar(
            title = "Mi Perfil",
            showBackButton = true,
            onBackClick = onBackClick
        )

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            uiState.user?.let { user ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Profile Header
                    item {
                        ProfileHeader(user = user, isAdmin = isAdmin, onEditClick = onEditProfileClick)
                    }

                    // Profile Actions
                    item {
                        ProfileActions(
                            viewModel = viewModel,
                            isAdmin = isAdmin,
                            onEditProfileClick = onEditProfileClick,
                            onOrdersClick = onOrdersClick,
                            onAddressesClick = onAddressesClick,
                            onPaymentMethodsClick = onPaymentMethodsClick,
                            onAdminClick = onAdminClick,
                            onLogoutClick = onLogoutClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(
    user: com.bangkok.app.data.models.User,
    isAdmin: Boolean,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Avatar
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = user.fullName.split(" ").map { it.first() }.joinToString(""),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // User Info
            Text(
                text = user.fullName,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = user.phone,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            // Badge de administrador
            if (isAdmin) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "ADMINISTRADOR",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Edit Profile Button
            OutlinedButton(
                onClick = onEditClick,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.primary
                    ).brush
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Editar perfil")
            }
        }
    }
}

@Composable
fun ProfileActions(
    viewModel: ProfileViewModel,
    isAdmin: Boolean,
    onEditProfileClick: () -> Unit,
    onOrdersClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPaymentMethodsClick: () -> Unit,
    onAdminClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val profileActions = mutableListOf(
        ProfileAction(
            title = "Mis pedidos",
            subtitle = "Ver historial de compras",
            icon = Icons.Default.ShoppingBag,
            onClick = onOrdersClick
        ),
        ProfileAction(
            title = "Direcciones",
            subtitle = "Gestionar direcciones de envío",
            icon = Icons.Default.LocationOn,
            onClick = onAddressesClick
        ),
        ProfileAction(
            title = "Métodos de pago",
            subtitle = "Tarjetas y métodos guardados",
            icon = Icons.Default.CreditCard,
            onClick = onPaymentMethodsClick
        ),
        ProfileAction(
            title = "Configuración",
            subtitle = "Preferencias de la cuenta",
            icon = Icons.Default.Settings,
            onClick = { /* TODO: Implement settings */ }
        ),
        ProfileAction(
            title = "Ayuda y soporte",
            subtitle = "Centro de ayuda y contacto",
            icon = Icons.Default.Help,
            onClick = { /* TODO: Implement help */ }
        )
    )
    
    // Agregar opción de administración solo si es admin
    if (isAdmin) {
        profileActions.add(
            ProfileAction(
                title = "Administración de Productos",
                subtitle = "Gestionar productos y catálogo",
                icon = Icons.Default.AdminPanelSettings,
                onClick = onAdminClick
            )
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        profileActions.forEach { action ->
            ProfileActionItem(action = action)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Logout Button
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { 
                    viewModel.logout()
                    onLogoutClick()
                },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.1f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(24.dp)
                )
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Cerrar sesión",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "Salir de tu cuenta",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileActionItem(
    action: ProfileAction
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { action.onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = action.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = action.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = action.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

data class ProfileAction(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    BangkokTheme {
        ProfileScreen(
            onEditProfileClick = {},
            onOrdersClick = {},
            onAddressesClick = {},
            onPaymentMethodsClick = {},
            onBackClick = {},
            onLogoutClick = {}
        )
    }
}
