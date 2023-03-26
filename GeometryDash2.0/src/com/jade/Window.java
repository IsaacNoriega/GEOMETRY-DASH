package com.jade;

import com.util.Constants;
import com.util.Time;

import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    public ML mouseListener;
    public KL keyListener;
    public boolean isInEditor=true;
    private static Window window=null;
    private boolean isRunning=true;
    private Scene currentScene=null;
    private Image doubleBufferImage = null;
    public Graphics doubleBufferGraphics = null;
    public Window(){
        this.mouseListener=new ML();
        this.keyListener=new KL();
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);//Tmaño de la ventana
        this.setTitle(Constants.SCREEN_TITLE);//Titulo
        this.setResizable(false);//No escalable
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);//Regista los clicks y el drag
        this.addMouseMotionListener(mouseListener);//Registra el mov
        this.setLocationRelativeTo(null);//Centra la ventana

    }
    public void init(){
    changeScene(0);
    }

    public static Window getWindow(){
        if(Window.window==null){
            Window.window=new Window();
        }
        return Window.window;
    }

    public void update(double dt){
        currentScene.update(dt);
        draw(getGraphics());
    }
    public void draw(Graphics g) {
        if(doubleBufferImage == null){
            doubleBufferImage=createImage(getWidth(),getHeight());
            doubleBufferGraphics=doubleBufferImage.getGraphics();
        }
        renderOffscreen(doubleBufferGraphics);
        g.drawImage(doubleBufferImage,0,0,getWidth(),getHeight(),null);
    }
    public void renderOffscreen(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        currentScene.draw(g2);
        /*g.setColor(bgColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        float r = Math.max(Math.min(bgColor.getRed() + redStepAmount, 255.0f), 0.0f);
        float gr = Math.max(Math.min(bgColor.getGreen() + greenStepAmount, 255.0f), 0.0f);
        float b = Math.max(Math.min(bgColor.getBlue() + blueStepAmount, 255.0f), 0.0f);

        bgColor = new Color(r / 255.0f, gr / 255.0f, b / 255.0f);
        //steps -= 1.0f;
        if (steps <= 0) {
            steps = ogSteps;
            currentColor = (currentColor + 1) % colors.length;
            nextColor = (nextColor + 1) % colors.length;
            bgColor = colors[currentColor];

            int oldRed = colors[currentColor].getRed();
            int newRed = colors[nextColor].getRed();
            int oldBlue = colors[currentColor].getBlue();
            int newBlue = colors[nextColor].getBlue();
            int oldGreen = colors[currentColor].getGreen();
            int newGreen = colors[nextColor].getGreen();
            blueStepAmount = (newBlue - oldBlue) / steps;
            redStepAmount = (newRed - oldRed) / steps;
            greenStepAmount = (newGreen - oldGreen) / steps;
        }

        Window.scene.draw(g2);*/
    }

    public Scene getCurrentScene(){
        return currentScene;
    }

    public void changeScene(int scene){
        switch (scene){
            case 0:
                isInEditor=true;
                currentScene= new LevelEditorScene("Lever Editor");
                currentScene.init();
                break;
            case 1:
                isInEditor=false;
                currentScene= new LevelScene("Level");
                currentScene.init();
                break;
            default:
                System.out.println("Do not know what this scene is ");
                currentScene=null;
                break;
        }

    }

    @Override
    public void run(){
    double lastTimeFrame=0.0;
        try {
            while (isRunning) {
                double time = Time.getTime();
                double deltaTime = time - lastTimeFrame; //Cuanto tiempo a pasado desde que inicio el juego
                update(deltaTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
