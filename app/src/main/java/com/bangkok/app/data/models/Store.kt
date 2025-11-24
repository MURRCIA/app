package com.bangkok.app.data.models

data class Store(
    val id: String,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val phone: String,
    val schedule: String,
    val imageUrl: String? = null
)

// Datos mock de tiendas
object MockStoreData {
    val stores = listOf(
        Store(
            id = "store_1",
            name = "Bangkok Centro",
            address = "Av. Principal 123, Centro Histórico, Ciudad",
            latitude = 19.4326, // Coordenadas de ejemplo (Ciudad de México)
            longitude = -99.1332,
            phone = "+52 55 1234 5678",
            schedule = "Lun - Vie: 10:00 - 20:00\nSáb - Dom: 11:00 - 19:00",
            imageUrl = null
        ),
        Store(
            id = "store_2",
            name = "Bangkok Norte",
            address = "Plaza Norte, Local 45, Zona Norte",
            latitude = 19.4426,
            longitude = -99.1432,
            phone = "+52 55 9876 5432",
            schedule = "Lun - Dom: 10:00 - 21:00",
            imageUrl = null
        )
    )
}

