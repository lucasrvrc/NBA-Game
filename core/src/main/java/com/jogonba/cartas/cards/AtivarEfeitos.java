package com.jogonba.cartas.cards;

import com.jogonba.cartas.players.Jogador;

public interface AtivarEfeitos {
    public void ativarEfeito(Jogador jogador, Jogador oponente, int posicao);
}

