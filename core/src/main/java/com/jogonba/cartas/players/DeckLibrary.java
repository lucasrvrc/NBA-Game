package com.jogonba.cartas.players;
import com.badlogic.gdx.graphics.Texture;
import com.jogonba.cartas.cards.*;
import java.util.Collections;
import java.util.LinkedList;

public class DeckLibrary {
    private Deck deck1;
    private Deck deck2;
    LinkedList<Carta> cartasDeck;

    public DeckLibrary() {

        this.deck1 = new Deck();
        this.deck2 = new Deck();


        //Deck 1
        deck1.cartasDeck.add(new CartaCriatura("Michael Jordan", 6, 16, 12, "The GOAT: Ganha +2 de ataque para cada inimigo em campo", new Texture("michaeljordan.png")));
        deck1.cartasDeck.add(new CartaCriatura("Scottie Pippen", 5, 12, 12, "Contrato barato: No começo dos rounds, o jogador recebe +1 de mana", new Texture("scottiepippen.png")));
        deck1.cartasDeck.add(new CartaCriatura("Dennis Rodman", 5, 4, 14, "O verme: A cada rodada que passar vivo, aumenta em +1 a defesa de todo o time", new Texture("dennisrodman.png")));
        deck1.cartasDeck.add(new CartaCriatura("Steve Kerr", 4, 9, 6, "Clutch: Se for jogado nos últimos 3 rounds da partida, ganha +5 de ataque", new Texture("stevekerr.png")));
        deck1.cartasDeck.add(new CartaCriatura("Toni Kukoč", 4, 8, 7, "Lobo solitário: Se for jogado sem mais nenhum aliado em campo, ganha +3 de ataque e +3 de defesa", new Texture("tonikukoc.png")));
        deck1.cartasDeck.add(new CartaCriatura("Ron Harper", 3, 6, 6, "", new Texture("ronharper.png")));
        deck1.cartasDeck.add(new CartaCriatura("Luc Longley", 3, 8, 4, "", new Texture("luclongley.png")));
        deck1.cartasDeck.add(new CartaCriatura("Bill Wennington", 2, 5, 3, "", new Texture("billwennington.png")));
        deck1.cartasDeck.add(new CartaCriatura("Randy Brown", 2, 2, 6, "", new Texture("randybrown.png")));
        deck1.cartasDeck.add(new CartaCriatura("Jud Buechler", 2, 4, 4, "", new Texture("judbuechler.png")));
        deck1.cartasDeck.add(new CartaCriatura("Dickey Simpkins", 1, 3, 1, "", new Texture("dickeysimpkins.png")));
        deck1.cartasDeck.add(new CartaCriatura("Jason Caffey", 1, 2, 2, "", new Texture("jasoncaffey.png")));
        deck1.cartasDeck.add(new CartaCriatura("James Edwards", 1, 2, 1, "", new Texture("jamesedwards.png")));
        deck1.cartasDeck.add(new CartaFeitico("Assistência", 3, "tipoefeito", 0, new Texture("assistencia.png")));
        deck1.cartasDeck.add(new CartaFeitico("Bola de 3", 1, "Causa 3 de dano a qualquer inimigo", 0, new Texture("bolade3.png")));
        deck1.cartasDeck.add(new CartaFeitico("Alley-oop", 1, "Um jogador transfere seu ataque para outro nesse turno", 0, new Texture("alleyoop.png")));
        deck1.cartasDeck.add(new CartaFeitico("Expulsão", 5, "Uma carta inimiga é enviada diretamente ao cemitério", 0, new Texture("expulsao.png")));
        deck1.cartasDeck.add(new CartaEncantamento("Provocação", 2, "Reduz em -2 a defesa do time inimigo", 0, new Texture("provocacao.png")));
        deck1.cartasDeck.add(new CartaEncantamento("Clutch time", 2, "Aumenta em o ataque do time aliado em +1; se for usado nos últimos 3 rounds da partida, aumenta em +3", 0, new Texture("clutchtime.png")));


        //Deck 2
        deck2.cartasDeck.add(new CartaCriatura("LeBron James", 6, 15, 15, "The King: Ganha +2 de defesa para cada carta aliada em campo", new Texture("lebronjames.png")));
        deck2.cartasDeck.add(new CartaCriatura("Dwyane Wade", 5, 14, 11, "Flash: Quando atacar, pode atacar duas vezes por turno.", new Texture("dwaynewade.png")));
        deck2.cartasDeck.add(new CartaCriatura("Chris Bosh", 5, 13, 10, "Silencioso: Ganha +1 de ataque e +1 de defesa a cada turno.", new Texture("chrisbosh.png")));
        deck2.cartasDeck.add(new CartaCriatura("Ray Allen", 4, 10, 8, "Clutch Shooter: Quando entra em campo, causa 3 de dano direto ao oponente.", new Texture("rayallen.png")));
        deck2.cartasDeck.add(new CartaCriatura("Shane Battier", 4, 9, 9, "Defensor Implacável: Ganha +2 de defesa contra criaturas inimigas com mais de 10 de ataque.", new Texture("shanebattier.png")));
        deck2.cartasDeck.add(new CartaCriatura("Mario Chalmers", 3, 7, 5, "", new Texture("mariochalmers.png")));
        deck2.cartasDeck.add(new CartaCriatura("Mike Miller", 3, 6, 5, "", new Texture("mikemiller.png")));
        deck2.cartasDeck.add(new CartaCriatura("Udonis Haslem", 2, 4, 3, "", new Texture("udonishaslem.png")));
        deck2.cartasDeck.add(new CartaCriatura("Norris Cole", 2, 5, 1, "", new Texture("norriscole.png")));
        deck2.cartasDeck.add(new CartaCriatura("Joel Anthony", 2, 2, 4, "", new Texture("joelanthony.png")));
        deck2.cartasDeck.add(new CartaCriatura("Rashard Lewis", 1, 2, 2, "", new Texture("rashardlewis.png")));
        deck2.cartasDeck.add(new CartaCriatura("Chris Andersen", 1, 1, 2, "", new Texture("chrisandersen.png")));
        deck2.cartasDeck.add(new CartaCriatura("James Jones", 1, 2, 1, "", new Texture("jamesjones.png")));
        deck2.cartasDeck.add(new CartaFeitico("Assistência", 3, "tipoefeito", 0, new Texture("assistencia.png")));
        deck2.cartasDeck.add(new CartaFeitico("Bola de 3", 1, "Causa 3 de dano a qualquer inimigo", 0, new Texture("bolade3.png")));
        deck2.cartasDeck.add(new CartaFeitico("Alley-oop", 1, "Um jogador transfere seu ataque para outro nesse turno", 0, new Texture("alleyoop.png")));
        deck2.cartasDeck.add(new CartaFeitico("Expulsão", 5, "Uma carta inimiga é enviada diretamente ao cemitério", 0, new Texture("expulsao.png")));
        deck2.cartasDeck.add(new CartaEncantamento("Provocação", 2, "Reduz em -2 a defesa do time inimigo", 0, new Texture("provocacao.png")));
        deck2.cartasDeck.add(new CartaEncantamento("Clutch time", 2, "Aumenta em o ataque do time aliado em +1; se for usado nos últimos 3 rounds da partida, aumenta em +3", 0, new Texture("clutchtime.png")));
    }

    public Deck getDeck1(){
        return deck1;
    }
    public Deck getDeck2(){
        return deck2;
    }

    public void embaralharCartas1(){
        deck1.shuffle();
    }

    public void embaralharCartas2(){
        deck2.shuffle();
    }

    public LinkedList<Carta> getCartasDeck(){
        return cartasDeck;
    }

}
