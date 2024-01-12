package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static class USB {
      public static final int kDriverControllerPort = 0;
    }
  }

  public static class DriveConstants {
    public static final boolean kInvertLeft = true;
    public static final boolean kInvertRight = false;
    public static final double kAcceleration = 0.6;

    public static class CAN {
      public static final int kMotorPortLeftA = 3;
      public static final int kMotorPortLeftB = 4;
      public static final int kMotorPortRightA = 1;
      public static final int kMotorPortRightB = 2;
    }
  }

  public static class IntakeConstants {
    public static final double kIntakeSpeed = 0.75;
    public static final boolean kIsInverted = true;

    public static class PWM {
      public static final int kMotorPort = 1;
    }
  }

  public static class LaunchConstants {
    public static final double kFlywheelSpeed = 0.75;
    public static final boolean kFlywheelInverted = true;

    public static class PWM {
      public static final int kFlywheelPort = 0;
    }
  }
}

/*
 * CAN IDs:
 * 1: Front right
 * 2: Back right
 * 3: Back left
 * 4: Front left
 * 9: (to be determined)
 * 10: PDP
 * 11: PCM
 */
