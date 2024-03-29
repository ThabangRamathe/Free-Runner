package com.freerunner.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.freerunner.Freerunner;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height=Freerunner.HEIGHT;
		config.width=Freerunner.WIDTH;
		config.title=Freerunner.TITLE;
		new LwjglApplication(new Freerunner(), config);
	}
}
