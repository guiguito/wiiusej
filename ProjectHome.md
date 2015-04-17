# **WiiuseJ** #


---


![http://www.easy-dj.com/wiiusej12.png](http://www.easy-dj.com/wiiusej12.png)

## Latest News: ##

_**19/01/09:**_
I'm currently looking for a job :D I'm too bored in my current job and i'll quit it at the beginning of next april. If some people could be interested by my programming skills i'd be glad to hear about it! Even more if it is wiimote related ;) Please find my resume on the following link : http://www.easy-dj.com/CV/CV.zip.

_**18/01/09:**_
A different news but an important one :D Thanks to Young-Jae i added in the download section the new libwiiuse.so for linux. He pointed out the fact that Ubuntu Intrepid (8.10) now uses libbluetooth version 3 instead of version 2. He recompiled it and made it available to everyone else. Greetings to him ! Download is here : http://wiiusej.googlecode.com/files/libwiiuse.zip

_**18/01/09:**_
Happy new year everybody !!! Best wishes to all of you !
Some news about WiiuseJ. I haven't given up the project. No new release have been plan for the moment because the current version is working pretty well and no new features are in wiiuse. I haven't received too much complaints about wiiusej quality yet :D However i received two features requests for the next release :
  * Filter the mouse movement to make it smoother.
  * Add the possibility to get the bluetooth address of the connected wiimote.
Those requests are understandable and be sure that i will add it in the next release. But to be honest i won't do a release just for this because it is too much work for so few things :)
Last news but not least. You may be wondering when will be the next release. None are planned because the current one support all wiiuse features and wiiuse hasn't evolved for a while. Fortunately the wiiuse project has recently gained a bit of activity and they are looking for a person to make the MAC version. Therefore WiiuseJ may become compatible with MAC. You can check on wiiuse site for more info about it and stay tuned on WiiuseJ :D


_**21/11/08:**_
Hello, a small news to tell you that in the downloads section you can find the two libs needed for **linux 64 Bit**. Thank you to kristian for compiling it and providing it to the community. So if you are under linux 64 Bit, please do download the two libs (zip file containing libWiiuseJ.so and wiiuse.so) and replace it in WiiuseJ. The forum is still available if you have any issue.

## Presentation: ##

**WiiuseJ** is a **java API** to use wiimotes on your computer. This API is built on top of an excellent API called [Wiiuse](http://wiiuse.net) using _JNI_.

WiiuseJ intents to be a very _simple_, _easy to use_ and _lightweight_ java API for wiimotes.

Some of the great aspects of this API are :


  * The **compatibility** which is as large as Wiiuse's API compatibility
  * Cross-platform : **Windows** and **linux** (like Wiiuse)
  * **Easy to use**

## Features: ##
You can use all almost all features of the [Wiiuse](http://www.wiiuse.net/?nav=docs) C api for the Wiimote (not the expansions). Main features are :
  * Get accelerometer events on different forms (Orientation, GForce and raw Acceleration)
  * Get events from infrared Camera
  * Get button events
  * Make the wiimote vibrate
  * Set lights of each wiimotes
  * Get events from expansions
  * ... (see special function from wiiuse)

## Example of project made with WiiUseJ ##

**WiimoteDesktopControl :
http://eduard.metzger.googlepages.com/wiimotedesktopcontrol**

**Ajax and WiiuseJ for a dart game :
http://ajaxian.com/archives/wii-darts-powering-ajax-applications-with-wii-controllers**


**HeadTracking with WiiuseJ :
http://www.youtube.com/watch?v=KWVtBzAnuKg**

**JOGL and wiiuseJ demo :
http://www.youtube.com/watch?v=mgTwWzhTVEY**


**Java3D and WiiuseJ for mars visualization :
http://videos.softmemo.com/v-BHK7fTOhk3A.html**

## Other Java API for wiimotes: ##

Other java APIs for wiiimotes already exist : [WiiremoteJ](http://www.wiili.org/index.php/WiiremoteJ), [motej](http://motej.sourceforge.net/) and [Wiimote simple](http://code.google.com/p/wiimote-simple/). Those are full java APIs using jsr-082 specifications and compatibility is often not as good as this api.

## Screenshot: ##

Here is the test GUI launched:

![http://www.easy-dj.com/wiiusej12.png](http://www.easy-dj.com/wiiusej12.png)

This GUI permits you to test all the functionalities provided by WiiuseJ for each wiimote.

## Requirements: ##

All you need is a bluetooth receiver and a wiimote.

Next you need java **1.6**.

Windows version has been tested with [bluesoleil 2.3](http://www.bluesoleil.com/download/index.asp?topic=bluesoleil_edr) stack. Wiiuse is supposed to work with almost every windows stack. Please let us some feedbacks in the discussion group to indicate which stack does work.
Linux version has been tested also and works well.


## Disclaimer: ##

This API was made to have a simple working java API to use wiimotes. I had problems when i tried to use WiiremoteJ with my windows + bluesoleil laptop. Now i have everything i need to have some fun using a wiimote. Consequently i'm not planning to implement a lot more features **unless some people ask for it** or people implement it themselves(this is an open source project!!!).



## Older news: ##


_**29/09/08:**_
Hello, just a small news to announce a new release of **WiiUseJ : 0.12b**. Since there is no new version of wiiuse this release is a minor one. It only fixes a bug happening when **disconnecting several wiimotes** and make wiiusej compatible with **java 1.5**. The compatibility with java 1.5 might need to recompile the .dll and .so, if it is the case let me know and i'll make it for you. Those fixes come for both from two users of wiiusej. Many thanks to **jbrownbridge** and **Oskard** for their help !!! The javadoc and the examples didn't change.


_**30/07/08:**_
Here is the new version of **WiiuseJ**. It adds the full support of the guitar hero 3 controller and the classic controller. It is always as easy to use as before. Feel free to leave any feedback on the forum, **report bugs**, ask for help or new features and **please show us the projects you make using WiiuseJ**. Now WiiuseJ almost **fully supports wiiuse features**. Only reading the wiimote memory is not ported to java(and i'm not planning to implement it). Now i'll wait for the next wiiuse release !


_**25/06/08:**_
I made the next version. The new features are just the guitar hero 3
and classic controller support. I've been able to borrow the guitar and test it with the GUI, it works fine. Unfortunately i couldn't borrow a classic controller and i don't really want to buy it. Can someone test it and tell me where he sees bugs in the GUI ?
http://groups.google.com/group/wiiusej-users/attach/b211eb11ff0424f8/wiiuse.zip?part=4
Could you give feedbacks in the following topic :
http://groups.google.com/group/wiiusej-users/browse_thread/thread/2beb0329ee8fdcf0


_**01/06/08:**_  I am still working on wiiusej and i recently programmed support for Guitar Hero 3 controller and Classic Controller. I haven't made the GUI yet and i can't test it because i don't have any of these extensions. Is there anyone who could test the DEV version of WiiuseJ with these extensions? Download it in the users discussion group : Topic building wiiusej.dll http://groups.google.com/group/wiiusej-users/attach/f31e9af1d6c78c3e/wiiuseJ_dev_version_controllers.zip?part=4.
And please give feedbacks.

_**01/06/08:**_ WiiuseJ 0.12 is out. Added nunchuk support !!!! It is now based on wiiuse 0.12 and plenty of other improvements !!! The main improvements are:
  * added nunchuk support, with separated thresholds configuration
  * background architecture greatly improved(notably to andle easily different extensions)
  * you can now sleep in event-handling method(it will delay wiimote polling)
  * added ir\_sensitivity functions
  * added a method to set up timeouts
  * fixed the Z coordinates on the IR Event
  * you can now reconnect wiimotes during runtime
  * possibility to force bluetooth stack detection
  * project cleaned


_**25/05/08:**_ Hello, all. Just a small news to make some excuses for the delay for this new version. I worked really a lot on it this week but i'm still facing some small bugs and i don't like to release crappy things. Well, really i'm sorry for the delay. It may take another week to release it and fix these issues. I may also release the new version with some known bugs, just to know if everybody are facing it on its configuration. Anyway, this version will have a lot of improvements behind the scene(it will help to implement the other extensions) and for the user (With at least the nunchuk support!).

_**18/05/08:**_ Just a small reminder to tell you that so far you need **java 1.6** to use WiiuseJ. I may make it compatible with other java versions in a future release.

_**13/05/08:**_ Here is a great news. WiiuseJ has been used for a demonstration at the javaone.
More details here: http://ajaxian.com/archives/wii-darts-powering-ajax-applications-with-wii-controllers.
Concerning the new version, i've been working on it for a while now. I faced some problems with synchronization, that i finally bypassed. I got a working version, but i still have more tests to do and the GUI to update. For what i can tell you so far, the API has been cleaned and updated with latest enhancements of wiiuse and the major improvement for this release will be the **nunchuk support** !!! Stay tuned for the new release within the next two weeks !

_**21/03/08:**_ Here is the new version of WiiuseJ: v0.11. It is now compatible with **linux** thanks to plyd who compiled it. Another user asked me to change the api so that you can choose wether or not you make the wiimotes rumble the first time you connect to it.

_**14/03/08:**_ I did a small documentation to make a small sample program we WiiuseJ. This sample program is downloadable, as a netbeans project, in the download section. Please let me know if you want more documentation or if you have any questions or features requests. You can reach me through the discussion [group](http://groups.google.com/group/wiiusej-users).

_**10/03/08:**_ First release of WiiuseJ API. **WiiuseJ 0.1**. Currently only the windows version exists. The linux version will come soon (normally i just need to compile a C file under linux). Please leave some feedbacks on the user discussion group and help us to track bugs.