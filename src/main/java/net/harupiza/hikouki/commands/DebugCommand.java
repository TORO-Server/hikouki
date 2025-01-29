package net.harupiza.hikouki.commands;

import net.harupiza.hikouki.AircraftManager;
import net.harupiza.hikouki.aircraft.SeparatedPart;
import net.harupiza.hikouki.aircraft.parts.CustomModel;
import net.harupiza.hikouki.aircraft.parts.Seat;
import net.harupiza.hikouki.aircraft.plane.Crime320;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            AircraftManager.aircrafts.add(
                    new Crime320(new CustomModel(player.getLocation(), 21),
                    new SeparatedPart<>(0, 0, 0, new CustomModel(player.getLocation(), 1)),
                    new SeparatedPart<>(0, 0, 0, new Seat(player.getLocation())),
                    null
            ));
        }
        return true;
    }
}
