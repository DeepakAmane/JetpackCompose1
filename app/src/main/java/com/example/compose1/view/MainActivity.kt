package com.example.compose1.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.compose1.ui.theme.DeepsCompose1Theme
import com.example.compose1.viewmodel.EmployeeListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: EmployeeListViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // old way of creating the viewModel instance
        /*val viewModel: EmployeeListViewModel =
            ViewModelProvider(this)[EmployeeListViewModel::class.java]*/

        enableEdgeToEdge()
        setContent {
            DeepsCompose1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "Jetpack Compose",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color(0xFFFF9800)
                            )
                        )
                    }) { innerPadding ->
                    EmployeeList(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun EmployeeList(
    viewModel: EmployeeListViewModel,
    modifier: Modifier = Modifier
) {
    val employees by viewModel.employeeList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    /**
     *  contentPadding = PaddingValues(vertical = 8.dp)
     *  Adds 8.dp space at the top and bottom of the entire list.
     *
     *  verticalArrangement = Arrangement.spacedBy(8.dp)
     *  Adds 8.dp space between each card (like a gap).
     * */

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp), //  ensures padding at top & bottom
            verticalArrangement = Arrangement.spacedBy(8.dp) //  spacing between items
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

        //  Overlay the loading spinner
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.6f)) // optional dim background
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

@Composable
fun EmployeeCard(
    imageUrl: String,
    name: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image inside a Box that takes 20% of the Row width
            Box(
                modifier = Modifier
                    .weight(0.2f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(56.dp) // Ensures circular shape
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(
                modifier = Modifier
                    .width(16.dp)
                    .background(Color.Yellow)
            )

            // Column for text takes 80% of the Row width
            Column(
                modifier = Modifier.weight(0.8f),
                verticalArrangement = Arrangement.Center
            ) {

                val isDark = isSystemInDarkTheme()
                val textColor = if (isDark) Color.White else Color.Black

                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineLarge.copy(lineHeight = 18.sp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFFFF9800)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge.copy(lineHeight = 18.sp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Thin,
                    color = textColor
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
        //  EmployeeList(employees = getEmployees())
    }
}

