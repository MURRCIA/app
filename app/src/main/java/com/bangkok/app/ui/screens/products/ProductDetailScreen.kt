package com.bangkok.app.ui.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.bangkok.app.ui.components.BangkokButton
import com.bangkok.app.ui.components.BangkokTopBar
import com.bangkok.app.ui.components.ExpandableDescription
import com.bangkok.app.ui.components.SizeSelector
import com.bangkok.app.ui.components.SimilarProductCard
import com.bangkok.app.ui.theme.BangkokAccentRed
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: String,
    onBackClick: () -> Unit,
    onProductClick: (String) -> Unit = {}
) {
    val viewModel: ProductDetailViewModel = koinInject()
    val uiState by viewModel.uiState.collectAsState()
    val similarProducts by viewModel.similarProducts.collectAsState()
    
    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }
    
    Scaffold(
        topBar = {
            BangkokTopBar(
                title = "Detalle del Producto",
                showBackButton = true,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                uiState.errorMessage != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = uiState.errorMessage ?: "Error desconocido",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        TextButton(onClick = onBackClick) {
                            Text("Volver")
                        }
                    }
                }
                uiState.product != null -> {
                    ProductDetailContent(
                        product = uiState.product!!,
                        selectedSize = uiState.selectedSize,
                        similarProducts = similarProducts,
                        addToCartSuccess = uiState.addToCartSuccess,
                        addToCartError = uiState.addToCartError,
                        onSizeSelected = { viewModel.selectSize(it) },
                        onAddToCart = { viewModel.addToCart() },
                        onProductClick = onProductClick,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductDetailContent(
    product: com.bangkok.app.data.models.Product,
    selectedSize: com.bangkok.app.data.models.ProductSize?,
    similarProducts: List<com.bangkok.app.data.models.Product>,
    addToCartSuccess: Boolean,
    addToCartError: String?,
    onSizeSelected: (com.bangkok.app.data.models.ProductSize) -> Unit,
    onAddToCart: () -> Unit,
    onProductClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Imagen del producto
        item {
            ProductImageSection(product = product)
        }
        
        // Información del producto
        item {
            ProductInfoSection(product = product)
        }
        
        // Selector de tallas
        if (product.availableSizes.isNotEmpty()) {
            item {
                SizeSelector(
                    availableSizes = product.availableSizes,
                    selectedSize = selectedSize,
                    onSizeSelected = onSizeSelected,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
        
        // Descripción detallada
        if (!product.detailedDescription.isNullOrEmpty()) {
            item {
                ExpandableDescription(
                    title = "Descripción",
                    description = product.detailedDescription,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
        
        // Características
        item {
            ProductCharacteristicsSection(
                description = product.detailedDescription ?: product.description,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        
        // Botón añadir al carrito
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (addToCartError != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(
                            text = addToCartError,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                }
                
                if (addToCartSuccess) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Producto agregado al carrito",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
                
                BangkokButton(
                    text = "Añadir al Carrito",
                    onClick = onAddToCart,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = product.availableSizes.isEmpty() || selectedSize != null
                )
            }
        }
        
        // Productos similares
        if (similarProducts.isNotEmpty()) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Productos Similares",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.Black
                    )
                    
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(similarProducts, key = { it.id }) { similarProduct ->
                            SimilarProductCard(
                                product = similarProduct,
                                onClick = { onProductClick(similarProduct.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductImageSection(
    product: com.bangkok.app.data.models.Product,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.Gray.copy(alpha = 0.1f))
    ) {
        if (product.imageUrl != null) {
            SubcomposeAsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize(),
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            )
        }
        
        // Tags
        if (product.tags.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                product.tags.take(3).forEach { tag ->
                    Surface(
                        modifier = Modifier.clip(RoundedCornerShape(4.dp)),
                        color = when {
                            tag.contains("NUEVO", ignoreCase = true) -> MaterialTheme.colorScheme.primary
                            tag.contains("PREORDEN", ignoreCase = true) -> BangkokAccentRed
                            else -> MaterialTheme.colorScheme.secondary
                        }
                    ) {
                        Text(
                            text = tag,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductInfoSection(
    product: com.bangkok.app.data.models.Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = product.name,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
        
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black.copy(alpha = 0.7f)
        )
        
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (product.discountPercentage != null) {
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black.copy(alpha = 0.4f),
                    textDecoration = TextDecoration.LineThrough
                )
                val discountedPrice = product.price * (1 - product.discountPercentage / 100.0)
                Text(
                    text = "$${String.format("%.2f", discountedPrice)}",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = BangkokAccentRed
                )
            } else {
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
private fun ProductCharacteristicsSection(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Características",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
        
        // Extraer características de la descripción
        val characteristics = description.split("\n")
            .filter { it.isNotBlank() }
            .take(5)
        
        characteristics.forEach { characteristic ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "•",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = characteristic.trim(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black.copy(alpha = 0.7f),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

