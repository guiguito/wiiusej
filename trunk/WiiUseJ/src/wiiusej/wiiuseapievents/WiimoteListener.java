package wiiusej.wiiuseapievents;


public interface WiimoteListener extends java.util.EventListener {

	void onButtonsEvent(ButtonsEvent e);
	
	void onIrEvent(IREvent e);
	
	void onMotionSensingEvent(MotionSensingEvent e);
	
	void onStatusEvent(StatusEvent e);

	void onDisconnectionEvent(DisconnectionEvent e);

}
