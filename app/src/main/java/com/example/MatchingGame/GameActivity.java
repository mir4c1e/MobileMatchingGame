package com.example.MatchingGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private final int NUMBER_OF_CARD = 12;
    private final int NUMBER_OF_ROW = 4;
    private final int NUMBER_OF_COLUMN = 3;
    private final int CARD_IMAGE_SIZE = 325;
    private final int CARD_PADDING = 15;
    private ArrayList<Card> cardArrayList = new ArrayList<>();

    private ArrayList<Integer> imageValue = new ArrayList<>();
    private ArrayList<LinearLayout> linearLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setupEnvironment();
    }

    private void setupEnvironment() {
        if (!cardArrayList.isEmpty())
            cardArrayList.clear();

        for (int i = 0; i < NUMBER_OF_CARD; i++) {
            cardArrayList.add(new Card(getApplicationContext(), 0, false, true));
        }

        linearLayouts = new ArrayList<>(
                Arrays.asList(
                        findViewById(R.id.firstRow),
                        findViewById(R.id.secondRow),
                        findViewById(R.id.thirdRow),
                        findViewById(R.id.fourthRow)
                )
        );

        int index = 0;
        for (int i = 0; i < NUMBER_OF_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMN; j++) {
                linearLayouts.get(i).addView(cardArrayList.get(index));
                cardArrayList.get(index).changeCardImageSize(CARD_IMAGE_SIZE, CARD_PADDING);
                index++;
            }
        }

        initGame();
    }

    private void initGame() {
        if (!imageValue.isEmpty())
            imageValue.clear();

        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (Integer i = 1; i < NUMBER_OF_CARD + 1; i++) {
            frequency.put(i, 0);
        }

        for (int i = 0; i < NUMBER_OF_CARD; i++) {
            // number 0 is not used
            boolean pass = false;
            do {
                Random random= new Random();
                Integer randomNumber = random.nextInt(NUMBER_OF_CARD / 2) + 1;
                Log.d("GameDebug", "randomNumber: " + randomNumber);
                Integer frequencyCheck = frequency.get(randomNumber);
                Log.d("GameDebug", "frequencyCheck: " + frequencyCheck);
                if (frequencyCheck < 2) {
                    pass = true;
                    frequency.put(randomNumber, frequencyCheck + 1);
                    // update imageValueList
                    imageValue.add(randomNumber);
                }
            } while (!pass);
        }

        // init Card
        for (int i = 0; i < NUMBER_OF_CARD; i ++) {
            cardArrayList.get(i).setImageValue(imageValue.get(i));
            Log.d("GameDebug", "Card " + i + ": " + cardArrayList.get(i).getImageValue());
            cardArrayList.get(i).setOnClickListener(this);
        }

        Log.d("GameDebug", "imageValue: " + imageValue);
    }

    @Override
    public void onClick(View v) {
        Log.d("GameDebug", "Clicked!");
        Card card = (Card) v;
        card.setImageResource(card.getImage().get(card.getImageValue()));
    }
}