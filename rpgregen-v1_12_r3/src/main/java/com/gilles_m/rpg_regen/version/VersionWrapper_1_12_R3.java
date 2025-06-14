package com.gilles_m.rpg_regen.version;

import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class VersionWrapper_1_12_R3 implements VersionWrapper {

    private static final String NATURAL_REGENERATION_GAMERULE_KEY = "naturalRegeneration";

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

    @Override
    public boolean getNaturalGenerationGameRule(@NotNull World world) {
        return "true".equals(world.getGameRuleValue(NATURAL_REGENERATION_GAMERULE_KEY));
    }

}
