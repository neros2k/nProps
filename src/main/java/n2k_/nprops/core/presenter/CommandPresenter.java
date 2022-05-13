package n2k_.nprops.core.presenter;
import n2k_.nprops.base.APresenter;
import n2k_.nprops.base.IInteractor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public class CommandPresenter extends APresenter implements CommandExecutor {
    public CommandPresenter(IInteractor INTERACTOR) {
        super(INTERACTOR);
    }
    @Override
    public void init() {
        PluginCommand COMMAND = super.getInteractor().getPlugin().getCommand("props");
        assert COMMAND != null;
        COMMAND.setExecutor(this);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender SENDER, @NotNull Command COMMAND, @NotNull String STR, @NotNull String @NotNull [] ARGS) {
        if(ARGS.length == 0 || ARGS[0].equals("help")) {

        }
        if(ARGS[0].equals("reload")) {

        }
        if(ARGS[0].equals("clear")) {
            if(ARGS.length == 2) {
                Player PLAYER = this.getPlayerByName(ARGS[1]);
                super.getInteractor().getEngine(PLAYER).clear();
            }
        }
        if(ARGS[0].equals("find")) {
            if(ARGS.length == 2) {
                Player PLAYER = this.getPlayerByName(ARGS[1]);
                StringBuilder BUILDER = new StringBuilder();
                BUILDER.append("---\n");
                super.getInteractor().getEngine(PLAYER).getList().forEach(LOCATION ->
                    BUILDER.append("> [").append(LOCATION.getX()).append(", ")
                           .append(LOCATION.getY()).append(", ")
                           .append(LOCATION.getZ()).append("] : ")
                           .append(LOCATION.getBlock().getType()).append(" ;\n")
                );
                BUILDER.append("---");
                SENDER.sendMessage(BUILDER.toString());
            }
        }
        if(ARGS[0].equals("count")) {
            if(ARGS.length == 2) {
                Player PLAYER = this.getPlayerByName(ARGS[1]);
                SENDER.sendMessage(String.valueOf(super.getInteractor().getEngine(PLAYER).getList().size()));
            }
        }
        return true;
    }
    private Player getPlayerByName(String NAME) {
        return super.getInteractor().getPlugin().getServer().getPlayer(NAME);
    }
}
