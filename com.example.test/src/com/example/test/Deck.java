package com.example.test;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
    Deck()
    {
        cards = new ArrayList<Card>();
        int x=0;
        for (int a=0; a<=3; a++)
        {
            for (int b=0; b<=12; b++)
             {
            	cards.add( new Card(a,b) );
             }
        }
    }

    public Card drawFromDeck()
    {

        Random generator = new Random();
        int index= generator.nextInt( cards.size() );
        return cards.remove(index);
    }

}
