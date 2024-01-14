package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.classes.SmartMotorControllerGroup;

/**
 * Handles the robot's drive train.
 */
public class DriveSubsystem extends SubsystemBase {
  /**
   * The subcontrollers for the left and right drive trains.
   */
  private final SmartMotorControllerGroup m_trainL, m_trainR;

  /**
   * Initialize the drive trains to a stopped state.
   *
   * @param trainL The subcontroller for the left drive train.
   * @param trainR The subcontroller for the right drive train.
   */
  public DriveSubsystem(SmartMotorControllerGroup trainL, SmartMotorControllerGroup trainR) {
    m_trainL = trainL;
    m_trainR = trainR;
    forceTo(0, 0);
  }

  /**
   * Set the robot velocity immediately using the given values.
   *
   * @param x The requested movement rate.
   * @param r The requested turning rate.
   */
  public void forceTo(double x, double r) {
    m_trainL.forceTo(x - r);
    m_trainR.forceTo(x + r);
  }

  /**
   * Request the robot to drive using the given velocity values.
   *
   * @param x The requested movement rate.
   * @param r The requested turning rate.
   */
  public void accelTo(double x, double r) {
    m_trainL.accelTo(x - r);
    m_trainR.accelTo(x + r);
  }
}
