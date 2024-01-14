package frc.robot.classes;

import frc.robot.interfaces.IDriveController;

/**
 * Handles a set of drive motors with their configuration.
 * Includes support for linear velocity ramping.
 */
public class DriveConfig {
  /**
   * The direction in which the motors should spin.
   */
  public final boolean m_invert;

  /**
   * The rate at which the motor should get faster. 
   */
  public final double m_accelUp;

  /**
   * The rate at which the motor should get slower or change direction.
   */
  public final double m_accelDown;

  /**
   * The master motor controller which is followed by all the others.
   */
  public final IDriveController m_controller;

  /**
   * Initialize the configuration for the drive motors.
   *
   * @param invert The direction in which the motors should spin.
   * @param accelUp The rate at which the motor should get faster. 
   * @param accelDown The rate at which the motor should get slower or change direction.
   * @param controllers The motor controllers to be driven.
   */
  public DriveConfig(boolean invert, double accelUp, double accelDown, IDriveController... controllers) {
    m_invert = invert;
    m_accelUp = accelUp;
    m_accelDown = accelDown;
    m_controller = controllers[0];

    for (IDriveController controller : controllers) {
      controller.setInverted(m_invert);

      if (controller != m_controller) {
        controller.follow(m_controller);
      }
    }
  }

  /**
   * Initialize the configuration for the drive motors.
   * Assume that the acceleration rate is the same for both directions.
   *
   * @param invert The direction in which the motors should spin.
   * @param accel The rate at which the motor should change speed.
   * @param controllers The motor controllers to be driven.
   */
  public DriveConfig(boolean invert, double accel, IDriveController... controllers) {
    this(invert, accel, accel, controllers);
  }

  /**
   * Accelerate the motor towards the given velocity.
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

  public final IDriveController getController() {
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
