package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.classes.SmartMotorController;

/**
 * Handles the robot's launch flywheel and feeder motor.
 */
public class LaunchSubsystem extends SubsystemBase {
  /**
   * The flywheel motor controller.
   */
  private final SmartMotorController m_flywheel;

  /**
   * Initialize the motors.
   */
  public LaunchSubsystem(SmartMotorController flywheel) {
    m_flywheel = flywheel;
  }

  /**
   * Generate a command to enable the flywheel motor.
   *
   * @return The command.
   */
  public Command flywheelOn() {
    return runOnce(() -> m_flywheel.set(1.0));
  }

  /**
   * Generate a command to disable the flywheel motor.
   *
   * @return The command.
   */
  public Command flywheelOff() {
    return runOnce(() -> m_flywheel.set(0.0));
  }
}
