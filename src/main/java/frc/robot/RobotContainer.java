// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import frc.robot.lib.SmartMotorController.SmartMotorController;
import frc.robot.lib.SmartMotorController.SmartMotorControllerGroup;

import frc.robot.Constants.*;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LaunchSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;

/**
 * Handles the majority of the high-level robot control.
 * This is where commands, subsystems, controllers, and button bindings are located.
 */
public class RobotContainer {
  /**
   * The HID used to control the robot.
   */
  private final CommandXboxController m_driverController = new CommandXboxController(
    OperatorConstants.USB.kDriverControllerPort
  );

  /**
   * The subsystem used to make the robot move around.
   */
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(
    new SmartMotorControllerGroup<>(
      DriveConstants.kInvertLeft,
      DriveConstants.kMultiplier,
      DriveConstants.kAcceleration,
      (master, follower) -> follower.follow(master),
      new WPI_TalonSRX(DriveConstants.CAN.kMotorLeftA),
      new WPI_TalonSRX(DriveConstants.CAN.kMotorLeftB)
    ),
    new SmartMotorControllerGroup<>(
      DriveConstants.kInvertRight,
      DriveConstants.kMultiplier,
      DriveConstants.kAcceleration,
      (master, follower) -> follower.follow(master),
      new WPI_TalonSRX(DriveConstants.CAN.kMotorRightA),
      new WPI_TalonSRX(DriveConstants.CAN.kMotorRightB)
    )
  );

  /**
   * The command used by DriveSubsystem to translate HID input into movement.
   */
  private final DriveCommand m_driveCommand = new DriveCommand(
    m_driveSubsystem,
    () -> -m_driverController.getLeftY(),
    () -> m_driverController.getLeftTriggerAxis(),
    () -> m_driverController.getRightX(),
    () -> m_driverController.getRightTriggerAxis()
  );

  /**
   * The subsystem used to pick up game pieces off the ground.
   */
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem(
    new SmartMotorController(
      IntakeConstants.kIntakeInverted,
      IntakeConstants.kIntakeSpeed,
      0, 0,
      new Spark(IntakeConstants.PWM.kIntake)
    )
  );

  /**
   * The subsystem used to score game pieces in the baskets.
   */
  private final LaunchSubsystem m_launchSubsystem = new LaunchSubsystem(
    new SmartMotorController(
      LaunchConstants.kFlywheelInverted,
      LaunchConstants.kFlywheelSpeed,
      LaunchConstants.kFlywheelAcceleration,
      new Spark(LaunchConstants.PWM.kFlywheel)
    ),
    new SmartMotorController(
      LaunchConstants.kFeederInverted,
      LaunchConstants.kFeederSpeed,
      LaunchConstants.kFeederAcceleration,
      new WPI_TalonSRX(LaunchConstants.CAN.kFeeder)
    )
  );

  /**
   * The subsystem used to score endgame points on the bars.
   */
  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem(
    new SmartMotorControllerGroup<>(
      ClimbConstants.kClimbInverted,
      ClimbConstants.kClimbSpeed,
      ClimbConstants.kClimbAcceleration,
      (master, follower) -> follower.follow(master),
      new CANSparkMax(ClimbConstants.CAN.kClimbA, MotorType.kBrushless),
      new CANSparkMax(ClimbConstants.CAN.kClimbB, MotorType.kBrushless)
    )
  );

  /**
   * The subsystem used to control compressed-air flow.
   */
  private final PneumaticsSubsystem m_pneumaticsSubsystem = new PneumaticsSubsystem(
    PneumaticsConstants.CAN.kPCM,
    PneumaticsConstants.kPcmType
  );

  /**
   * Configure default commands and button bindings.
   */
  public RobotContainer() {
    m_driveSubsystem.setDefaultCommand(m_driveCommand);

    configureBindings();
  }

  /**
   * Bind commands to controller events.
   */
  private void configureBindings() {
    m_driverController.a()
      .onTrue(m_pneumaticsSubsystem.intakeOpen());
    m_driverController.b()
      .onTrue(m_pneumaticsSubsystem.intakeClose());

    m_driverController.povUp()
      .onTrue(m_intakeSubsystem.intakeIn());
    m_driverController.povDown()
      .onTrue(m_intakeSubsystem.intakeOut());
    m_driverController.povLeft()
      .onTrue(m_intakeSubsystem.intakeStop());
    m_driverController.povRight()
      .onTrue(m_intakeSubsystem.intakeStop());

    m_driverController.x()
      .onTrue(m_launchSubsystem.feederToggle());
    m_driverController.y()
      .onTrue(m_launchSubsystem.flywheelToggle());

    m_driverController.leftBumper()
      .onTrue(m_pneumaticsSubsystem.climbOpen());
    m_driverController.rightBumper()
      .onTrue(m_pneumaticsSubsystem.climbClose());

    m_driverController.start()
      .onTrue(m_climbSubsystem.climberExtend())
      .onFalse(m_climbSubsystem.climberStop());
    m_driverController.back()
      .onTrue(m_climbSubsystem.climberRetract())
      .onFalse(m_climbSubsystem.climberStop());
  }

  /**
   * Generate dummy auto command, which just prints a message to satisfy the API.
   * 
   * @return The auto command.
   */
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
