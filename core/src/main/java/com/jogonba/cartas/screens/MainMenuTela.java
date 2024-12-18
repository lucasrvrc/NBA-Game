package com.jogonba.cartas.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.jogonba.cartas.JogoCarta;

public class MainMenuTela implements Screen {
    private static final int EXIT_BUTTON_LARGURA = 200;
    private static final int EXIT_BUTTON_ALTURA = 70;
    private static final int EXIT_BUTTON_Y = 125;
    private static final int PLAY_BUTTON_LARGURA = 300;
    private static final int PLAY_BUTTON_ALTURA = 100;
    private static final int PLAY_BUTTON_Y = 200;
    private static final int NBA_PLAYOFFS_LOGO_ALTURA = 600;
    private static final int NBA_PLAYOFFS_LOGO_LARGURA = 900;
    private static final int NBA_PLAYOFFS_LOGO_Y = 300;

    private JogoCarta jogo;
    private final Texture playButtonActive;
    private final Texture playButtonInactive;
    private final Texture exitButtonActive;
    private final Texture exitButtonInactive;
    private final Texture nbaPlayoffsLogo;
    private final Texture nbaWallpaper;

    public MainMenuTela(JogoCarta jogo){
        this.jogo = jogo;
        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");
        nbaPlayoffsLogo = new Texture("nba_playoffs.png");
        nbaWallpaper = new Texture("nba_wallpaper.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        jogo.batch.begin();

        jogo.batch.draw(nbaWallpaper, 0, 0, JogoCarta.LARGURA, JogoCarta.ALTURA);

        jogo.batch.draw(nbaPlayoffsLogo, JogoCarta.LARGURA / 2 - NBA_PLAYOFFS_LOGO_LARGURA / 2, NBA_PLAYOFFS_LOGO_Y, NBA_PLAYOFFS_LOGO_LARGURA, NBA_PLAYOFFS_LOGO_ALTURA);

        int xPLAY = JogoCarta.LARGURA / 2 - PLAY_BUTTON_LARGURA / 2;
        if (Gdx.input.getX() < xPLAY + PLAY_BUTTON_LARGURA && Gdx.input.getX() > xPLAY && JogoCarta.ALTURA - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_ALTURA && JogoCarta.ALTURA - Gdx.input.getY() > PLAY_BUTTON_Y) {
            jogo.batch.draw(playButtonActive, xPLAY, PLAY_BUTTON_Y, PLAY_BUTTON_LARGURA, PLAY_BUTTON_ALTURA);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                this.dispose();
                jogo.setScreen(new SelectionTela(jogo));
            }
        } else {
            jogo.batch.draw(playButtonInactive, xPLAY, PLAY_BUTTON_Y, PLAY_BUTTON_LARGURA, PLAY_BUTTON_ALTURA);
        }

        int xEXIT = JogoCarta.LARGURA / 2 - EXIT_BUTTON_LARGURA / 2;
        if (Gdx.input.getX() < xEXIT + EXIT_BUTTON_LARGURA && Gdx.input.getX() > xEXIT && JogoCarta.ALTURA - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_ALTURA && JogoCarta.ALTURA - Gdx.input.getY() > EXIT_BUTTON_Y) {
            jogo.batch.draw(exitButtonActive, xEXIT, EXIT_BUTTON_Y, EXIT_BUTTON_LARGURA, EXIT_BUTTON_ALTURA);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                Gdx.app.exit();
            }
        } else {
            jogo.batch.draw(exitButtonInactive, xEXIT, EXIT_BUTTON_Y, EXIT_BUTTON_LARGURA, EXIT_BUTTON_ALTURA);
        }
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
