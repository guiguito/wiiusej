package tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import wiiusej.Point2DInteger;
import wiiusej.WiiMoteEvent;
import wiiusej.WiiUseApiListener;
import wiiusej.WiiUseApiManager;

public class Tests implements WiiUseApiListener {

	Robot robot;

	private static int DISPLAY_EACH_VALUE = 1;
	private static int DUMP = 2;
	private static int MOVE_MOUSE = 3;
	private static int ORIENT_THRESH_CONT = 4;
	private static int TEST_LEDS = 5;

	int dump = ORIENT_THRESH_CONT;

	public Tests() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void wiimoteEvent(WiiMoteEvent e) {
		
		/* leave if nothing is connected */ 
		if (WiiUseApiManager.getInstance().getNbConnectedWiimotes() == 0) {
			WiiUseApiManager.getInstance().shutdown();
		}
		
		if (dump == DISPLAY_EACH_VALUE) {
			if (e.isConnected()) {
				// System.out.println("*********** WIIMOTE ID : "+
				// e.getWiimoteId() + " **************");
				/* button ONE */
				if (e.isButtonOneJustPressed()) {
					System.out.println("button one pressed");
				}
				if (e.isButtonOneHeld()) {
					System.out.println("button one held");
				}
				if (e.isButtonOneJustReleased()) {
					System.out.println("button one released");
				}

				/* button TWO */
				if (e.isButtonTwoJustPressed()) {
					System.out.println("button two pressed");
				}
				if (e.isButtonTwoHeld()) {
					System.out.println("button two held");
				}
				if (e.isButtonTwoJustReleased()) {
					System.out.println("button two released");
				}

				/* button A */
				if (e.isButtonAJustPressed()) {
					System.out.println("button A pressed");
				}
				if (e.isButtonAHeld()) {
					System.out.println("button A held");
				}
				if (e.isButtonAJustReleased()) {
					System.out.println("button A released");
				}

				/* button B */
				if (e.isButtonBJustPressed()) {
					System.out.println("button B pressed");
				}
				if (e.isButtonBHeld()) {
					System.out.println("button B held");
				}
				if (e.isButtonBJustReleased()) {
					System.out.println("button B released");
				}

				/* button LEFT */
				if (e.isButtonLeftJustPressed()) {
					System.out.println("button Left pressed");
				}
				if (e.isButtonLeftHeld()) {
					System.out.println("button Left held");
				}
				if (e.isButtonLeftJustReleased()) {
					System.out.println("button Left released");
				}

				/* button RIGHT */
				if (e.isButtonRightJustPressed()) {
					System.out.println("button Right pressed");
				}
				if (e.isButtonRightHeld()) {
					System.out.println("button Right held");
				}
				if (e.isButtonRightJustReleased()) {
					System.out.println("button Right released");
				}

				/* button UP */
				if (e.isButtonUpJustPressed()) {
					System.out.println("button UP pressed");
				}
				if (e.isButtonUpHeld()) {
					System.out.println("button UP held");
				}
				if (e.isButtonUpJustReleased()) {
					System.out.println("button UP released");
				}

				/* button DOWN */
				if (e.isButtonDownJustPressed()) {
					System.out.println("button DOWN pressed");
				}
				if (e.isButtonDownHeld()) {
					System.out.println("button DOWN held");
				}
				if (e.isButtonDownJustReleased()) {
					System.out.println("button DOWN released");
				}

				/* button MINUS */
				if (e.isButtonMinusJustPressed()) {
					System.out.println("button MINUS pressed");
				}
				if (e.isButtonMinusHeld()) {
					System.out.println("button MINUS held");
				}
				if (e.isButtonMinusJustReleased()) {
					System.out.println("button MINUS released");
				}

				/* button PLUS */
				if (e.isButtonPlusJustPressed()) {
					System.out.println("button PLUS pressed");
				}
				if (e.isButtonPlusHeld()) {
					System.out.println("button PLUS held");
				}
				if (e.isButtonPlusJustReleased()) {
					System.out.println("button PLUS released");
				}

				/* button HOME */
				if (e.isButtonHomeJustPressed()) {
					System.out.println("button HOME pressed");
				}
				if (e.isButtonHomeHeld()) {
					System.out.println("button HOME held");
				}
				if (e.isButtonHomeJustReleased()) {
					System.out.println("button HOME released");
				}

				/* get status */
				if (e.isButtonMinusJustPressed()&&e.isButtonPlusJustPressed()) {
					WiiUseApiManager.getInstance().getStatus(1);
				}

				/* Activate rumble */
				if (e.isButtonOneJustPressed()) {
					System.out.println("Rumble Activated");
					WiiUseApiManager.getInstance().activateRumble(1);
				}
				if (e.isButtonTwoJustPressed()) {
					System.out.println("Rumble Deactivated");
					WiiUseApiManager.getInstance().deactivateRumble(1);
				}

				/* Activate IR Tracking */
				if (e.isButtonAJustPressed()) {
					System.out.println("IR Activated");
					WiiUseApiManager.getInstance().activateIRTRacking(1);
				}
				if (e.isButtonBJustPressed()) {
					System.out.println("IR Deactivated");
					WiiUseApiManager.getInstance().deactivateIRTRacking(1);
				}

				/* Activate Motion sensing */
				if (e.isButtonPlusJustPressed()){
					System.out.println("Motion sensing Activated");
					WiiUseApiManager.getInstance().activateMotionSensing(1);					
				}
				if (e.isButtonMinusJustPressed()){
					System.out.println("Motion sensing Deactivated");
					WiiUseApiManager.getInstance().deactivateMotionSensing(1);					
				}

				/* display status */
				if (e.getBatteryLevel() != -1) {
					System.out
							.println("battery level : " + e.getBatteryLevel());
					System.out.println("= --- Leds : " + e.getLeds() + "\n");
					System.out.println("= --- Rumble : " + e.isRumbleActive() + "\n");
				}

				/* display ir points */
				if (e.isIrActive()) {
					Point2DInteger[] list = e.getIRPoints();
					for (int i = 0; i < list.length; i++) {
						if (list[i] != null)
							System.out.print("Point :(" + list[i].getX() + ","
									+ list[i].getY() + ") ");
					}
					System.out.println("");
				}
				
				/* display motion sensing */
				if (e.isMotionSensingActive()){
					System.out.println("Motion Sensing :"+e.getOrientation()+" , "+e.getGforce());
				}
				
				/* leave test */
				if (e.isButtonHomeJustPressed()) {
					System.out.println("LEAVING TEST");
					WiiUseApiManager.getInstance().closeConnection(1);					
				}
			} else {
				System.out.println(" WIIMOTE ID   : " + e.getWiimoteId()
						+ "  DISCONNECTED !!!!!");
				WiiUseApiManager.getInstance().closeConnection(1);				
			}
		} else if (dump == DUMP) {
			System.out.println(e);
			/* Activate all */
			if (e.isButtonAJustPressed()) {
				System.out.println("IR Activated");
				WiiUseApiManager.getInstance().activateIRTRacking(1);
				WiiUseApiManager.getInstance().activateMotionSensing(1);
				WiiUseApiManager.getInstance().activateRumble(1);
			}
			if (e.isButtonBJustPressed()) {
				System.out.println("IR Deactivated");
				WiiUseApiManager.getInstance().deactivateIRTRacking(1);
				WiiUseApiManager.getInstance().deactivateMotionSensing(1);
				WiiUseApiManager.getInstance().deactivateRumble(1);
			}
			
			/* leave test */
			if (e.isButtonHomeJustPressed()) {
				System.out.println("LEAVING TEST");
				WiiUseApiManager.getInstance().closeConnection(1);
				if (WiiUseApiManager.getInstance().getNbConnectedWiimotes() == 0) {
					WiiUseApiManager.getInstance().shutdown();
				}
			}
		} else if (dump == MOVE_MOUSE) {
			/* Activate IR Tracking */
			if (e.isButtonOneJustPressed()) {
				System.out.println("IR Activated");
				WiiUseApiManager.getInstance().activateIRTRacking(1);
			}
			if (e.isButtonTwoJustPressed()) {
				System.out.println("IR Deactivated");
				WiiUseApiManager.getInstance().deactivateIRTRacking(1);
			}

			/* button A */
			if (e.isButtonAJustPressed()) {
				robot.mousePress(InputEvent.BUTTON1_MASK);
			}
			if (e.isButtonAJustReleased()) {
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}

			/* button B */
			if (e.isButtonBJustPressed()) {
				robot.mousePress(InputEvent.BUTTON2_MASK);
			}
			if (e.isButtonBJustReleased()) {
				robot.mouseRelease(InputEvent.BUTTON2_MASK);
			}

			Point2DInteger[] list = e.getIRPoints();
			if (e.isIrActive() && list[0] != null) {

				int x1 = (int) list[0].getX();
				int y1 = (int) list[0].getY();

				int mousex = (int) Math.round(((double) x1 / 1024.0) * 1280.0);
				int mousey = (int) Math.round(((double) y1 / 768.0) * 1024.0);
				robot.mouseMove(mousex, mousey);
			}
			
			/* leave test */
			if (e.isButtonHomeJustPressed()) {
				System.out.println("LEAVING TEST");
				WiiUseApiManager.getInstance().closeConnection(1);				
			}
		}else if(dump == ORIENT_THRESH_CONT){
			WiiUseApiManager.getInstance().activateMotionSensing(1);
			if (e.isButtonOneJustPressed()) {
				System.out.println("Continous activated");
				WiiUseApiManager.getInstance().activateContinuous(1);
			}
			if (e.isButtonTwoJustPressed()) {
				System.out.println("Continous deactivated");
				WiiUseApiManager.getInstance().deactivateContinuous(1);
			}
			if (e.isButtonAJustPressed()) {
				System.out.println("Smoothing activated");
				WiiUseApiManager.getInstance().activateSmoothing(1);
			}
			if (e.isButtonBJustPressed()) {
				System.out.println("Smoothing deactivated");
				WiiUseApiManager.getInstance().deactivateSmoothing(1);
			}
			if (e.isButtonPlusJustPressed()) {
				System.out.println("Threshold orientation 10 degrees");
				WiiUseApiManager.getInstance().setOrientationThreshold(1, 10);
			}
			if (e.isButtonMinusJustPressed()) {
				System.out.println("Threshold orientation 0.5 degrees");
				WiiUseApiManager.getInstance().setOrientationThreshold(1, (float)0.5);
			}
			System.out.println(e);
			
			
			/* leave test */
			if (e.isButtonHomeJustPressed()) {
				System.out.println("LEAVING TEST");
				WiiUseApiManager.getInstance().closeConnection(1);				
			}
		}else if(dump == TEST_LEDS){
			WiiUseApiManager.getInstance().activateMotionSensing(1);
			if (e.isButtonUpJustPressed()) {				
				WiiUseApiManager.getInstance().setLeds(1, true, false, false, false);
			}
			if (e.isButtonDownJustPressed()) {				
				WiiUseApiManager.getInstance().setLeds(1, false, true, false, false);
			}
			if (e.isButtonLeftJustPressed()) {				
				WiiUseApiManager.getInstance().setLeds(1, false, false, true, false);
			}
			if (e.isButtonRightJustPressed()) {				
				WiiUseApiManager.getInstance().setLeds(1, false, false, false, true);
			}
	
			/* leave test */
			if (e.isButtonHomeJustPressed()) {
				System.out.println("LEAVING TEST");
				WiiUseApiManager.getInstance().closeConnection(1);				
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tests tests = new Tests();
		WiiUseApiManager manager = WiiUseApiManager.getInstance();
		manager.addWiiUseApiListener(tests);
		manager.loadLibrary();
		manager.connectWiimotes();
		manager.start();
//		java.util.Timer timer = new java.util.Timer();
//		timer.scheduleAtFixedRate(new LedsTask(), 0, 100);

	}

}
