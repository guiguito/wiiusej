package wiiusej.wiiuseapievents;



/**
 * Interface defining the methods a WiiUseApiListener must have
 * @author gduche
 *
 */
public interface WiiUseApiListener extends java.util.EventListener {
	
	void onWiiUseApiEvent(WiiUseApiEvent e);
	
	
}
