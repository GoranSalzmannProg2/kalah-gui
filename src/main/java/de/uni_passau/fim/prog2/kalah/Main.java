package de.uni_passau.fim.prog2.kalah;

import de.uni_passau.fim.prog2.kalah.controller.Controller;
import de.uni_passau.fim.prog2.kalah.model.Model;
import de.uni_passau.fim.prog2.kalah.view.View;

/**
 * Contains the entrypoint to the program.
 */
public final class Main {
    private Main() throws InstantiationException {
        throw new InstantiationException("Main should not be constructed.");
    }

    /**
     * Entry point to the program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
    }
}
