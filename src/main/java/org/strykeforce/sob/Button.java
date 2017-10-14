package org.strykeforce.sob;

import edu.wpi.first.wpilibj.Joystick;

public class Button {
  private final Joystick joystick;
  private final int button;
  private boolean isActiveLast = false;

  public Button(Joystick joystick, int button) {
    this.joystick = joystick;
    this.button = button;
  }

  public boolean hasActivated() {
    if (joystick.getRawButton(button)) {
      if (!isActiveLast) {
        isActiveLast = true;
        return true;
      }
    } else {
      isActiveLast = false;
    }
    return false;
  }
}
