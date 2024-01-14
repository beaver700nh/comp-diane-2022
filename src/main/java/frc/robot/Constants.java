package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * Contains constants used throughout the robot code.
 * Constants are placed here to make them easy to find and change.
 */
public final class Constants {
  public static class OperatorConstants {
    public static class USB {
      public static final int kDriverControllerPort = 0;
    }
  }

  public static class DriveConstants {
    public static final boolean kInvertLeft = true;
    public static final boolean kInvertRight = false;
    public static final double kMultiplier = 1.0;
    public static final double kAcceleration = 0.6;
    public static final double kMinPower = 0.4;
    public static final double kMinSteer = 0.2;

    public static class CAN {
      public static final int kMotorLeftA = 3;
      public static final int kMotorLeftB = 4;
      public static final int kMotorRightA = 1;
      public static final int kMotorRightB = 2;
    }
  }

  public static class IntakeConstants {
    public static final boolean kIntakeInverted = true;
    public static final double kIntakeSpeed = 0.75;

    public static class PWM {
      public static final int kIntake = 1;
    }
  }

  public static class LaunchConstants {
    public static final boolean kFlywheelInverted = true;
    public static final double kFlywheelSpeed = 0.75;

    public static class PWM {
      public static final int kFlywheel = 0;
    }
  }

  public static class PneumaticsConstants {
    public static final PneumaticsModuleType kPcmType = PneumaticsModuleType.CTREPCM;
    public static class CAN {
      public static final int kPCM = 11;
    }

  }
}

/*
 * Other CAN IDs:
 * ==============
 * 9: launch feeder (probably)
 * 10: PDP
 */
