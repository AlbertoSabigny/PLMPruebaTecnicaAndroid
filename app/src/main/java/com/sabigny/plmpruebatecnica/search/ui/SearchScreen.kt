package com.sabigny.plmpruebatecnica.search.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sabigny.plmpruebatecnica.search.domain.model.Drug
import com.sabigny.plmpruebatecnica.search.ui.componets.DrugItem
import com.sabigny.plmpruebatecnica.search.ui.componets.SearchBar

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.onEvent(SearchEvent.OnSearchQueryChange(it)) },
            onSearch = { viewModel.onEvent(SearchEvent.OnSearchSubmit) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is SearchState.Idle -> Text(
                text = "Ingrese un medicamento para buscar",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            is SearchState.Loading -> CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            is SearchState.Success -> DrugList((state as SearchState.Success).drugs)

            is SearchState.Empty -> Text(
                text = "No se encontraron medicamentos",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            is SearchState.ServerUnavailable -> Column {
                Text(
                    text = "El servidor no está disponible actualmente. Se muestra una lista de muestra. Intente nuevamente más tarde.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                DrugList(getMockDrugs())
            }

            is SearchState.Error -> Text(
                text = "⚠️ Error desconocido",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

fun getMockDrugs(): List<Drug> {
    return listOf(
        Drug("Paracetamol", "Tableta"),
        Drug("Ibuprofeno", "Cápsula"),
        Drug("Aspirina", "Tableta"),
        Drug("Omeprazol", "Cápsula")
    )
}


@Composable
fun DrugList(drugs: List<Drug>) {
    LazyColumn {
        items(drugs) { drug ->
            DrugItem(drug)
        }
    }
}

