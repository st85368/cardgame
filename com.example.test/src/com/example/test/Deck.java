package com.example.test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	private ArrayList<Card> deck;
	Deck(boolean fillDeck)
    {
		if(fillDeck) 
		{
			deck = new ArrayList<Card>();
	        int x=0;
	        for (int a=0; a<=3; a++)
	        {
	            for (int b=0; b<=12; b++)
	             {
	            	deck.add( new Card(a,b) );
	             }
	        }
		}
		else
		{
			deck = new ArrayList<Card>();
		}
    }

    public Card DrawFromTop()
    {
    	if(!deck.isEmpty())
    		return deck.remove(1);
    	else
    		return null;
    }
    
    public void AddCardOnTop(Card _card)
    {
    	deck.add(0, _card);
    }
    
    public void AddCardOnBottom(Card _card)
    {
    	deck.add(_card);
    }
    
    public void ShuffleDeck()
    {
    	Collections.shuffle(deck);
    }

	public Card DrawFromBottom() {
		if(!deck.isEmpty()) 
		{
			deck.trimToSize();
			return deck.remove(deck.size() - 1);
		}
		return null;
	}
}
