package com.bangkok.app.ui.screens.stores

import android.Manifest
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import androidx.core.content.ContextCompat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bangkok.app.data.models.Store
import com.bangkok.app.ui.components.BangkokTopBar
import com.bangkok.app.ui.components.CameraSearchButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import org.koin.compose.koinInject
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun StoresScreen(
    onBackClick: () -> Unit
) {
    val viewModel: StoresViewModel = koinInject()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    
    LaunchedEffect(locationPermissionState.allPermissionsGranted) {
        viewModel.updateLocationPermission(locationPermissionState.allPermissionsGranted)
    }
    
    // Inicializar FusedLocationProviderClient si no está disponible
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    
    // Obtener ubicación del usuario cuando se conceden permisos
    LaunchedEffect(locationPermissionState.allPermissionsGranted) {
        if (locationPermissionState.allPermissionsGranted) {
            getUserLocation(context, fusedLocationClient) { location ->
                viewModel.updateUserLocation(location)
            }
        }
    }
    
    Scaffold(
        topBar = {
            BangkokTopBar(
                title = "Nuestras Tiendas",
                showBackButton = true,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            // Mapa
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                OSMMapView(
                    stores = uiState.stores,
                    userLocation = uiState.userLocation,
                    hasLocationPermission = uiState.hasLocationPermission,
                    onLocationPermissionRequest = {
                        locationPermissionState.launchMultiplePermissionRequest()
                    },
                    onGetUserLocation = {
                        if (locationPermissionState.allPermissionsGranted) {
                            getUserLocation(context, fusedLocationClient) {
                                viewModel.updateUserLocation(it)
                            }
                        }
                    }
                )
            }
            
            // Botón de búsqueda con cámara
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                CameraSearchButton()
            }
            
            // Lista de tiendas
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = "TIENDAS DISPONIBLES",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                
                items(uiState.stores) { store ->
                    StoreCard(store = store)
                }
            }
        }
    }
}

@Composable
fun OSMMapView(
    stores: List<Store>,
    userLocation: Location?,
    hasLocationPermission: Boolean,
    onLocationPermissionRequest: () -> Unit = {},
    onGetUserLocation: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    
    // Configurar OSMDroid
    LaunchedEffect(Unit) {
        Configuration.getInstance().load(
            context,
            context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        )
        Configuration.getInstance().userAgentValue = "BangkokApp"
    }
    
    AndroidView(
        factory = { ctx ->
            MapView(ctx).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                
                // Configurar zoom
                controller.setZoom(13.0)
                
                // Centrar en la primera tienda o ubicación por defecto
                if (stores.isNotEmpty()) {
                    val firstStore = stores[0]
                    controller.setCenter(GeoPoint(firstStore.latitude, firstStore.longitude))
                } else {
                    // Ubicación por defecto (Ciudad de México)
                    controller.setCenter(GeoPoint(19.4326, -99.1332))
                }
                
                // Agregar marcadores para cada tienda
                stores.forEach { store ->
                    val marker = Marker(this)
                    marker.position = GeoPoint(store.latitude, store.longitude)
                    marker.title = store.name
                    marker.snippet = store.address
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                    
                    // Usar icono por defecto de OSMDroid
                    val drawable = ContextCompat.getDrawable(ctx, android.R.drawable.ic_menu_mylocation)
                    if (drawable != null) {
                        marker.icon = drawable
                    }
                    
                    overlays.add(marker)
                }
                
                // Agregar overlay de ubicación del usuario si está disponible
                if (hasLocationPermission && userLocation != null) {
                    val myLocationOverlay = MyLocationNewOverlay(this)
                    myLocationOverlay.enableMyLocation()
                    overlays.add(myLocationOverlay)
                }
            }
        },
        modifier = modifier,
        update = { mapView ->
            // Actualizar marcadores si cambian las tiendas
            mapView.overlays.clear()
            
            stores.forEach { store ->
                val marker = Marker(mapView)
                marker.position = GeoPoint(store.latitude, store.longitude)
                marker.title = store.name
                marker.snippet = store.address
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                
                val drawable = ContextCompat.getDrawable(mapView.context, android.R.drawable.ic_menu_mylocation)
                if (drawable != null) {
                    marker.icon = drawable
                }
                
                mapView.overlays.add(marker)
            }
            
            if (hasLocationPermission && userLocation != null) {
                val myLocationOverlay = MyLocationNewOverlay(mapView)
                myLocationOverlay.enableMyLocation()
                mapView.overlays.add(myLocationOverlay)
            }
            
            mapView.invalidate()
        }
    )
}

@Composable
fun StoreCard(
    store: Store,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Nombre de la tienda
            Text(
                text = store.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
            
            // Dirección
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Dirección",
                    tint = Color.Black.copy(alpha = 0.6f),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = store.address,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black.copy(alpha = 0.7f),
                    modifier = Modifier.weight(1f)
                )
            }
            
            // Teléfono
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Teléfono",
                    tint = Color.Black.copy(alpha = 0.6f),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = store.phone,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black.copy(alpha = 0.7f)
                )
            }
            
            // Horario
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = "Horario",
                    tint = Color.Black.copy(alpha = 0.6f),
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = store.schedule,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black.copy(alpha = 0.7f),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

private fun getUserLocation(
    context: Context,
    fusedLocationClient: com.google.android.gms.location.FusedLocationProviderClient,
    onLocationObtained: (Location?) -> Unit
) {
    try {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            onLocationObtained(location)
        }
    } catch (e: SecurityException) {
        // Permiso no concedido
        onLocationObtained(null)
    }
}

