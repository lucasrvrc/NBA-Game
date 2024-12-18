package com.jogonba.cartas.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jogonba.cartas.players.Jogador;

public abstract class Carta implements AtivarEfeitos{
    private final int LARGURA_CARTA = 130;
    private final int ALTURA_CARTA = 180;

    Texture textura;
    Vector2 posicaoCarta;

    private String nome;
    private int custoMana;
    private boolean efeitoAtivo;
    private String tipoEfeito;
    private Efeitos efeito;

    public Carta(String nome, int custoMana, String tipoEfeito, Texture textura){
        this.nome = nome;
        this.custoMana = custoMana;
        this.efeitoAtivo = false;
        this.textura = textura;
        this.tipoEfeito = tipoEfeito;
        this.efeito = null;
        posicaoCarta = new Vector2(-1,-1);
    }

    public void desenharCarta(SpriteBatch batch, Vector2 posicao){
        batch.draw(textura, posicao.x, posicao.y, LARGURA_CARTA, ALTURA_CARTA);
        posicaoCarta = new Vector2(posicao);
    }

    //Getters e setters:
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    public String toString(){
        return "Carta: " + nome + ", Custo de Mana: " + custoMana;
    }

    public void setCustoMana(int custoMana){
        this.custoMana = custoMana;
    }
    public int getCustoMana(){
        return custoMana;
    }

    public void setTipoEfeito(String tipoEfeito){
        this.tipoEfeito = tipoEfeito;
    }
    public String getTipoEfeito(){
        return tipoEfeito;
    }
    public void setEfeitoAtivo(boolean efeitoAtivo) {
        this.efeitoAtivo = efeitoAtivo;
    }
    public Efeitos getEfeito(){
        return efeito;
    }
    public void setEfeito(Efeitos efeito){
        this.efeito = efeito;
    }

    @Override
    public abstract void ativarEfeito(Jogador jogador, Jogador oponente, int posicao);

    public Vector2 getPosicaoCarta(){
        return posicaoCarta;
    }
}
