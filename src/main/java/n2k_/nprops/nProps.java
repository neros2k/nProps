package n2k_.nprops;
import n2k_.nprops.base.IInteractor;
import n2k_.nprops.core.Interactor;
import org.bukkit.plugin.java.JavaPlugin;
public final class nProps extends JavaPlugin {
    private final IInteractor INTERACTOR;
    public nProps() {
        this.INTERACTOR = new Interactor(this);
    }
    @Override
    public void onEnable() {
        this.INTERACTOR.init();
    }
    @Override
    public void onDisable() {
        this.INTERACTOR.getMap().forEach((NAME, ENGINE) -> ENGINE.clear());
    }
}
