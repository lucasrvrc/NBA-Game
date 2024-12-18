package com.jogonba.cartas.players;
import java.util.ArrayList;
import com.jogonba.cartas.cards.Carta;

public class Cemiterio {
    private ArrayList<Carta> cartasCemiterio;

    public Cemiterio(){
        this.cartasCemiterio = new ArrayList<>();
    }

    //Métodos relevantes:
    public void adicionarCarta(Carta carta){
        cartasCemiterio.add(carta);
    }

    public void mostrarCemiterio(){
        if (!cartasCemiterio.isEmpty()){
            for (int i = 0; i < cartasCemiterio.size(); i++) {
                System.out.println(cartasCemiterio.get(i));
            }
        } else {
            System.out.println("O cemitério está vazio!");
        }
    }
}
