package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.lib.SmartMotorController.SmartMotorController;

/**
 * Handles the robot's intake motor.
 */
public class IntakeSubsystem extends SubsystemBase {
  /**
   * The intake motor controller.
   */
  private final SmartMotorController m_intake;

  /**
   * Initialize the intake motor.
   */
  public IntakeSubsystem(SmartMotorController intake) {
    m_intake = intake;
  }

  /**
   * Generate a command to run the intake motor.
   *
   * @return The command.
   */
  public Command intakeIn() {
    return runOnce(() -> m_intake.set(1.0));
  }

  /**
   * Generate a command to run the intake motor in reverse.
   *
   * @return The command.
   */
  public Command intakeOut() {
    return runOnce(() -> m_intake.set(-1.0));
  }

  /**
   * Generate a command to stop the intake motor.
   *
   * @return The command.
   */
  public Command intakeStop() {
    return runOnce(() -> m_intake.set(0.0));
  }
}
