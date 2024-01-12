package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class DriveConfig {
  public final MotorController m_controller;
  public final boolean m_invert;
  public final double m_accelUp;
  public final double m_accelDown;

  public DriveConfig(MotorController controller, boolean invert, double accelUp, double accelDown) {
    m_controller = controller;
    m_invert = invert;
    m_accelUp = accelUp;
    m_accelDown = accelDown;

    m_controller.setInverted(m_invert);
  }

  public DriveConfig(MotorController controller, boolean invert, double accel) {
    this(controller, invert, accel, accel);
  }

  /**
   * Accelerates the motor towards the given velocity.
   * Returns the actual velocity of the motor.
   *
   * @param velocity The requested velocity of the motor.
   * @return The actual velocity of the motor.
   */
  public double accelTo(double velocity) {
    double now = m_controller.get();

    double accelDir = Math.signum(velocity - now);
    double accelMag = (
      Math.signum(now) == Math.signum(velocity) &&
      Math.abs(velocity) < Math.abs(now) ?
      m_accelDown : m_accelUp
    );

    now += accelDir * accelMag;

    if (accelDir < 0) {
      now = Math.max(now, velocity);
    }
    else {
      now = Math.min(now, velocity);
    }

    m_controller.set(now);
    return now;
  }

  public final MotorController getController() {
    return m_controller;
  }

  public final boolean getInvert() {
    return m_invert;
  }

  public final double getAccelUp() {
    return m_accelUp;
  }

  public final double getAccelDown() {
    return m_accelDown;
  }
}
