package presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.robotemi.sdk.Robot
import com.robotemi.sdk.SttLanguage
import com.robotemi.sdk.TtsRequest
import com.robotemi.sdk.listeners.OnConversationStatusChangedListener
import config.TemiConstants


@Composable
fun VoiceCommandsScreen(
    robot: Robot,
    onBack: () -> Unit
) {

    var asrState by remember { mutableStateOf(0) }
    var lastText by remember { mutableStateOf("TEst") }
    var backgroundColor by remember { mutableStateOf(Color.DarkGray) }

    DisposableEffect(Unit) {


        val conversationListener = object : OnConversationStatusChangedListener {
            override fun onConversationStatusChanged(status: Int, text: String) {

                when (status) {
                    OnConversationStatusChangedListener.THINKING -> {
                        // Frase final reconocida
                        val command = text.lowercase()
                        lastText = text


                        when {
                            command.contains("base") -> robot.goTo(TemiConstants.HOME_BASE)
                            command.contains("puerta") -> {
                                backgroundColor = Color(0xFF3B82F6)

                                val ttsRequest = TtsRequest.create(
                                    speech = "Voy a la puerta",
                                    isShowOnConversationLayer = false,
                                    language = TtsRequest.Language.ES_ES
                                )

                                robot.goTo(TemiConstants.DOOR)

                                robot.speak(ttsRequest)


                            }
                            command.contains("para") -> robot.stopMovement()
                        }
                    }

                    OnConversationStatusChangedListener.LISTENING -> {
                        // Temi está escuchando

                    }

                    OnConversationStatusChangedListener.SPEAKING -> {
                        // Temi está hablando
                    }

                    OnConversationStatusChangedListener.IDLE -> {
                        // Conversación terminada
                    }
                }
            }
        }


        robot.addOnConversationStatusChangedListener(conversationListener)

        onDispose {
            robot.removeOnConversationStatusChangedListener(conversationListener)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .safeDrawingPadding()
    ) {

        Button(
            onClick = { robot.wakeup() },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Hablar")
        }

        Text(
            text = lastText,
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Color.White,
            fontSize = 20.sp
        )

        Button(
            onClick = onBack,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Text("Volver")
        }
    }
}



/*
@Composable
fun VoiceCommandsScreen(
    robot: Robot,
    onBack: () -> Unit
) {

    var lastCommand by remember { mutableStateOf("Di un comando") }
    var backgroundColor by remember {mutableStateOf(Color.DarkGray)}

    DisposableEffect(Unit) {

        //robot.wakeup()
        robot.setKioskModeOn(true)
        val asrListener = object : Robot.AsrListener {

            override fun onAsrResult(asrResult: String, sttLanguage: SttLanguage) {
                val command = asrResult.lowercase()
                lastCommand = asrResult

                when {
                    command.contains("modo base") -> {
                        backgroundColor = Color(0xFF10B981)
                        robot.speak(TtsRequest.create("Voy a la base", false))
                        robot.goTo(TemiConstants.HOME_BASE)
                    }

                    command.contains("modo puerta") -> {
                        backgroundColor = Color(0xFF3B82F6)

                        val ttsRequest = TtsRequest.create(
                            speech = "Voy a la puerta",
                            isShowOnConversationLayer = false,
                            language = TtsRequest.Language.ES_ES
                        )

                        robot.speak(ttsRequest)

                        robot.goTo(TemiConstants.DOOR)
                    }

                    command.contains("modo para") -> {
                        backgroundColor = Color(0xFFEF4444)
                        robot.speak(TtsRequest.create("Me detengo", false))
                        robot.stopMovement()
                    }

                    else -> {
                        backgroundColor = Color(0xFFF59E0B)
                        robot.speak(
                            TtsRequest.create(
                                "No he entendido el comando",
                                false
                            )
                        )
                    }
                }
            }
        }

        robot.addAsrListener(asrListener)

        onDispose {
            robot.removeAsrListener(asrListener)
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        // BOTÓN VOLVER (hijo directo del Box)
        Button(
            onClick = onBack,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Text("Volver")
        }

        // CONTENIDO CENTRAL
        Text(
            text = lastCommand,
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }



}
*/