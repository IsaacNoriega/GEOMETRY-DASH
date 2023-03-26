package com.Component;

import com.dataScructure.AssetPool;
import com.jade.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite extends Component {
    public BufferedImage image;
    public String pictureFile;
    public boolean isSpritesheet = false;
    public int tileWidth = 0;
    public int tileHeight = 0;
    public int width = 0, height = 0;
    public int spacing = 0;
    public int columns = 0;
    public int gid = 1;
    public int size;


    public Sprite(String pictureFile){
        this.pictureFile=pictureFile;
        try {
            File file=new File(pictureFile);
            if(AssetPool.hasSprite(pictureFile)){
                throw new IOException("Asset already exist: "+ pictureFile);
            }
            this.image= ImageIO.read(file);
            this.width=image.getWidth();
            this.height=image.getHeight();
        }catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }
    public Sprite(BufferedImage image){
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }
    @Override
    public void draw(Graphics2D g2){
        g2.drawImage(image,(int)gameObject.transform.position.x,(int)gameObject.transform.position.y,width,height,null);
    }

    @Override
    public Component copy() {
        return new Sprite(this.image);
    }
}
