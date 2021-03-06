package com.ieti.ietigame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ieti.ietigame.IetiGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "IETI Game!";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new IetiGame(), config);
	}
}
