package presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.robotemi.sdk.Robot
import presentation.ui.VoiceCommandsScreen

class VoiceCommandsActivity : ComponentActivity() {

    private val robot by lazy { Robot.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VoiceCommandsScreen(
                robot = robot,
                onBack = { finish() }
                )
        }
    }
}
