package com.gilles_m.rpg_regen.version;

import org.bukkit.attribute.Attribute;
import org.bukkit.potion.PotionEffectType;

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

}
