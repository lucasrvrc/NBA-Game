package com.jogonba.cartas.cards;

import com.badlogic.gdx.graphics.Texture;
import com.jogonba.cartas.players.Jogador;

public class CartaEncantamento extends Carta {
    private int valor;

    public CartaEncantamento(String nome, int custoMana, String tipoEfeito, int valor, Texture textura) {
        super(nome, custoMana, tipoEfeito, textura);
        this.valor = valor;
    }

    @Override
    public void ativarEfeito(Jogador jogador, Jogador oponente, int posicao){
        switch (getNome()){
            case "Provocação":
                getEfeito().provocacao(oponente);
                break;
            case "Clutch time":
                getEfeito().clutchTime(jogador);
                break;
        }

    }

    //public String toString(){
    //    return super.toString() + ", Efeito: " + efeito;
    //}
}
