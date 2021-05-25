package DevJam.Tasks;

import DevJam.Events.UpdateEvent;
import org.bukkit.Bukkit;

public class Update implements Runnable {
    private UpdateEvent updateEvent;

    public Update() {
        updateEvent = new UpdateEvent();
    }

    public void run() { // Called once every 20 ticks by default (changeable in config)
        Bukkit.getPluginManager().callEvent(updateEvent);
    }
}
