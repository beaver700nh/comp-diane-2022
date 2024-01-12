package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LaunchSubsystem extends SubsystemBase {
  private final Spark m_flywheelController;
  private final double m_flywheelSpeed;

  /**
   * @param channel The RIO PWM channel of the motor controller.
   */
  public LaunchSubsystem(int flywheelChannel, double flywheelSpeed, boolean flywheelInvert) {
    m_flywheelController = new Spark(flywheelChannel);
    m_flywheelSpeed = flywheelInvert ? 0 - flywheelSpeed : flywheelSpeed;
  }

  public Command enable() {
    return runOnce(() -> m_flywheelController.set(m_flywheelSpeed));
  }

  public Command disable() {
    return runOnce(() -> m_flywheelController.set(0));
  }
}
