package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Handles the robot's pneumatics system.
 */
public class PneumaticsSubsystem extends SubsystemBase {
  /**
   * The air compressor.
   */
  private final Compressor m_compressor;

  /**
   * The solenoid the tilts the climbing mechanism.
   */
  private final Solenoid m_climb;

  /**
   * The solenoid that opens and closes the intake.
   */
  private final DoubleSolenoid m_intake;

  /**
   * Initialize the pneumatics system and its components.
   * Also enable the compressor.
   * 
   * @param pcmId The CAN ID of the PCM.
   */
  public PneumaticsSubsystem(int pcmId, PneumaticsModuleType pcmType) {
    m_compressor = new Compressor(pcmId, pcmType);

    m_climb = new Solenoid(pcmId, pcmType, 0);
    m_intake = new DoubleSolenoid(pcmId, pcmType, 1, 2);

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
