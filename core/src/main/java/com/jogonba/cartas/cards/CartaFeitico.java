package com.jogonba.cartas.cards;

import com.badlogic.gdx.graphics.Texture;
import com.jogonba.cartas.players.Jogador;

import java.util.Scanner;

public class CartaFeitico extends Carta {
    private int valor;
    Scanner scanner = new Scanner(System.in);

    public CartaFeitico(String nome, int custoMana, String tipoEfeito, int valor, Texture textura) {
        super(nome, custoMana, tipoEfeito, textura);
        this.valor = valor;
    }

    @Override
    public void ativarEfeito(Jogador jogador, Jogador oponente, int posicao){
        switch(getNome()){
            case "Bola de 3":
                getEfeito().bolaDe3p(oponente, posicao);
                break;
            case "Alley-oop":
                System.out.println("Escolha uma carta para aumentar o dano: ");
                int posicao2 = Integer.parseInt(scanner.nextLine());
                getEfeito().alleyOop(jogador, posicao, posicao2);
                break;
            case "Assistência":
                getEfeito().assistencia(jogador, oponente, posicao);
                break;
            case "Expulsão":
                getEfeito().expulsao(oponente, posicao);
                break;
        }
    }

    public String toString (){
        return super.toString();
    }
}
