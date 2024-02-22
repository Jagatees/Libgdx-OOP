package com.mygdx.game.Config;


/**
 * Configuration settings for the game launcher, including screen size, game title, and icons.
 */
public class LauncherConfig {

    private int screenWidth, screenHeight;
    private String gameTitle,icon16, icon32, icon48 ;

    /**
     * Constructor for LauncherConfig that sets default values.
     */
    public LauncherConfig() {
        LauncherConfig(1280, 720, "OOPA1", "configImage/icon16.png", "configImage/icon32.png", "configImage/icon48.png");
    }

    /**
     * Overloaded constructor to set custom launcher configuration.
     *
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     * @param gameTitle The title of the game.
     * @param icon16 Path to the 16x16 icon.
     * @param icon32 Path to the 32x32 icon.
     * @param icon48 Path to the 48x48 icon.
     */
    public void LauncherConfig(int screenWidth, int screenHeight , String gameTitle, String icon16, String icon32, String icon48){
        setScreenWidth(screenWidth);
        setScreenHeight(screenHeight);
        setGameTitle(gameTitle);
        setIcon16(icon16);
        setIcon32(icon32);
        setIcon48(icon48);
    }

    /**
     * Getters
     */
    public String getIcon16() {
        return icon16;
    }
    public String getIcon32() {
        return icon32;
    }
    public String getGameTitle() {
        return gameTitle;
    }
    public String getIcon48() {
        return icon48;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Setters
     */
    public void setIcon32(String icon32) {
        this.icon32 = icon32;
    }
    public void setIcon48(String icon48) {
        this.icon48 = icon48;
    }
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }
    public void setIcon16(String icon16) {
        this.icon16 = icon16;
    }
}
