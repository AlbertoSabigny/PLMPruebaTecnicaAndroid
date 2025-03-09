package com.sabigny.plmpruebatecnica.auth.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sabigny.plmpruebatecnica.auth.ui.components.CustomButton
import com.sabigny.plmpruebatecnica.auth.ui.components.CustomDropdown
import com.sabigny.plmpruebatecnica.auth.ui.components.CustomTextField
import androidx.compose.ui.focus.FocusRequester

@Composable
fun RegistrationScreen(
    onRegistrationComplete: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val emailFocus = remember { FocusRequester() }
    val firstNameFocus = remember { FocusRequester() }
    val lastNameFocus = remember { FocusRequester() }
    val maternalLastNameFocus = remember { FocusRequester() }
    val phoneFocus = remember { FocusRequester() }
    val professionalLicenseFocus = remember { FocusRequester() }

    if (state.registrationCompleted) {
        LaunchedEffect(Unit) {
            onRegistrationComplete()
        }
    }

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Registro",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Email:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(120.dp)
                    )
                    CustomTextField(
                        value = state.email,
                        onValueChange = { viewModel.onEvent(RegistrationEvent.EmailChanged(it)) },
                        label = "Email",
                        keyboardType = KeyboardType.Email,
                        errorMessage = state.emailError,
                        focusRequester = emailFocus,
                        onNext = { firstNameFocus.requestFocus() },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Nombre:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(120.dp)
                    )
                    CustomTextField(
                        value = state.firstName,
                        onValueChange = { viewModel.onEvent(RegistrationEvent.FirstNameChanged(it)) },
                        label = "Nombre",
                        errorMessage = state.firstNameError,
                        focusRequester = firstNameFocus,
                        onNext = { lastNameFocus.requestFocus() },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "A. Paterno:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(120.dp)
                    )
                    CustomTextField(
                        value = state.lastName,
                        onValueChange = { viewModel.onEvent(RegistrationEvent.LastNameChanged(it)) },
                        label = "Apellido Paterno",
                        errorMessage = state.lastNameError,
                        focusRequester = lastNameFocus,
                        onNext = { maternalLastNameFocus.requestFocus() },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "A. Materno:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(120.dp)
                    )
                    CustomTextField(
                        value = state.maternalLastName,
                        onValueChange = { viewModel.onEvent(RegistrationEvent.MaternalLastNameChanged(it)) },
                        label = "Apellido Materno",
                        errorMessage = state.maternalLastNameError,
                        focusRequester = maternalLastNameFocus,
                        onNext = { phoneFocus.requestFocus() },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Teléfono:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(120.dp)
                    )
                    CustomTextField(
                        value = state.phone,
                        onValueChange = { viewModel.onEvent(RegistrationEvent.PhoneChanged(it)) },
                        label = "Teléfono",
                        keyboardType = KeyboardType.Number,
                        errorMessage = state.phoneError,
                        focusRequester = phoneFocus,
                        onNext = {
                            if (state.isProfessionalLicenseRequired) {
                                professionalLicenseFocus.requestFocus()
                            } else {
                                viewModel.onEvent(RegistrationEvent.SubmitRegistration)

                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Profesión:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(120.dp)
                    )
                    CustomDropdown(
                        options = state.professions,
                        selectedOption = state.selectedProfession,
                        onOptionSelected = { viewModel.onEvent(RegistrationEvent.ProfessionSelected(it)) },
                        label = "Profesión",
                        displayText = { it.name },
                        errorMessage = state.professionError,
                        isLoading = state.isLoadingProfessions,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                AnimatedVisibility(
                    visible = state.isProfessionalLicenseRequired,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cédula:",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.width(120.dp)
                        )
                        CustomTextField(
                            value = state.professionalLicense,
                            onValueChange = { viewModel.onEvent(RegistrationEvent.ProfessionalLicenseChanged(it)) },
                            label = "Cédula Profesional",
                            errorMessage = state.professionalLicenseError,
                            focusRequester = professionalLicenseFocus,
                            onNext = { viewModel.onEvent(RegistrationEvent.SubmitRegistration) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "País:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(120.dp)
                    )
                    CustomDropdown(
                        options = state.countries,
                        selectedOption = state.selectedCountry,
                        onOptionSelected = { viewModel.onEvent(RegistrationEvent.CountrySelected(it)) },
                        label = "País",
                        displayText = { it.name },
                        errorMessage = state.countryError,
                        isLoading = state.isLoadingCountries,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Estado:",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.width(120.dp)
                    )
                    CustomDropdown(
                        options = state.states,
                        selectedOption = state.selectedState,
                        onOptionSelected = { viewModel.onEvent(RegistrationEvent.StateSelected(it)) },
                        label = "Estado",
                        displayText = { it.name },
                        errorMessage = state.stateError,
                        isLoading = state.isLoadingStates,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                CustomButton(
                    text = "Registrarse",
                    onClick = { viewModel.onEvent(RegistrationEvent.SubmitRegistration) },
                    enabled = !state.isSubmitting,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }

            if (state.error != null) {
                item {
                    Text(
                        text = state.error,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}