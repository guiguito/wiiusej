/**
 * This file is part of WiiuseJ.
 *
 *  WiiuseJ is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  WiiuseJ is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with WiiuseJ.  If not, see <http://www.gnu.org/licenses/>.
 */
package wiiusej;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.event.EventListenerList;

import wiiusej.wiiuseapievents.EventsGatherer;
import wiiusej.wiiuseapievents.WiiUseApiEvent;
import wiiusej.wiiuseapievents.WiiUseApiListener;
import wiiusej.wiiuseapirequest.FloatValueRequest;
import wiiusej.wiiuseapirequest.IntValueRequest;
import wiiusej.wiiuseapirequest.LedsRequest;
import wiiusej.wiiuseapirequest.TwoIntValueRequest;
import wiiusej.wiiuseapirequest.WiiUseApiRequest;

/**
 * Class that manages the use of Wiiuse API.
 * 
 * @author guiguito
 */
public class WiiUseApiManager extends Thread {

	private static WiiUseApiManager instance = new WiiUseApiManager();

	private final EventListenerList listeners = new EventListenerList();

	private Wiimote[] wiimotes;

	private WiiUseApi wiiuse = WiiUseApi.getInstance();

	private int connected = -1;

	private AtomicBoolean running = new AtomicBoolean(false);

	private WiiUseApiRequestsManager requestManager = WiiUseApiRequestsManager
			.getInstance();

	public static WiiUseApiManager getInstance() {
		return instance;
	}

	/**
	 * Get wiimotes. Load library if necessary. Connect to wiimotes if
	 * necessary. Start polling if necessary. Return an array with the connected
	 * wiimotes.
	 * 
	 * @param nb
	 *            try to connect nb wiimotes
	 * @param rumble
	 *            make the connected wiimotes rumble
	 * @return an array with connected wiimotes or NULL.
	 */
	public synchronized static Wiimote[] getWiimotes(int nb, boolean rumble) {
		WiiUseApiManager manager = getInstance();
		if (manager.connected <= 0 && !manager.running.get()) {
			int nbWiimotes = manager.connectWiimotes(nb, rumble);
			manager.wiimotes = new Wiimote[nbWiimotes];
			for (int i = 1; i <= nbWiimotes; i++) {
				Wiimote wim = new Wiimote(i, manager);
				manager.wiimotes[i - 1] = wim;
				manager.addWiiUseApiListener(wim);
			}
		}

		if (manager.connected == 0) {
			return new Wiimote[0];
		}

		if (!manager.isAlive()){
			manager.start();
			manager.requestManager.start();
		}

		return manager.wiimotes;
	}

	/**
	 * Connect wiimote and get the number of wiimotes connected. Supposed to be
	 * used once.
	 * 
	 * @param nb
	 *            try to connect nb wiimotes
	 * @param rumble
	 *            make the connected wiimotes rumble
	 * @return 0 if nothing connected or the number of wiimotes connected.
	 */
	private int connectWiimotes(int nb, boolean rumble) {
		if (connected <= 0) {
			connected = wiiuse.doConnections(nb, rumble);
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
		removeWiiUseApiListener(wiimotes[id - 1]);
		wiimotes[id - 1] = null;
		connected--;
		if (connected == 0) {// stop this thread if there is
			// no more wiimotes connected.
			//stop thread
			shutdown();
		}
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_CLOSE_CONNECTION_REQUEST));
		// System.out.println("Wiimote " + id + " disconnected !");
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
		if (connected > 0) {
			for (Wiimote wim : wiimotes) {
				if (wim != null)
					wim.disconnect();
			}
		}
		running.set(false);
		wiiuse.shutdownApi();
	}

	/**
	 * Activate the rumble for the wiimote with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateRumble(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_RUMBLE_REQUEST));
	}

	/**
	 * Deactivate the rumble for the wiimote with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void deactivateRumble(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_DEACTIVATE_RUMBLE_REQUEST));
	}

	/**
	 * Activate IR Tracking for the wiimote with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateIRTRacking(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_IR_TRACKING_REQUEST));
	}

	/**
	 * Deactivate IR Tracking for the wiimote with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void deactivateIRTRacking(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_DEACTIVATE_IR_TRACKING_REQUEST));
	}

	/**
	 * Activate motion sensing for the wiimote with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateMotionSensing(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_MOTION_SENSING_REQUEST));
	}

	/**
	 * Deactivate motion sensing for the wiimoter with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void deactivateMotionSensing(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_DEACTIVATE_MOTION_SENSING_REQUEST));
	}

	/**
	 * Activate smoothing the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateSmoothing(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_SMOOTHING_REQUEST));
	}

	/**
	 * Deactivate smoothing the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void deactivateSmoothing(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_DEACTIVATE_SMOOTHING_REQUEST));
	}

	/**
	 * Activate continuous for the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote.
	 */
	public void activateContinuous(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ACTIVATE_CONTINUOUS_REQUEST));
	}

	/**
	 * Deactivate continuous for the wiimotes with the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void deactivateContinuous(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
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
		requestManager.addRequests(new LedsRequest(id,
				WiiUseApiRequest.WIIUSE_LEDS_REQUEST, l1, l2, l3, l4));
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
		requestManager.addRequests(new FloatValueRequest(id,
				WiiUseApiRequest.WIIUSE_ORIENT_THRESHOLHD_REQUEST, th));
	}

	/**
	 * Set the acceleration threshold for the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 * @param th
	 *            threshold
	 */
	public void setAccelerationThreshold(int id, int th) {
		requestManager.addRequests(new IntValueRequest(id,
				WiiUseApiRequest.WIIUSE_ACCEL_THRESHOLHD_REQUEST, th));
	}

	/**
	 * Set alpha smoothing for the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 * @param th
	 *            threshold
	 */
	public void setAlphaSmoothing(int id, float th) {
		requestManager.addRequests(new FloatValueRequest(id,
				WiiUseApiRequest.WIIUSE_ALPHA_SMOOTHING_REQUEST, th));
	}

	/**
	 * Try to resync with the wiimote by starting a new handshake.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void reSync(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_RESYNC));
	}

	/**
	 * Set screen aspect ratio to 4/3 for the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void setScreenAspectRatio43(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ASPECT_RATIO_4_3));
	}

	/**
	 * Set screen aspect ratio to 16/9 for the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void setScreenAspectRatio169(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_ASPECT_RATIO_16_9));
	}

	/**
	 * Set the sensor bar to be above the screen.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void setSensorBarAboveScreen(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_SENSOR_BAR_ABOVE));
	}

	/**
	 * Set the sensor bar to be below the screen.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void setSensorBarBelowScreen(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_SENSOR_BAR_BELOW));
	}

	/**
	 * Set virtual resolution. It is used to automatically compute the position
	 * of a cursor on this virtual screen using the sensor bar. These results
	 * come in the IREvent.
	 * 
	 * @param id
	 *            id of the wiimote
	 * @param x
	 *            x resolution
	 * @param y
	 *            y resolution
	 */
	public void setVirtualResolution(int id, int x, int y) {
		requestManager.addRequests(new TwoIntValueRequest(id,
				WiiUseApiRequest.WIIUSE_SET_VIRTUAL_RESOLUTION, x, y));
	}

	/**
	 * Get Status for the wiimote for the given id.
	 * 
	 * @param id
	 *            id of the wiimote
	 */
	public void getStatus(int id) {
		requestManager.addRequests(new WiiUseApiRequest(id,
				WiiUseApiRequest.WIIUSE_STATUS_REQUEST));
	}

	@Override
	public void run() {

		if (connected > 0) {
			running.set(true);

			EventsGatherer gather = new EventsGatherer(connected);

			// Start polling and tell the observers when there are Wiimote
			// events
			while (running.get() && connected > 0) {

				/* Polling */
				synchronized (wiiuse) {
					wiiuse.specialPoll(gather);
				}

				/* deal with events gathered in Wiiuse API */
				for (WiiUseApiEvent evt : gather.getEvents()) {
					if (evt.getWiimoteId() != -1) {// event filled
						// there is an event notify observers
						notifyWiiUseApiListener(evt);
						if (evt.getEventType() == WiiUseApiEvent.DISCONNECTION_EVENT) {
							// check if it was a disconnection
							// in this case disconnect the wiimote
							closeConnection(evt.getWiimoteId());
						}
					} else {
						System.out
								.println("There is an event with id == -1 ??? there is a problem !!! : "
										+ evt);
					}
				}
				gather.clearEvents();
			}
		} else {
			if (connected <= 0) {
				System.out.println("No wiimotes connected !");
			}
		}

	}

	/**
	 * Add WiiUseApiListener to the listeners list.
	 * 
	 * @param listener
	 *            a WiiUseApiListener
	 */
	public void addWiiUseApiListener(WiiUseApiListener listener) {
		listeners.add(WiiUseApiListener.class, listener);
	}

	/**
	 * Remove WiiUseApiListener from the listeners list.
	 * 
	 * @param listener
	 *            a WiiUseApiListener
	 */
	public void removeWiiUseApiListener(WiiUseApiListener listener) {
		listeners.remove(WiiUseApiListener.class, listener);
	}

	/**
	 * Get the list of WiiUseApiListeners.
	 * 
	 * @return the list of WiiUseApiListeners.
	 */
	public WiiUseApiListener[] getWiiUseApiListeners() {
		return listeners.getListeners(WiiUseApiListener.class);
	}

	/**
	 * Notify WiiUseApiListeners that an event occured.
	 * 
	 * @param evt
	 *            WiimoteEvent occured
	 */
	private void notifyWiiUseApiListener(WiiUseApiEvent evt) {
		for (WiiUseApiListener listener : getWiiUseApiListeners()) {
			listener.onWiiUseApiEvent(evt);
		}
	}

}
