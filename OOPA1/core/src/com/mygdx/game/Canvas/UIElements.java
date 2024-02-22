package com.mygdx.game.Canvas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class UIElements {

    private static final BitmapFont defaultFont = new BitmapFont();


    public static TextButton createTextButton(Stage stage, String text, float x, float y, int width , int height, Color backgroundColor, ChangeListener actionListener) {
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

        stage.addActor(button);

        return button;
    }



    public static Label createLabel(Stage stage, Color color) {
        return createLabelwithallInput(stage, "Button", 100 ,100, color);
    }

    public static Label createLabel(Stage stage, float x, float y) {
        return createLabelwithallInput(stage, "Button", x ,y, Color.BLACK);
    }
    public static Label createLabel(Stage stage, String text, float x, float y) {
        return createLabelwithallInput(stage, text, x ,y, Color.BLACK);
    }
    public static Label createLabel(Stage stage, String text) {
        return createLabelwithallInput(stage, text, 100 ,100, Color.BLACK);
    }

    public static Label createLabel(Stage stage, String text, float x, float y, Color fontColor) {
        return createLabelwithallInput(stage, text, x ,y, fontColor);
    }

    private static Label createLabelwithallInput(Stage stage, String text, float x, float y, Color fontColor) {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = defaultFont;
        style.fontColor = fontColor;

        Label label = new Label(text, style);
        label.setPosition(x, y);

        stage.addActor(label); // Automatically add the label to the stage

        return label;
    }
}
