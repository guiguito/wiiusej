#ifndef WIN32
#include <unistd.h>
#define WIIUSE_PATH		"./wiiuse.so"
#else
#define WIIUSE_PATH		"wiiuse.dll"
#endif

#include "wiiusej_WiiUseApi.h"
#include "wiiuse.h"
/*
 *	These are some identifiers for wiimotes
 *
 *	See below in main() for what they are used for.
 */
#define WIIMOTE_ID_1		1
#define WIIMOTE_ID_2		2
#define WIIMOTE_STATE_RUMBLE			0x08
#define WIIMOTE_STATE_CONNECTED			0x04
#define WIIMOTE_IS_SET(wm, s)			((wm->state & (s)) == (s))
#define WIIMOTE_IS_FLAG_SET(wm, s)		((wm->flags & (s)) == (s))

/****************** CALLBACKS DECLARATIONS *************************/

static void handle_event(struct wiimote_t* wm);
static void handle_ctrl_status(struct wiimote_t* wm, int attachment,
		int speaker, int ir, int led[4], float battery_level);
static void handle_disconnect(wiimote* wm);

static void copy_common_status(struct wiimote_t* wm);/* function with common code for callbacks */

/********************* VARIABLES DECLARATIONS *****************************/

/*
 *	Make a temp array of wiimote ids.
 *	Here I only anticipate connecting up to
 *	two wiimotes.  Each wiimote connected
 *	will get one of these ids.
 */
static int ids[] = { WIIMOTE_ID_1, WIIMOTE_ID_2 };
static wiimote** wiimotes;
static JNIEnv *globalEnv;
static jobject globalObj;
static jobject globalWim;

/****************** GENERAL FUNCTIONS DEFINITIONS *************************/

/*
 *	Load the wiiuse library
 *
 *	This needs to be done before anything else can happen
 *	wiiuse_startup() will return the version of the library loaded.
 * 
 *  @return 0 if there is an error, 1 if everything is ok.
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_loadLibrary
(JNIEnv *env, jobject obj) {
	const char* version;
	version = wiiuse_startup(WIIUSE_PATH);
	//printf("Wiiuse Version = %s\n", version);
	if (!version) {
		return 0;
	}

	/* no problems loading library */
	return 1;
}

/**
 * Try to connect to 2 wiimotes.
 * Make them rumble to show they are connected.
 * 
 * @return 0 if there is an error otherwise it returns 
 * 			the number of wiimotes connected..
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_doConnections
(JNIEnv *env, jobject obj) {

	/* variables declarations */
	int found, connected;

	/*
	 *	Initialize an array of wiimote objects.
	 *
	 *	The first parameter is the number of wiimotes
	 *	I want to create.  I only have two wiimotes
	 *	so I'm limiting the test to just 2.
	 *
	 *	Then I get it the array of ids and a couple
	 *	callback functions to invoke when something
	 *	happens on one of the wiimotes.
	 *
	 *	handle_event gets called when a generic event occurs (button press, motion sensing, etc)
	 *	handle_ctrl_status gets called when a response to a status request arrives (battery power, etc)
	 *	handle_disconnect gets called when the wiimote disconnect (holding power button)
	 */
	wiimotes = wiiuse_init(2, ids, handle_event, handle_ctrl_status,
			handle_disconnect);

	/*
	 *	Find wiimote devices
	 *	Now we need to find some wiimotes.
	 *	Give the function the wiimote array we created, and tell it there
	 *	are 2 wiimotes we are interested in.
	 *	Set the timeout to be 5 seconds.
	 *	This will return the number of actual wiimotes that are in discovery mode.
	 */
	found = wiiuse_find(wiimotes, 2, 5);
	if (!found) return 0;

	/*
	 *	Connect to the wiimotes
	 *	Now that we found some wiimotes, connect to them.
	 *	Give the function the wiimote array and the number of wiimote devices we found.
	 *	This will return the number of established connections to the found wiimotes.
	 */
	connected = wiiuse_connect(wiimotes, 2);
	if (!connected) return 0;

	//no problems during connection show that wiimotes are connected

	/*
	 *	Now set the LEDs and rumble for a second so it's easy
	 *	to tell which wiimotes are connected (just like the wii does).
	 */
	wiiuse_set_leds(wiimotes[0], WIIMOTE_LED_1);
	wiiuse_set_leds(wiimotes[1], WIIMOTE_LED_2);
	wiiuse_rumble(wiimotes[0], 1);
	wiiuse_rumble(wiimotes[1], 1);

#ifndef WIN32
	usleep(200000);
#else
	Sleep(200);
#endif

	wiiuse_rumble(wiimotes[0], 0);
	wiiuse_rumble(wiimotes[1], 0);

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
	wiiuse_shutdown();
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
 * Get status from the wiimotes and send it through call backs.
 * 
 * @param id the id of the wiimote. Must be 1 or 2.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_getStatus
(JNIEnv *env, jobject obj, jint id) {
	wiiuse_status(wiimotes[id-1]);
}

/**
 * Get status and values from the wiimotes and send it through callbacks.
 * @param wim the wiimote object to fill with the datas.
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_specialPoll
(JNIEnv *env, jobject obj, jobject wim) {

	globalEnv = env;
	globalObj = obj;
	globalWim = wim;

	wiiuse_poll(wiimotes, 2);
}

/****************** CALLBACKS DEFINITIONS *************************/

/**
 *	@brief Callback that handles an event.
 *
 *	@param wm		Pointer to a wiimote_t structure.
 *
 *	This function is called automatically by the wiiuse library when an
 *	event occurs on the specified wiimote.
 */
static void handle_event(struct wiimote_t* wm) {

	/* Variables Declarations */
	jclass cls = (*globalEnv)->GetObjectClass(globalEnv, globalWim);
	jmethodID mid;

	/* fill java class */
	copy_common_status(wm);

	/* Set all buttons */
	mid = (*globalEnv)->GetMethodID(globalEnv, cls, "setAllButtons", "(SSS)V");
	if (mid == 0) {
		return;
	}
	(*globalEnv)->CallVoidMethod(globalEnv, globalWim, mid, wm->btns,
			wm->btns_released, wm->btns_held);

	/*
	 *	If IR tracking is enabled then print the coordinates
	 *	on the virtual screen that the wiimote is pointing to.
	 *
	 *	Also make sure that we see at least 1 dot.
	 */
	if (WIIUSE_USING_IR(wm)) {
		int i = 0;
		/* go through each of the 4 possible IR sources */
		for (; i < 4; ++i) {
			/* check if the source is visible */
			if (wm->ir.dot[i].visible) {
				cls = (*globalEnv)->GetObjectClass(globalEnv, globalWim);
				mid = (*globalEnv)->GetMethodID(globalEnv, cls, "addIRpoint",
						"(II)V");
				if (mid == 0) {
					return;
				}
				(*globalEnv)->CallVoidMethod(globalEnv, globalWim, mid,
						wm->ir.dot[i].x, wm->ir.dot[i].y);
			}
		}
		//printf("IR cursor: (%u, %u)\n", wm->ir.x, wm->ir.y);
		//printf("IR z distance: %f\n", wm->ir.z);
	}

	/* Motion Sensing */
	if (WIIUSE_USING_ACC(wm)) {
		/* set orientation and gravity force */
		cls = (*globalEnv)->GetObjectClass(globalEnv, globalWim);
		mid = (*globalEnv)->GetMethodID(globalEnv, cls,
				"setOrientationAndGforce", "(FFFFFF)V");
		if (mid == 0) {
			return;
		}
		(*globalEnv)->CallVoidMethod(globalEnv, globalWim, mid,
				wm->orient.roll, wm->orient.pitch, wm->orient.yaw,
				wm->gforce.x, wm->gforce.y, wm->gforce.z);
	}
}

/**
 *	@brief Callback that handles a controller status event.
 *
 *	@param wm				Pointer to a wiimote_t structure.
 *	@param attachment		Is there an attachment? (1 for yes, 0 for no)
 *	@param speaker			Is the speaker enabled? (1 for yes, 0 for no)
 *	@param ir				Is the IR support enabled? (1 for yes, 0 for no)
 *	@param led				What LEDs are lit.
 *	@param battery_level	Battery level, between 0.0 (0%) and 1.0 (100%).
 *
 *	This occurs when either the controller status changed
 *	or the controller status was requested explicitly by
 *	wiiuse_status().
 *
 *	One reason the status can change is if the nunchuk was
 *	inserted or removed from the expansion port.
 */
static void handle_ctrl_status(struct wiimote_t* wm, int attachment,
		int speaker, int ir, int led[4], float battery_level) {

	/* Variables Declarations */
	jclass cls = (*globalEnv)->GetObjectClass(globalEnv, globalWim);
	jmethodID mid;
	short leds = 0;

	/* fill java class */
	copy_common_status(wm);

	/* LEDS */
	if (led[0])
		leds += 1;
	if (led[1])
		leds += 2;
	if (led[2])
		leds += 4;
	if (led[3])
		leds += 8;

	/* set values for battery, leds, speaker and attachment*/
	mid = (*globalEnv)->GetMethodID(globalEnv, cls,
			"setBatteryLedsSpeakerAttachment", "(FSZZ)V");
	if (mid == 0) {
		return;
	}
	(*globalEnv)->CallVoidMethod(globalEnv, globalWim, mid,
			battery_level, leds, speaker, attachment);

}

/**
 *	@brief Callback that handles a disconnection event.
 *
 *	@param wm				Pointer to a wiimote_t structure.
 *
 *	This can happen if the POWER button is pressed, or
 *	if the connection is interrupted.
 */
static void handle_disconnect(wiimote* wm) {
	
	/* call java method handling disconnection */

	copy_common_status(wm);

}

/**
 * Fills status variables. This method fills some status variables always filled in a WiiMoteEvent object.
 * This function is called in every callback function.
 */
static void copy_common_status(struct wiimote_t* wm) {

	/* Variables Declarations */
	jmethodID mid;
	jclass cls = (*globalEnv)->GetObjectClass(globalEnv, globalWim);

	/* set statuses */
	mid = (*globalEnv)->GetMethodID(globalEnv, cls, "setPermanentStatus",
			"(IZZZZFZZ)V");
	if (mid == 0) {
		return;
	}
	(*globalEnv)->CallVoidMethod(globalEnv, globalWim, mid,
							wm->unid, WIIMOTE_IS_SET(wm, WIIMOTE_STATE_CONNECTED),
							WIIUSE_USING_IR(wm), WIIMOTE_IS_SET(wm, WIIMOTE_STATE_RUMBLE),
							WIIUSE_USING_ACC(wm), wm->orient_threshold,
							WIIMOTE_IS_FLAG_SET(wm,WIIUSE_CONTINUOUS),
							WIIMOTE_IS_FLAG_SET(wm,WIIUSE_SMOOTHING));

}
