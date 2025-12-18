package presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.viewmodel.TemiViewModel



@Composable
fun TemiScreen(
    viewModel: TemiViewModel
) {

    Scaffold { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F4F7)) // color suave
                .padding(paddingValues)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // TÍTULO
                Text(
                    text = "Control de navegación",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1F2933)
                )

                Spacer(modifier = Modifier.height(48.dp))

                // BOTÓN
                Button(
                    onClick = {
                        viewModel.onNavigateToEUPTBikes()
                    },
                    modifier = Modifier
                        .height(72.dp)
                        .width(320.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Ir a EuptBikes",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                // BOTÓN Base de cargca
                Button(
                    onClick = {
                        viewModel.onNavigateToHome()
                    },
                    modifier = Modifier
                        .height(72.dp)
                        .width(320.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Ir a la base",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                // BOTÓN Base de cargca
                Button(
                    onClick = {
                        viewModel.onNavigateToDoor()
                    },
                    modifier = Modifier
                        .height(72.dp)
                        .width(320.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Ir a la puerta",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
