package jade;

import util.Time;

import javax.swing.JFrame;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;

public class Window extends JFrame implements Runnable {
    public ML mouseListener;
    public KL keyListener;
    private static Window window=null;
    private boolean isRunning=true;
    private Scene currentScene=null;
    public Window(){
        this.mouseListener=new ML();
        this.keyListener=new KL();
        this.setSize(1280,720);//Tmaño de la ventana
        this.setTitle("Geometry Dash");//Titulo
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
    }

    public void changeScene(int scene){
        switch (scene){
            case 0:
                currentScene=new LevelEditorScene("Level Editor ");
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
