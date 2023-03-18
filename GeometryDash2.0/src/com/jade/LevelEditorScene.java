package com.jade;

import com.Component.BoxBounds;
import com.Component.Spritesheet;
import com.dataScructure.AssetPool;
import com.dataScructure.Transform;
import com.util.Constants;
import com.util.Vector2;

import java.awt.*;

public class LevelEditorScene extends Scene {
    GameObject testObj;
    public LevelEditorScene(String name){
         super.Scene(name);
    }


    @Override
    public void init() {

    testObj= new GameObject("Some game object",new Transform(new Vector2(500,350.0f)));
    Spritesheet spritesheet=new Spritesheet("assets/player/layerOne.png",42,42,2,13,13*5);
    testObj.addComponent(spritesheet.sprites.get(20));
    }

    @Override
    public void update(double dt) {
        testObj.update(dt);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.fillRect(0,0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

        testObj.draw(g2);
    }
}
