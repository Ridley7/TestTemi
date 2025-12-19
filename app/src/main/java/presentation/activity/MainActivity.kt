package presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import presentation.ui.TemiScreen
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener
import com.robotemi.sdk.listeners.OnRobotReadyListener
import config.TemiConstants
import domain.ui.TemiUiEvent
import infraestructure.robot.TemiRobotController
import kotlinx.coroutines.launch
import presentation.viewmodel.TemiViewModel

class MainActivity : ComponentActivity(), OnRobotReadyListener, OnGoToLocationStatusChangedListener
{
    private val robot: Robot by lazy{
        Robot.getInstance()
    }

    private lateinit var viewModel: TemiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inyeccion de dependencias manual, no uso Hilt ni nada por ahora
        val robotController = TemiRobotController(robot)
        viewModel = TemiViewModel(robotController)

        setContent {
            //Esta es la interfaz de Temi
            TemiScreen(viewModel = viewModel)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect { event ->
                    when (event) {

                        TemiUiEvent.NavigateToVoiceCommands -> {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    VoiceCommandsActivity::class.java
                                )
                            )
                        }

                        // otros eventos que ya tengas
                    }
                }
            }
        }


    }

    override fun onStart(){
        super.onStart()
        robot.addOnRobotReadyListener(this);
        robot.addOnGoToLocationStatusChangedListener(this)
    }

    override fun onStop(){
        super.onStop()
        robot.removeOnRobotReadyListener(this);
        robot.removeOnGoToLocationStatusChangedListener(this)
    }

    override fun onGoToLocationStatusChanged(
        location: String,
        status: String,
        descriptionId: Int,
        description: String
    ) {
        if(location == TemiConstants.EUPT_BIKES)
            when(status){
                OnGoToLocationStatusChangedListener.COMPLETE -> {
                    viewModel.onArrivedAtEUPTBikes()
                }

                OnGoToLocationStatusChangedListener.ABORT,
                    OnGoToLocationStatusChangedListener.REPOSING,
                    OnGoToLocationStatusChangedListener.CALCULATING ->{
                        viewModel.onErrorDuringNavigation()
                    }
            }


        if(location == TemiConstants.DOOR)
            when(status){
                OnGoToLocationStatusChangedListener.COMPLETE -> {
                    viewModel.onArrivedAtDoor()

                    //Tenemos que hacer que cambie la interfaz aqui
                }
            }

    }

    override fun onRobotReady(isReady: Boolean) {
        if(isReady){

            //Hacemos que el robot hable cuando este listo
            val ttsRequest = TtsRequest.create(
                speech = "Listo",
                isShowOnConversationLayer = false,
                language = TtsRequest.Language.ES_ES
            )

            //robot.speak(ttsRequest)
        }
    }

}
