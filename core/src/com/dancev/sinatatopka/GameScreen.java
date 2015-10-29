package com.dancev.sinatatopka;

import java.util.Iterator;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
    final SinataTopka igra;

    Texture sinaTopkaSlika;
    Texture kosnicaSlika;
   // Sound dropSound;
   // Music rainMusic;
    OrthographicCamera camera;
    Rectangle kosnica;
    Array<Rectangle> siniTopki;
    long posledniPustenaTopka;
    int sobereniSiniTopki;
    public int zivot = 3;

    public GameScreen(final SinataTopka gam) {
        this.igra = gam;

       
        sinaTopkaSlika = new Texture(Gdx.files.internal("sinatatopka.png"));
        kosnicaSlika = new Texture(Gdx.files.internal("kosnica.png"));

       
    //    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
     //   rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
     //   rainMusic.setLooping(true);

      
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        kosnica = new Rectangle();
        kosnica.x = 800 / 2 - 64 / 2; 
        kosnica.y = 20; 
                      
        kosnica.width = 128;
        kosnica.height = 40;

       
        siniTopki = new Array<Rectangle>();
        pustiSinaTopka();

    }

    private void pustiSinaTopka() {
        Rectangle sinaTopka = new Rectangle();
        sinaTopka.x = MathUtils.random(0, 800 - 64);
        sinaTopka.y = 480;
        sinaTopka.width = 64;
        sinaTopka.height = 64;
        siniTopki.add(sinaTopka);
        posledniPustenaTopka = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        if(zivot == 0){
        	
        	igra.setScreen(new MainMenuScreen(igra));
        	
        }
        Gdx.gl.glClearColor(230/255f, 36/255f, 36/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         
       
        camera.update();

  
        igra.batch.setProjectionMatrix(camera.combined);

    
        igra.batch.begin();
        igra.font.draw(igra.batch, "Spaseni Sini Topki: " + sobereniSiniTopki, 7, 475);
        igra.font.draw(igra.batch, "Ostanati Zivoti: " + zivot, 580, 475);
        igra.batch.draw(kosnicaSlika, kosnica.x, kosnica.y);
        for (Rectangle sinaTopka : siniTopki) {
            igra.batch.draw(sinaTopkaSlika, sinaTopka.x, sinaTopka.y);
        }
        igra.batch.end();

     
        if (Gdx.input.isTouched()) {
            Vector3 pozicija = new Vector3();
            pozicija.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(pozicija);
            kosnica.x = pozicija.x - 64 / 2;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            kosnica.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            kosnica.x += 200 * Gdx.graphics.getDeltaTime();


        if (kosnica.x < 0)
            kosnica.x = 0;
        if (kosnica.x > 800 - 128)
            kosnica.x = 800 - 128;
        


        if (TimeUtils.nanoTime() - posledniPustenaTopka > 1000000000)
            pustiSinaTopka();


        Iterator<Rectangle> iter = siniTopki.iterator();
        while (iter.hasNext()) {
            Rectangle sinaTopka = iter.next();
            sinaTopka.y -= 200 * Gdx.graphics.getDeltaTime();
            if (sinaTopka.y + 64 < 0){
                iter.remove();
                zivot--;
            }
            if (sinaTopka.overlaps(kosnica)) {
                sobereniSiniTopki++;
              //  dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

      
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        sinaTopkaSlika.dispose();
        kosnicaSlika.dispose();

    }

}