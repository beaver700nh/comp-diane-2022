package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  private final Spark m_motorController;
  private final double m_motorSpeed;

  /**
   * @param channel The RIO PWM channel of the motor controller.
   */
  public IntakeSubsystem(int channel, double speed, boolean invert) {
    m_motorController = new Spark(channel);
    m_motorSpeed = invert ? 0 - speed : speed;
  }

  public Command intake() {
    return runOnce(() -> m_motorController.set(m_motorSpeed));
  }

  public Command outtake() {
    return runOnce(() -> m_motorController.set(-m_motorSpeed));
  }

  public Command stop() {
    return runOnce(() -> m_motorController.set(0));
  }
}
