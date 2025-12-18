package di

import com.robotemi.sdk.Robot
import domain.robot.RobotController
import infraestructure.robot.TemiRobotController
import presentation.viewmodel.TemiViewModel

object AppModule {

    fun provideRobotController(): RobotController {
        return TemiRobotController(Robot.getInstance())
    }

    fun provideTemiViewModel(): TemiViewModel {
        return TemiViewModel(provideRobotController())
    }
}
