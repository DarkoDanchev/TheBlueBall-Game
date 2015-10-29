package com.dancev.sinatatopka;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MainMenuScreen implements Screen {
    SinataTopka igra;
    TextButton play;
    SpriteBatch batch;
    TextureAtlas atlas;
    Skin skin;
    Viewport viewport;
     Stage stage;
    Label heading;
    BitmapFont font;
    OrthographicCamera camera;
    Table tab;
    public MainMenuScreen(final SinataTopka gam) {
        this.igra = gam;
        camera = new OrthographicCamera();
        camera.viewportHeight = 480;
        camera.viewportWidth = 800;
        camera.position.set(camera.viewportWidth * .5f,camera.viewportHeight * .5f, 0f);
        camera.update();
		batch = new SpriteBatch();
		//font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font0.png"), false);
       
      

    }

	@Override
	public void show() {
		stage = new Stage(new ExtendViewport(200, 350));
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas(Gdx.files.internal("meni.pack"));
        skin = new Skin(atlas);
        skin.addRegions(atlas);
        tab = new Table(skin);
        tab.setFillParent(true);
        tab.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        final TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.down = skin.getDrawable("igraj_dole");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = igra.font;
        textButtonStyle.up = skin.getDrawable("igraj_gore");
        
        play = new TextButton("",textButtonStyle);

        tab.add(play);
        play.addListener(new ChangeListener(){
            @Override
 			public void changed(ChangeEvent event, Actor actor) {
         	   igra.setScreen(new GameScreen(igra));
         	   stage.clear();
 				
 			}
         	
         });
        stage.addActor(tab);
	}

	@Override
	public void render(float delta) {
		stage.act();
		camera.update();
		Gdx.gl.glClearColor(124/255F, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        igra.font.draw(batch, "Igrata e napravena od Darko Danchev", 30, 30);
        batch.end();
        stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		stage.dispose();
		skin.dispose();
		atlas.dispose();

	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		atlas.dispose();

	}

}
