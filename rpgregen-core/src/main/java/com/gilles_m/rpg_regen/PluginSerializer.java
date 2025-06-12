package com.gilles_m.rpg_regen;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;

public class PluginSerializer {

    protected PluginSerializer() { }

    public static class DamageCauseSerializer extends JsonSerializer<EntityDamageEvent.DamageCause> {

        @Override
        public void serialize(final EntityDamageEvent.DamageCause damageCause, JsonGenerator jsonGenerator,
                              SerializerProvider serializer) throws IOException {

            jsonGenerator.writeString(damageCause.name());
        }

    }

    public static class PotionEffectTypeSerializer extends JsonSerializer<PotionEffectType> {

        @Override
        public void serialize(PotionEffectType potionEffect, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
            jsonGenerator.writeString(potionEffect.getName());
        }

    }

}
