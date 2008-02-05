package wiiusej;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.event.EventListenerList;

/**
 * Class that manage the use of Wiiuse API.
 * 
 * @author gduche
 * 
 */
public class WiiUseApiManager extends Thread {

	private static WiiUseApiManager instance = new WiiUseApiManager();

	private final EventListenerList listeners = new EventListenerList();

	private WiiUseApi wiiuse = WiiUseApi.getInstance();

	private boolean loaded = false;

	private int connected = 0;

	private AtomicBoolean running = new AtomicBoolean(false);

	private ConcurrentLinkedQueue<WiiUseApiRequest> requests = new ConcurrentLinkedQueue<WiiUseApiRequest>();

	public static WiiUseApiManager getInstance() {
		return instance;
	}

	/**
	 * Load the wiimote library.
	 * 
	 * @return false if the library is not loaded true otherwise.
	 */
	public boolean loadLibrary() {
		if (!loaded) {// not yet loaded try to load it
			int load = wiiuse.loadLibrary();
			if (load > 0) {
				loaded = true;
				return true;
			} else {
				loaded = false;
				System.out.println("Error loading the Wiimote library !!!");
				return false;
			}
		}
		// already loaded
		return loaded;
	}

	/**
	 * Connect wiimote and get the nu ber of wiimotes connected. Supposed to be
	 * used once.
	 * 
	 * @return 0 if nothing connected or the number of wiimotes connected.
	 */
	public int connectWiimotes() {
		if (loaded) {
			connected = wiiuse.doConnections();
			System.out.println(connected + " wiimote(s) connected !!!");
			return connected;
		} else {// library not loaded, no wiimotes connected
			return 0;
		}
	}

	/**
	 * Ask the thread to close a connection.
	 * 
	 * @param id
	 *            id of the wiimote to disconnect.
	 */
	public void closeConnection(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_CLOSE_CONNECTION_REQUEST));
	}

	/**
	 * Get the number of wiimotes connected.
	 * 
	 * @return the number of wiimotes connected.
	 */
	public int getNbConnectedWiimotes() {
		return connected;
	}

	/**
	 * Stop thread and shutdown wiiuse Api.
	 */
	public void shutdown() {
		running.set(false);
		wiiuse.shutdownApi();
	}

	/**
	 * Activate the rumble for the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateRumble(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_RUMBLE_REQUEST));
	}

	/**
	 * Deactivate the rumble for the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void deactivateRumble(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_DEACTIVATE_RUMBLE_REQUEST));
	}

	/**
	 * Activate IR Tracking for the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateIRTRacking(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_IR_TRACKING_REQUEST));
	}

	/**
	 * Deactivate IR Tracking for the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void deactivateIRTRacking(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_DEACTIVATE_IR_TRACKING_REQUEST));
	}

	/**
	 * Activate IR Tracking of the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateMotionSensing(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_MOTION_SENSING_REQUEST));
	}

	/**
	 * Deactivate IR Tracking of the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void deactivateMotionSensing(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_DEACTIVATE_MOTION_SENSING_REQUEST));
	}

	/**
	 * Activate smoothing the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateSmoothing(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_SMOOTHING_REQUEST));
	}

	/**
	 * Deactivate smoothing the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void deactivateSmoothing(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_DEACTIVATE_SMOOTHING_REQUEST));
	}

	/**
	 * Activate continuous for the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateContinuous(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_CONTINUOUS_REQUEST));
	}

	/**
	 * Deactivate continuous for the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void deactivateContinuous(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_DEACTIVATE_CONTINUOUS_REQUEST));
	}

	/**
	 * Set leds for the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 * @param l1
	 *            status of led1. True : ON, False : OFF
	 * @param l2
	 *            status of led2. True : ON, False : OFF
	 * @param l3
	 *            status of led3. True : ON, False : OFF
	 * @param l4
	 *            status of led4. True : ON, False : OFF
	 */
	public void setLeds(int id, boolean l1, boolean l2, boolean l3, boolean l4) {
		requests.add(new LedsRequest(id, WiiUseApiRequest.WIIUSE_LEDS_REQUEST,
				l1, l2, l3, l4));
	}

	/**
	 * Set the orientation threshold for the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 * @param th
	 *            threshold in degrees
	 */
	public void setOrientationThreshold(int id, float th) {
		requests.add(new OrientThresholdRequest(id,
				WiiUseApiRequest.WIIUSE_ORIENT_THRESHOLHD_REQUEST, th));
	}

	/**
	 * Get Status for the wiimote for the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void getStatus(int id) {
		requests.add(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_STATUS_REQUEST));
	}

	@Override
	public void run() {

		if (loaded && (connected > 0)) {
			running.set(true);

			WiiMoteEvent evt = new WiiMoteEvent();

			// Start polling and tell the observers when there Wiimote events
			while (running.get()) {				
				WiiUseApiRequest req = requests.poll();
				if (req != null) {// there is a request for the wiiuse api
					int id = req.getId();
					if (req.getRequestType() == WiiUseApiRequest.WIIUSE_CLOSE_CONNECTION_REQUEST) {
						/* Close connections requests */
						wiiuse.closeConnection(id);
						connected--;
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_STATUS_REQUEST) {
						/* Status requests */
						wiiuse.getStatus(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ACTIVATE_RUMBLE_REQUEST) {
						/* Activate Rumble requests */
						wiiuse.activateRumble(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_DEACTIVATE_RUMBLE_REQUEST) {
						/* Deactivate Rumble requests */
						wiiuse.deactivateRumble(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ACTIVATE_IR_TRACKING_REQUEST) {
						/* Activate IR Tracking requests */
						wiiuse.activateIRTracking(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_DEACTIVATE_IR_TRACKING_REQUEST) {
						/* Deactivate IR Tracking requests */
						wiiuse.deactivateIRTracking(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ACTIVATE_MOTION_SENSING_REQUEST) {
						/* Activate Motion sensing requests */
						wiiuse.activateMotionSensing(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_DEACTIVATE_MOTION_SENSING_REQUEST) {
						/* Deactivate Motion sensing requests */
						wiiuse.deactivateMotionSensing(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_LEDS_REQUEST) {
						/* leds requests */
						LedsRequest reqLed = (LedsRequest) req;
						wiiuse.setLeds(id, reqLed.isLed1(), reqLed.isLed2(),
								reqLed.isLed3(), reqLed.isLed4());
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ACTIVATE_SMOOTHING_REQUEST) {
						/* Activate smoothing requests */
						wiiuse.activateSmoothing(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_DEACTIVATE_SMOOTHING_REQUEST) {
						/* Deactivate smoothing requests */
						wiiuse.deactivateSmoothing(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ACTIVATE_CONTINUOUS_REQUEST) {
						/* Activate continuous requests */
						wiiuse.activateContinuous(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_DEACTIVATE_CONTINUOUS_REQUEST) {
						/* Deactivate continuous requests */
						wiiuse.deactivateContinuous(id);
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ORIENT_THRESHOLHD_REQUEST) {
						/* set orientation request */
						wiiuse.setOrientThreshold(req.getId(),
								((OrientThresholdRequest) req).getThresholhd());
					} else {
						System.out.println("Bad request to Wiiuse API !!!!!");
					}
				}

				/* Polling */
				wiiuse.specialPoll(evt);

				if (evt.getWiimoteId() != -1) {// there is an event notify
					// observers
					notifyWiiUseApiListener(evt);
				}
				if (evt.getWiimoteId() != -1) {// create a new event when the last event created was filled
					if (!evt.isConnected()){//check if it was a disconnection
						connected --;
						if (connected == 0){
							System.out.println("No more wiimotes connected !!!");
						}
					}
					evt = new WiiMoteEvent();
				}								
			}

		} else {
			System.out.println("No polling possible !!!");
		}

	}

	/**
	 * 
	 * @param listener
	 */
	public void addWiiUseApiListener(WiiUseApiListener listener) {
		listeners.add(WiiUseApiListener.class, listener);
	}

	/**
	 * 
	 * @param listener
	 */
	public void removeWiiUseApiListener(WiiUseApiListener listener) {
		listeners.remove(WiiUseApiListener.class, listener);
	}

	/**
	 * 
	 * @return
	 */
	public WiiUseApiListener[] getWiiUseApiListeners() {
		return listeners.getListeners(WiiUseApiListener.class);
	}

	/**
	 * 
	 * @param evt
	 */
	public void notifyWiiUseApiListener(WiiMoteEvent evt) {
		for (WiiUseApiListener listener : getWiiUseApiListeners()) {
			listener.wiimoteEvent(evt);
		}
	}

}
