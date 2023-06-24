// Copyright© by Fin

package Listeners;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWrites(SignChangeEvent e) {
        Color c = new Color();
        String line1 = c.getColorFormat(e.getLine(0));
        String line2 = c.getColorFormat(e.getLine(1));
        String line3 = c.getColorFormat(e.getLine(2));
        String line4 = c.getColorFormat(e.getLine(3));
        e.setLine(0, line1);
        e.setLine(1, line2);
        e.setLine(2, line3);
        e.setLine(3, line4);
        Sign s = (Sign) e.getBlock().getState();
        s.update(true);
    }
}