package com.dancev.sinatatopka.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dancev.sinatatopka.SinataTopka;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	      config.title = "Sinata Topka";
	      config.width = 800;
	      config.height = 480;
		new LwjglApplication(new SinataTopka(), config);
	}
}
