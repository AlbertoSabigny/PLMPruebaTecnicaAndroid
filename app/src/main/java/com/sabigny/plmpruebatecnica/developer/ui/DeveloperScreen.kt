package com.sabigny.plmpruebatecnica.developer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sabigny.plmpruebatecnica.developer.ui.components.DeveloperCard

@Composable
fun DeveloperScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DeveloperCard(
            name = "Alberto Tovar Ramirez",
            email = "Alberto.tovar.dev@gmail.com",
            phone = "+52 55 1764 3861",
            role = "Desarrollador Móvil",
            location = "CDMX, México"
        )
    }
}




