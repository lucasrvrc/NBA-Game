package com.jogonba.cartas.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jogonba.cartas.players.Jogador;
import java.util.Scanner;

public class CartaCriatura extends Carta {
    private final int LARGURA_CARTA = 130;
    private final int ALTURA_CARTA = 180;

    int xATAQUE, yATAQUE, xDEFESA, yDEFESA;

    private int ataque;
    private int defesa;
    Scanner scanner = new Scanner(System.in);

    private BitmapFont font;

    public CartaCriatura(String nomeCarta, int custoMana, int ataque, int defesa, String tipoEfeito, Texture textura){
        super(nomeCarta, custoMana, tipoEfeito, textura);
        this.ataque = ataque;
        this.defesa = defesa;
        font = new BitmapFont(Gdx.files.internal("fontejogo.fnt"));
        font.getData().setScale(0.3f);
        font.setColor(Color.BLACK);
    }

    //Métodos relevantes:
    public void aumentarAtaque(int valor){
        this.ataque += valor;
    }
    public void diminuirAtaque(int valor){
        this.ataque -= valor;
    }
    public void aumentarDefesa(int valor){
        this.defesa += valor;
    }
    public void diminuirDefesa(int valor){
        this.defesa -= valor;
    }

    @Override
    public void desenharCarta(SpriteBatch batch, Vector2 posicao){
        batch.draw(textura, posicao.x, posicao.y, LARGURA_CARTA, ALTURA_CARTA);
        font.draw(batch, printarAtaque(), posicao.x + 7, posicao.y + 20);
        font.draw(batch, printarDefesa(), posicao.x + 107, posicao.y + 20);
    }

    public String toString(){
        return super.toString() + ", Ataque: " + ataque + ", Defesa: " + defesa;
    }

    //Getters e setters:
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque() {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }
    public void setDefesa() {
        this.defesa = defesa;
    }

    public String printarAtaque(){
        return String.valueOf(ataque);
    }
    public String printarDefesa(){
        return String.valueOf(defesa);
    }

    @Override
    public void ativarEfeito(Jogador jogador, Jogador oponente, int posicao){
        switch(getNome()){
            case "Michael Jordan":
                getEfeito().theGoat(jogador, oponente, posicao);
                break;
            case "Scottie Pippen":
                getEfeito().contratoBarato(jogador);
                setEfeitoAtivo(true);
                break;
            case "Dennis Rodman":
                getEfeito().oVerme(jogador, posicao);
                setEfeitoAtivo(true);
                break;
            case "Steve Kerr":
                getEfeito().clutchTimeKerr(jogador, posicao);
                break;
            case "Toni Kukoč":
                getEfeito().loboSolitario(jogador, posicao);
                break;
            case "Lebron James":
                getEfeito().theKing(jogador, posicao);
                break;
            case "Dwyane Wade":
                for (int i = 0; i <= 1; i++) {
                    System.out.println("Qual carta será atacada?");
                    int posicao2 = Integer.parseInt(scanner.nextLine());
                    getEfeito().flash(jogador, oponente, posicao, posicao2);
                }
                setEfeitoAtivo(true);
                break;
            case "Chris Bosh":
                getEfeito().silencioso(jogador, posicao);
                setEfeitoAtivo(true);
                break;
            case "Ray Allen":
                getEfeito().clutchShooter(oponente);
                break;
            case "Shane Battier":
                getEfeito().defensorImplacavel(jogador, oponente, posicao);
                break;
        }
    }
}
