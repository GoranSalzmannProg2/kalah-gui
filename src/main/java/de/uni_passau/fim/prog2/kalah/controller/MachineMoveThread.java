package de.uni_passau.fim.prog2.kalah.controller;

import de.uni_passau.fim.prog2.kalah.model.Model;
import de.uni_passau.fim.prog2.kalah.model.Player;
import de.uni_passau.fim.prog2.kalah.view.NotificationType;

/**
 * Separate thread tasked with calculating a move by the machine player and
 * updating all states accordingly.
 */
public class MachineMoveThread extends Thread {
    private Controller controller;

    /**
     * Creates new thread object with access to a controller.
     *
     * @param controller Corresponding controller of the application.
     */
    public MachineMoveThread(Controller controller) {
        this.controller = controller;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Model model = controller.getModel();
        if (!controller.checkGameOver()) {
            if (model.getBoard().next() == Player.COMPUTER) {
                makeMove();
            } else {
                controller.showNotification(NotificationType.COMPUTER_MISS);
            }
        }
        controller.unsetWorkerThread();
    }

    private void makeMove() {
        Model model = controller.getModel();
        try {
            Thread.sleep(500);
            model.setBoard(model.getBoard().machineMove());
        } catch (InterruptedException ignored) {
            return;
        }
        controller.reload();
        if (!controller.checkGameOver()) {
            if (model.getBoard().next() == Player.COMPUTER) {
                controller.showNotification(NotificationType.HUMAN_MISS);
                makeMove();
            }
        }
    }
}
