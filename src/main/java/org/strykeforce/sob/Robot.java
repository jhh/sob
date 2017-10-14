package org.strykeforce.sob;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {

  private CANTalon talon;
  private Joystick joystick;
  private Button buttonA;
  private Button buttonY;

  private double startTime;
  private boolean running = false;

  @Override
  public void robotInit() {
    talon = new CANTalon(1);
    joystick = new Joystick(0);
    buttonA = new Button(joystick, 1);
    buttonY = new Button(joystick, 4);
  }

  @Override
  public void teleopInit() {
    talon.changeControlMode(TalonControlMode.Voltage);
    talon.setCurrentLimit(5);
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void teleopPeriodic() {

    if (buttonA.hasActivated()) {
      System.out.println("button A");
      startTime = Timer.getFPGATimestamp();
      running = true;
    }

    if (buttonY.hasActivated()) {
      System.out.println("button Y");
      talon.set(0.0);
      running = false;
    }

    if (!running) return;

    double elapsedTime = Timer.getFPGATimestamp() - startTime;
    if (elapsedTime < 2.0) {
      talon.set(2.0);
    } else if (elapsedTime < 4.0) {
      talon.set(4.0);
    } else if (elapsedTime < 6.0) {
      talon.set(6.0);
    }

  }
}
