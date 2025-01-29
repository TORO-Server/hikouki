package net.harupiza.hikouki.aircraft.parts;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class CustomModel extends PartBasic {
    @Nonnull
    public ItemDisplay itemDisplay;

    public CustomModel(@Nonnull Location location, @Nullable Integer customModelData) {
        itemDisplay = location.getWorld().spawn(location, ItemDisplay.class);

        if (Objects.nonNull(customModelData)) {
            ItemStack item = new ItemStack(Material.DISC_FRAGMENT_5, 1);
            var itemMeta = item.getItemMeta();
            itemMeta.setCustomModelData(customModelData);
            item.setItemMeta(itemMeta);
            itemDisplay.setItemStack(item);
        }

        itemDisplay.setTransformation(new Transformation(
                new Vector3f((float) location.x(), (float) location.y(), (float) location.z()),
                new Quaternionf(0, 0, 0, 1),
                new Vector3f((float) scaleX, (float) scaleY, (float) scaleZ),
                new Quaternionf(0, 0, 0, 1)
        ));
    }
}
