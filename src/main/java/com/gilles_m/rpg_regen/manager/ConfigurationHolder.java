package com.gilles_m.rpg_regen.manager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gilles_m.rpg_regen.PluginDeserializer;
import com.gilles_m.rpg_regen.PluginSerializer;
import lombok.Getter;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigurationHolder {

    @Getter
    @JsonProperty("period")
    private double period = 2;

    @Getter
    @JsonProperty("amount")
    private double amount = 1;

    @Getter
    @JsonProperty("replace-minecraft-system")
    private boolean replaceMinecraftSystem = false;

    @Getter
    @JsonProperty("combat-duration")
    private double combatDuration = 8;

    @Getter
    @JsonProperty("use-food-level")
    private boolean useFoodLevel = true;

    @Getter
    @JsonProperty("bonus-level-interval")
    private int bonusLevelInterval = 0;

    @Getter
    @JsonProperty("bonus-per-level-interval")
    private double bonusPerLevelInterval = 0.5;

    @Getter
    @JsonProperty("excluded-damage-causes")
    @JsonDeserialize(contentUsing = PluginDeserializer.DamageCauseDeserializer.class)
    @JsonSerialize(contentUsing = PluginSerializer.DamageCauseSerializer.class)
    private Set<EntityDamageEvent.DamageCause> excludedDamageCauses = Set.of(
            EntityDamageEvent.DamageCause.FALL,
            EntityDamageEvent.DamageCause.FALLING_BLOCK
    );

    @Getter
    @JsonProperty("fighting-potions")
    @JsonDeserialize(contentUsing = PluginDeserializer.PotionEffectTypeDeserializer.class)
    @JsonSerialize(contentUsing = PluginSerializer.PotionEffectTypeSerializer.class)
    private Set<PotionEffectType> fightingPotions = new HashSet<>();

    @Getter
    @JsonProperty("splash-fighting-potions")
    @JsonDeserialize(contentUsing = PluginDeserializer.PotionEffectTypeDeserializer.class)
    @JsonSerialize(contentUsing = PluginSerializer.PotionEffectTypeSerializer.class)
    private Set<PotionEffectType> splashFightingPotions = Set.of(
            PotionEffectType.INSTANT_HEALTH,
            PotionEffectType.INSTANT_DAMAGE,
            PotionEffectType.POISON
    );

    @Getter
    @JsonProperty("lingering-fighting-potions")
    @JsonDeserialize(contentUsing = PluginDeserializer.PotionEffectTypeDeserializer.class)
    @JsonSerialize(contentUsing = PluginSerializer.PotionEffectTypeSerializer.class)
    private Set<PotionEffectType> lingeringFightingPotions = Set.of(
            PotionEffectType.INSTANT_HEALTH,
            PotionEffectType.INSTANT_DAMAGE,
            PotionEffectType.POISON
    );

    @Getter
    @JsonProperty("whitelisted-worlds")
    private Set<String> whitelistedWorlds = new HashSet<>();

    @Getter
    @JsonProperty("blacklisted-worlds")
    private Set<String> blacklistedWorlds = new HashSet<>();

}
