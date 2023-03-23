package com.jade;

import com.Component.BoxBounds;
import com.Component.Player;
import com.Component.Spritesheet;
import com.dataScructure.AssetPool;
import com.dataScructure.Transform;
import com.util.Constants;
import com.util.Vector2;

import java.awt.*;

public class LevelEditorScene extends Scene {
    GameObject player;
    public LevelEditorScene(String name){
         super.Scene(name);
    }


    @Override
    public void init() {

    player= new GameObject("Some game object",new Transform(new Vector2(500,350.0f)));
    Spritesheet layerOne=new Spritesheet("assets/player/layerOne.png",42,42,2,13,13*5);
    Spritesheet layerTwo=new Spritesheet("assets/player/layerTwo.png",42,42,2,13,13*5);
    Spritesheet layerThree=new Spritesheet("assets/player/layerThree.png",42,42,2,13,13*5);
    Player playerComp=new Player(
            layerOne.sprites.get(0),
            layerTwo.sprites.get(0),
            layerThree.sprites.get(0),
            Color.RED,
            Color.GREEN);
    player.addComponent(playerComp);

    renderer.submit(player);
    }

    @Override
    public void update(double dt) {
        player.update(dt);
        player.transform.rotation += dt * 0.1f;
        camera.position.x += dt * 0.1f;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(new Color(1.0f,1.0f,1.0f));
        g2.fillRect(0,0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

       renderer.render(g2);
    }
}
