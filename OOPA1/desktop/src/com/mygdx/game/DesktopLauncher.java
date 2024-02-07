package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Config.LauncherConfig;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		LauncherConfig launcherConfig = new LauncherConfig();
		Lwjgl3ApplicationConfiguration config;
		config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowIcon(launcherConfig.getICON_16(), launcherConfig.getICON_32(), launcherConfig.getICON_48());
		config.setTitle(launcherConfig.getGAME_TITLE());
		config.setWindowedMode(launcherConfig.getSCREEN_WIDTH(), launcherConfig.getSCREEN_HEIGHT());
		new Lwjgl3Application(new Game(), config);
	}
}
