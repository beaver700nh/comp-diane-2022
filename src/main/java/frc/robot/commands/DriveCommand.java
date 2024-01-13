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

  /**
   * Connects to a drive subsystem with drive and damping inputs.
   *
   * @param drive The drive subsystem to control.
   * @param inputX The power input.
   * @param inputP The power damping.
   * @param inputR The steer input.
   * @param inputQ The steer damping.
   */
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

  /**
   * Connects to a drive subsystem with drive inputs and no damping.
   *
   * @param drive The drive subsystem to control.
   * @param inputX The power input.
   * @param inputR The steer input.
   */
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

  /**
   * Halts motors when the command is started.
   */
  @Override
  public void initialize() {
    m_drive.forceTo(0, 0);
  }

  /**
   * Drives the drive subsystem according to the inputs, every tick.
   */
  @Override
  public void execute() {
    m_drive.accelTo(dampedX(), dampedR());
  }

  /**
   * Applies damping to the power input.
   *
   * @return The damped power value.
   */
  private double dampedX() {
    final double damping = 1 - m_inputP.get();
    return m_inputX.get() * MIN_X * (1 - m_inputP.get()) + MIN_X);
  }

  /**
   * Applies damping to the steer input.
   *
   * @return The damped steer value.
   */
  private double dampedR() {
    return m_inputR.get() * ((1 - MIN_R) * m_inputQ.get() + MIN_R);
  }

  /**
   * Halts the motor when the command is stopped.
   */
  @Override
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public void end(boolean interrupted) {
    m_drive.forceTo(0, 0);
  }

  /**
   * Reports that the command is never finished because the robot should always be drivable when enabled.
   */
  @Override
  public boolean isFinished() {
    return false;
  }
}
