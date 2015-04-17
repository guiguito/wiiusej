#Quick user guide.

# Quick doc #

```

1) import wiiusej.jar in your java project.

2) put the 2 dlls files libWiiUseJ.dll and wiiuse.dll at the root of your project (or the two .so files if you are under linux).
Connect your wiimote to your bluetooth stack.

3) In your code : Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(X, true);//x is the number of wiimotes to connect. True : make it rumble the first time you get the wiimotes.

4) Make a class (MyListener) implementing the WiimoteListener interface.

5) wiimote[0].addWiiMoteEventListeners(new MyListener());//register my class as a listener of the first wiimote.

===> MyListener will receive events from the wiimotes is registered on.

```