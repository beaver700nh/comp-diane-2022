package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.lib.SmartMotorController.SmartMotorControllerGroup;

import frc.robot.Constants.*;

/**
 * Handles the robot's drive train.
 */
public class DriveSubsystem extends SubsystemBase {
  /**
   * The subcontrollers for the left and right drive trains.
   */
  private final SmartMotorControllerGroup<WPI_TalonSRX> m_trainL, m_trainR;

  /**
   * Initialize the drive trains to a stopped state.
   *
   * @param trainL The subcontroller for the left drive train.
   * @param trainR The subcontroller for the right drive train.
   */
  public DriveSubsystem(SmartMotorControllerGroup<WPI_TalonSRX> trainL, SmartMotorControllerGroup<WPI_TalonSRX> trainR) {
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
    m_trainL.forceTo(DriveConstants.kGainLeft.apply(x + r));
    m_trainR.forceTo(DriveConstants.kGainRight.apply(x - r));
  }

  /**
   * Request the robot to drive using the given velocity values.
   *
   * @param x The requested movement rate.
   * @param r The requested turning rate.
   */
  public void accelTo(double x, double r) {
    m_trainL.accelTo(DriveConstants.kGainLeft.apply(x + r));
    m_trainR.accelTo(DriveConstants.kGainRight.apply(x - r));
  }

  /**
   * Request the robot to drive tank-style.
   * 
   * @param l The requested speed of the left drivetrain).
   * @param r The requested speed of the right drivetrain).
   */
  public void tankDrive(double l, double r) {
    m_trainL.accelTo(l);
    m_trainR.accelTo(r);
  }
}
