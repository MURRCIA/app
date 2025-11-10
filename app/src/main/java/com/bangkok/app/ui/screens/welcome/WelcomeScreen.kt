package com.bangkok.app.ui.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkok.app.ui.components.BangkokButton
import com.bangkok.app.ui.components.ButtonVariant
import com.bangkok.app.ui.theme.BangkokTheme

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Hero Image placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                    RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "👕\nFashion Store",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 32.sp
                ),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Welcome Title
        Text(
            text = "Bienvenido a Bangkok",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Subtitle
        Text(
            text = "Descubre la mejor ropa de moda con estilo urbano y tendencias únicas. Encuentra tu look perfecto con nuestra colección exclusiva.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp
            ),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        // Action Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BangkokButton(
                text = "Iniciar Sesión",
                onClick = onLoginClick,
                variant = ButtonVariant.Primary
            )
            
            BangkokButton(
                text = "Registrarse",
                onClick = onRegisterClick,
                variant = ButtonVariant.Outline
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    BangkokTheme {
        WelcomeScreen(
            onLoginClick = {},
            onRegisterClick = {}
        )
    }
}
