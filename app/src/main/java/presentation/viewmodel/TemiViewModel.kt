package presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robotemi.sdk.TtsRequest
import config.TemiConstants
import domain.robot.RobotController
import domain.ui.TemiUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TemiViewModel(private val robotController: RobotController) : ViewModel() {

    fun onNavigateToHome() {
        //Indicamos al robot que vaya a la ubicacion Home
        robotController.goToLocation(TemiConstants.HOME_BASE)
    }

    fun onNavigateToEUPTBikes() {
        //Indicamos al robot que vaya a la ubicacion EUPT Bikes
        robotController.goToLocation(TemiConstants.EUPT_BIKES)
    }

    fun onNavigateToDoor() {
        //Indicamos al robot que vaya a la ubicacion Puerta Laboratorio
        robotController.goToLocation(TemiConstants.DOOR)
    }

    fun onArrivedAtEUPTBikes() {
        //Hacemos que Temi diga algo al llegar a la ubicacion
        robotController.speakText("Estamos en el punto de EUPT Bikes")

    }

    fun onArrivedAtDoor(){
        //Hacemos que Temi diga algo al llegar a la ubicacion
        robotController.speakText("Ahora en la puerta que necesitas?")
    }

    fun onErrorDuringNavigation(){
        //Hacemos que Temi diga algo si hay un error durante la navegacion
        robotController.speakText("Ha ocurrido un error durante la navegación")
    }

    //Navegacion
    private val _uiEvent = MutableSharedFlow<TemiUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onNavigateToVoiceCommands() {
        viewModelScope.launch {
            _uiEvent.emit(TemiUiEvent.NavigateToVoiceCommands)
        }
    }
}