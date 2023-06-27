package de.uni_passau.fim.prog2.kalah.view;

/**
 * Enum to differentiate between different notifications.
 */
public enum NotificationType {

    /**
     * Notification for when the human must miss a turn.
     */
    HUMAN_MISS,

    /**
     * Notification for when the computer must miss a turn.
     */
    COMPUTER_MISS,

    /**
     * Notification for when the human won the game.
     */
    HUMAN_WIN,

    /**
     * Notification for when the game ended in a tie.
     */
    TIE,

    /**
     * Notification for when the machine won the game.
     */
    COMPUTER_WIN,

    /**
     * Notification for when the human tried to make an illegal move.
     */
    ILLEGAL_MOVE,
}
