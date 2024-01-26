package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.lib.SmartMotorController.SmartMotorControllerGroup;

import com.revrobotics.CANSparkMax;

/**
 * Handles the robot's launch flywheel and feeder motor.
 */
public class ClimbSubsystem extends SubsystemBase {
  /**
   * The pair of motor controllers.
   */
  private final SmartMotorControllerGroup<CANSparkMax> m_controllers;

  /**
   * Initialize the motors.
   */
  public ClimbSubsystem(SmartMotorControllerGroup<CANSparkMax> controllers) {
    m_controllers = controllers;
  }

  /**
   * Set the climber velocity immediately to a value.
   */
  public void set(double velocity) {
    m_controllers.forceTo(velocity);
  }

  public Command climberStop() {
    return runOnce(() -> m_controllers.forceTo(0));
  }

  public Command climberExtend() {
    return runOnce(() -> m_controllers.forceTo(1));
  }

  public Command climberRetract() {
    return runOnce(() -> m_controllers.forceTo(-1));
  }
}
