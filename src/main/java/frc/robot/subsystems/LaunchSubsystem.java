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
   * The feed motor controller.
   */
  private final SmartMotorController m_feeder;

  /**
   * Initialize the motors.
   */
  public LaunchSubsystem(SmartMotorController flywheel, SmartMotorController feeder) {
    m_flywheel = flywheel;
    m_feeder = feeder;
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

  /**
   * Generate a command to toggle the flywheel motor.
   *
   * @return The command.
   */
  public Command flywheelToggle() {
    return runOnce(() -> m_flywheel.toggle());
  }

  /**
   * Generate a command to enable the feed motor.
   *
   * @return The command.
   */
  public Command feederOn() {
    return runOnce(() -> m_feeder.set(1.0));
  }

  /**
   * Generate a command to disable the feed motor.
   *
   * @return The command.
   */
  public Command feederOff() {
    return runOnce(() -> m_feeder.set(0.0));
  }

  /**
   * Generate a command to enable the flywheel motor.
   *
   * @return The command.
   */
  public Command feederToggle() {
    return runOnce(() -> m_feeder.toggle());
  }
}
