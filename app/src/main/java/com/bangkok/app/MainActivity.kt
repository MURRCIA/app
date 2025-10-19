package com.bangkok.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bangkok.app.ui.screens.auth.AuthViewModel
import com.bangkok.app.ui.screens.auth.LoginScreen
import com.bangkok.app.ui.screens.auth.RegisterScreen
import com.bangkok.app.ui.screens.home.HomeScreen
import com.bangkok.app.ui.screens.profile.ProfileScreen
import com.bangkok.app.ui.screens.profile.ProfileViewModel
import com.bangkok.app.ui.screens.splash.SplashScreen
import com.bangkok.app.ui.screens.welcome.WelcomeScreen
import com.bangkok.app.ui.theme.BangkokTheme
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Configurar Koin para inyección de dependencias
        startKoin {
            modules(appModule)
        }
        
        setContent {
            BangkokTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BangkokNavigation()
                }
            }
        }
    }
}

// Módulo de Koin para inyección de dependencias
val appModule = module {
    viewModel { AuthViewModel() }
    viewModel { ProfileViewModel() }
}

@Composable
fun BangkokNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate("welcome") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }
        
        composable("welcome") {
            WelcomeScreen(
                onLoginClick = {
                    navController.navigate("login")
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }
        
        composable("home") {
            HomeScreen(
                onCategoryClick = { categoryId ->
                    // TODO: Navegar a pantalla de categoría
                },
                onProductClick = { productId ->
                    // TODO: Navegar a detalle del producto
                },
                onProfileClick = {
                    navController.navigate("profile")
                }
            )
        }
        
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                },
                onForgotPasswordClick = {
                    // TODO: Implementar pantalla de recuperación de contraseña
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("welcome") { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate("login")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        composable("profile") {
            ProfileScreen(
                onEditProfileClick = {
                    // TODO: Implementar pantalla de edición de perfil
                },
                onOrdersClick = {
                    // TODO: Implementar pantalla de pedidos
                },
                onAddressesClick = {
                    // TODO: Implementar pantalla de direcciones
                },
                onPaymentMethodsClick = {
                    // TODO: Implementar pantalla de métodos de pago
                },
                onLogoutClick = {
                    navController.navigate("welcome") {
                        popUpTo("profile") { inclusive = true }
                    }
                }
            )
        }
    }
}
