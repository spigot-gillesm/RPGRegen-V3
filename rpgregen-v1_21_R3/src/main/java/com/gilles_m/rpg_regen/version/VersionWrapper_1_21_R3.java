package com.gilles_m.rpg_regen.version;

import org.bukkit.attribute.Attribute;
import org.bukkit.potion.PotionEffectType;

public class VersionWrapper_1_21_R3 implements VersionWrapper {

    @Override
    public Attribute getAttributeMaxHealth() {
        return Attribute.MAX_HEALTH;
    }

    @Override
    public PotionEffectType getPotionEffectInstantHealth() {
        return PotionEffectType.INSTANT_HEALTH;
    }

    @Override
    public PotionEffectType getPotionEffectInstantDamage() {
        return PotionEffectType.INSTANT_DAMAGE;
    }

}
