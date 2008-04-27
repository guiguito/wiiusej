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
#ifndef WIN32
	#include <unistd.h>
#else
	
#endif

#include "wiiusej_WiiUseApi.h"
#include "wiiuse.h"
/*
 *	These are some identifiers for wiimotes
 *
 *	See below in main() for what they are used for.
 */
#define WIIMOTE_STATE_RUMBLE			0x08
#define WIIMOTE_STATE_CONNECTED			0x04
#define WIIMOTE_IS_SET(wm, s)			((wm->state & (s)) == (s))
#define WIIMOTE_IS_FLAG_SET(wm, s)		((wm->flags & (s)) == (s))

/********************* VARIABLES DECLARATIONS *****************************/

/*
 *	Make a temp array of wiimote ids.
 *	Here I only anticipate connecting up to
 *	two wiimotes.  Each wiimote connected
 *	will get one of these ids.
 */
static wiimote** wiimotes;

static int nbMaxWiiMotes=0;

/****************** GENERAL FUNCTIONS DEFINITIONS *************************/

/**
 * Connect to a wiimote or wiimotes once an address is known.
 * @param nbWiimotes The number of wiimotes.
 * @return The number of wiimotes that successfully connected.
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_connect
  (JNIEnv *env, jobject obj, jint nbWiimotes){
	return wiiuse_connect(wiimotes, nbWiimotes);
}

/**
 * Find a wiimote or wiimotes.
 * @param nbMaxWiimotes The number of wiimotes.
 * @param timeout The number of seconds before the search times out.
 * @return The number of wiimotes found.
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_find
  (JNIEnv *env, jobject obj, jint nbMaxWiimotes, jint timeout){
	return wiiuse_find(wiimotes, nbMaxWiimotes, timeout);
}

/**
 * Initialize an array of wiimote structures (for the C side of the library).
 * @param nbPossibleWiimotes size of the array.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_init
  (JNIEnv *env, jobject obj, jint nbPossibleWiimotes){
	wiimotes = wiiuse_init(nbPossibleWiimotes);
}

/**
 * Try to connect to 2 wiimotes.
 * Make them rumble to show they are connected.
 * @param nbConnects number of connections maximum.
 * @param rumble
 * 			make the connected wiimotes rumble.
 * @return 0 if there is an error otherwise it returns 
 * 			the number of wiimotes connected..
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_doConnections
(JNIEnv *env, jobject obj, jint nbConnects, jboolean rumble) {

	/* variables declarations */
	int found, connected, i;
	short leds;

	nbMaxWiiMotes = nbConnects;

	/* initialize wiimotes array with the maximum number of wiimotes */
	wiimotes = wiiuse_init(nbMaxWiiMotes);

	/*
	 *	Find wiimote devices
	 *	Now we need to find some wiimotes.
	 *	Give the function the wiimote array we created, and tell it there
	 *	are 2 wiimotes we are interested in.
	 *	Set the timeout to be 5 seconds.
	 *	This will return the number of actual wiimotes that are in discovery mode.
	 */
	found = wiiuse_find(wiimotes, nbMaxWiiMotes, 5);
	if (!found) return 0;

	/*
	 *	Connect to the wiimotes
	 *	Now that we found some wiimotes, connect to them.
	 *	Give the function the wiimote array and the number of wiimote devices we found.
	 *	This will return the number of established connections to the found wiimotes.
	 */
	connected = wiiuse_connect(wiimotes, nbMaxWiiMotes);
	if (!connected) return 0;

	//no problems during connection show that wiimotes are connected

	/*
	 *	Now set the LEDs and rumble for a second so it's easy
	 *	to tell which wiimotes are connected (just like the wii does).
	 */
	for (i=0;i<nbMaxWiiMotes;i++) {
		leds = 0;
		if (i%4==0) leds |= WIIMOTE_LED_1;
		else if (i%4==1) leds |= WIIMOTE_LED_2;
		else if (i%4==2) leds |= WIIMOTE_LED_3;
		else if (i%4==3) leds |= WIIMOTE_LED_4;
		wiiuse_set_leds(wiimotes[i], leds);
		if (rumble) wiiuse_rumble(wiimotes[i], 1);
	}
	if (rumble) {
		#ifndef WIN32
			usleep(200000);
		#else
			Sleep(200);
		#endif	
		for (i=0;i<nbMaxWiiMotes;i++) {
			wiiuse_rumble(wiimotes[i], 0);
		}
	}

	//no pb connecting leave	
	return connected;
}

/**
 * Close connection to the wiimote with the given id.
 * 
 * @param id the id of the wiimote to disconnect.Must be 1 or 2.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_closeConnection
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_disconnect(wiimotes[id-1]);
}

/**
 * Shutdown api.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_shutdownApi
(JNIEnv *env, jobject obj) {
	//wiiuse_shutdown();
	wiiuse_cleanup(wiimotes, nbMaxWiiMotes);
}

/**
 * Activate rumble for the wiimote with the given id.
 * @param id the id of the wiimote.Must be 1 or 2.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateRumble
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_rumble(wiimotes[id-1], 1);
}

/**
 * Deactivate rumble for the wiimote with the given id.
 * @param id the id of the wiimote.Must be 1 or 2.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateRumble
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_rumble(wiimotes[id-1], 0);
}

/**
 * Activate IR TRacking for the wiimote with the given id.
 * @param id the id of the wiimote.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateIRTracking
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_ir(wiimotes[id-1], 1);
}

/**
 * Deactivate IR TRacking for the wiimote with the given id.
 * @param id the id of the wiimote.Must be 1 or 2.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateIRTracking
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_ir(wiimotes[id-1], 0);
}

/**
 * Activate Motion Sensing for the wiimote with the given id.
 * @param id the id of the wiimote.Must be 1 or 2.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateMotionSensing
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_motion_sensing(wiimotes[id-1], 1);
}

/**
 * Deactivate Motion Sensing for the wiimote with the given id.
 * @param id the id of the wiimote.Must be 1 or 2.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateMotionSensing
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_motion_sensing(wiimotes[id-1], 0);
}

/**
 * Set wiimote leds status.
 * @param id the id of the wiimote concerned
 * @param led1 status of led1: True=ON, False=OFF
 * @param led2 status of led2: True=ON, False=OFF
 * @param led3 status of led3: True=ON, False=OFF
 * @param led4 status of led4: True=ON, False=OFF
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setLeds
(JNIEnv *env, jobject obj, jint id, jboolean led1, jboolean led2, jboolean led3, jboolean led4) {
	int leds = 0;

	if (led1) leds |= WIIMOTE_LED_1;
	if (led2) leds |= WIIMOTE_LED_2;
	if (led3) leds |= WIIMOTE_LED_3;
	if (led4) leds |= WIIMOTE_LED_4;

	wiiuse_set_leds(wiimotes[id-1], leds);
}

/**
 * Set how many degrees an angle must change to generate an event.
 * @param id id of the wiimote concerned
 * @param angle minimum angle detected by an event
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setOrientThreshold
(JNIEnv *env, jobject obj, jint id, jfloat thresh) {
	wiiuse_set_orient_threshold(wiimotes[id-1], thresh);
}

/**
 * Set how much acceleration must change to generate an event.
 * @param id id of the wiimote concerned
 * @param value minimum value detected by an event
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setAccelThreshold
(JNIEnv *env, jobject obj, jint id, jint val) {
	wiiuse_set_accel_threshold(wiimotes[id-1], val);	
}

/**
 * Set alpha smoothing parameter for the given id.
 * @param id id of the wiimote concerned
 * @param value alpha smoothing value
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setAlphaSmoothing
(JNIEnv *env, jobject obj, jint id, jfloat val) {
	wiiuse_set_smooth_alpha(wiimotes[id-1], val);
}

/**
 * Try to resync with the wiimote by starting a new handshake.
 * @param id id of the wiimote concerned
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_reSync
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_resync(wiimotes[id-1]);
}

/**
 * Make the the accelerometers give smoother results.
 * This is set by default.
 * @param id the id of the wiimote concerned
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateSmoothing
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_flags(wiimotes[id-1], WIIUSE_SMOOTHING, 0);
}

/**
 * Make the the accelerometers give raw results.
 * @param id the id of the wiimote concerned
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateSmoothing
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_flags(wiimotes[id-1], 0, WIIUSE_SMOOTHING);
}

/**
 * Make the wiimote generate an event each time we poll.
 * Not set by default.
 * @param id the id of the wiimote concerned
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateContinuous
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_flags(wiimotes[id-1], WIIUSE_CONTINUOUS, 0);
}

/**
 * Make the wiimote generate an event only when there is one.
 * (default behavior)
 * @param id the id of the wiimote concerned
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateContinuous
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_flags(wiimotes[id-1], 0, WIIUSE_CONTINUOUS);
}

/**
 * Notify wiiuse that your screen has an aspect ratio of 4/3.
 * @param id the id of the wiimote concerned
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setScreenRatio43
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_aspect_ratio(wiimotes[id-1], WIIUSE_ASPECT_4_3);
}

/**
 * Notify wiiuse that your screen has an aspect ratio of 16/9.
 * @param id the id of the wiimote concerned
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setScreenRatio169
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_aspect_ratio(wiimotes[id-1], WIIUSE_ASPECT_4_3);
}

/**
 * Notify wiiuse that the sensor bar is above your screen.
 * @param id the id of the wiimote concerned
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setSensorBarAboveScreen
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_ir_position(wiimotes[id-1], WIIUSE_IR_ABOVE);
}

/**
 * Notify wiiuse that the sensor bar is below your screen.
 * @param id the id of the wiimote concerned
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setSensorBarBelowScreen
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_set_ir_position(wiimotes[id-1], WIIUSE_IR_BELOW);
}

/**
 * Set virtual screen resolution. It is used to automatically 
 * compute the position of a cursor on this virtual screen
 *  using the sensor bar. These results come in the IREvent.
 * @param id the id of the wiimote concerned
 * @param x x resolution.
 * @param y y resolution.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setVirtualScreenResolution
(JNIEnv *env, jobject obj, jint id, jint x, jint y) {
	wiiuse_set_ir_vres(wiimotes[id-1], x, y);
}

/**
 * Get status from the wiimotes and send it through call backs.
 * 
 * @param id the id of the wiimote. Must be 1 or 2.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_getStatus
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_status(wiimotes[id-1]);
}

/**
 * Set the normal and expansion handshake timeouts.
 * 
 * @param id
 *            the id of the wiimote concerned.
 * @param nbWiimote
 *            Number of wiimotes connected.
 * @param normalTimeout
 *            The timeout in milliseconds for a normal read.
 * @param expansionTimeout
 *            The timeout in millisecondsd to wait for an expansion
 *            handshake.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setTimeout
  (JNIEnv *env, jobject obj, jint id, jint nbWiimote, jshort normalTimeout, jshort expansionTimeout){
	wiiuse_set_timeout(wiimotes, nbWiimote, normalTimeout, expansionTimeout);
}

/**
 * Set the IR sensitivity.
 * 
 * @param id
 *            the id of the wiimote concerned.
 * @param level
 *            1-5, same as Wii system sensitivity setting. If the level is <
 *            1, then level will be set to 1. If the level is > 5, then
 *            level will be set to 5.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setIrSensitivity
  (JNIEnv *env, jobject obj, jint id, jint level){
	wiiuse_set_ir_sensitivity(wiimotes[id-1], level);
}


/**
 * Get status and values from the wiimotes and send it through callbacks.
 * @param wim the wiimote object to fill with the datas.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_specialPoll
(JNIEnv *env, jobject obj, jobject gath) {

	/* Variables Declarations */
	int i;
	short leds = 0;
	jclass cls = (*env)->GetObjectClass(env, gath);
	jmethodID mid;
	
	if (wiiuse_poll(wiimotes, nbMaxWiiMotes)) {
		/*
		 *	This happens if something happened on any wiimote.
		 *	So go through each one and check if anything happened.
		 */
		for (i=0; i < nbMaxWiiMotes; ++i) {
			switch (wiimotes[i]->event) {
				case WIIUSE_EVENT:
				/* a generic event occured */
				mid = (*env)->GetMethodID(env, cls, "prepareWiiMoteEvent", "(ISSS)V");
				if (mid == 0) {
					return;
				}
				(*env)->CallVoidMethod(env, gath, mid, wiimotes[i]->unid, wiimotes[i]->btns,
						wiimotes[i]->btns_released, wiimotes[i]->btns_held);
				/*
				 *	If IR tracking is enabled then print the coordinates
				 *	on the virtual screen that the wiimote is pointing to.
				 *
				 *	Also make sure that we see at least 1 dot.
				 */
				if (WIIUSE_USING_IR(wiimotes[i])) {
					int a = 0;

					mid = (*env)->GetMethodID(env, cls, "prepareIRevent",
							"(IIIIIIIIISS)V");
					if (mid == 0) {
						return;
					}
					(*env)->CallVoidMethod(env, gath, mid,
							wiimotes[i]->ir.x, wiimotes[i]->ir.y, wiimotes[i]->ir.z,
							wiimotes[i]->ir.ax, wiimotes[i]->ir.ay,
							wiimotes[i]->ir.vres[0], wiimotes[i]->ir.vres[1],
							wiimotes[i]->ir.offset[0], wiimotes[i]->ir.offset[1],
							wiimotes[i]->ir.pos, wiimotes[i]->ir.aspect);

					mid = (*env)->GetMethodID(env, cls, "addIRPointToPreparedWiiMoteEvent",
							"(IISSS)V");
					if (mid == 0) {
						return;
					}
					/* go through each of the 4 possible IR sources */
					for (; a < 4; a++) {
						/* check if the source is visible */
						if (wiimotes[i]->ir.dot[a].visible) {
							(*env)->CallVoidMethod(env, gath, mid,
									wiimotes[i]->ir.dot[a].x, wiimotes[i]->ir.dot[a].y,
									wiimotes[i]->ir.dot[a].rx, wiimotes[i]->ir.dot[a].ry,
									wiimotes[i]->ir.dot[a].size);
						}
					}
				}

				/* Motion Sensing */
				if (WIIUSE_USING_ACC(wiimotes[i])) {
					/* set orientation and gravity force */
					mid = (*env)->GetMethodID(env, cls,
							"addMotionSensingValues", "(FIZFFFFFFFSSS)V");
					if (mid == 0) {
						return;
					}
					(*env)->CallVoidMethod(env, gath, mid,
							wiimotes[i]->orient_threshold, wiimotes[i]->accel_threshold,
							WIIMOTE_IS_FLAG_SET(wiimotes[i],WIIUSE_SMOOTHING), wiimotes[i]->accel_calib.st_alpha,
							wiimotes[i]->orient.roll, wiimotes[i]->orient.pitch, wiimotes[i]->orient.yaw,
							wiimotes[i]->orient.a_roll, wiimotes[i]->orient.a_pitch,
							wiimotes[i]->gforce.x, wiimotes[i]->gforce.y, wiimotes[i]->gforce.z,
							wiimotes[i]->accel.x, wiimotes[i]->accel.y, wiimotes[i]->accel.z);
				}
 
				/* add generic event to java object used to gather events in c environment */
				mid = (*env)->GetMethodID(env, cls, "addWiimoteEvent",
						"()V");
				if (mid == 0) {
					return;
				}
				(*env)->CallVoidMethod(env, gath, mid);
				break;

				case WIIUSE_STATUS:
				/* a status event occured */
				mid = (*env)->GetMethodID(env, cls, "addStatusEvent", "(IZFSZIZZZZ)V");
				if (mid == 0) {
					return;
				}
				/* LEDS */
				if (WIIUSE_IS_LED_SET(wiimotes[i], 1)) leds += 1;
				if (WIIUSE_IS_LED_SET(wiimotes[i], 2)) leds += 2;
				if (WIIUSE_IS_LED_SET(wiimotes[i], 3)) leds += 4;
				if (WIIUSE_IS_LED_SET(wiimotes[i], 4)) leds += 8;

				(*env)->CallVoidMethod(env, gath, mid,
						wiimotes[i]->unid, WIIMOTE_IS_SET(wiimotes[i], WIIMOTE_STATE_CONNECTED),
						wiimotes[i]->battery_level, leds, WIIUSE_USING_SPEAKER(wiimotes[i]),
						wiimotes[i]->exp.type,WIIMOTE_IS_SET(wiimotes[i], WIIMOTE_STATE_RUMBLE),
						WIIMOTE_IS_FLAG_SET(wiimotes[i],WIIUSE_CONTINUOUS),
						WIIUSE_USING_IR(wiimotes[i]),WIIUSE_USING_ACC(wiimotes[i]));
				break;

				case WIIUSE_DISCONNECT:
				/* the wiimote disconnected */
				mid = (*env)->GetMethodID(env, cls, "addDisconnectionEvent", "(I)V");
				if (mid == 0) {
					return;
				}
				(*env)->CallVoidMethod(env, gath, mid, wiimotes[i]->unid);
				break;

				default:
				break;
			}
		}
	}
}
