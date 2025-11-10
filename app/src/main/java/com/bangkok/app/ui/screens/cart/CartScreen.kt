package com.bangkok.app.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkok.app.ui.components.BangkokButton
import com.bangkok.app.ui.components.BangkokTopBar
import com.bangkok.app.ui.theme.BangkokTheme
import org.koin.compose.koinInject
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CartScreen(
    onBackClick: () -> Unit,
    onCheckoutClick: () -> Unit = {}
) {
    val viewModel: CartViewModel = koinInject()
    val cartItems by viewModel.cartItems.collectAsState()
    val total by viewModel.total.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        BangkokTopBar(
            title = "Carrito de Compras",
            showBackButton = true,
            onBackClick = onBackClick
        )
        
        if (cartItems.isEmpty()) {
            // Empty cart state
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Carrito vacío",
                        modifier = Modifier.size(64.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Tu carrito está vacío",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Gray
                    )
                    Text(
                        text = "Agrega productos para comenzar",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(cartItems, key = { it.id }) { item ->
                    CartItemCard(
                        item = item,
                        onQuantityChange = { quantity ->
                            viewModel.updateQuantity(item.id, quantity)
                        },
                        onRemove = {
                            viewModel.removeFromCart(item.id)
                        }
                    )
                }
            }
            
            // Total and checkout section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total:",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = formatPrice(total),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                    
                    BangkokButton(
                        text = "Proceder al Pago",
                        onClick = onCheckoutClick,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    item: com.bangkok.app.data.models.CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Product image placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Gray.copy(alpha = 0.2f))
            ) {
                // Image would go here
            }
            
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = item.product.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = formatPrice(item.product.price),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onQuantityChange(item.quantity - 1) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Text("-")
                    }
                    Text(
                        text = item.quantity.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(
                        onClick = { onQuantityChange(item.quantity + 1) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Text("+")
                    }
                }
            }
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = formatPrice(item.totalPrice),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

fun formatPrice(price: Double): String {
    val format = NumberFormat.getCurrencyInstance(Locale("es", "MX"))
    return format.format(price)
}

