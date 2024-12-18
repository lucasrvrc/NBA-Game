package com.jogonba.cartas.board;
import com.jogonba.cartas.players.*;
import java.util.Random;
import java.util.Scanner;

public class GerenciadorTurno {
    private Jogador jogador1;
    private Jogador jogador2;
    private int IDJogadorAtivo;
    private Random random;
    private int turno;
    Scanner scanner = new Scanner(System.in);
    private DeckLibrary dl;
    private Deck deckCB;
    private Deck deckMH;

    public GerenciadorTurno(){
        this.jogador1 = new Jogador(1);
        this.jogador2 = new Jogador(2);
        this.dl = new DeckLibrary();
        this.random = new Random();
        this.deckCB = new Deck();
        this.deckMH = new Deck();
        this.turno = 1;
    }

    //Métodos relevantes:

    public void inicio(){
        System.out.println("Bem vindos ao jogo:");
        System.out.println("Começaremos com os nomes.");
        System.out.println("DIGITEM SEUS NOMES");
        String nome1 = scanner.nextLine();
        jogador1.setNome(nome1);
        String nome2 = scanner.nextLine();
        jogador2.setNome(nome2);
    }

    public void sorteio(){
        System.out.println("Agora é hora do sorteio");
        System.out.println("Se o número for 0, " + jogador1.getNome() + " começa jogando!");
        int numero = random.nextInt(2);
        System.out.println("O número foi: " + numero);
        if (numero == 0){
            IDJogadorAtivo = 1;
            System.out.println(jogador1.getNome() + " começa jogando!");
        } else {
            IDJogadorAtivo = 2;
            System.out.println(jogador2.getNome() + " começa jogando!");
        }
    }

    public void escolhaDeckCB() {
        deckCB.setCartasDeck(dl.getDeck1().getCartasDeck());
        jogador1.setDeck(deckCB);
        jogador1.setIdentificadorDeck("CB");
        deckMH.setCartasDeck(dl.getDeck2().getCartasDeck());
        jogador2.setDeck(deckMH);
        jogador2.setIdentificadorDeck("MH");
    }

    public void escolhaDeckMH() {
        deckMH.setCartasDeck(dl.getDeck2().getCartasDeck());
        jogador1.setDeck(deckMH);
        jogador1.setIdentificadorDeck("MH");
        deckCB.setCartasDeck(dl.getDeck1().getCartasDeck());
        jogador2.setDeck(deckCB);
        jogador2.setIdentificadorDeck("CB");
    }

    public void alternarJogador(){
        if (IDJogadorAtivo == 1){
            IDJogadorAtivo = 2;
        } else {
            IDJogadorAtivo = 1;
        }
    }

    public void rodadaInicial(){
        jogador1.setJogador(jogador1);
        jogador2.setJogador(jogador2);
        jogador1.setOponente(jogador2);
        jogador2.setOponente(jogador1);
        if (IDJogadorAtivo == 1){
            jogador1.faseCompra();
            jogador1.faseMana(turno);
            jogador1.fasePosicionamento();
            jogador2.faseCompra();
            jogador2.faseMana(turno);
            jogador2.fasePosicionamento();
            System.out.println("Fim do Turno");
            //alternarJogador();
            turno++;
        } else {
            jogador2.faseCompra();
            jogador2.faseMana(turno);
            jogador2.fasePosicionamento();
            jogador1.faseCompra();
            jogador1.faseMana(turno);
            jogador1.fasePosicionamento();
            System.out.println("Fim do Turno");
            //alternarJogador();
            turno++;
        }
    }

    public void rodadaInicialTeste(){
        jogador1.faseMana(turno);
        jogador1.fasePosicionamento();
        jogador2.faseMana(turno);
        jogador2.fasePosicionamento();
    }

    public void fasesJ1 (){
        jogador1.fasesJogador(turno);
        jogador2.fasesJogador(turno);
    }

    public void fasesJ2 (){
        jogador2.fasesJogador(turno);
        jogador1.fasesJogador(turno);
    }

    public void turno(){
        if (IDJogadorAtivo == 1){
            fasesJ1();
            System.out.println("Fim do Turno");
            //alternarJogador();
        } else {
            fasesJ2();
            System.out.println("Fim do Turno");
            //alternarJogador();
        }
    }

    public boolean verificadorTurno(){
        if (jogador1.getVivo() == true && jogador2.getVivo() == true && jogador1.semCartas() == false && jogador2.semCartas() == false){
            return true;
        } else {
            return false;
        }
    }

    public void gameplay(){
        inicio();
        sorteio();
        rodadaInicial();
        System.out.println("Fim da rodada inicial. ");
        while (verificadorTurno()){
            turno();
            turno++;
        }
        if (!jogador1.getVivo() || jogador1.getDeck().estaVazio()){
            System.out.println("Parabéns " + jogador2.getNome() + ", você ganhou o jogo!");
            jogador2.passouNivel(50);
            jogador1.passouNivel(15);
        } else {
            System.out.println("Parabéns " + jogador1.getNome() + ", você ganhou o jogo!");
            jogador1.passouNivel(50);
            jogador2.passouNivel(15);
        }
    }

    public Jogador getJogador1(){
        return jogador1;
    }
    public Jogador getJogador2(){
        return jogador2;
    }
}
