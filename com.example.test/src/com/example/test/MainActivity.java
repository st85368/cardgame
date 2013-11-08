package com.example.test;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {
	  private static final int REQUEST_CODE = 10; //wut is this dawg
	  
	  /*
	   * activeDeck is cards user has access to
	   * every card on the table is in displayDeck
	   * every card in users hand is userDeck;
	   * 
	   * active + display + discard = 52
	   */
	  Deck activeDeck;
	  Deck displayDeck;
	  Deck userDeck;
	  
	  Card currentCard;
	  
	  ListView userDeckListView;
	  ArrayList<String> userDeckStrings;
	  
	  ListView displayDeckListView;
	  ArrayList<String> displayDeckStrings;
	  
	  Button drawCardBtn;
	  Button showBtn;
	  Button shuffleBtn;
	  
	  ArrayAdapter<String> userDeckAA;
	  ArrayAdapter<String> displayDeckAA;
	  
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*
         * Card objects
         */
        activeDeck = new Deck(true);
        displayDeck = new Deck(false);
        userDeck = new Deck(false);
        
        /*
         * UI objects
         */
        drawCardBtn = (Button)findViewById(R.id.draw_card_btn);
        drawCardBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				currentCard = activeDeck.DrawFromTop();
				userDeck.AddCardOnTop(currentCard);
				
				userDeckStrings.add(currentCard.toString());
				userDeckAA.notifyDataSetChanged();
			}
        	
        });
        
        showBtn = (Button)findViewById(R.id.show_btn);
        showBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				currentCard = userDeck.DrawFromBottom();
				displayDeck.AddCardOnTop(currentCard);
				
				displayDeckStrings.add(currentCard.toString());
				displayDeckAA.notifyDataSetChanged();
			}
        	
        });
        
        shuffleBtn = (Button)findViewById(R.id.shuffle_btn);
        shuffleBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activeDeck.ShuffleDeck();
			}
        	
        });
        
        userDeckListView = (ListView)findViewById(R.id.userdecklistView);
        userDeckStrings = new ArrayList<String>();
        userDeckAA = new ArrayAdapter<String>(this,
        								      android.R.layout.simple_list_item_1,
        									  userDeckStrings);
        userDeckListView.setAdapter(userDeckAA);
        
        displayDeckListView = (ListView)findViewById(R.id.displaydecklistView);
        displayDeckStrings = new ArrayList<String>();
        displayDeckAA = new ArrayAdapter<String>(this,
        								android.R.layout.simple_list_item_1,
        								displayDeckStrings);
        displayDeckListView.setAdapter(displayDeckAA);
        
        /*
         * Alljoyn objects
         */
        //TODO later
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
          if (data.hasExtra("returnkey")) {
            String result = data.getExtras().getString("returnkey");
            if (result != null && result.length() > 0) {
              Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
          }
        }
    }
    
}
