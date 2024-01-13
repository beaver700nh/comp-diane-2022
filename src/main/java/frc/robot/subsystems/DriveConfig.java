package frc.robot.subsystems;

public class DriveConfig {
  public final DriveController m_controller;
  public final boolean m_invert;
  public final double m_accelUp;
  public final double m_accelDown;

  public DriveConfig(boolean invert, double accelUp, double accelDown, DriveController... controllers) {
    m_invert = invert;
    m_accelUp = accelUp;
    m_accelDown = accelDown;

    m_controller = controllers[0];
    m_controller.setInverted(m_invert);
  
    for (int i = 1; i < controllers.length; ++i) {
      controllers[i].setInverted(m_invert);
      controllers[i].follow(m_controller);
    }
  }

  public DriveConfig(boolean invert, double accel, DriveController... controllers) {
    this(invert, accel, accel, controllers);
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

  public final DriveController getController() {
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
