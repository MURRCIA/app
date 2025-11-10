package com.bangkok.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bangkok.app.data.models.Product
import com.bangkok.app.data.models.ProductCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductEditDialog(
    product: Product?,
    onDismiss: () -> Unit,
    onConfirm: (Product) -> Unit
) {
    var name by remember { mutableStateOf(product?.name ?: "") }
    var description by remember { mutableStateOf(product?.description ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }
    var category by remember { mutableStateOf(product?.category ?: ProductCategory.TSHIRT) }
    var imageUrl by remember { mutableStateOf(product?.imageUrl ?: "") }
    var tags by remember { mutableStateOf(product?.tags?.joinToString(", ") ?: "") }
    var isFeatured by remember { mutableStateOf(product?.isFeatured ?: false) }
    var isNewArrival by remember { mutableStateOf(product?.isNewArrival ?: false) }
    var discountPercentage by remember { mutableStateOf(product?.discountPercentage?.toString() ?: "") }

    var categoryExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (product == null) "Agregar Producto" else "Editar Producto") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre *") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción *") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 5
                )

                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Precio *") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )

                // Selector de categoría
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = !categoryExpanded }
                ) {
                    OutlinedTextField(
                        value = category.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Categoría *") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) }
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        ProductCategory.values().forEach { productCategory ->
                            DropdownMenuItem(
                                text = { Text(productCategory.name) },
                                onClick = {
                                    category = productCategory
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    label = { Text("URL de Imagen") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = tags,
                    onValueChange = { tags = it },
                    label = { Text("Tags (separados por coma)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isFeatured,
                        onCheckedChange = { isFeatured = it }
                    )
                    Text("Producto destacado")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isNewArrival,
                        onCheckedChange = { isNewArrival = it }
                    )
                    Text("Nueva llegada")
                }

                OutlinedTextField(
                    value = discountPercentage,
                    onValueChange = { discountPercentage = it },
                    label = { Text("Descuento (%)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newProduct = if (product != null) {
                        product.copy(
                            name = name.trim(),
                            description = description.trim(),
                            price = price.toDoubleOrNull() ?: 0.0,
                            category = category,
                            imageUrl = imageUrl.trim().ifBlank { null },
                            tags = tags.split(",").map { it.trim() }.filter { it.isNotBlank() },
                            isFeatured = isFeatured,
                            isNewArrival = isNewArrival,
                            discountPercentage = discountPercentage.toIntOrNull()
                        )
                    } else {
                        Product(
                            id = "", // Se generará UUID en el ViewModel
                            name = name.trim(),
                            description = description.trim(),
                            price = price.toDoubleOrNull() ?: 0.0,
                            category = category,
                            imageUrl = imageUrl.trim().ifBlank { null },
                            tags = tags.split(",").map { it.trim() }.filter { it.isNotBlank() },
                            isFeatured = isFeatured,
                            isNewArrival = isNewArrival,
                            discountPercentage = discountPercentage.toIntOrNull()
                        )
                    }
                    onConfirm(newProduct)
                },
                enabled = name.isNotBlank() && description.isNotBlank() && price.toDoubleOrNull() != null
            ) {
                Text(if (product == null) "Agregar" else "Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

