package wiiusej;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

import wiiusej.wiiuseapirequest.FloatValueRequest;
import wiiusej.wiiuseapirequest.IntValueRequest;
import wiiusej.wiiuseapirequest.LedsRequest;
import wiiusej.wiiuseapirequest.TwoIntValueRequest;
import wiiusej.wiiuseapirequest.WiiUseApiRequest;

/**
 * This class is thread dedicated to send request to WiiUseApi. It is used only
 * (and must be used only) by WiiUseApiManager.
 * 
 * @author guiguito
 * 
 */
public class WiiUseApiRequestsManager extends Thread {

	//protected Semaphore semaphore = new Semaphore(0);
	private WiiUseApi wiiuse = WiiUseApi.getInstance();
	private ConcurrentLinkedQueue<WiiUseApiRequest> requests = new ConcurrentLinkedQueue<WiiUseApiRequest>();
	private static WiiUseApiRequestsManager instance = new WiiUseApiRequestsManager();
	private boolean running = false;

	/**
	 * Default constructor.
	 */
	WiiUseApiRequestsManager() {
		super();
	}

	public static WiiUseApiRequestsManager getInstance() {
		return instance;
	}

	public void addRequests(WiiUseApiRequest request) {
		requests.add(request);
		//semaphore.release();
		synchronized (requests) {
			requests.notify();
		}
	}

	public void run() {
		running = true;
		while (running) {
			/* deal with requests done to wiiuse API */
			
			/*
			try {
			    semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/
			if (requests.size()==0){
				synchronized (requests) {
					try {
						requests.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			WiiUseApiRequest req = requests.poll();
			if (req != null) {// there is a request for the wiiuse api
				int id = req.getId();
				synchronized (wiiuse) {// synchronized on wiiuseApi
					if (req.getRequestType() == WiiUseApiRequest.WIIUSE_CLOSE_CONNECTION_REQUEST) {
						/* Close connections requests */
						wiiuse.closeConnection(id);
						if (WiiUseApiManager.getInstance()
								.getNbConnectedWiimotes() <= 0) {
							this.shutdown();
						}
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
						/* set orientation threshold request */
						wiiuse.setOrientThreshold(req.getId(),
								((FloatValueRequest) req).getFloatValue());
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ACCEL_THRESHOLHD_REQUEST) {
						/* set acceleration threshold request */
						wiiuse.setAccelThreshold(req.getId(),
								((IntValueRequest) req).getIntValue());
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ALPHA_SMOOTHING_REQUEST) {
						/* set alpha smoothing request */
						wiiuse.setAlphaSmoothing(req.getId(),
								((FloatValueRequest) req).getFloatValue());
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_RESYNC) {
						/* set resync request */
						wiiuse.reSync(req.getId());
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ASPECT_RATIO_4_3) {
						/* set screen aspect ratio to 4/3 */
						wiiuse.setScreenRatio43(req.getId());
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_ASPECT_RATIO_16_9) {
						/* set screen aspect ratio to 16/9 */
						wiiuse.setScreenRatio169(req.getId());
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_SENSOR_BAR_ABOVE) {
						/* set sensor bar above the screen */
						wiiuse.setSensorBarAboveScreen(req.getId());
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_SENSOR_BAR_BELOW) {
						/* set sensor bar above the screen */
						wiiuse.setSensorBarBelowScreen(req.getId());
					} else if (req.getRequestType() == WiiUseApiRequest.WIIUSE_SET_VIRTUAL_RESOLUTION) {
						/* set virtual resolution */
						wiiuse.setVirtualScreenResolution(req.getId(),
								((TwoIntValueRequest) req).getIntValue(),
								((TwoIntValueRequest) req).getSecondIntValue());
					} else {
						System.out.println("Bad request to Wiiuse API !!!!!");
					}
				}// end synchronized on wiiuseApi
			}//if (req != null)			
		}//end while(running)
	}

	public void shutdown() {
		running = false;
	}

}
