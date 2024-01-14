package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Handles the robot's launch flywheel and feeder motor.
 */
public class LaunchSubsystem extends SubsystemBase {
  /**
   * The flywheel motor controller.
   */
  private final Spark m_flywheelController;

  /**
   * The velocity at which the flywheel motor should run.
   */
  private final double m_flywheelSpeed;

  /**
   * Initialize the motors and their operating velocities.
   *
   * @param flywheelChannel The PWM channel of the motor controller.
   */
  public LaunchSubsystem(int flywheelChannel, double flywheelSpeed, boolean flywheelInvert) {
    m_flywheelController = new Spark(flywheelChannel);
    m_flywheelSpeed = flywheelInvert ? 0 - flywheelSpeed : flywheelSpeed;
  }

  /**
   * Generate a command to enable the flywheel motor.
   *
   * @return The command.
   */
  public Command enable() {
    return runOnce(() -> m_flywheelController.set(m_flywheelSpeed));
  }

  /**
   * Generate a command to disable the flywheel motor.
   *
   * @return The command.
   */
  public Command disable() {
    return runOnce(() -> m_flywheelController.set(0));
  }
}
