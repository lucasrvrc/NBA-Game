package com.jogonba.cartas.cards;
import com.jogonba.cartas.players.*;

public class Efeitos {

    public Efeitos(){
    }

    //Efeito das criaturas
    //Efeitos do Bulls
    public void theGoat (Jogador jogador, Jogador oponente, int posicao){
        int ataqueGanho = oponente.getTabuleiro().quantCartas() * 2;
        jogador.getTabuleiro().escolherCarta(posicao).aumentarAtaque(ataqueGanho);
    }

    public void contratoBarato(Jogador jogador){
        jogador.setMana(jogador.getMana() + 1);
    }

    public void oVerme(Jogador jogador, int posicao){
        jogador.getTabuleiro().escolherCarta(posicao).aumentarDefesa(1);
    }

    public void clutchTimeKerr(Jogador jogador, int posicao){
        if (jogador.getVida() <= 50){
            jogador.getTabuleiro().escolherCarta(posicao).aumentarAtaque(5);
        }
    }

    public void loboSolitario(Jogador jogador, int posicao){
        if(jogador.getTabuleiro().tabuleiroOcupado() == 1){
            jogador.getTabuleiro().escolherCarta(posicao).aumentarDefesa(3);
            jogador.getTabuleiro().escolherCarta(posicao).aumentarAtaque(3);
        }
    }

    //Efeitos do Heat
    public void theKing(Jogador jogador, int posicao){
        int defesaGanha = (jogador.getTabuleiro().quantCartas() - 1) * 2;
        jogador.getTabuleiro().escolherCarta(posicao).aumentarDefesa(defesaGanha);
    }

    public void flash(Jogador jogador, Jogador oponente, int posicao, int posicao2){
        CartaCriatura cartaAtaque = jogador.getTabuleiro().escolherCarta(posicao);
        if(oponente.getTabuleiro().tabuleiroOcupado() != 0) {
            CartaCriatura cartaDefesa = oponente.getTabuleiro().escolherCarta(posicao2);
            jogador.atacarCarta(cartaAtaque, cartaDefesa, oponente);
        } else {
            jogador.ataqueDireto(cartaAtaque, oponente);
        }
    }

    public void silencioso(Jogador jogador, int posicao){
        jogador.getTabuleiro().escolherCarta(posicao).aumentarDefesa(1);
        jogador.getTabuleiro().escolherCarta(posicao).aumentarAtaque(1);
    }

    public void clutchShooter(Jogador oponente){
        oponente.diminuirVida(3);
    }

    public void defensorImplacavel(Jogador jogador, Jogador oponente, int posicao){
        for (int i = 0; i <= 4; i++){
            if (oponente.getTabuleiro().escolherCarta(i) != null){
                int ataque = oponente.getTabuleiro().escolherCarta(i).getAtaque();
                if(ataque >= 10) {
                    jogador.getTabuleiro().escolherCarta(posicao).aumentarDefesa(2);
                }
            }
            i++;
        }
    }

    //Efeito dos feitiços
    //Bola de 3 recebendo como parâmetro a posição da carta atacada
    public void bolaDe3p (Jogador oponente, int n){
        CartaCriatura carta = oponente.getTabuleiro().escolherCarta(n);
        carta.diminuirDefesa(3);
    }

    public void alleyOop (Jogador jogador, int posCartaPerde, int posCartaGanha){
        jogador.getTabuleiro().escolherCarta(posCartaPerde).diminuirAtaque(1);
        jogador.getTabuleiro().escolherCarta(posCartaGanha).aumentarAtaque(1);
    }

    public void assistencia(Jogador jogador, Jogador oponente, int posicao){
        jogador.getTabuleiro().escolherCarta(posicao).ativarEfeito(jogador, oponente, posicao);
    }

    public void expulsao(Jogador oponente, int posicao){
        oponente.getTabuleiro().removerCarta(posicao);
    }

    //Efeitos de Encantamentos
    public void provocacao(Jogador oponente){
        for (int i = 0; i < 5; i++) {
            oponente.getTabuleiro().escolherCarta(i).diminuirAtaque(2);
        }
    }

    public void clutchTime (Jogador jogador){
        int vida = jogador.getVida();
        if (vida >= 50){
            for (int i = 0; i < 5;) {
                if (jogador.getTabuleiro().escolherCarta(i) == null){
                    i++;
                } else {
                    jogador.getTabuleiro().escolherCarta(i).aumentarAtaque(1);
                    i++;
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (jogador.getTabuleiro().escolherCarta(i) == null) {
                    i++;
                } else {
                    jogador.getTabuleiro().escolherCarta(i).aumentarAtaque(3);
                    i++;
                }
            }
        }
    }
}
