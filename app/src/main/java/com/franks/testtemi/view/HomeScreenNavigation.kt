package com.franks.testtemi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreenNavigation(navController: NavController){
    val items = listOf("Gato", "Perro", "Loro")

    Column (
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text("Pantalla de home")

        Spacer(modifier = Modifier.height(16.dp))

        items.forEach{ item ->
            Button(
                onClick = {navController.navigate("details/$item")},
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ){
                Text("Ver detalles de $item")
            }
        }
    }
}
