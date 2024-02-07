package com.mygdx.game.Config;

public class LauncherConfig {
    private int SCREEN_WIDTH, SCREEN_HEIGHT;
    private String GAME_TITLE;
    private String ICON_16, ICON_32, ICON_48;


    public LauncherConfig() {
        LauncherConfig(1280, 720, "OOPA1", "icon16.png", "icon32.png", "icon48.png");
    }

    public String getICON_16() {
        return ICON_16;
    }

    public void setICON_16(String ICON_16) {
        this.ICON_16 = ICON_16;
    }

    public String getICON_32() {
        return ICON_32;
    }

    public void setICON_32(String ICON_32) {
        this.ICON_32 = ICON_32;
    }

    public String getICON_48() {
        return ICON_48;
    }

    public void setICON_48(String ICON_48) {
        this.ICON_48 = ICON_48;
    }



    public void LauncherConfig(int SCREEN_WIDTH, int SCREEN_HEIGHT , String title, String icon16, String icon32, String icon48){
        setSCREEN_WIDTH(SCREEN_WIDTH);
        setSCREEN_HEIGHT(SCREEN_HEIGHT);
        setGAME_TITLE(title);
        setICON_16(icon16);
        setICON_32(icon32);
        setICON_48(icon48);
    }

    public int getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    public void setSCREEN_WIDTH(int SCREEN_WIDTH) {
        this.SCREEN_WIDTH = SCREEN_WIDTH;
    }

    public int getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

    public void setSCREEN_HEIGHT(int SCREEN_HEIGHT) {
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
    }

    public String getGAME_TITLE() {
        return GAME_TITLE;
    }

    public void setGAME_TITLE(String GAME_TITLE) {
        this.GAME_TITLE = GAME_TITLE;
    }
}
