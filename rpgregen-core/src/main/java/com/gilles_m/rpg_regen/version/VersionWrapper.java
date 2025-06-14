package com.gilles_m.rpg_regen.version;

import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

/**
 * An interface abstracting version specific API calls.
 */
public interface VersionWrapper {

    /**
     * Get the max health attribute. This attribute is used for modifying an entity's max health.
     *
     * @return an instance of Attribute
     */
    Attribute getAttributeMaxHealth();

    /**
     * Get the instant healing potion effect.
     *
     * @return an instance of PotionEffectType
     */
    PotionEffectType getPotionEffectInstantHealth();

    /**
     * Get the instant damage potion effect.
     *
     * @return an instance of PotionEffectType
     */
    PotionEffectType getPotionEffectInstantDamage();

    /**
     * get the naturalRegeneration game rule from the given world.
     *
     * @param world the world from which to get the game rule's value
     * @return true if the game rule is set to true, false otherwise
     */
    boolean getNaturalGenerationGameRule(@NotNull World world);

}
