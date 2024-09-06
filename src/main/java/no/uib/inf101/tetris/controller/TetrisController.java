package no.uib.inf101.tetris.controller;


import no.uib.inf101.tetris.view.TetrisView;
import no.uib.inf101.tetris.midi.TetrisSong;
import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.Tetromino.Tetromino;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class TetrisController implements java.awt.event.KeyListener{
    private ControllableTetrisModel controllableTetrisModel;
    private TetrisView tetrisView;
    private Timer timer;
    private TetrisSong tetrisSong = new TetrisSong();
    
   

    public TetrisController(ControllableTetrisModel controllableTetrisModel, TetrisView tetrisView){
        this.controllableTetrisModel = controllableTetrisModel;
        this.tetrisView = tetrisView;
        tetrisView.addKeyListener(this);
        this.timer = new Timer(controllableTetrisModel.getSeconds(),this::clockTick);
        timer.start();
        tetrisSong.run();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //whats controllable when the game is on the welcome screen
        if(controllableTetrisModel.getGameState() == GameState.WELCOME_SCREEN){
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
               TetrisModel.GameState = GameState.ACTIVE_GAME;
            }
        }
        //Whats controllable when the game is paused
        if(controllableTetrisModel.getGameState() == GameState.GAME_PAUSE){
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                TetrisModel.GameState = GameState.ACTIVE_GAME;
             }
        }
        //whats controllable when the game is active
        if(controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME){
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                //Left arraow pressed
                controllableTetrisModel.moveTetromino(0, -1);
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                //right arrow pressed
                controllableTetrisModel.moveTetromino(0, 1);
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                // Down arrow was pressed
                controllableTetrisModel.moveTetromino(1, 0);
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                // Up arrow was pressed
                controllableTetrisModel.rotatedTetromino();
            }
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // Spacebar was pressed
                controllableTetrisModel.dropTetromino();
            }
            else if (e.getKeyCode() == KeyEvent.VK_P){
                TetrisModel.GameState = GameState.GAME_PAUSE;
            }
            tetrisView.repaint();
        }
    }
    /**
     * This method is used as an listener. When the tick goes of it calls repaint on the tetrisboard
     * @param event
     */
    public void clockTick(ActionEvent event){
        if(controllableTetrisModel.getGameState() == GameState.ACTIVE_GAME){
            controllableTetrisModel.clockTick();
            controllableTetrisModel.getSeconds();
            tetrisView.repaint();
        }
    }
    /**
     * sets the delay/seconds between each tick
     */
    private void setClock(){
        timer.setDelay(controllableTetrisModel.getSeconds());
        timer.setInitialDelay(controllableTetrisModel.getSeconds());

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    
}
