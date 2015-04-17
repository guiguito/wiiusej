#User documentation.



# Detailed documentation #


WiiuseJ is very easy to use. You have very few things to do get info from your wiimote(s).


First, make a project. Import wiiusej.jar and put the 2 dlls files at the root of your project (or the two .so files if you are under linux).



Then make a class (for example MyClass) implementing WiimoteListener with it.
For example, in the following example, each method implemented receives the differents event Types : ButtonsEvent, IREvent, MotionSensingEvent, ExpansionEvent, StatusEvent, DisconnectionEvent, NunchukInsertedEvent and NunchukRemovedEvent(check Javadoc to see what infos you get from each event type). In this case in each method we only display the event received.


```
public void onButtonsEvent(WiimoteButtonsEvent arg0) {
        System.out.println(arg0);
        if (arg0.isButtonAPressed()){
            WiiUseApiManager.shutdown();
        }
    }

    public void onIrEvent(IREvent arg0) {
        System.out.println(arg0);
    }

    public void onMotionSensingEvent(MotionSensingEvent arg0) {
        System.out.println(arg0);
    }

    public void onExpansionEvent(ExpansionEvent arg0) {
        System.out.println(arg0);
    }

    public void onStatusEvent(StatusEvent arg0) {
        System.out.println(arg0);
    }

    public void onDisconnectionEvent(DisconnectionEvent arg0) {
        System.out.println(arg0);
    }

    public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
        System.out.println(arg0);
    }

    public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
        System.out.println(arg0);
    }


```


Next you can make a main. Like this :


```
    public static void main(String[] args) {
        Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
        Wiimote wiimote = wiimotes[0];
        wiimote.activateIRTRacking();
        wiimote.activateMotionSensing();
        wiimote.addWiiMoteEventListeners(new MyClass());
    }
```


Here you ask to try to connect to 1 wiimote (and make it rumble, the first time you call this method). You can ask for more if more are connected. It returns you an array with the connected wiimote. Then you can manipulate each wiimote and activate IR tracking, motion tracking and set up other parameters if you want... Check javadoc for more details.


```
wiimote.addWiiMoteEventListeners(new MyClass());
```


This line permits you to set an instance of MyClass as a listener of the wiimote.
When events from this wiimote occur they are sent to every WiimoteListener. Here there is only one, an instance of MyClass.


If you launch this program it will display the content of every events.



An example of use of ButtonsEvent can be this one. If we want to end our program you can shutdown the WiiuseManager(Object that manages all wiimotes) when the button A is pressed.
Example:

```
public void onButtonsEvent(ButtonsEvent arg0) {
        System.out.println(arg0);
        if (arg0.isButtonAPressed()){
            WiiUseApiManager.getInstance().shutdown();
        }
    }
```

This sample app can be downloaded in the download section. It is a Netbeans project.