package com.mygdx.game.Canvas;

import com.badlogic.gdx.Gdx;
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

    private static final BitmapFont defaultFont = new BitmapFont(); // Default font

    public static TextButton createTextButton(String text, float x, float y, int width , int height, Color backgroundColor, ChangeListener actionListener) {
        // Create a Pixmap with the desired color
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(backgroundColor);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose(); // We're done with the pixmap, dispose it.
        Drawable drawable = new TextureRegionDrawable(texture);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = drawable; // Use the solid color drawable as the button background
        style.font = defaultFont; // Use default font

        TextButton button = new TextButton(text, style);
        button.setPosition(x, y);
        button.addListener(actionListener);

        return button;
    }


    // Method to create a Label
    public static Label createLabel(String text, float x, float y, Color fontColor) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = defaultFont; // Use default font
        style.fontColor = fontColor; // Set font color

        Label label = new Label(text, style);
        label.setPosition(x, y);

        return label;
    }
}
