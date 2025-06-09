package com.example.compose1.screens.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose1.model.Employee
import com.example.compose1.screens.employees.EmployeeCard
import com.example.compose1.ui.theme.DeepsCompose1Theme

@Composable
fun EmployeeListContent(
    employees: List<Employee>,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {

    /**
     *  contentPadding = PaddingValues(vertical = 8.dp)
     *  Adds 8.dp space at the top and bottom of the entire list.
     *
     *  verticalArrangement = Arrangement.spacedBy(8.dp)
     *  Adds 8.dp space between each card (like a gap).
     * */

    Box(modifier = modifier.fillMaxSize()) {
        if (employees.isNotEmpty()) {

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

        } else if (!isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Connection Error",
                        tint = Color.Gray,
                        modifier = Modifier.size(72.dp)
                    )

                    Text(
                        text = "No response from the server.",
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                }
            }
        }


        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.6f))
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(64.dp),
                    color = Color(0xFFFF9800),
                    strokeWidth = 6.dp
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Pixel 9 Pro XL Preview",
    device = "spec:width=411dp,height=891dp,dpi=440"
)
@Composable
fun EmployeeListView() {
    DeepsCompose1Theme {
        EmployeeListContent(
            employees = listOf(
                Employee("Jack Sparrow", "Software Engineer", "https://example.com/john.jpg"),
                Employee("jane Junior", "Product Manager", "https://example.com/jane.jpg")
            ),
            isLoading = false
        )
    }

}
