#Frequently Asked Questions

# Frequently Asked Questions #

**Q :** Can WiiuseJ send audio to the wiimote ?

A : Wiiuse doesn't support it so WiiuseJ can't support either.


**Q :** Why do i got this error ?
```

Exception in thread "Thread-0" java.lang.NoSuchMethodError: java.util.Arrays.copyOfRange([Ljava/lang/Object;II)[Ljava/lang/Object;

at wiiusej.wiiuseapievents.EventsGatherer.getEvents(EventsGatherer.java:227)

at wiiusej.WiiUseApiManager.run(WiiUseApiManager.java:415)

[INFO] Connected to wiimote [id 1].

```

A : So far you need java 1.6 to use WiiuseJ. Here a method from java 1.6 is missing in the java you have. I'll fix it in another release.


**Q :** How can i reconnect the wiimotes and find new wiimotes.

A : Shutdown WiiuseJ: WiiUseApiManager.shutdown(); and get wiimotes again : WiiUseApiManager.getWiimotes(2, true);


**Q :** I have this error: Error: [ERROR](ERROR.md) Unable to
determine bluetooth stack type. What can i do ?

A : You can try to force the type of bluetooth stack you got. This is only for windows. For this get the wiimotes this way : WiiUseApiManager.getWiimotes(2, true, WiiUseApiManager.WIIUSE\_STACK\_MS);   if you got a windows bluetooth stack or widcomm. Use WiiUseApiManager.WIIUSE\_STACK\_BLUESOLEIL  if you got bluesoleil.