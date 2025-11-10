package com.bangkok.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.bangkok.app.data.models.MockProductData
import com.bangkok.app.data.models.Product
import com.bangkok.app.ui.theme.BangkokAccentGreen
import com.bangkok.app.ui.theme.BangkokAccentRed
import com.bangkok.app.ui.theme.BangkokTheme

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(180.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Imagen del producto
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                if (product.imageUrl != null) {
                    SubcomposeAsyncImage(
                        model = product.imageUrl,
                        contentDescription = product.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                        loading = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(40.dp),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    )
                } else if (product.imageRes != null) {
                    Image(
                        painter = painterResource(id = product.imageRes),
                        contentDescription = product.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    )
                }
                
                // Etiquetas (tags)
                if (product.tags.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        product.tags.take(2).forEach { tag ->
                            Text(
                                text = tag,
                                modifier = Modifier
                                    .background(
                                        color = when {
                                            tag.contains("NUEVO", ignoreCase = true) -> BangkokAccentGreen
                                            tag.contains("PREORDEN", ignoreCase = true) -> BangkokAccentRed
                                            else -> MaterialTheme.colorScheme.primary
                                        },
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                
                // Badge de descuento
                product.discountPercentage?.let { discount ->
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(
                                color = BangkokAccentRed,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "-$discount%",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            // Información del producto
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                // Nombre
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                // Descripción
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black.copy(alpha = 0.6f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Precio
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (product.discountPercentage != null) {
                        // Precio original tachado
                        Text(
                            text = "$${product.price}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black.copy(alpha = 0.4f),
                            textDecoration = TextDecoration.LineThrough
                        )
                        // Precio con descuento
                        val discountedPrice = product.price * (1 - product.discountPercentage / 100.0)
                        Text(
                            text = "$${String.format("%.2f", discountedPrice)}",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Black
                            ),
                            color = BangkokAccentRed
                        )
                    } else {
                        Text(
                            text = "$${product.price}",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Black
                            ),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    BangkokTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductCard(
                product = MockProductData.sampleProducts[0],
                onClick = {}
            )
            ProductCard(
                product = MockProductData.sampleProducts[1],
                onClick = {}
            )
        }
    }
}

