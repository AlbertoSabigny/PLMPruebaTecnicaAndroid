package com.sabigny.plmpruebatecnica.search.ui.componets

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sabigny.plmpruebatecnica.search.domain.model.Drug

@Composable
fun DrugItem(drug: Drug) {
    val isDarkTheme = isSystemInDarkTheme()

    val grayColor = if (isDarkTheme) {
        Color(0xFF424242)
    } else {
        Color(0xFFE0E0E0)
    }

    val textColor = if (isDarkTheme) {
        Color.White
    } else {
        Color.Black
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = grayColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = drug.name,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
            Text(
                text = drug.form,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor.copy(alpha = 0.7f)
            )
        }
    }
}