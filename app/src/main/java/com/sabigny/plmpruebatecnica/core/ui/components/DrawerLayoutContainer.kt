package com.sabigny.plmpruebatecnica.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.sabigny.plmpruebatecnica.navigation.NavigationRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerLayoutContainer(
    navController: NavHostController,
    currentRoute: String,
    screenTitle: String,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()


    val systemBarsPadding = WindowInsets.systemBars.asPaddingValues()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Añadimos padding al drawer para respetar los insets del sistema
            Box(modifier = Modifier.padding(top = systemBarsPadding.calculateTopPadding())) {
                DrawerContent(
                    currentRoute = currentRoute,
                    onSearchClick = {
                        if (currentRoute != NavigationRoute.Search.route) {
                            navController.navigate(NavigationRoute.Search.route) {
                                popUpTo(NavigationRoute.Search.route) { inclusive = true }
                            }
                        }
                        scope.launch { drawerState.close() }
                    },
                    onDeveloperClick = {
                        if (currentRoute != NavigationRoute.Developer.route) {
                            navController.navigate(NavigationRoute.Developer.route)
                        }
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(screenTitle) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir menú"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    }
}