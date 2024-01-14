package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Handles the robot's intake motor.
 */
public class IntakeSubsystem extends SubsystemBase {
  /**
   * The intake motor controller.
   */
  private final Spark m_motorController;

  /**
   * The velocity at which the intake motor should run.
   */
  private final double m_motorSpeed;

  /**
   * Initialize the intake motor and its operating velocity.
   *
   * @param channel The PWM channel of the motor controller.
   */
  public IntakeSubsystem(int channel, double speed, boolean invert) {
    m_motorController = new Spark(channel);
    m_motorSpeed = invert ? 0 - speed : speed;
  }

  /**
   * Generate a command to run the intake motor.
   *
   * @return The command.
   */
  public Command intake() {
    return runOnce(() -> m_motorController.set(m_motorSpeed));
  }

  /**
   * Generate a command to run the intake motor in reverse.
   *
   * @return The command.
   */
  public Command outtake() {
    return runOnce(() -> m_motorController.set(-m_motorSpeed));
  }

  /**
   * Generate a command to stop the intake motor.
   *
   * @return The command.
   */
  public Command stop() {
    return runOnce(() -> m_motorController.set(0));
  }
}
