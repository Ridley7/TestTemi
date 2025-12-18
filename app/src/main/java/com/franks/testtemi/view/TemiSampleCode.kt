package com.franks.testtemi.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun TemiSampleCode() {
    Scaffold(
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 0.dp, 0.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Button(onClick = {
                        // Acción Exit
                    }) {
                        Text("Exit")
                    }

                    Button(onClick = {
                        // Acción Request Kiosk
                    }) {
                        Text("Request Kiosk")
                    }
                }
            }
        }
    ) { paddingValues ->
        // Aquí va el contenido principal de tu pantalla
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Contenido principal")
        }
    }
}
