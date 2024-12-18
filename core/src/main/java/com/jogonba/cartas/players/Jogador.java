package com.jogonba.cartas.players;
import com.jogonba.cartas.cards.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jogonba.cartas.board.ManaInsuficienteException;
import com.jogonba.cartas.board.Tabuleiro;
import com.jogonba.cartas.cards.Carta;
import com.jogonba.cartas.cards.CartaCriatura;

public class Jogador{
    private String nome;
    private int identificador;
    private int vida;
    private int mana;
    private boolean vivo;
    String identificadorDeck;
    Hand hand;
    Deck deck;
    Cemiterio cemiterio;
    Tabuleiro tabuleiro;
    DeckLibrary dl;
    Scanner scanner = new Scanner(System.in);
    private Carta cartaRemovida;
    private Jogador oponente;
    private Jogador jogador;
    private boolean continuar = true;
    private int experiencia;
    private int nivel;



    public Jogador(int identificador){
        this.identificador = identificador;
        this.vida = 50;
        this.mana = 1;
        this.vivo = true;
        this.hand = new Hand();
        this.cemiterio = new Cemiterio();
        this.deck = new Deck();
        this.dl = new DeckLibrary();
        this.tabuleiro = new Tabuleiro();
        this.experiencia = 0;
        this.nivel = 0;
    }

    //Métodos relevantes:
    public void diminuirVida(int valor){
        this.vida -= valor;
        System.out.println(this.vida);
    }
    public void diminuirMana(int valor){
        this.mana -= valor;
        System.out.println(this.mana);
    }

    public void jogarCarta (int posicaoHand) throws ManaInsuficienteException{
        Carta cartaUsada = hand.removerCarta(posicaoHand);
        if (!(cartaUsada instanceof CartaCriatura)){
            if (cartaUsada instanceof CartaEncantamento){
                int quantMana = cartaUsada.getCustoMana();
                if (this.mana == 0) {
                    System.out.println("Sem mana disponível.");
                } else if (quantMana > this.mana) {
                    throw new ManaInsuficienteException("Mana insuficiente!");
                } else {
                    CartaEncantamento cartaEncantamento = (CartaEncantamento) cartaUsada;
                    diminuirMana(quantMana);
                    cartaEncantamento.setEfeito(new Efeitos());
                    cartaEncantamento.ativarEfeito(jogador, oponente, posicaoHand);
                }
            } else {
                int quantMana = cartaUsada.getCustoMana();
                if (this.mana == 0) {
                    System.out.println("Sem mana disponível.");
                } else if (quantMana > this.mana) {
                    throw new ManaInsuficienteException("Mana insuficiente!");
                } else {
                    CartaFeitico cartaFeitico = (CartaFeitico) cartaUsada;
                    diminuirMana(quantMana);
                    cartaFeitico.setEfeito(new Efeitos());
                    System.out.println("Em qual carta deseja aplicar o efeito?");
                    int posicao = Integer.parseInt(scanner.nextLine());
                    cartaFeitico.ativarEfeito(jogador, oponente, posicao);
                }
            }
            cemiterio.adicionarCarta(cartaUsada);
            hand.removerCarta(posicaoHand);
        } else if (tabuleiro.tabuleiroOcupado() != 0) {
            this.cartaRemovida = (CartaCriatura) cartaUsada;
            int quantMana = cartaRemovida.getCustoMana();
            if (this.mana == 0) {
                System.out.println("Sem mana disponível.");
            } else if (quantMana > this.mana) {
                throw new ManaInsuficienteException("Mana insuficiente!");
            } else {
                System.out.println("Escolha em qual posição do tabuleiro deseja jogar");
                int posicao = Integer.parseInt(scanner.nextLine());
                diminuirMana(quantMana);
                tabuleiro.colocarCarta(posicao, cartaRemovida);
                cartaRemovida.setEfeito(new Efeitos());
                cartaRemovida.ativarEfeito(jogador, oponente, posicao);
            }
        } else {
            System.out.println("O tabuleiro está completo.");
        }
    }

    public void removerCarta (int posicaoTabuleiro){
        Carta cartaRemovida = tabuleiro.removerCarta (posicaoTabuleiro);
        cemiterio.adicionarCarta (cartaRemovida);
    }

    public void atacarCarta (CartaCriatura cartaAtacante, CartaCriatura cartaOponente, Jogador jogadorOponente){
        if (cartaAtacante.getAtaque() >= cartaOponente.getDefesa()){
            int posicaoTabuleiro = jogadorOponente.getTabuleiro().verificarPosicao(cartaOponente);
            int dano = cartaAtacante.getAtaque() - cartaOponente.getDefesa();
            jogadorOponente.diminuirVida(dano);
            jogadorOponente.removerCarta(posicaoTabuleiro);
        } else {
            cartaOponente.diminuirDefesa(cartaAtacante.getAtaque());
        }
    }

    public void puxarHandInicial(){
        deck.shuffle();
        for (int i = 0; i < 5; i++){
            hand.comprarCarta(deck);
        }
    }

    public void mostrarHand(){
        hand.mostrarHand();
    }

    public void ataqueDireto (CartaCriatura cartaAtaque, Jogador oponente){
        int dano = cartaAtaque.getAtaque();
        oponente.diminuirVida(dano);
        System.out.println("Vida de " + oponente.getNome() + ": " + oponente.getVida());
    }




    //Fases do jogador:
    public void faseCompra(){
        System.out.println("Fase de Compra de " + nome);
        hand.comprarCarta(deck);
    }

    public void faseMana (int turno){
        if (this.mana <= 10){
            this.mana = turno;
        } else {
            this.mana = 10;
        }
    }

    public void fasePosicionamento(){
        continuar = true;
        deck.contDeck();
        System.out.println("Fase de Posicionamento de " + nome);
        while (continuar){
            if (mana == 0){
                System.out.println("Sem mana disponível.");
                continuar = false;
            } else {
                System.out.println("A mana disponível é: " + mana);
                mostrarHand();
                System.out.println("Escolha qual carta quer jogar, " + nome);
                int posicao = Integer.parseInt(scanner.nextLine());
                try {
                    jogarCarta(posicao);
                } catch (ManaInsuficienteException e) {
                    System.out.println(e.getMessage());
                    hand.devolverCarta(posicao, cartaRemovida);
                }
                System.out.println("Deseja jogar outra carta, " + nome + "? [S/N]");
                String escolhaCont = scanner.nextLine();
                if (escolhaCont.equalsIgnoreCase("n")) {
                    continuar = false;
                }
            }
        }

        tabuleiro.mostrarTabuleiro();
    }

    public void faseCombate (){
        System.out.println("Fase de Combate de " + nome);
        boolean continuar = true;
        System.out.println("Deseja atacar o adversário, " + nome + "? [S/N]");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("n")){
            continuar = false;
        }
        ArrayList<Integer> cartasUsadas = new ArrayList<>();
        while (continuar){
            CartaCriatura cartaAtaque = null;
            System.out.println("Escolha a carta que atacará: ");
            int escolha1 = Integer.parseInt(scanner.nextLine());
            if (cartasUsadas.contains(escolha1)){
                System.out.println("Essa carta já foi utilizada.");
            } else {
                cartasUsadas.add(escolha1);
                CartaCriatura carta1 = (CartaCriatura) tabuleiro.escolherCarta(escolha1);
                cartaAtaque = carta1;
                if (oponente.getTabuleiro().tabuleiroOcupado() != 4) {
                    System.out.println("Agora, escolha qual carta do oponente você deseja atacar: ");
                    CartaCriatura cartaDefesa = null;
                    int escolha2 = Integer.parseInt(scanner.nextLine());
                    Carta carta2 = oponente.getTabuleiro().escolherCarta(escolha2);
                    cartaDefesa = (CartaCriatura) carta2;
                    assert cartaDefesa != null;
                    atacarCarta(cartaAtaque, cartaDefesa, oponente);
                } else {
                    ataqueDireto(cartaAtaque, oponente);
                }
            }

            System.out.println("Deseja atacar novamente? [S/N]");
            String decisao = scanner.nextLine();
            if (decisao.equalsIgnoreCase("n")){
                continuar = false;
            }
        }
        oponente.getCemiterio().mostrarCemiterio();
    }

    public void fasesJogador(int turno){
        faseCompra();
        faseMana(turno);
        fasePosicionamento();
        faseCombate();
    }

    public void armazenarPosicoesHand(){
        if (identificadorDeck.equals("CB")){
            for (int i = 0; i < 9; i++){
                hand.posicoesHand[i].set(220 + 140 * i, 574);
            }
        } else {
            for (int i = 0; i < 9; i++){
                hand.posicoesHand[i].set(220 + 140 * i, 0);
            }
        }
    }

    public void armazenarPosicoesTabuleiro(){
        if (identificadorDeck.equals("CB")){
            for (int i = 0; i < 5; i++){
                tabuleiro.posicoesTabuleiro[i].set(246 + 184 * i, 200);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                tabuleiro.posicoesTabuleiro[i].set(246 + 184 * i, 400);
            }
        }
    }

    public void desenharCartasHandInicial(SpriteBatch batch){
        armazenarPosicoesHand();
        for (int i = 0; i < 5; i++){
            Carta carta = hand.cartasHand.get(i);
            carta.desenharCarta(batch, hand.posicoesHand[i]);
        }
    }

    public void desenharCartaComprada(SpriteBatch batch){
        Carta carta = hand.comprarCarta(deck);
        for (int i = 0; i < 7; i++){
            boolean continuar = tabuleiro.lugarVazio(i);
            if (continuar = false){
                carta.desenharCarta(batch, hand.posicoesHand[i]);
            }
        }
    }

    public void desenharStatus(SpriteBatch batch){
        if (identificadorDeck.equals("CB")){

        }
    }


    //Condições de vitória:
    public void isVivo(){
        if (vida < 0){
            this.vivo = false;
        }
    }
    public boolean semCartas (){
        return deck.estaVazio();
    }


    //Getters e setters:
    public void setVida(int vida) {
        this.vida = vida;
    }
    public int getVida() {
        return vida;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    public int getMana() {
        return mana;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    public int getIdentificador(){
        return identificador;
    }
    public void setIdentificador(int identificador){
        this.identificador = identificador;
    }

    public Hand getHand(){
        return hand;
    }

    public Deck getDeck(){
        return deck;
    }

    public void setDeck(Deck deck){
        this.deck = deck;
    }

    public Cemiterio getCemiterio(){
        return cemiterio;
    }

    public void setOponente(Jogador oponente){
        this.oponente = oponente;
    }

    public void setJogador(Jogador jogador){
        this.jogador = jogador;
    }
    public Jogador getOponente(){
        return oponente;
    }

    public Tabuleiro getTabuleiro(){
        return tabuleiro;
    }

    public void status(){
        System.out.println("Vida atual: " + vida);
        System.out.println("Mana atual: " + mana);
    }

    public boolean getVivo(){
        isVivo();
        return vivo;
    }

    public void passouNivel(int expOb){
        experiencia = experiencia + expOb;
        if (expOb == 50){;
            nivel++;
            System.out.println("Parabéns " + nome + "! Você subiu de nível! Agora é nível " + nivel);
            System.out.println("Experiência atual: " + experiencia);
        } else {
            System.out.println("Experiência atual: " + experiencia);
        }
    }

    public String getIdentificadorDeck(){
        return identificadorDeck;
    }
    public void setIdentificadorDeck(String identificadorDeck){
        this.identificadorDeck = identificadorDeck;
    }

    public String printarMana(){
        return String.valueOf(mana);
    }
    public String printarVida(){
        return String.valueOf(vida);
    }
}