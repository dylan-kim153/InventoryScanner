package com.dylankim.inventoryscanner

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.dylankim.inventoryscanner.data.local.InventoryDatabase
import com.dylankim.inventoryscanner.data.local.entity.Company
import com.dylankim.inventoryscanner.data.local.entity.Location
import com.dylankim.inventoryscanner.ui.theme.InventoryScannerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = InventoryDatabase.getDatabase(applicationContext)

        lifecycleScope.launch {
            val count = database.companyDao().getCount()

            //초기 셋팅 DB Count 후 0 일 경우만
            if (count == 0) {
                database.companyDao().insertAll(
                    listOf(
                        Company(name = "업체A"),
                        Company(name = "업체B")
                    )
                )
                val companies = database.companyDao().getAll()

                //A업체 - 매장,창고
                database.locationDao().insert(
                    Location(
                        companyId = companies[0].id,
                        name = "매장"
                    )
                )
                database.locationDao().insert(
                    Location(
                        companyId = companies[0].id,
                        name = "창고"
                    )
                )
                //B업체 - 매장,창고,외부창고
                database.locationDao().insert(
                    Location(
                        companyId = companies[1].id,
                        name = "매장"
                    )
                )
                database.locationDao().insert(
                    Location(
                        companyId = companies[1].id,
                        name = "창고"
                    )
                )
                database.locationDao().insert(
                    Location(
                        companyId = companies[1].id,
                        name = "외부창고"
                    )
                )

                Log.d("RoomTest", "companies = $companies")

                val companyALocations =
                    database.locationDao().getLocationsByCompanyId(companies[0].id)

                val companyBLocations =
                    database.locationDao().getLocationsByCompanyId(companies[1].id)

                Log.d("RoomTest", "업체A locations = $companyALocations")
                Log.d("RoomTest", "업체B locations = $companyBLocations")

            }


        }

        enableEdgeToEdge()
        setContent {
            InventoryScannerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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