package com.gilles_m.rpg_regen.version;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

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

    @Override
    public boolean getNaturalGenerationGameRule(@NotNull World world) {
        return Boolean.TRUE.equals(world.getGameRuleValue(GameRule.NATURAL_REGENERATION));
    }

}
