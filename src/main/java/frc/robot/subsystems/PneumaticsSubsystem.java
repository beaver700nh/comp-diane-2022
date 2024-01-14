package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.*;

/**
 * Handles the robot's pneumatics system.
 */
public class PneumaticsSubsystem extends SubsystemBase {
  /**
   * The air compressor.
   */
  private final Compressor m_compressor = new Compressor(
    PneumaticsConstants.CAN.kPCM, PneumaticsModuleType.CTREPCM
  );

  /**
   * The solenoid the tilts the climbing mechanism.
   */
  private final Solenoid m_climb = new Solenoid(
    PneumaticsConstants.CAN.kPCM, PneumaticsModuleType.CTREPCM, 0
  );

  /**
   * The solenoid that opens and closes the intake.
   */
  private final DoubleSolenoid m_intake = new DoubleSolenoid(
    PneumaticsConstants.CAN.kPCM, PneumaticsModuleType.CTREPCM, 1, 2
  );

  /**
   * Initialize the pneumatics system and enable the compressor.
   */
  public PneumaticsSubsystem() {
    m_compressor.enableDigital();
  }

  /**
   * Generate a command to open the intake.
   *
   * @return The command.
   */
  public Command intakeOpen() {
    return runOnce(() -> m_intake.set(Value.kForward));
  }

  /**
   * Generate a command to close the intake.
   *
   * @return The command.
   */
  public Command intakeClose() {
    return runOnce(() -> m_intake.set(Value.kReverse));
  }

  /**
   * Generate a command to tilt the climbing mechanism.
   *
   * @return The command.
   */
  public Command climbOpen() {
    return runOnce(() -> m_climb.set(true));
  }

  /**
   * Generate a command to right the climbing mechanism.
   *
   * @return The command.
   */
  public Command climbClose() {
    return runOnce(() -> m_climb.set(false));
  }
}
