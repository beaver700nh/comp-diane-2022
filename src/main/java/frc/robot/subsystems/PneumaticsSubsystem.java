package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticsSubsystem extends SubsystemBase {
  private final Compressor m_compressor = new Compressor(11, PneumaticsModuleType.CTREPCM);
  private final Solenoid m_climb = new Solenoid(11, PneumaticsModuleType.CTREPCM, 0);
  private final DoubleSolenoid m_intake = new DoubleSolenoid(11, PneumaticsModuleType.CTREPCM, 1, 2);

  public PneumaticsSubsystem() {
    m_compressor.enableDigital();
  }

  public Command intakeOpen() {
    return runOnce(() -> m_intake.set(Value.kForward));
  }

  public Command intakeClose() {
    return runOnce(() -> m_intake.set(Value.kReverse));
  }

  public Command climbOpen() {
    return runOnce(() -> m_climb.set(true));
  }

  public Command climbClose() {
    return runOnce(() -> m_climb.set(false));
  }
}
