package com.bangkok.app.data.models

data class User(
    val id: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val profileImageUrl: String? = null,
    val registrationDate: String,
    val isEmailVerified: Boolean = false,
    val preferences: UserPreferences = UserPreferences()
)

data class UserPreferences(
    val notificationsEnabled: Boolean = true,
    val marketingEmailsEnabled: Boolean = false,
    val language: String = "es",
    val currency: String = "MXN"
)

data class UserAddress(
    val id: String,
    val street: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String,
    val isDefault: Boolean = false
)

data class PaymentMethod(
    val id: String,
    val type: PaymentType,
    val lastFourDigits: String? = null,
    val expiryDate: String? = null,
    val isDefault: Boolean = false
)

enum class PaymentType {
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    CASH_ON_DELIVERY
}

// Mock data for testing
object MockUserData {
    val sampleUser = User(
        id = "user_001",
        fullName = "Juan Pérez García",
        email = "juan.perez@email.com",
        phone = "+52 55 1234 5678",
        profileImageUrl = null,
        registrationDate = "2024-01-15",
        isEmailVerified = true,
        preferences = UserPreferences(
            notificationsEnabled = true,
            marketingEmailsEnabled = true,
            language = "es",
            currency = "MXN"
        )
    )
    
    val sampleAddresses = listOf(
        UserAddress(
            id = "addr_001",
            street = "Av. Insurgentes Sur 123, Col. Roma Norte",
            city = "Ciudad de México",
            state = "CDMX",
            postalCode = "06700",
            country = "México",
            isDefault = true
        ),
        UserAddress(
            id = "addr_002",
            street = "Calle Reforma 456, Col. Juárez",
            city = "Ciudad de México",
            state = "CDMX",
            postalCode = "06600",
            country = "México",
            isDefault = false
        )
    )
    
    val samplePaymentMethods = listOf(
        PaymentMethod(
            id = "pm_001",
            type = PaymentType.CREDIT_CARD,
            lastFourDigits = "1234",
            expiryDate = "12/25",
            isDefault = true
        ),
        PaymentMethod(
            id = "pm_002",
            type = PaymentType.DEBIT_CARD,
            lastFourDigits = "5678",
            expiryDate = "08/26",
            isDefault = false
        )
    )
}
