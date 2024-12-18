package com.jogonba.cartas.players;

import java.util.LinkedList;
import java.util.Collections;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jogonba.cartas.cards.Carta;

public class Deck {
    protected LinkedList<Carta> cartasDeck;

    public Deck(){
        this.cartasDeck = new LinkedList<>();
    }

    //Métodos relevantes:
    public void add (Carta carta){
        cartasDeck.add(carta);
    };
    public void shuffle(){
        Collections.shuffle(cartasDeck);
    }



    public Carta removerCarta(){
        return cartasDeck.poll();
    }

    public boolean estaVazio(){
        return cartasDeck.isEmpty();
    }

    public LinkedList<Carta> getCartasDeck(){
        return this.cartasDeck;
    }

    public void setCartasDeck (LinkedList<Carta> cartasDeck){
        this.cartasDeck = cartasDeck;
    }

    public void contDeck(){
        System.out.println("Quantidade de cartas no deck: " + cartasDeck.size());
    }


}
