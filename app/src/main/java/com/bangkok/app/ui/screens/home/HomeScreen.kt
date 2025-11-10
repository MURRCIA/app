package com.bangkok.app.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkok.app.R
import com.bangkok.app.ui.components.CategoryCard
import com.bangkok.app.ui.components.ProductCard
import com.bangkok.app.ui.theme.BangkokTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCategoryClick: (String) -> Unit = {},
    onProductClick: (String) -> Unit = {},
    onProfileClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onAdminClick: () -> Unit = {}
) {
    val viewModel: HomeViewModel = koinInject()
    val categories by viewModel.categories.collectAsState()
    val products by viewModel.products.collectAsState()
    val isAdmin by viewModel.isAdmin.collectAsState()
    
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                isAdmin = isAdmin,
                onCloseDrawer = { scope.launch { drawerState.close() } },
                onProfileClick = {
                    scope.launch { drawerState.close() }
                    onProfileClick()
                },
                onAdminClick = {
                    scope.launch { drawerState.close() }
                    onAdminClick()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Custom TopBar con logo y menu
            CustomTopBar(
                onMenuClick = { scope.launch { drawerState.open() } },
                onProfileClick = onProfileClick,
                onCartClick = onCartClick
            )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            // Sección de Categorías
            item {
                Text(
                    text = "CATEGORÍAS",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 1.sp
                    ),
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // Scroll horizontal de categorías
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(categories) { category ->
                        CategoryCard(
                            category = category,
                            onClick = { onCategoryClick(category.id) },
                            modifier = Modifier.width(300.dp)
                        )
                    }
                }
            }

            // Espaciador
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Sección de Productos
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "PRODUCTOS",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            letterSpacing = 1.sp
                        ),
                        color = Color.Black
                    )
                    
                    Text(
                        text = "Ver todo",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = Color.Black.copy(alpha = 0.6f)
                    )
                }
            }

            // Grid de productos (2 columnas)
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    products.chunked(2).forEach { rowProducts ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            rowProducts.forEach { product ->
                                ProductCard(
                                    product = product,
                                    onClick = { onProductClick(product.id) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            // Rellenar espacio si hay un solo producto en la fila
                            if (rowProducts.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            // Espaciador final
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    onMenuClick: () -> Unit,
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo_splash),
                contentDescription = "Bangkok Logo",
                modifier = Modifier.height(150.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = onCartClick) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Carrito",
                    tint = Color.White
                )
            }
            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Composable
fun DrawerContent(
    isAdmin: Boolean,
    onCloseDrawer: () -> Unit,
    onProfileClick: () -> Unit,
    onAdminClick: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = Color.White
    ) {
        // Header del Drawer con logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_splash),
                contentDescription = "Bangkok Logo",
                modifier = Modifier.size(160.dp)
            )
        }
        
        Divider(color = Color.Black.copy(alpha = 0.1f))
        
        // Opciones del menú
        DrawerMenuItem(
            icon = Icons.Default.Home,
            title = "Inicio",
            onClick = onCloseDrawer
        )
        
        DrawerMenuItem(
            icon = Icons.Default.ShoppingCart,
            title = "Carrito",
            onClick = { /* TODO: Navigate to cart */ onCloseDrawer() }
        )
        
        DrawerMenuItem(
            icon = Icons.Default.Favorite,
            title = "Favoritos",
            onClick = { /* TODO */ onCloseDrawer() }
        )
        
        DrawerMenuItem(
            icon = Icons.Default.Person,
            title = "Mi Cuenta",
            onClick = {
                onProfileClick()
                onCloseDrawer()
            }
        )
        
        // Opción de administración solo para admin
        if (isAdmin) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black.copy(alpha = 0.1f)
            )
            
            DrawerMenuItem(
                icon = Icons.Default.AdminPanelSettings,
                title = "Administración",
                onClick = {
                    onAdminClick()
                    onCloseDrawer()
                }
            )
        }
        
        Divider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.Black.copy(alpha = 0.1f)
        )
        
        DrawerMenuItem(
            icon = Icons.Default.Settings,
            title = "Configuración",
            onClick = { /* TODO */ onCloseDrawer() }
        )
        
        DrawerMenuItem(
            icon = Icons.Default.Info,
            title = "Acerca de",
            onClick = { /* TODO */ onCloseDrawer() }
        )
        
        DrawerMenuItem(
            icon = Icons.Default.ExitToApp,
            title = "Cerrar Sesión",
            onClick = { /* TODO */ onCloseDrawer() }
        )
    }
}

@Composable
fun DrawerMenuItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(24.dp))
        
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            ),
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BangkokTheme {
        HomeScreen()
    }
}

