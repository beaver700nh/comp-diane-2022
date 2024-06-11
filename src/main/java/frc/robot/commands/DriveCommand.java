package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants.*;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Handles controller inputs and drives the drive subsystem accordingly.
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
   * Connect to a drive subsystem with drive and damping inputs.
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
    m_inputX = () -> MathUtil.applyDeadband(inputX.get(), OperatorConstants.kDeadband);
    m_inputP = () -> MathUtil.applyDeadband(inputP.get(), OperatorConstants.kDeadband);
    m_inputR = () -> MathUtil.applyDeadband(inputR.get(), OperatorConstants.kDeadband);
    m_inputQ = () -> MathUtil.applyDeadband(inputQ.get(), OperatorConstants.kDeadband);

    addRequirements(drive);
  }

  /**
   * Connect to a drive subsystem with drive inputs and no damping.
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
   * Halt motors when the command is started.
   */
  @Override
  public void initialize() {
    m_drive.forceTo(0, 0);
  }

  /**
   * Drive the drive subsystem according to the inputs, every tick.
   */
  @Override
  public void execute() {
    m_drive.accelTo(dampedX(), dampedR());
  }

  /**
   * Apply damping to the power input.
   *
   * @return The damped power value.
   */
  private double dampedX() {
    final double damping = (DriveConstants.kMinPower - 1) * m_inputP.get() + 1;
    return m_inputX.get() * damping;
  }

  /**
   * Apply damping to the steer input.
   *
   * @return The damped steer value.
   */
  private double dampedR() {
    final double damping = (DriveConstants.kMinSteer - 1) * m_inputQ.get() + 1;
    return m_inputR.get() * damping;
  }

  /**
   * Halt the motor when the command is stopped.
   */
  @Override
  @SuppressWarnings("PMD.UnusedFormalParameter")
  public void end(boolean interrupted) {
    m_drive.forceTo(0, 0);
  }

  /**
   * Report that the command is never finished because the robot should always be drivable when enabled.
   */
  @Override
  public boolean isFinished() {
    return false;
  }
}
