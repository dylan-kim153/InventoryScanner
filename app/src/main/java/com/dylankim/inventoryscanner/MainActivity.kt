package com.dylankim.inventoryscanner

import android.os.Bundle
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
import com.dylankim.inventoryscanner.ui.theme.InventoryScannerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = InventoryDatabase.getDatabase(applicationContext)

        lifecycleScope.launch {
            val companyId = database.companyDao().insert(
                Company(name = "테스트 업체")
            )
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