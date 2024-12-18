package com.jogonba.cartas.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jogonba.cartas.JogoCarta;
import com.jogonba.cartas.board.GerenciadorTurno;
import com.jogonba.cartas.cards.CartaCriatura;
import com.jogonba.cartas.players.Jogador;

public class JogoTela implements Screen {

    private Jogador jogador1;
    private Jogador jogador2;

    private final int FINISH_BUTTON_X = 1042;

    private BitmapFont font;
    private Texture tabuleiro;
    private Texture fundo;
    private Texture gradient;
    private Texture finishActive;
    private Texture finishInactive;

    private JogoCarta jogo;

    public JogoTela(JogoCarta jogo){
        this.jogo = jogo;

        jogador1 = new Jogador(1);
        jogador2 = new Jogador(2);

        fundo = new Texture("wood.jpg");
        tabuleiro = new Texture("tabuleirotexture.png");
        gradient = new Texture("gradient.png");
        finishActive = new Texture("finish_active.png");
        finishInactive = new Texture("finish_inactive.png");
        font = new BitmapFont(Gdx.files.internal("fontejogo.fnt"));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        jogo.gt.getJogador1().puxarHandInicial();
        jogo.gt.getJogador2().puxarHandInicial();

        jogo.batch.begin();
        jogo.batch.draw(fundo, 0, 0);
        jogo.batch.draw(tabuleiro, JogoCarta.LARGURA / 2 - tabuleiro.getWidth() / 2, 0);
        jogo.batch.draw(gradient, JogoCarta.LARGURA / 2 - 460, 0, 920, gradient.getHeight());
        jogo.batch.draw(gradient, JogoCarta.LARGURA / 2 - 460, JogoCarta.ALTURA, 920, - gradient.getHeight());

        int yFINISH = JogoCarta.ALTURA / 2 - finishActive.getHeight() / 2;
        if (Gdx.input.getX() < FINISH_BUTTON_X + finishActive.getWidth() && Gdx.input.getX() > FINISH_BUTTON_X && JogoCarta.ALTURA - Gdx.input.getY() < yFINISH + finishActive.getHeight() && JogoCarta.ALTURA - Gdx.input.getY() > yFINISH){
            jogo.batch.draw(finishActive, FINISH_BUTTON_X, yFINISH);
        } else {
            jogo.batch.draw(finishInactive, FINISH_BUTTON_X, yFINISH);
        }

        jogo.gt.getJogador1().desenharCartasHandInicial(jogo.batch);
        jogo.gt.getJogador2().desenharCartasHandInicial(jogo.batch);

        while (jogo.gt.verificadorTurno()){

        }

        font.draw(jogo.batch, "TESTE DE TEXTO", 0, JogoCarta.ALTURA);
        jogo.batch.end();
    }



    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        jogo.batch.dispose();
        tabuleiro.dispose();
    }


}
