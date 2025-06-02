package com.example.compose1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose1.components.EmptyStateMessage
import com.example.compose1.components.LoadingOverlay
import com.example.compose1.viewmodel.EmployeeListViewModel
import com.example.compose1.viewmodel.EmployeeUiState

@Composable
fun EmployeeList(
    viewModel: EmployeeListViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.employeeUiState.collectAsStateWithLifecycle()

    /**
     *  contentPadding = PaddingValues(vertical = 8.dp)
     *  Adds 8.dp space at the top and bottom of the entire list.
     *
     *  verticalArrangement = Arrangement.spacedBy(8.dp)
     *  Adds 8.dp space between each card (like a gap).
     * */

    Box(modifier = modifier.fillMaxSize()) {

        when (uiState) {
            is EmployeeUiState.Loading -> {
                LoadingOverlay(modifier = Modifier.align(Alignment.Center))
            }

            is EmployeeUiState.Success -> {
                val employees = (uiState as EmployeeUiState.Success).employees

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(employees) { employee ->
                        EmployeeCard(
                            imageUrl = employee.imageUrl,
                            name = employee.name,
                            title = employee.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }

            is EmployeeUiState.Error -> {
                val message = (uiState as EmployeeUiState.Error).message
                EmptyStateMessage(
                    message = message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

