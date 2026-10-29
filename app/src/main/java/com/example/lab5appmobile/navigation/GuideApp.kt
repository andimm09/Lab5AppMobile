package com.example.lab5appmobile.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.lab5appmobile.GuideViewModel
import com.example.lab5appmobile.R
import com.example.lab5appmobile.data.Category
import com.example.lab5appmobile.data.PlacesRepository
import com.example.lab5appmobile.ui.screens.AboutScreen
import com.example.lab5appmobile.ui.screens.FavoritesScreen
import com.example.lab5appmobile.ui.screens.LoginScreen
import com.example.lab5appmobile.ui.screens.PlaceDetailScreen
import com.example.lab5appmobile.ui.screens.PlaceListScreen
import kotlin.reflect.KClass

private data class TopLevelTab(
    val route: Any,
    val routeClass: KClass<*>,
    val label: String,
    val icon: ImageVector,
)

private val tabs = listOf(
    TopLevelTab(CategoriesGraph, CategoriesGraph::class, "Categorii", Icons.Filled.Place),
    TopLevelTab(Favorites, Favorites::class, "Favorite", Icons.Filled.Favorite),
    TopLevelTab(About, About::class, "Despre", Icons.Filled.Info),
)

@Composable
fun GuideApp(
    appName: String,
    version: String,
    viewModel: GuideViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    // Bara inferioară apare doar după autentificare.
    val showBottomBar = currentDestination != null && !currentDestination.hasRoute(Login::class)

    val filterName by viewModel.filter.collectAsStateWithLifecycle()
    val favoriteIds by viewModel.favoriteIds.collectAsStateWithLifecycle()
    val selectedCategory = filterName?.let { Category.valueOf(it) }

    Scaffold(
        // Fiecare ecran își gestionează singur inset-urile (are propriul TopAppBar).
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController, currentDestination)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Login,
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
        ) {
            composable<Login> {
                LoginScreen(
                    backgroundRes = R.drawable.img_login,
                    onLoginSuccess = {
                        navController.navigate(CategoriesGraph) {
                            // Login dispare din stivă: Back de pe listă închide aplicația.
                            popUpTo<Login> { inclusive = true }
                        }
                    },
                )
            }

            navigation<CategoriesGraph>(startDestination = PlaceList) {
                composable<PlaceList> {
                    PlaceListScreen(
                        places = PlacesRepository.places,
                        selectedCategory = selectedCategory,
                        onCategorySelected = viewModel::setFilter,
                        onNavigateToDetail = { id -> navController.navigate(PlaceDetail(id)) },
                    )
                }
                composable<PlaceDetail> { entry ->
                    val route = entry.toRoute<PlaceDetail>()
                    PlaceDetailScreen(
                        place = PlacesRepository.getPlace(route.id),
                        isFavorite = route.id in favoriteIds,
                        onToggleFavorite = { viewModel.toggleFavorite(route.id) },
                        onBack = { navController.popBackStack() },
                    )
                }
            }

            composable<Favorites> {
                FavoritesScreen(
                    favorites = favoriteIds.mapNotNull { PlacesRepository.getPlace(it) },
                    onRemoveFavorite = viewModel::removeFavorite,
                )
            }

            composable<About> {
                AboutScreen(appName = appName, version = version, headerRes = R.drawable.img_about)
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    NavigationBar {
        tabs.forEach { tab ->
            // hierarchy: în PlaceDetail rămâne selectat tab-ul Categorii (graful părinte).
            val selected = currentDestination?.hierarchy?.any { it.hasRoute(tab.routeClass) } == true
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(tab.route) {
                        // Login a fost scos din stivă, deci lista (startul grafului Categorii)
                        // este baza stivei; echivalentul lui graph.findStartDestination().
                        popUpTo<PlaceList> { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(tab.icon, contentDescription = null) },
                label = { Text(tab.label) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    }
}
