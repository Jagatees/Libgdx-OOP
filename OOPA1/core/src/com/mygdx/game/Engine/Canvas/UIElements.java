package com.mygdx.game.Engine.Canvas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Arrays;

public class UIElements {

    private static final BitmapFont defaultFont = new BitmapFont();

    static Skin skin = new Skin(Gdx.files.internal(
            "skin/qh/quantum-horizon-ui.json")); // Ensure you have this skin file in your assets


    public static SelectBox<String> createDropdown(Stage stage, String[] items, float x, float y, ChangeListener actionListener, String currentKeyBinding) {
        // Create a SelectBox with the provided skin
        SelectBox<String> selectBox = new SelectBox<>(skin);

        // Set the items of the SelectBox
        selectBox.setItems(items);

        selectBox.setSize(100, 25);

        // Set the position of the SelectBox
        selectBox.setPosition(x, y);

        // Add a ChangeListener to respond to item selection
        selectBox.addListener(actionListener);

        if (currentKeyBinding != null && Arrays.asList(items).contains(currentKeyBinding)) {
            selectBox.setSelected(currentKeyBinding);
        }

        // Add the SelectBox to the stage
        stage.addActor(selectBox);

        return selectBox;
    }

    public static CheckBox createCheckBox(Stage stage, String text, float x, float y, boolean isChecked, ChangeListener actionListener) {
        // Create a CheckBox with the provided skin and text
        CheckBox checkBox = new CheckBox(text, skin);
        checkBox.setPosition(x, y);
        checkBox.setChecked(isChecked);

        // Add a ChangeListener to react to check events
        checkBox.addListener(actionListener);

        // Add the CheckBox to the stage
        stage.addActor(checkBox);

        return checkBox;
    }

    public static Slider SimpleSlider(Stage stage, float min, float max, float x ,float y, float initValue,ChangeListener actionListener) {
        // Create a Slider with min, max, step values and a vertical orientation flag.
        Slider slider = new Slider(min, max, 0.1f, false, skin);
        slider.setValue(initValue);

        // Set the slider position and size (optional)
        slider.setPosition(x, y);
        slider.setSize(200, 20);


        // To listen for changes, you can add a ChangeListener to the slider
        slider.addListener(actionListener);

        stage.addActor(slider);

        return slider;
    }


    public static TextButton createTextButton(Stage stage, String text, float x, float y, int width , int height, Color backgroundColor, ChangeListener actionListener) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(backgroundColor);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        Drawable drawable = new TextureRegionDrawable(texture);


        TextButton button = new TextButton(text, skin);
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

        Label label = new Label(text, skin);
        label.setPosition(x, y);

        stage.addActor(label); // Automatically add the label to the stage

        return label;
    }
}
