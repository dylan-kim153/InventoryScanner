package com.dylankim.inventoryscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dylankim.inventoryscanner.data.local.InventoryDatabase
import com.dylankim.inventoryscanner.data.repository.CompanyRepository
import com.dylankim.inventoryscanner.data.repository.InventoryRecordRepository
import com.dylankim.inventoryscanner.data.repository.LocationRepository
import com.dylankim.inventoryscanner.data.repository.ProductRepository
import com.dylankim.inventoryscanner.ui.barcodescanner.BarcodeScannerScreen
import com.dylankim.inventoryscanner.ui.company.CompanyScreen
import com.dylankim.inventoryscanner.ui.inventory.InventoryScreen
import com.dylankim.inventoryscanner.ui.inventoryresult.InventoryResultScreen
import com.dylankim.inventoryscanner.ui.product.ProductRemoteScreen
import com.dylankim.inventoryscanner.ui.theme.InventoryScannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                                navController = navController
                            )
                        }
                        //재고조사화면
                        composable("inventory/{companyId}/{locationId}/{locationNumber}"){ backStackEntry ->
                            val companyId =
                                backStackEntry.arguments?.getString("companyId")?.toLongOrNull() ?: return@composable

                            val locationId =
                                backStackEntry.arguments?.getString("locationId")?.toLongOrNull() ?: return@composable

                            val locationNumber =
                                backStackEntry.arguments?.getString("locationNumber") ?: return@composable

                            InventoryScreen(
                                companyId = companyId,
                                locationId = locationId,
                                locationNumber = locationNumber,
                                navController = navController
                            )
                        }
                        //카메라 바코드 스캔 화면
                        composable("barcodeScanner") {
                            BarcodeScannerScreen(
                                onBarcodeScanned = { barcode ->
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("scannedBarcode", barcode)

                                    navController.popBackStack()
                                }
                            )
                        }
                        //재고조사 결과 화면
                        composable("inventoryresult/{companyId}") { backStackEntry ->

                            val companyId =
                                backStackEntry.arguments
                                    ?.getString("companyId")
                                    ?.toLongOrNull()
                                    ?: return@composable

                            InventoryResultScreen(
                                companyId = companyId,
                                navController = navController
                            )

                        }
                        //상품마스터 Download
                        composable("productRemote") {
                            ProductRemoteScreen()
                        }
                    }
                }
            }
        }
    }
}