package com.mygdx.game.Config;

public class LauncherConfig {

    private int screenWidth, screenHeight;
    private String gameTitle,icon16, icon32, icon48 ;

    public LauncherConfig() {
        LauncherConfig(1280, 720, "OOPA1", "icon16.png", "icon32.png", "icon48.png");
    }

    public void LauncherConfig(int screenWidth, int screenHeight , String gameTitle, String icon16, String icon32, String icon48){
        setScreenWidth(screenWidth);
        setScreenHeight(screenHeight);
        setGameTitle(gameTitle);
        setIcon16(icon16);
        setIcon32(icon32);
        setIcon48(icon48);
    }
    public String getIcon16() {
        return icon16;
    }

    public void setIcon16(String icon16) {
        this.icon16 = icon16;
    }

    public String getIcon32() {
        return icon32;
    }

    public void setIcon32(String icon32) {
        this.icon32 = icon32;
    }

    public String getIcon48() {
        return icon48;
    }

    public void setIcon48(String icon48) {
        this.icon48 = icon48;
    }





    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }
}
