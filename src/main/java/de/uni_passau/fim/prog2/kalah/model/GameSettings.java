package de.uni_passau.fim.prog2.kalah.model;

/**
 * Contains all relevant information for the settings of a game of kalah.
 * @param level indicates the difficulty setting
 * @param seedsPerPit indicates how many seeds a pit has initially when
 *                    starting a game
 * @param pitsPerPlayer indicates how many pits one player has during the
 *                      game not including the store
 */
public record GameSettings(int level, int seedsPerPit, int pitsPerPlayer) {
}
