package net.harupiza.hikouki.aircraft.parts;

import org.bukkit.Location;
import org.bukkit.entity.Interaction;

import javax.annotation.Nonnull;

public class Seat extends CustomModel {
    @Nonnull
    public Interaction interaction;

    public Seat(@Nonnull Location location) {
        super(location, null);
        itemDisplay.setTeleportDuration(3);
        interaction = location.getWorld().spawn(location, Interaction.class);
        interaction.setInteractionWidth(1);
        interaction.setInteractionHeight(1);
        itemDisplay.addPassenger(interaction);
    }
}
