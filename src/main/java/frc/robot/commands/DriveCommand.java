package frc.robot.commands;

import java.util.function.Supplier;

import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * This subsystem takes power and damping inputs from the controller and drives the drive train accordingly.
 */
public class DriveCommand extends Command {
  /**
   * The drive subsystem to control.
   */
  private final DriveSubsystem m_drive;

  /**
   * The inputs to the drive subsystem power.
   */
  private final Supplier<Double> m_inputX, m_inputP;

  /**
   * The inputs to the drive subsystem steering.
   */
  private final Supplier<Double> m_inputR, m_inputQ;

  private final double MIN_X = 0.4, MIN_R = 0.2;

  public DriveCommand(
    DriveSubsystem drive,
    Supplier<Double> inputX, Supplier<Double> inputP,
    Supplier<Double> inputR, Supplier<Double> inputQ
  ) {
    m_drive = drive;
    m_inputX = inputX;
    m_inputP = inputP;
    m_inputR = inputR;
    m_inputQ = inputQ;

    addRequirements(drive);
  }

  public DriveCommand(
    DriveSubsystem drive,
    Supplier<Double> inputX, Supplier<Double> inputR
  ) {
    this(
      drive,
      inputX, () -> 0.0,
      inputR, () -> 0.0
    );
  }

  @Override
  public void initialize() {
    m_drive.forceTo(0, 0);
  }

  @Override
  public void execute() {
    m_drive.accelTo(dampedX(), dampedR());
  }

  private double dampedX() {
    return m_inputX.get() * ((1 - MIN_X) * m_inputP.get() + MIN_X);
  }

  private double dampedR() {
    return m_inputR.get() * ((1 - MIN_R) * m_inputQ.get() + MIN_R);
  }

  @Override
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public void end(boolean interrupted) {
    m_drive.forceTo(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
