Very light summary on how I managed to compile Wiiusej on linux.
Contact the mailing list if you face a bug!

# Introduction #

What packages you need installed: libc-dev, libbluetooth-dev, xlibmesa-gl-dev, libglu1-mesa-dev, libsdl.

You need also Java 6 jdk installed, to get the jni interface.

You need to get `libwiiuse.so` compiled before trying to compile Wiiusej library.

# Wiiuse library #

  1. First, [download according version of Wiiuse](http://sourceforge.net/project/showfiles.php?group_id=187194) (for WiiuseJ v0.12, download Wiiuse v0.12).
  1. Second, compile the Wiiuse lib `libwiiuse.so`

```
make
make install
```

Here, if you did not install the sdl library, you may get a bug. The library is well-compiled anyway.

# Wiiusej library #
Move the `libwiiuse.so` compiled in the src directory of WiiUseJC.

''Don't forget to replace my jdk folder by yours''

```
gcc -shared -fPIC -o libWiiuseJ.so -Wall -L. -lwiiuse -I/home/vincent/jdk1.6.0_05/include -I/home/vincent/jdk1.6.0_05/include/linux wiiusej_WiiUseApi.c
```

# Launch on linux #

Put both libs `libwiiuse.so` and `libWiiuseJ.so` in the folder of `wiiusej.jar`.
Put your wiimote in discovery mode by clicking on the buttons 1 and 2 in the mean time.
Now launch
```
LD_LIBRARY_PATH=. java -Djava.library.path=. -jar wiiusej.jar
wiiuse v0.12 loaded.
  By: Michael Laforest <thepara[at]gmail{dot}com>
  http://wiiuse.net  http://wiiuse.sf.net
[INFO] Found 1 bluetooth device(s).
[INFO] Found wiimote (00:1B:7A:35:55:F6) [id 1].
[INFO] Connected to wiimote [id 1].
...
window is opening
```