package com.bangkok.app.data.models

data class Category(
    val id: String,
    val name: String,
    val imageUrl: String,
    val productCount: Int = 0
)

object MockCategoryData {
    val categories = listOf(
        Category(
            id = "cat_001",
            name = "NEW",
            imageUrl = "https://nikifilini.com/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2024/12/IMG_9829-2_0001_Fon-768x512.jpg.webp",
            productCount = 24
        ),
        Category(
            id = "cat_002",
            name = "HOODIES",
            imageUrl = "https://nikifilini.com/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2024/08/LOOKBOOK-427.08.24_13-768x512.jpg.webp",
            productCount = 18
        ),
        Category(
            id = "cat_003",
            name = "TOP",
            imageUrl = "https://nikifilini.com/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2024/08/LOOKBOOK-227.08.24_4-768x576.jpg.webp",
            productCount = 32
        ),
        Category(
            id = "cat_004",
            name = "BOTTOMS",
            imageUrl = "https://nikifilini.com/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2024/08/10-Lookbook_27.08.24.jpg-768x512.jpg.webp",
            productCount = 15
        ),
        Category(
            id = "cat_005",
            name = "ACCESSORIES",
            imageUrl = "https://nikifilini.com/wp-content/webp-express/webp-images/doc-root/wp-content/uploads/2024/08/LOOKBOOK-628.08.24-768x512.jpg.webp",
            productCount = 12
        )
    )
}

