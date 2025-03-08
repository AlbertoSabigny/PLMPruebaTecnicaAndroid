package com.sabigny.plmpruebatecnica.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.sabigny.plmpruebatecnica.navigation.NavigationRoute

@Composable
fun DrawerContent(
    currentRoute: String,
    onSearchClick: () -> Unit,
    onDeveloperClick: () -> Unit
) {
    val statusBarHeight = with(LocalDensity.current) {
        WindowInsets.statusBars.getTop(this).toDp()
    }

    Surface(
        modifier = Modifier
            .width((LocalConfiguration.current.screenWidthDp * 0.75).dp)
            .fillMaxHeight(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp + statusBarHeight, bottom = 16.dp)
        ) {
            Text(
                text = "Menú",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Divider(modifier = Modifier.padding(bottom = 16.dp))

            DrawerItem(
                title = "Búsqueda",
                icon = Icons.Default.Search,
                isSelected = currentRoute == NavigationRoute.Search.route,
                onClick = onSearchClick
            )

            DrawerItem(
                title = "Desarrollador",
                icon = Icons.Default.AccountBox,
                isSelected = currentRoute == NavigationRoute.Developer.route,
                onClick = onDeveloperClick
            )
        }
    }
}

@Composable
fun DrawerItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val textColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = textColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            color = textColor
        )
    }
}