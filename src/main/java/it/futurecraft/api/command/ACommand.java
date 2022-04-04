package it.futurecraft.api.command;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ACommand extends ASubCommand {

    /**
     *
     * @param commandName - Name of the command (example = "heal")
     * @param aliases - List of the alias or null
     * @param permission - Create a permission for the command or null
     * @param subs - List of the sub commands
     */
    public ACommand(String commandName, List<String> aliases, String permission, ASubCommand... subs) {
        super(aliases,permission,subs);
        if (commandName==null || commandName.isEmpty())
            throw new NullPointerException();
        if (commandName.contains(" "))
            throw new IllegalArgumentException("Command Name '"+commandName+"' contains spaces");
        this.name = commandName.toLowerCase();
    }
    private final String name;

    /**
     * @return Name of the command
     */
    public String getName() {
        return name;
    }



    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        ArrayList<String> params = new ArrayList<>();
        if(!hasPermission(sender))
            return params;
        if (playersOnly() && !(sender instanceof Player))
            return params;
        params.addAll(Arrays.asList(args));

        return onTab(sender,label,args);
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!hasPermission(sender)) {
            sender.sendMessage(VersLang.ERROR_PERM.getMessage());
            return true;
        }
        if (playersOnly() && !(sender instanceof Player)) {
            sender.sendMessage(VersLang.ERROR_INSTANCE.getMessage());
            return true;
        }
        ArrayList<String> params = new ArrayList<>();
        Collections.addAll(params, args);

        if (playersOnly()) onCmd((Player)sender,label,args);
        else onCmd(sender,label,args);
        return true;
    }
}
