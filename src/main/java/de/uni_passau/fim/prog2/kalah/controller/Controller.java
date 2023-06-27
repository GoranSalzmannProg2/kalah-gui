package de.uni_passau.fim.prog2.kalah.controller;

import de.uni_passau.fim.prog2.kalah.model.Board;
import de.uni_passau.fim.prog2.kalah.model.GameSettings;
import de.uni_passau.fim.prog2.kalah.model.Model;
import de.uni_passau.fim.prog2.kalah.model.Player;
import de.uni_passau.fim.prog2.kalah.view.NotificationType;
import de.uni_passau.fim.prog2.kalah.view.PitLabel;
import de.uni_passau.fim.prog2.kalah.view.View;

import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.util.Stack;

/**
 * Controller class creating and assigning all listeners and managing threads.
 */
public class Controller {
    private final View view;
    private Model model;
    private final PitMouseListener pml;
    private final ButtonControls bc;
    private final LevelChangeListener lcl;
    private Thread workerThread;
    private final Stack<Board> gameHistory;
    private JButton undoButton;

    /**
     * Creates new controller with access to a view.
     *
     * @param model The corresponding model of the application.
     * @param view  The corresponding view of the application.
     */
    public Controller(Model model, View view) {
        this.view = view;
        this.model = model;
        pml = new PitMouseListener(this);
        bc = new ButtonControls(this);
        lcl = new LevelChangeListener(this);
        initListeners();
        gameHistory = new Stack<>();
    }

    /**
     * Creates all required listeners and assigns them.
     */
    public void initListeners() {
        for (JButton b : view.getAllControlButtons()) {
            b.addActionListener(bc);
            switch (b.getText()) {
                case "NEW" -> b.setMnemonic(KeyEvent.VK_N);
                case "SWITCH" -> b.setMnemonic(KeyEvent.VK_S);
                case "UNDO" -> {
                    undoButton = b;
                    b.setMnemonic(KeyEvent.VK_U);
                }
                case "QUIT" -> b.setMnemonic(KeyEvent.VK_Q);
                default -> {
                }
            }
        }
        view.getLevelControl().addActionListener(lcl);
        fullReload();
    }

    /**
     * Reassigns listeners to all pit labels.
     */
    public void fullReload() {
        view.createBoardPanel(model.getBoard());
        for (PitLabel pl : view.getAllPitLabels()) {
            pl.addMouseListener(pml);
        }
    }

    /**
     * Saves a handle to the thread that currently computes a machine move.
     *
     * @param workerThread The thread object calculating a machine move.
     */
    public void setWorkerThread(Thread workerThread) {
        this.workerThread = workerThread;
    }

    /**
     * Removes the handle to the thread that currently computes a machine move.
     */
    public void unsetWorkerThread() {
        this.workerThread = null;
    }

    /**
     * Stops executing the thread currently calculating a machine move.
     */
    public void stopWorkerThread() {
        if (workerThread != null) {
            workerThread.interrupt();
            unsetWorkerThread();
        }
    }

    /**
     * Saves a new position to the history of past board positions.
     *
     * @param item
     */
    public void pushHistory(Board item) {
        gameHistory.push(item);
        undoButton.setEnabled(true);
    }

    /**
     * Removes and returns the latest past board position.
     *
     * @return A board model of the past state.
     */
    public Board popHistory() {
        Board game = gameHistory.pop();
        if (gameHistory.empty()) {
            undoButton.setEnabled(false);
        }
        return game;
    }

    /**
     * Clears the history of past board positions.
     */
    public void clearHistory() {
        gameHistory.clear();
        undoButton.setEnabled(false);
    }

    /**
     * Gets the current model.
     *
     * @return The current model.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Sets the current model.
     *
     * @param model A new model.
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Update the application state.
     */
    public void reload() {
        view.updatePits(model.getBoard());
    }

    /**
     * Trigger a notification inside the view.
     *
     * @param type The type of event that happened.
     */
    public void showNotification(NotificationType type) {
        view.showNotification(type, model.getBoard());
    }

    /**
     * Returns a record of the currently selected settings for the game.
     *
     * @return All game settings
     */
    public GameSettings getSelectedSettings() {
        return view.getSelectedSettings();
    }

    /**
     * Execute a machine move inside new thread.
     */
    public void instantiateMachineMove() {
        MachineMoveThread machineMove
                = new MachineMoveThread(this);
        setWorkerThread(machineMove);
        machineMove.start();
    }

    /**
     * Checks if game is over and notifies user accordingly.
     *
     * @return true if game is over.
     */
    public boolean checkGameOver() {
        if (model.getBoard().isGameOver()) {
            switch (model.getBoard().getWinner()) {
                case HUMAN -> showNotification(
                        NotificationType.HUMAN_WIN);
                case COMPUTER -> showNotification(
                        NotificationType.COMPUTER_WIN);
                default -> showNotification(
                        NotificationType.TIE);
            }
            return true;
        } else {
            return false;
        }
    }

}
