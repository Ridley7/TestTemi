package infraestructure.robot

import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest
import domain.robot.RobotController

class TemiRobotController (private val robot : Robot) : RobotController{
    override fun goToLocation(locationName: String) {
        robot.goTo(locationName);
    }

    override fun stopMovement() {
        robot.stopMovement();
    }

    override fun speakText(text: String) {
        val ttsRequest = TtsRequest.create(
            speech = text,
            language = TtsRequest.Language.ES_ES
        )

        robot.speak(ttsRequest)
    }

}