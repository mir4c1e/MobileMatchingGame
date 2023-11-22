package com.example.MatchingGame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class Card extends androidx.appcompat.widget.AppCompatImageView {

    private static final ArrayList<Integer> image = new ArrayList<>(
            Arrays.asList(
                    R.drawable.question,
                    R.drawable.candy,
                    R.drawable.hat,
                    R.drawable.santa,
                    R.drawable.snowflake,
                    R.drawable.snowman,
                    R.drawable.tree)
    );
    private Integer imageValue;
    private boolean Flipped;
    private boolean Visible;
    private static boolean TwoFlipped = false;

    public Card(@NonNull Context context, Integer imageValue, boolean isFlipped, boolean isVisible) {
        super(context);
        this.imageValue = imageValue;
        setFlipped(isFlipped);
        setVisible(isVisible);
    }

    public ArrayList<Integer> getImage() {
        return image;
    }

    public Integer getImageValue() {
        return imageValue;
    }

    public void setImageValue(Integer imageValue) {
        this.imageValue = imageValue;
    }

    public boolean isFlipped() {
        return Flipped;
    }

    public void setFlipped(boolean flipped) {
        this.Flipped = flipped;

        if (this.Flipped)
            this.setImageResource(image.get(this.imageValue));
        else
            this.setImageResource(image.get(0));
    }

    public boolean isVisible() {
        return Visible;
    }

    public void setVisible(boolean visible) {
        this.Visible = visible;

        if (this.Visible)
            this.setVisibility(View.VISIBLE);
        else
            this.setVisibility(View.INVISIBLE);
    }

    public static boolean isTwoFlipped() {
        return TwoFlipped;
    }

    public void changeCardImageSize(int size, int padding) {
        ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
        layoutParams.width = size;
        layoutParams.height = size;
        this.setLayoutParams(layoutParams);

        this.setPadding(padding, padding, padding, padding);
    }
}
