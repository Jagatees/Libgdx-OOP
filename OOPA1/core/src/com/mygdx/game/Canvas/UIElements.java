package com.mygdx.game.Canvas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UIElements {

    private static final BitmapFont defaultFont = new BitmapFont();



    public static TextButton createTextButton(String text, float x, float y, int width , int height, Color backgroundColor, ChangeListener actionListener) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(backgroundColor);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        Drawable drawable = new TextureRegionDrawable(texture);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = drawable;
        style.font = defaultFont;

        TextButton button = new TextButton(text, style);
        button.setPosition(x, y);
        button.addListener(actionListener);

        return button;
    }



    public static Label createLabel(Color color) {
        return createLabelwithallInput("Button", 100 ,100, color);
    }

    public static Label createLabel(float x, float y) {
        return createLabelwithallInput("Button", x ,y, Color.BLACK);
    }
    public static Label createLabel(String text, float x, float y) {
        return createLabelwithallInput(text, x ,y, Color.BLACK);
    }
    public static Label createLabel(String text) {
        return createLabelwithallInput(text, 100 ,100, Color.BLACK);
    }

    public static Label createLabel(String text, float x, float y, Color fontColor) {
        return createLabelwithallInput(text, x ,y, fontColor);
    }

    private static Label createLabelwithallInput(String text, float x, float y, Color fontColor) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = defaultFont;
        style.fontColor = fontColor;

        Label label = new Label(text, style);
        label.setPosition(x, y);

        return label;
    }
}
