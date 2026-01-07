package Elio.model;

import Elio.gui.GUI;
import Elio.gui.LanternaGUI;
import Elio.state.*;
import java.io.IOException;

public class Game {
    final private LanternaGUI gui;
    private State state;
    private boolean running;

    public Game() throws IOException {
        this.gui = new LanternaGUI(100, 25);
        this.running = true;
        this.state = new IntroMenuState(new Menu(GUI.BLACK, GUI.WHITE, 100, 25));
    }

    public State getState() {
        return state;
    }

    public GUI getGui() { return gui; }

    public void setState(State state){
        this.state = state;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public static void main(String[] args) throws IOException {
        new Game().start();
    }


    private void start() throws IOException {
        int FPS = 40;
        int frameTime = 1000 / FPS;

        long startTime = System.currentTimeMillis();

        while (running) {

            //Calculating passed time
            long startTimeLoop = System.currentTimeMillis();
            long passedTime = (startTimeLoop - startTime);

            startTime = startTimeLoop;

            if(state.getClass() == GameState.class){
                ((GameState)state).update(passedTime);
                Arena model = ((GameState)state).getModel();

                if(model.getWaveManager().isFinished()){
                    this.setState(new GameWinState(new Menu(GUI.BLACK, GUI.WHITE, 100, 25)));
                }
                else if(model.isGameOver()){
                    this.setState(new GameOverState(new Menu(GUI.BLACK, "#FF0000", 100, 25)));
                }
                else {
                    int levels = model.isHeroLevelUp();
                    if (levels > 0)
                        this.setState(new LevelUpState(new Menu(GUI.BLACK, GUI.WHITE, 100, 25), model, levels));
                }
            }

            //process stuff
            state.step(this, gui, startTime);

            //then draw
            state.draw(gui);


            long elapsedTime = System.currentTimeMillis() - startTimeLoop;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0)
                    Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

        }
        gui.close();
    }
}