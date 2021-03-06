/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class wiiusej_WiiUseApi */

#ifndef _Included_wiiusej_WiiUseApi
#define _Included_wiiusej_WiiUseApi
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     wiiusej_WiiUseApi
 * Method:    loadLibrary
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_loadLibrary
  (JNIEnv *, jobject);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    doConnections
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_wiiusej_WiiUseApi_doConnections
  (JNIEnv *, jobject);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    closeConnection
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_closeConnection
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    shutdownApi
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_shutdownApi
  (JNIEnv *, jobject);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateRumble
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateRumble
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateRumble
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateRumble
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateIRTracking
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateIRTracking
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateIRTracking
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateIRTracking
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateMotionSensing
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateMotionSensing
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateMotionSensing
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateMotionSensing
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setLeds
 * Signature: (IZZZZ)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setLeds
  (JNIEnv *, jobject, jint, jboolean, jboolean, jboolean, jboolean);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    setOrientThreshold
 * Signature: (IF)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_setOrientThreshold
  (JNIEnv *, jobject, jint, jfloat);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateSmoothing
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateSmoothing
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateSmoothing
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateSmoothing
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    activateContinuous
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_activateContinuous
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    deactivateContinuous
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_deactivateContinuous
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    getStatus
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_getStatus
  (JNIEnv *, jobject, jint);

/*
 * Class:     wiiusej_WiiUseApi
 * Method:    specialPoll
 * Signature: (Lwiiusej/WiiMoteEvent;)V
 */
JNIEXPORT void JNICALL Java_wiiusej_WiiUseApi_specialPoll
  (JNIEnv *, jobject, jobject);

#ifdef __cplusplus
}
#endif
#endif
