package com.jogonba.cartas.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jogonba.cartas.JogoCarta;
import com.jogonba.cartas.board.GerenciadorTurno;
import com.jogonba.cartas.players.DeckLibrary;

import java.util.Random;

public class SelectionTela implements Screen {

    private static final int CB_BUTTON_LARGURA = 400;
    private static final int CB_BUTTON_ALTURA = 400;
    private static final int CB_BUTTON_X = 470 - CB_BUTTON_LARGURA/2;
    private static final int MH_BUTTON_X = 940 - CB_BUTTON_LARGURA/2;
    private static final int CBMH_Y = (JogoCarta.ALTURA - CB_BUTTON_ALTURA)/2;

    private JogoCarta jogo;

    private final Texture nbaWallpaper;
    private final Texture cbInactive;
    private final Texture cbActive;
    private final Texture mhInactive;
    private final Texture mhActive;
    private Texture texturaAtualCB;
    private Texture texturaAtualMH;
    private BitmapFont font;

    public SelectionTela(JogoCarta jogo){
        this.jogo = jogo;
        nbaWallpaper = new Texture("nba_wallpaper.jpg");
        cbInactive = new Texture("cb_inactive.png");
        cbActive = new Texture("cb_active.png");
        mhInactive = new Texture("mh_inactive.png");
        mhActive = new Texture("mh_active.png");
        texturaAtualCB = cbInactive;
        texturaAtualMH = mhInactive;
        font = new BitmapFont(Gdx.files.internal("fontejogo.fnt"));
        font.getData().setScale(2f);
        font.setColor(Color.BLACK);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        if (Gdx.input.getX() > CB_BUTTON_X && Gdx.input.getX() < CB_BUTTON_X + CB_BUTTON_LARGURA && JogoCarta.ALTURA - Gdx.input.getY() < CBMH_Y + CB_BUTTON_ALTURA && JogoCarta.ALTURA - Gdx.input.getY() > CBMH_Y){
            texturaAtualCB = cbActive;
            if (Gdx.input.isTouched()){
                this.dispose();
                jogo.gt.escolhaDeckCB();
                jogo.setScreen(new JogoTela(jogo));
            }
        } else {
            texturaAtualCB = cbInactive;
        }
        if (Gdx.input.getX() > MH_BUTTON_X && Gdx.input.getX() < MH_BUTTON_X + CB_BUTTON_LARGURA && JogoCarta.ALTURA - Gdx.input.getY() < CBMH_Y + CB_BUTTON_ALTURA && JogoCarta.ALTURA - Gdx.input.getY() > CBMH_Y){
            texturaAtualMH = mhActive;
            if (Gdx.input.isTouched()){
                this.dispose();
                jogo.gt.escolhaDeckMH();
                jogo.setScreen(new JogoTela(jogo));
            }
        } else {
            texturaAtualMH = mhInactive;
        }

        jogo.batch.begin();

        jogo.batch.draw(nbaWallpaper, 0, 0, JogoCarta.LARGURA, JogoCarta.ALTURA);

        font.draw(jogo.batch, "SELECIONE SEU TIME", 200, 700);

        jogo.batch.draw(texturaAtualCB, CB_BUTTON_X, CBMH_Y, CB_BUTTON_LARGURA, CB_BUTTON_ALTURA);
        jogo.batch.draw(texturaAtualMH, MH_BUTTON_X, CBMH_Y, CB_BUTTON_LARGURA, CB_BUTTON_ALTURA);

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

    }
}
