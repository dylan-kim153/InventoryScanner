package com.dylankim.inventoryscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dylankim.inventoryscanner.data.local.InventoryDatabase
import com.dylankim.inventoryscanner.data.repository.CompanyRepository
import com.dylankim.inventoryscanner.data.repository.LocationRepository
import com.dylankim.inventoryscanner.ui.company.CompanyScreen
import com.dylankim.inventoryscanner.ui.company.CompanyViewModel
import com.dylankim.inventoryscanner.ui.company.CompanyViewModelFactory
import com.dylankim.inventoryscanner.ui.inventory.InventoryScreen
import com.dylankim.inventoryscanner.ui.theme.InventoryScannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = InventoryDatabase.getDatabase(applicationContext)

        val companyRepository = CompanyRepository(
            database.companyDao()
        )

        val locationRepository = LocationRepository(
            database.locationDao()
        )


        val factory = CompanyViewModelFactory(
            companyRepository,
            locationRepository
        )

        enableEdgeToEdge()
        setContent {


            InventoryScannerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "company",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        //셋팅화면
                        composable("company") {
                            CompanyScreen(
                                factory = factory,
                                navController = navController
                            )
                        }
                        //재고조사화면
                        composable("inventory"){
                            InventoryScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InventoryScannerTheme {
        Greeting("Android")
    }
}