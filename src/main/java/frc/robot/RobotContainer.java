// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.DriveConfig;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final MotorControllerGroup m_motorGroupL = new MotorControllerGroup(new WPI_VictorSPX(3), new WPI_VictorSPX(4));
  private final MotorControllerGroup m_motorGroupR = new MotorControllerGroup(new WPI_VictorSPX(1), new WPI_VictorSPX(2));

  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem(
    new DriveConfig(m_motorGroupL, false, 0.6, 0.4),
    new DriveConfig(m_motorGroupR, true, 0.6, 0.4)
  );

  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final DriveCommand m_driveCommand = new DriveCommand(
    m_driveSubsystem,
    m_driverController::getLeftY,
    m_driverController::getRightX
  );

  public RobotContainer() {
    m_driveSubsystem.setDefaultCommand(m_driveCommand);

    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
