// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.*;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveConfig;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LaunchSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/*
 * TODO:
 * - fix warnings
 */

/**
 * The class containing the majority of the robot's control code.
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
    /* Left */
    new DriveConfig(
      DriveConstants.kInvertLeft,
      DriveConstants.kAcceleration,
      new WPI_TalonSRX(DriveConstants.CAN.kMotorPortLeftA),
      new WPI_TalonSRX(DriveConstants.CAN.kMotorPortLeftB)
    ),
    /* Right */
    new DriveConfig(
      DriveConstants.kInvertRight,
      DriveConstants.kAcceleration,
      new WPI_TalonSRX(DriveConstants.CAN.kMotorPortRightA),
      new WPI_TalonSRX(DriveConstants.CAN.kMotorPortRightB)
    )
  );

  /**
   * The command used by DriveSubsystem to translate HID input into movement.
   */
  private final DriveCommand m_driveCommand = new DriveCommand(
    m_driveSubsystem,
    /* Left stick for power (up/down), deviate horizontally for damping */
    m_driverController::getLeftY,
    m_driverController::getLeftX,
    /* Right stick for steering (left/right), deviate vertically for damping */
    m_driverController::getRightX,
    m_driverController::getRightY
  );

  /**
   * The subsystem used to pick up game pieces off the ground.
   */
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem(
    Constants.IntakeConstants.PWM.kMotorPort,
    Constants.IntakeConstants.kIntakeSpeed,
    Constants.IntakeConstants.kIsInverted
  );

  /**
   * The subsystem used to score game pieces in the baskets.
   */
  private final LaunchSubsystem m_launchSubsystem = new LaunchSubsystem(
    Constants.LaunchConstants.PWM.kFlywheelPort,
    Constants.LaunchConstants.kFlywheelSpeed,
    Constants.LaunchConstants.kFlywheelInverted
  );

  /**
   * The subsystem used to control compressed-air flow.
   */
  private final PneumaticsSubsystem m_pneumaticsSubsystem = new PneumaticsSubsystem();

  /**
   * Configure default commands and button bindings.
   */
  public RobotContainer() {
    m_driveSubsystem.setDefaultCommand(m_driveCommand);

    configureBindings();
  }

  /**
   * Bind commands to HID events.
   */
  private void configureBindings() {
    m_driverController.a()
      .onTrue(m_pneumaticsSubsystem.intakeOpen());
    m_driverController.b()
      .onTrue(m_pneumaticsSubsystem.intakeClose());

    m_driverController.povUp()
      .onTrue(m_intakeSubsystem.intake());
    m_driverController.povDown()
      .onTrue(m_intakeSubsystem.outtake());
    m_driverController.povLeft()
      .onTrue(m_intakeSubsystem.stop());
    m_driverController.povRight()
      .onTrue(m_intakeSubsystem.stop());

    m_driverController.x()
      .onTrue(m_launchSubsystem.enable());
    m_driverController.y()
      .onTrue(m_launchSubsystem.disable());

    m_driverController.leftBumper()
      .onTrue(m_pneumaticsSubsystem.climbOpen());
    m_driverController.rightBumper()
      .onTrue(m_pneumaticsSubsystem.climbClose());
  }

  /**
   * Factory for dummy auto command, which just prints a message, to satisfy the API.
   * 
   * @return The auto command.
   */
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
