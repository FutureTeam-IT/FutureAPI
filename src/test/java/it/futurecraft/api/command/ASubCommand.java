package it.futurecraft.api.command;

import java.util.*;

public class ASubCommand {
    private final String permission;
    private final List<String> aliases = new ArrayList<>();
    protected final HashMap<String,ASubCommand> subsByAlias = new HashMap<>();
    protected final HashSet<ASubCommand> subs = new HashSet<>();

    /**
     *
     * @param name - Name of the SubCommand (example = "island go" -> "go" is the SubCommand)
     * @param permission - Required permission to execute this SubCommand or null
     * @param subs - List of the SubCommands
     */
    public ASubCommand(String name,String permission,ASubCommand... subs) {
        this(Collections.singletonList(name),permission,subs);
    }
    /**
     *
     * @param aliases - Aliases of this SubCommand (example = /is GO or /is TELEPORT)
     * @param permission - Required permission to execute this SubCommand or null
     * @param subs - List of the SubCommands
     */
    public ASubCommand(List<String> aliases,String permission,ASubCommand... subs) {
        if (aliases!=null)
            for (String alias : aliases) {
                if (alias == null || alias.isEmpty() )
                    try {
                        throw new NullPointerException("attempting to register null or empty alias");
                    } catch (Exception e) { e.printStackTrace(); }
                else if (alias.contains(" "))
                    try {
                        throw new NullPointerException("attempting to register alias with a space");
                    } catch (Exception e) { e.printStackTrace(); }
                else if (!this.aliases.contains(alias.toLowerCase()))
                    this.aliases.add(alias.toLowerCase());
                else
                    try {
                        throw new IllegalArgumentException("attempting to register alias "+alias.toLowerCase()+" twice");
                    } catch (Exception e) { e.printStackTrace(); }
            }
        this.permission = permission;
        if (subs != null && subs.length != 0 ) {
            for (ASubCommand sub : subs) {
                if (sub !=null ) {
                    this.subs.add(sub);
                    for (String alias : sub.getAliases())
                        if (!this.subsByAlias.containsKey(alias))
                            this.subsByAlias.put(alias,sub);
                        else
                            try {
                                throw new IllegalArgumentException("attempting to register subcommand alias "+alias.toLowerCase()+" twice");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                }
            }
        }
    }
    /**
     * Implements for SubCommands
     *
     * @param sender - Command Executor
     * @param label - Alias of the used command
     * @param args - Parameters list (example = /bal pay maxigame 45 , "pay","maxigame",45")
     */
    public void onCmd(CommandSender sender,String label,String[] args) {
        if (subs.isEmpty()) {
            sender.sendMessage(VersLang.ERROR_ARG.getMessage());
            return;
        }
        if (args==null || args.length==0 || !subsByAlias.containsKey(args[0].toLowerCase())) {
            onHelp(sender,label,args);
            return;
        }
        ASubCommand sub = subsByAlias.get(args[0].toLowerCase());

        if (sub.playersOnly() && !(sender instanceof Player)) {
            sender.sendMessage(VersLang.ERROR_INSTANCE.getMessage());
            return;
        }
        if (sub.hasPermission(sender)) {
            args = shift(args);
            sub.onCmd(sender,label, args);
            return;
        }
        sender.sendMessage(VersLang.ERROR_PERM.getMessage()); //, sub.getPermission());
    }

    /**
     * Implements for SubCommands
     *
     * @param player - Player Executor
     * @param label - Alias of the used command
     * @param args - Parameters list (example = /bal pay maxigame 45 , "pay","maxigame",45")
     */
    public void onCmd(Player player, String label, String[] args) {
        if (subs.isEmpty()) {
            player.sendMessage(VersLang.ERROR_ARG.getMessage());
            return;
        }
        if (args==null || !subsByAlias.containsKey(args[0].toLowerCase())) {
            onHelp(player,label,args);
            return;
        }
        ASubCommand sub = subsByAlias.get(args[0].toLowerCase());
        if (sub.hasPermission(player)) {
            args = shift(args);
            sub.onCmd(player,label, args);
            return;
        }
        player.sendMessage(VersLang.ERROR_PERM.getMessage()); //, sub.getPermission());
    }
    /**
     *
     * Implements a autocommand compiler whit tab
     *
     * @param sender - Command executor
     * @param label - Alias of the used command
     * @param args - List of the parameters (example = /is set time , "set","time")
     */
    public ArrayList<String> onTab(CommandSender sender, String label, String[] args) {
        if (args==null || subs.isEmpty())
            return new ArrayList<>();
        if (args.length==1) {
            ArrayList<String> list = new ArrayList<>();
            for (ASubCommand sub : subs) {
                if (sub.playersOnly() && !(sender instanceof Player))
                    continue;
                if (sub.hasPermission(sender) && sub.getFirstAlias().startsWith(args[0].toLowerCase()))
                    list.add(sub.getFirstAlias());
            }
            return list;
        }

        if (!subsByAlias.containsKey(args[0].toLowerCase()))
            return new ArrayList<>();

        ASubCommand sub = subsByAlias.get(args[0].toLowerCase());
        if (!sub.hasPermission(sender))
            return new ArrayList<>();
        args = shift(args);
        return sub.onTab(sender, label, args);
    }

    private boolean playerOnly = false;
    /**
     * Set the SubCommand only executable by players
     *
     * @param value True to set player only
     * @return the object for chaining
     */
    public ASubCommand setPlayersOnly(boolean value) {
        playerOnly = value;
        return this;
    }
    public boolean playersOnly() {
        return playerOnly;
    }
    /**
     * Check if the sender have the permission
     *
     * @param s Sender to check
     * @return true if s has permission for this or if permission is null
     */
    public boolean hasPermission(CommandSender s) {
        return permission == null || s.hasPermission(permission);
    }
    private String params;
    public List<String> getAliases(){
        return Collections.unmodifiableList(aliases);
    }
    public String getFirstAlias() {
        return aliases.get(0);
    }
    public String getPermission() {
        return permission;
    }
    public String getParams() {
        return params;
    }

    /**
     * For Help Utility
     *
     * @param params - Help Parameter (example = "[text to add]")
     * @return this
     */
    protected ASubCommand setParams(String params) {
        this.params = params;
        return this;
    }
    private String description;
    public String getDescription() {
        return description;
    }
    /**
     * for help utility
     * @param list - (ex: for /itemedit lore add , list = Arrays.asList("&6Aggiunge il testo nell'ultima linea","&cNota:se il testo non è presente aggiunge una linea vuota"))
     */
    protected void setDescription(List<String> list) {
        if (list==null || list.isEmpty()) {
            setDescription((String) null);
            return;
        }
        StringBuilder text = new StringBuilder();
        for (String line : list)
            text.append("\n").append(line);
        setDescription(text.toString().replaceFirst("\n" , ""));
    }
    protected void setDescription(String s) {
        this.description = s;
    }
    private boolean showLockedSuggestions() {
        return showLockedSuggestions;
    }
    /**
     * Show the SubCommands of the command
     * (example = /is set -> he show me "border, time, weather")
     *
     * @param value True if the sender don't see it
     */
    protected void setShowLockedSuggestions(boolean value) {
        showLockedSuggestions = value;
    }

    private boolean showLockedSuggestions = true;
    /**
     * Help message method
     */
    public void onHelp(CommandSender sender,String label,String[] args) {
        //String previousParam = getPreviousParam(label,args);
        TextComponent comp = new TextComponent(
                ""+ChatColor.BLUE+ChatColor.BOLD+ChatColor.STRIKETHROUGH+"-----"
                        +ChatColor.GRAY+ChatColor.BOLD+ChatColor.STRIKETHROUGH+"[--"
                        +ChatColor.BLUE+"   Help   "
                        +ChatColor.GRAY+ChatColor.BOLD+ChatColor.STRIKETHROUGH+"--]"
                        +ChatColor.BLUE+ChatColor.BOLD+ChatColor.STRIKETHROUGH+"-----");
        if (!subs.isEmpty()) {
            comp.addExtra("\n"+ChatColor.BLUE+" - /"+label+ " [...]");
            if (getDescription()!=null)
                comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new Text(fixString(getDescription(),null,true))
                ));
            comp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/"+label));

            boolean deepPermission = false;
            for (ASubCommand sub : subs) {
                if (sub.hasPermission(sender)) {
                    deepPermission = true;
                    if (sub.getParams()==null)
                        comp.addExtra("\n"+ChatColor.DARK_AQUA+sub.getFirstAlias());
                    else
                        comp.addExtra("\n"+ChatColor.DARK_AQUA+sub.getFirstAlias()+" "+ChatColor.AQUA+sub.getParams());
                    if (sub.getDescription()!=null)
                        comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new Text(fixString(getDescription(),null,true))
                        ));
                    comp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/"+label+" "+sub.getFirstAlias()));
                }
                else if (showLockedSuggestions()) {
                    if (sub.getParams()==null)
                        comp.addExtra("\n"+ChatColor.RED+sub.getFirstAlias());
                    else
                        comp.addExtra("\n"+ChatColor.RED+sub.getFirstAlias()+" "+ChatColor.GOLD+sub.getParams());
                    if (getDescription()!=null)
                        comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                new Text(fixString(getDescription(),null,true))
                        ));
                }
            }
            if (!deepPermission && !showLockedSuggestions()) {
                //for (ASubCommand sub : subs)
                sender.sendMessage(VersLang.ERROR_PERM.getMessage()); //, sub.getPermission());
                return;
            }
        }
        else {
            if (getParams()!=null)
                comp.addExtra("\n"+ChatColor.RED+" - /"+label+" "+ChatColor.GOLD+getParams());
            else
                comp.addExtra("\n"+ChatColor.RED+" - /"+label);
            if (getDescription()!=null)
                comp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new Text(fixString(getDescription(),null,true))
                ));
            comp.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,"/"+label));

        }
        comp.addExtra("\n");
        comp.addExtra(""+ChatColor.AQUA+ChatColor.BOLD+ChatColor.STRIKETHROUGH+"-----"
                +ChatColor.GRAY+ChatColor.BOLD+ChatColor.STRIKETHROUGH+"[--"
                +ChatColor.YELLOW+"   Help   "
                +ChatColor.GRAY+ChatColor.BOLD+ChatColor.STRIKETHROUGH+"--]"
                +ChatColor.AQUA+ChatColor.BOLD+ChatColor.STRIKETHROUGH+"-----");
        if (sender instanceof Player)
            sender.sendMessage(comp.toPlainText());
        else
            for (BaseComponent b: comp.getExtra())
                sender.sendMessage(b.toPlainText());

    }
//    private String getPreviousParam(String label, String[] args) {
//        int max = args.length-params.size();
//        StringBuilder text = new StringBuilder(label);
//        for (int i=0;i<max;i++) {
//            text.append(" ").append(args[i]);
//        }
//        return text.toString();
//    }


    private static String fixString(String text, Player p, boolean color, String... stuffs) {
        if (text == null)
            return null;

        // holders
        if (stuffs != null && stuffs.length % 2 != 0)
            throw new IllegalArgumentException("holder without replacer");
        if (stuffs != null && stuffs.length > 0)
            for (int i = 0; i < stuffs.length; i += 2)
                text = text.replace(stuffs[i], stuffs[i + 1]);

        // color
        if (color)
            text = ChatColor.translateAlternateColorCodes('&', text);

        return text;
    }

    private String[] shift(final String[] args) {
        return Arrays.copyOfRange(args, 1, args.length);
    }
}
