package com.gilles_m.rpg_regen.version;

import org.bukkit.attribute.Attribute;
import org.bukkit.potion.PotionEffectType;

public class VersionWrapper_1_17_R1 implements VersionWrapper {

    @Override
    public Attribute getAttributeMaxHealth() {
        return Attribute.GENERIC_MAX_HEALTH;
    }

    @Override
    public PotionEffectType getPotionEffectInstantHealth() {
        return PotionEffectType.HEAL;
    }

    @Override
    public PotionEffectType getPotionEffectInstantDamage() {
        return PotionEffectType.HARM;
    }

}
