package n2k_.nprops.base;
import org.bukkit.entity.Player;
public interface IInteractor extends IInitializable {
    void loadEngine(Player PLAYER);
    void unloadEngine(Player PLAYER);
    void clear(Player PLAYER);
    IEngine getEngine(Player PLAYER);
}
