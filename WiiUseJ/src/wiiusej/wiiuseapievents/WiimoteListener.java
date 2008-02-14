package wiiusej.wiiuseapievents;


public interface WiimoteListener extends java.util.EventListener {

	void wiimoteEvent(WiiMoteEvent e);

	void statusEvent(StatusEvent e);

	void disconnectionEvent(DisconnectionEvent e);

}
