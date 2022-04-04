package it.futurecraft.api.command;

/**
 * This record can be used as pojo for saving
 * commands information on database.
 *
 * @since v0.3.1
 */
public record Command(String command, String[] alias, String description, Command... subcommand) {
}
