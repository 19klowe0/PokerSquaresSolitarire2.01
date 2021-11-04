package com.example.pokersquaressolitarire;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

//Kira Lowe
// 09/07/2021
//CSC 309
//This Program creates the game Poker Squares Solitaire in an android app
//pictures are added along with sounds and a landscape activity


public class MainActivity extends AppCompatActivity {

    //button Grid
    ImageButton[][] cardPlacementBttn = new ImageButton[5][5];
    String[][] textForButtons = new String[5][5];

    //button for deck
    ImageButton deck;

    //keep track of score
    TextView currentScoreTextView;
    int currentScore;

    //random to shuffle deck
    Random rnd = new Random();


    //position in deck
    int position= 0;

    //array of cards
    final String[] cards = {"AD", "2D", "3D","4D","5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD",
            "AC", "2C", "3C","4C","5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC",
            "AH", "2H", "3H","4H","5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH",
            "AS", "2S", "3S","4S","5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS",};
    String[] shuffledCards = new String[52];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sound stuff
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.ding);
        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.alarm);


        Button newGame = findViewById((R.id.bttnNewGame));
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //user requesting a new game
                NewGame();
                mp1.start();
            }
        });

        //link to the screen items
        //row 1
        cardPlacementBttn[0][0] = findViewById(R.id.bttn_r1c1);
        cardPlacementBttn[0][1] = findViewById(R.id.bttn_r1c2);
        cardPlacementBttn[0][2] = findViewById(R.id.bttn_r1c3);
        cardPlacementBttn[0][3] = findViewById(R.id.bttn_r1c4);
        cardPlacementBttn[0][4] = findViewById(R.id.bttn_r1c5);
        //row2
        cardPlacementBttn[1][0] = findViewById(R.id.bttn_r2c1);
        cardPlacementBttn[1][1] = findViewById(R.id.bttn_r2c2);
        cardPlacementBttn[1][2] = findViewById(R.id.bttn_r2c3);
        cardPlacementBttn[1][3] = findViewById(R.id.bttn_r2c4);
        cardPlacementBttn[1][4] = findViewById(R.id.bttn_r2c5);
        //row3
        cardPlacementBttn[2][0] = findViewById(R.id.bttn_r3c1);
        cardPlacementBttn[2][1] = findViewById(R.id.bttn_r3c2);
        cardPlacementBttn[2][2] = findViewById(R.id.bttn_r3c3);
        cardPlacementBttn[2][3] = findViewById(R.id.bttn_r3c4);
        cardPlacementBttn[2][4] = findViewById(R.id.bttn_r3c5);
        //row4
        cardPlacementBttn[3][0] = findViewById(R.id.bttn_r4c1);
        cardPlacementBttn[3][1] = findViewById(R.id.bttn_r4c2);
        cardPlacementBttn[3][2] = findViewById(R.id.bttn_r4c3);
        cardPlacementBttn[3][3] = findViewById(R.id.bttn_r4c4);
        cardPlacementBttn[3][4] = findViewById(R.id.bttn_r4c5);
        //row5
        cardPlacementBttn[4][0] = findViewById(R.id.bttn_r5c1);
        cardPlacementBttn[4][1] = findViewById(R.id.bttn_r5c2);
        cardPlacementBttn[4][2] = findViewById(R.id.bttn_r5c3);
        cardPlacementBttn[4][3] = findViewById(R.id.bttn_r5c4);
        cardPlacementBttn[4][4] = findViewById(R.id.bttn_r5c5);

        //on click


        for (int row= 0; row <5; row++){
            for (int col = 0; col <5; col++){
                int finalRow = row;
                int finalCol = col;
                cardPlacementBttn[row][col].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ButtonPressed(finalRow, finalCol);
                        mp.start();
                    }
                });
            }
        }

        //setting the textviews/score/deck
        currentScoreTextView = findViewById(R.id.scoreTextView);
        currentScore = 0;
        position = 0;
        deck = findViewById(R.id.bttn_deck);



    }

    //stuff to save
    public static final String BTTNR1C1 = "bttn_r1c1";
    public static final String BTTNR1C2 = "bttn_r1c2";
    public static final String BTTNR1C3 = "bttn_r1c3";
    public static final String BTTNR1C4 = "bttn_r1c4";
    public static final String BTTNR1C5 = "bttn_r1c5";

    public static final String BTTNR2C1 = "bttn_r2c1";
    public static final String BTTNR2C2 = "bttn_r2c2";
    public static final String BTTNR2C3 = "bttn_r2c3";
    public static final String BTTNR2C4 = "bttn_r2c4";
    public static final String BTTNR2C5 = "bttn_r2c5";

    public static final String BTTNR3C1 = "bttn_r3c1";
    public static final String BTTNR3C2 = "bttn_r3c2";
    public static final String BTTNR3C3 = "bttn_r3c3";
    public static final String BTTNR3C4 = "bttn_r3c4";
    public static final String BTTNR3C5 = "bttn_r3c5";

    public static final String BTTNR4C1 = "bttn_r4c1";
    public static final String BTTNR4C2 = "bttn_r4c2";
    public static final String BTTNR4C3 = "bttn_r4c3";
    public static final String BTTNR4C4 = "bttn_r4c4";
    public static final String BTTNR4C5 = "bttn_r4c5";

    public static final String BTTNR5C1 = "bttn_r5c1";
    public static final String BTTNR5C2 = "bttn_r5c2";
    public static final String BTTNR5C3 = "bttn_r5c3";
    public static final String BTTNR5C4 = "bttn_r5c4";
    public static final String BTTNR5C5 = "bttn_r5c5";

    public static final String POSITION = "position";
    public static final String CURRENTSCORE = "currentScore";
    public static final String SHUFFLE = "shuffle";




    //Save state
    public void onSaveInstanceState( Bundle savedInstanceState ) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(BTTNR1C1, textForButtons[0][0]);
        savedInstanceState.putString(BTTNR1C2, textForButtons[0][1]);
        savedInstanceState.putString(BTTNR1C3, textForButtons[0][2]);
        savedInstanceState.putString(BTTNR1C4, textForButtons[0][3]);
        savedInstanceState.putString(BTTNR1C5, textForButtons[0][4]);
        savedInstanceState.putString(BTTNR2C1, textForButtons[1][0]);
        savedInstanceState.putString(BTTNR2C2, textForButtons[1][1]);
        savedInstanceState.putString(BTTNR2C3, textForButtons[1][2]);
        savedInstanceState.putString(BTTNR2C4, textForButtons[1][3]);
        savedInstanceState.putString(BTTNR2C5, textForButtons[1][4]);
        savedInstanceState.putString(BTTNR3C1, textForButtons[2][0]);
        savedInstanceState.putString(BTTNR3C2, textForButtons[2][1]);
        savedInstanceState.putString(BTTNR3C3, textForButtons[2][2]);
        savedInstanceState.putString(BTTNR3C4, textForButtons[2][3]);
        savedInstanceState.putString(BTTNR3C5, textForButtons[2][4]);
        savedInstanceState.putString(BTTNR4C1, textForButtons[3][0]);
        savedInstanceState.putString(BTTNR4C2, textForButtons[3][1]);
        savedInstanceState.putString(BTTNR4C3, textForButtons[3][2]);
        savedInstanceState.putString(BTTNR4C4, textForButtons[3][3]);
        savedInstanceState.putString(BTTNR4C5, textForButtons[3][4]);
        savedInstanceState.putString(BTTNR5C1, textForButtons[4][0]);
        savedInstanceState.putString(BTTNR5C2, textForButtons[4][1]);
        savedInstanceState.putString(BTTNR5C3, textForButtons[4][2]);
        savedInstanceState.putString(BTTNR5C4, textForButtons[4][3]);
        savedInstanceState.putString(BTTNR5C5, textForButtons[4][4]);

        savedInstanceState.putInt(POSITION, position);

        savedInstanceState.putInt(CURRENTSCORE, currentScore);

        savedInstanceState.putStringArray(SHUFFLE, shuffledCards);

    }
    public void onRestoreInstanceState( Bundle savedInstanceState ) {
        super.onRestoreInstanceState(savedInstanceState);

        position = savedInstanceState.getInt(POSITION);


        textForButtons[0][0] = savedInstanceState.getString(BTTNR1C1);
        textForButtons[0][1]= savedInstanceState.getString(BTTNR1C2);
        textForButtons[0][2]= savedInstanceState.getString(BTTNR1C3);
        textForButtons[0][3]= savedInstanceState.getString(BTTNR1C4);
        textForButtons[0][4]= savedInstanceState.getString(BTTNR1C5);
        textForButtons[1][0]= savedInstanceState.getString(BTTNR2C1);
        textForButtons[1][1]= savedInstanceState.getString(BTTNR2C2);
        textForButtons[1][2]= savedInstanceState.getString(BTTNR2C3);
        textForButtons[1][3]= savedInstanceState.getString(BTTNR2C4);
        textForButtons[1][4]= savedInstanceState.getString(BTTNR2C5);
        textForButtons[2][0]= savedInstanceState.getString(BTTNR3C1);
        textForButtons[2][1]= savedInstanceState.getString(BTTNR3C2);
        textForButtons[2][2]= savedInstanceState.getString(BTTNR3C3);
        textForButtons[2][3]= savedInstanceState.getString(BTTNR3C4);
        textForButtons[2][4]= savedInstanceState.getString(BTTNR3C5);
        textForButtons[3][0]= savedInstanceState.getString(BTTNR4C1);
        textForButtons[3][1]= savedInstanceState.getString(BTTNR4C2);
        textForButtons[3][2]= savedInstanceState.getString(BTTNR4C3);
        textForButtons[3][3]= savedInstanceState.getString(BTTNR4C4);
        textForButtons[3][4]= savedInstanceState.getString(BTTNR4C5);
        textForButtons[4][0]= savedInstanceState.getString(BTTNR5C1);
        textForButtons[4][1]= savedInstanceState.getString(BTTNR5C2);
        textForButtons[4][2]= savedInstanceState.getString(BTTNR5C3);
        textForButtons[4][3]= savedInstanceState.getString(BTTNR5C4);
        textForButtons[4][4]= savedInstanceState.getString(BTTNR5C5);

        currentScore = savedInstanceState.getInt( CURRENTSCORE );
        if (currentScore != 0){
            currentScoreTextView.setText("Score: "+currentScore);
        }

        for (int row= 0; row <5; row++) {
            for (int col = 0; col < 5; col++) {
                if (textForButtons[row][col] != null){
                    cardPlacementBttn[row][col].setImageResource(setPicture(row,col));
                }
            }
        }

        shuffledCards = savedInstanceState.getStringArray(SHUFFLE);

        deck.setImageResource(findPicture());
    }

    private int setPicture(int row, int col) {
        int imageId = 0;
        if(textForButtons[row][col].equals("AD")){
            imageId = R.drawable.c_ace_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("2D")){
            imageId = R.drawable.c_2_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("3D")){
            imageId = R.drawable.c_3_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("4D")){
            imageId = R.drawable.c_4_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("5D")){
            imageId = R.drawable.c_5_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("6D")){
            imageId = R.drawable.c_6_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("7D")){
            imageId = R.drawable.c_7_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("8D")){
            imageId = R.drawable.c_8_diamond;
        }
        else if(textForButtons[row][col].equals("9D")){
            imageId = R.drawable.c_9_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("10D")){
            imageId = R.drawable.c_10_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("JD")){
            imageId = R.drawable.c_jack_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("QD")){
            imageId = R.drawable.c_queen_diamond;
            return imageId;
        }
        else if(textForButtons[row][col].equals("KD")){
            imageId = R.drawable.c_king_diamond;
            return imageId;
        }

        //clubs
        else if(textForButtons[row][col].equals("AC")){
            imageId = R.drawable.c_ace_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("2C")){
            imageId = R.drawable.c_2_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("3C")){
            imageId = R.drawable.c_3_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("4C")){
            imageId = R.drawable.c_4_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("5C")){
            imageId = R.drawable.c_5_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("6C")){
            imageId = R.drawable.c_6_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("7C")){
            imageId = R.drawable.c_7_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("8C")){
            imageId = R.drawable.c_8_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("9C")){
            imageId = R.drawable.c_9_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("10C")){
            imageId = R.drawable.c_10_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("JC")){
            imageId = R.drawable.c_jack_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("QC")){
            imageId = R.drawable.c_queen_club;
            return imageId;
        }
        else if(textForButtons[row][col].equals("KC")){
            imageId = R.drawable.c_king_club;
            return imageId;
        }

        //hearts
        else if(textForButtons[row][col].equals("AH")){
            imageId = R.drawable.c_ace_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("2H")){
            imageId = R.drawable.c_2_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("3H")){
            imageId = R.drawable.c_3_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("4H")){
            imageId = R.drawable.c_4_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("5H")){
            imageId = R.drawable.c_5_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("6H")){
            imageId = R.drawable.c_6_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("7H")){
            imageId = R.drawable.c_7_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("8H")){
            imageId = R.drawable.c_8_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("9H")){
            imageId = R.drawable.c_9_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("10H")){
            imageId = R.drawable.c_10_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("JH")){
            imageId = R.drawable.c_jack_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("QH")){
            imageId = R.drawable.c_queen_heart;
            return imageId;
        }
        else if(textForButtons[row][col].equals("KH")){
            imageId = R.drawable.c_king_heart;
            return imageId;
        }

        //spades
        else if(textForButtons[row][col].equals("AS")){
            imageId = R.drawable.c_ace_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("2S")){
            imageId = R.drawable.c_2_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("3S")){
            imageId = R.drawable.c_3_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("4S")){
            imageId = R.drawable.c_4_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("5S")){
            imageId = R.drawable.c_5_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("6S")){
            imageId = R.drawable.c_6_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("7S")){
            imageId = R.drawable.c_7_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("8S")){
            imageId = R.drawable.c_8_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("9S")){
            imageId = R.drawable.c_9_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("10S")){
            imageId = R.drawable.c_10_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("JS")){
            imageId = R.drawable.c_jack_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("QS")){
            imageId = R.drawable.c_queen_spade;
            return imageId;
        }
        else if(textForButtons[row][col].equals("KS")){
            imageId = R.drawable.c_king_spade;
            return imageId;
        }

        return imageId;

    }


    //this function is called if a button on the grid is pressed
    private void ButtonPressed(int row, int col) {
        //set the text in the text array
        textForButtons[row][col] = shuffledCards[position];

        //set the button equal to the top card on the deck
        cardPlacementBttn[row][col].setImageResource(findPicture());

        //disable button
        cardPlacementBttn[row][col].setEnabled(false);

        //change position in deck
        position++;
        //switch to next card in deck

        //deck.setText((shuffledCards[position]));
        deck.setImageResource(findPicture());


        if (position > 25){
            LastPlaceUsed();
        }
    }

    private int findPicture() {
        int imageId = 0;
        if(shuffledCards[position].substring(0).equals("AD")){
            imageId = R.drawable.c_ace_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("2D")){
            imageId = R.drawable.c_2_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("3D")){
            imageId = R.drawable.c_3_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("4D")){
            imageId = R.drawable.c_4_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("5D")){
            imageId = R.drawable.c_5_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("6D")){
            imageId = R.drawable.c_6_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("7D")){
            imageId = R.drawable.c_7_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("8D")){
            imageId = R.drawable.c_8_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("9D")){
            imageId = R.drawable.c_9_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("10D")){
            imageId = R.drawable.c_10_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("JD")){
            imageId = R.drawable.c_jack_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("QD")){
            imageId = R.drawable.c_queen_diamond;
        }
        else if(shuffledCards[position].substring(0).equals("KD")){
            imageId = R.drawable.c_king_diamond;
        }

        //clubs
        else if(shuffledCards[position].substring(0).equals("AC")){
            imageId = R.drawable.c_ace_club;
        }
        else if(shuffledCards[position].substring(0).equals("2C")){
            imageId = R.drawable.c_2_club;
        }
        else if(shuffledCards[position].substring(0).equals("3C")){
            imageId = R.drawable.c_3_club;
        }
        else if(shuffledCards[position].substring(0).equals("4C")){
            imageId = R.drawable.c_4_club;
        }
        else if(shuffledCards[position].substring(0).equals("5C")){
            imageId = R.drawable.c_5_club;
        }
        else if(shuffledCards[position].substring(0).equals("6C")){
            imageId = R.drawable.c_6_club;
        }
        else if(shuffledCards[position].substring(0).equals("7C")){
            imageId = R.drawable.c_7_club;
        }
        else if(shuffledCards[position].substring(0).equals("8C")){
            imageId = R.drawable.c_8_club;
        }
        else if(shuffledCards[position].substring(0).equals("9C")){
            imageId = R.drawable.c_9_club;
        }
        else if(shuffledCards[position].substring(0).equals("10C")){
            imageId = R.drawable.c_10_club;
        }
        else if(shuffledCards[position].substring(0).equals("JC")){
            imageId = R.drawable.c_jack_club;
        }
        else if(shuffledCards[position].substring(0).equals("QC")){
            imageId = R.drawable.c_queen_club;
        }
        else if(shuffledCards[position].substring(0).equals("KC")){
            imageId = R.drawable.c_king_club;
        }

        //hearts
        else if(shuffledCards[position].substring(0).equals("AH")){
            imageId = R.drawable.c_ace_heart;
        }
        else if(shuffledCards[position].substring(0).equals("2H")){
            imageId = R.drawable.c_2_heart;
        }
        else if(shuffledCards[position].substring(0).equals("3H")){
            imageId = R.drawable.c_3_heart;
        }
        else if(shuffledCards[position].substring(0).equals("4H")){
            imageId = R.drawable.c_4_heart;
        }
        else if(shuffledCards[position].substring(0).equals("5H")){
            imageId = R.drawable.c_5_heart;
        }
        else if(shuffledCards[position].substring(0).equals("6H")){
            imageId = R.drawable.c_6_heart;
        }
        else if(shuffledCards[position].substring(0).equals("7H")){
            imageId = R.drawable.c_7_heart;
        }
        else if(shuffledCards[position].substring(0).equals("8H")){
            imageId = R.drawable.c_8_heart;
        }
        else if(shuffledCards[position].substring(0).equals("9H")){
            imageId = R.drawable.c_9_heart;
        }
        else if(shuffledCards[position].substring(0).equals("10H")){
            imageId = R.drawable.c_10_heart;
        }
        else if(shuffledCards[position].substring(0).equals("JH")){
            imageId = R.drawable.c_jack_heart;
        }
        else if(shuffledCards[position].substring(0).equals("QH")){
            imageId = R.drawable.c_queen_heart;
        }
        else if(shuffledCards[position].substring(0).equals("KH")){
            imageId = R.drawable.c_king_heart;
        }

        //spades
        else if(shuffledCards[position].substring(0).equals("AS")){
            imageId = R.drawable.c_ace_spade;
        }
        else if(shuffledCards[position].substring(0).equals("2S")){
            imageId = R.drawable.c_2_spade;
        }
        else if(shuffledCards[position].substring(0).equals("3S")){
            imageId = R.drawable.c_3_spade;
        }
        else if(shuffledCards[position].substring(0).equals("4S")){
            imageId = R.drawable.c_4_spade;
        }
        else if(shuffledCards[position].substring(0).equals("5S")){
            imageId = R.drawable.c_5_spade;
        }
        else if(shuffledCards[position].substring(0).equals("6S")){
            imageId = R.drawable.c_6_spade;
        }
        else if(shuffledCards[position].substring(0).equals("7S")){
            imageId = R.drawable.c_7_spade;
        }
        else if(shuffledCards[position].substring(0).equals("8S")){
            imageId = R.drawable.c_8_spade;
        }
        else if(shuffledCards[position].substring(0).equals("9S")){
            imageId = R.drawable.c_9_spade;
        }
        else if(shuffledCards[position].substring(0).equals("10S")){
            imageId = R.drawable.c_10_spade;
        }
        else if(shuffledCards[position].substring(0).equals("JS")){
            imageId = R.drawable.c_jack_spade;
        }
        else if(shuffledCards[position].substring(0).equals("QS")){
            imageId = R.drawable.c_queen_spade;
        }
        else if(shuffledCards[position].substring(0).equals("KS")){
            imageId = R.drawable.c_king_spade;
        }

        return imageId;

    }

    //all spaces taken up need to find score
    private void LastPlaceUsed(){

        //Array for a handForRows for rows
        String[] handForRows= new String[5];
        //Array for a handForCols for rows
        String[] handForCols= new String[5];
        //when to use the first two letters


        //fill array from placement
        for(int row = 0; row<5; row++) {
            //populate a handForRows for rows
            for(int i = 0;i <5; i++) {
                //handForRows[i] = cardPlacementBttn[row][i].getText()+"";
                handForRows[i] = textForButtons[row][i];
            }
            //populate a handForRows for cols
            for(int i = 0;i <5; i++) {
                //handForCols[i] = cardPlacementBttn[i][row].getText()+"";
                handForCols[i] = textForButtons[i][row];
            }
            //handle 10JQKA
            for(int i=0; i< 5; i++){
                //for rows
                //10
                if(handForRows[i].charAt(0)=='1'){
                    handForRows[i] = ":"+handForRows[i].charAt(2);
                }
                //J
                if(handForRows[i].charAt(0)=='J'){
                    handForRows[i] = ";"+handForRows[i].charAt(1);
                }
                //Q
                if(handForRows[i].charAt(0)=='Q'){
                    handForRows[i] = "<"+handForRows[i].charAt(1);
                }
                //K
                if(handForRows[i].charAt(0)=='K'){
                    handForRows[i] = "="+handForRows[i].charAt(1);
                }
                //A
                if(handForRows[i].charAt(0)=='A'){
                    handForRows[i] = ">"+handForRows[i].charAt(1);
                }
                //for cols
                //10
                if(handForCols[i].charAt(0)=='1'){
                    handForCols[i] = ":"+handForCols[i].charAt(2);
                }
                //J
                if(handForCols[i].charAt(0)=='J'){
                    handForCols[i] = ";"+handForCols[i].charAt(1);
                }
                //Q
                if(handForCols[i].charAt(0)=='Q'){
                    handForCols[i] = "<"+handForCols[i].charAt(1);
                }
                //K
                if(handForCols[i].charAt(0)=='K'){
                    handForCols[i] = "="+handForCols[i].charAt(1);
                }
                //A
                if(handForCols[i].charAt(0)=='A'){
                    handForCols[i] = ">"+handForCols[i].charAt(1);
                }

            }
            //sort the handForRows by the rank. (Bubble sort)
            int n = handForRows.length;
            for(int i = 0; i<n-1; i++){
                for (int j = 0; j< n-i-1; j++){
                    if(handForRows[j].charAt(0)-'0' > handForRows[j+1].charAt(0)-'0'){
                        //swap hand[] and arr[]
                        String temp = handForRows[j];
                        handForRows[j] = handForRows[j+1];
                        handForRows[j+1] = temp;

                    }
                }
            }
            //handforcol sort (bubble)
            for(int i = 0; i<n; i++){
                for (int j = 0; j< n-i-1; j++){
                    if(handForCols[j].charAt(0) > handForCols[j+1].charAt(0)){
                        //swap hand[] and arr[]
                        String temp = handForCols[j];
                        handForCols[j] = handForCols[j+1];
                        handForCols[j+1] = temp;
                    }
                }
            }
            //find score
            FigureOutScore(handForRows);
            FigureOutScore(handForCols);


       }

        //display score
        currentScoreTextView.setText("Score: "+currentScore);
    }

    //method to return current score
    int FigureOutScore(String[] handForRows){
        //first check for Royal flush
        //then check for straight flush
        //four of a kind
        //full house
        //flush
        //straight
        //three of a kind
        //two pairs
        //one pair

        //royal and straight flush
        if(Straight(handForRows) == 2){
            if(Flush(handForRows) == 1){
                //is a royal flush
                return currentScore += 100;
            }
        }
        //straight flush

        if(Straight(handForRows)==1){
            if(Flush(handForRows) == 1) {
                //straight flush
                return currentScore += 75;
            }
        }


        //four of a kind
        if( FourOfAKind(handForRows)== 1) {
            return currentScore += 50;
        }


        //full house
        if(ThreeOfAKind(handForRows) == 2){
            return currentScore += 25;
        }


        //flush
        if(Flush(handForRows)== 1){
            return currentScore += 20;
        }

        //straight
        if(Straight(handForRows)== 1){
            return currentScore += 15;
        }

        //three of a kind
        if(ThreeOfAKind(handForRows)== 1){
            return currentScore += 10;
        }


        //pairs
        int temp = Pairs(handForRows);
        if (temp == 1){
            return currentScore += 2;
        }
        if (temp == 2) {
            return currentScore += 5;
        }

        return 0;
    }

    //find if hand (5 cards) is a flush
    int Flush(String[] hand){
        //return 1 if they are all the same suit
        //return 0 if false
        char s;
        //determine the suit
        //get the first card's suit
        s = hand[0].charAt(1);
        //compare with all other cards
        for(int i = 1; i< 5; i++){
            if(s != hand[i].charAt(1)){
                return 0;
            }
        }
        return 1;
    }

    //find if hand (5 cards) is in sequence and if it is a high sequence (AKQJ10)
    int Straight(String[] hand) {
        //returns one for true, 2 for a high straight, 0 for no 5 card sequence
        //high straight then check suit for royal flush
        if (hand[0].charAt(0) == ':' && hand[1].charAt(0) == ';'&& hand[2].charAt(0) == '<' && hand[3].charAt(0) == '=' && hand[4].charAt(0) == '>'){
            return 2;
        }

        //other straights
        if((int)hand[0].charAt(0) != (int)hand[1].charAt(0)-1)
            return 0;
        if((int)hand[1].charAt(0) != (int)hand[2].charAt(0)-1)
            return 0;
        if((int)hand[2].charAt(0) != (int)hand[3].charAt(0)-1)
            return 0;
        if((int)hand[3].charAt(0) != (int)hand[4].charAt(0)-1)
            return 0;

        return 1;
    }

    //find if hand has 4 matching ranks
    int FourOfAKind(String[] hand) {
        //return 1 for true, 0 for false
        //test the first 4 cards
        if(hand[0].charAt(0)== hand[1].charAt(0) && hand[0].charAt(0) == hand[2].charAt(0) && hand[0].charAt(0) == hand[3].charAt(0)){
            return 1;
        }
        //test the last 4 cards
        if(hand[1].charAt(0)== hand[2].charAt(0) && hand[1].charAt(0) == hand[3].charAt(0) && hand[1].charAt(0) == hand[4].charAt(0)){
            return 1;
        }
        return 0;
    }

    //find if hand is three of a kind or if full house
    int ThreeOfAKind(String[] hand) {
        //return 1 for true, 0 for false
        //return 2 for full house
        //test the first three cards
        if (hand[0].charAt(0)== hand[1].charAt(0) && hand[1].charAt(0) == hand[2].charAt(0)) {
            //also check for a full house
            if(hand[3].charAt(0) == hand[4].charAt(0)){
                return 2;
            }
            else
                return 1;
        }
        //test the next three cards
        if (hand[1].charAt(0)== hand[2].charAt(0) && hand[2].charAt(0) == hand[3].charAt(0)){
            //don't need to check for full house here because cards are sorted
            return 1;
        }
        //test last three cards
        if (hand[2].charAt(0)== hand[3].charAt(0) && hand[3].charAt(0) == hand[4].charAt(0)){
            if(hand[0].charAt(0)== hand[1].charAt(0)){
                return 2;
            }
            else
                return 1;
        }
        return 0;
    }

    //find if hand has 1 or 2 pairs
    int Pairs(String[] hand) {
        //return 1 for 1 pair, 2 for 2 pairs and 0 for nothing

        //checkfirst two cards
        if(hand[0].charAt(0)==hand[1].charAt(0)){
            //check for second pair
            if(hand[2].charAt(0) == hand[3].charAt(0) || hand[3].charAt(0) == hand[4].charAt(0))
                return 2;
            else
                return (1);
        }
        //test second two cards
        if(hand[1].charAt(0)==hand[2].charAt(0)){
            //check for second pair
            if(hand[3].charAt(0) == hand[4].charAt(0))
                return 2;
            else
                return (1);
        }
        //test for a pair at the third position
        if(hand[2].charAt(0)==hand[3].charAt(0)){
            return 1;
        }
        //last position
        if(hand[3].charAt(0)==hand[4].charAt(0)){
            return 1;
        }

        return 0;
    }

    private void NewGame() {

//        //fill buttons for testing
//        //row 1
//        cardPlacementBttn[0][0].setText("9C");
//        cardPlacementBttn[0][1].setText("9H");
//        cardPlacementBttn[0][2].setText("9D");
//        cardPlacementBttn[0][3].setText("9S");
//        cardPlacementBttn[0][4].setText("10H");
//        //row2
//        cardPlacementBttn[1][0].setText("10S");
//        cardPlacementBttn[1][1].setText("10D");
//        cardPlacementBttn[1][2].setText("2S");
//        cardPlacementBttn[1][3].setText("5S");
//        cardPlacementBttn[1][4].setText("2D");
//        //row3
//        cardPlacementBttn[2][0].setText("3H");
//        cardPlacementBttn[2][1].setText("3D");
//        cardPlacementBttn[2][2].setText("3C");
//        cardPlacementBttn[2][3].setText("8D");
//        cardPlacementBttn[2][4].setText("8C");
//        //row4
//        cardPlacementBttn[3][0].setText("QD");
//        cardPlacementBttn[3][1].setText("QH");
//        cardPlacementBttn[3][2].setText("QS");
//        cardPlacementBttn[3][3].setText("9H");
//        cardPlacementBttn[3][4].setText("7D");
//        //row5
//        cardPlacementBttn[4][0].setText("7H");
//        cardPlacementBttn[4][1].setText("7S");
//        cardPlacementBttn[4][2].setText("AC");
//        cardPlacementBttn[4][3].setText("AD");
//        cardPlacementBttn[4][4].setText("AH");



        //reset position
        position = 0;
        //reset score
        currentScoreTextView.setText("Score will be given at the end of the game");
        currentScore = 0;

        //fill the shuffle deck
        for (int i = 0; i < cards.length; i++){
            shuffledCards[i]= cards[i];
        }



        //shuffle the deck
        for (int i = 0; i < cards.length; i++){
            //random position
            int r = i+rnd.nextInt(52 -i);

            //swapping the elements
            String temp = shuffledCards[r];
            shuffledCards[r] = shuffledCards[i];
            shuffledCards[i] = temp;
        }


        //clear buttons and reenable
        for(int row = 0; row<5; row++){
            for(int col = 0; col <5; col++){
                //cardPlacementBttn[row][col].setText("");
                cardPlacementBttn[row][col].setImageResource(R.drawable.c_blank);
                cardPlacementBttn[row][col].setEnabled(true);
                //empty text
                textForButtons[row][col] = null;
            }
        }

        //put new card on top
        //switch top card of deck
        position++;
        //deck.setText(shuffledCards[position]);
        deck.setImageResource(findPicture());

    }

}