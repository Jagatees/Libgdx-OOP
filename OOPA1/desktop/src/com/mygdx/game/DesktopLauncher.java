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
		config.setWindowIcon(launcherConfig.getIcon16(), launcherConfig.getIcon32(), launcherConfig.getIcon48());
		config.setTitle(launcherConfig.getGameTitle());
		config.setWindowedMode(launcherConfig.getScreenWidth(), launcherConfig.getScreenHeight());
		new Lwjgl3Application(new Game(), config);
	}
}
