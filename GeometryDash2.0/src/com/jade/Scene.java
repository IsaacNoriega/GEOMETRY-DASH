package com.jade;

import com.util.Vector2;

import java.awt.Graphics2D;
import java.util.ArrayList;
import  java.util.List;

public abstract class Scene {
    String name;
    public Camera camera;
    List<GameObject> gameObject;
    Renderer renderer;

    public void Scene(String name){
        this.name=name;
        this.camera=new Camera(new Vector2());
        this.gameObject=new ArrayList<>();
        this.renderer = new Renderer(this.camera);
    }

    public void init(){

    }

    public void addGameObject(GameObject g){
        gameObject.add(g);
        renderer.submit(g);
    }


    public abstract void update(double dt);
    public abstract void draw(Graphics2D g2);//Dibujara en la pantalla
}
