package com.jogonba.cartas.players;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jogonba.cartas.cards.Carta;

public class Hand {
    ArrayList<Carta> cartasHand;
    Vector2[] posicoesHand;

    public Hand(){
        this.cartasHand = new ArrayList<>();
        posicoesHand = new Vector2[10];

        for (int i = 0; i < posicoesHand.length; i++) {
            posicoesHand[i] = new Vector2();
        }
    }



    //Métodos relevantes:
    public Carta comprarCarta(Deck deck){
        Carta cartaRemovida = deck.removerCarta();
        cartasHand.add(cartaRemovida);
        return cartaRemovida;
    }

    public Carta removerCarta(int posicao){
        return cartasHand.remove(posicao);
    }

    public void devolverCarta(int posicao, Carta carta){
        cartasHand.add(posicao, carta);
    }

    public void mostrarHand() {
        for (int n = 0; n < cartasHand.size(); n++) {
            Carta carta = cartasHand.get(n);
            System.out.println("["+n+"]" + carta);
        }
    }
    private Vector2 detectarCartaClicada(float mouseX, float mouseY) {
        for (int i = 0; i < 9; i++) {
            Carta carta = cartasHand.get(i);
            if (mouseX >= carta.getPosicaoCarta().x && mouseX <= carta.getPosicaoCarta().x + 130 &&
                    mouseY >= carta.getPosicaoCarta().y && mouseY <= carta.getPosicaoCarta().y + 180) {
                return carta.getPosicaoCarta();
            }
        }
        return null; // Nenhuma carta foi clicada
    }


    public Carta escolherCarta(int n) {
        return cartasHand.get(n);
    }

    public ArrayList<Carta> getCartasHand(){
        return cartasHand;
    }
}