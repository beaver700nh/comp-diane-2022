package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Handles the robot's drive train.
 */
public class DriveSubsystem extends SubsystemBase {
  /**
   * The subcontrollers for the left and right drive trains.
   */
  private final DriveConfig m_configL, m_configR;

  /**
   * Initialize the drive trains to a stopped state.
   *
   * @param configL The subcontroller for the left drive train.
   * @param configR The subcontroller for the right drive train.
   */
  public DriveSubsystem(DriveConfig configL, DriveConfig configR) {
    m_configL = configL;
    m_configR = configR;
    forceTo(0, 0);
  }

  /**
   * Set the robot velocity immediately using the given values.
   *
   * @param x The requested movement rate.
   * @param r The requested turning rate.
   */
  public void forceTo(double x, double r) {
    m_configL.getController().set(x - r);
    m_configR.getController().set(x + r);
  }

  /**
   * Request the robot to drive using the given velocity values.
   *
   * @param x The requested movement rate.
   * @param r The requested turning rate.
   */
  public void accelTo(double x, double r) {
    m_configL.accelTo(x - r);
    m_configR.accelTo(x + r);
  }
}
