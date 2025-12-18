package domain.robot

interface RobotController {
    fun goToLocation(locationName: String)
    fun stopMovement()
    fun speakText(text: String)
}