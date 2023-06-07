package com.gilles_m.rpg_regen;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.spigot_gillesm.format_lib.Formatter;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;

public class PluginDeserializer {

    protected PluginDeserializer() { }

    public static class DamageCauseDeserializer extends StdDeserializer<EntityDamageEvent.DamageCause> {

        public DamageCauseDeserializer(final Class<?> vc) {
            super(vc);
        }

        public DamageCauseDeserializer() {
            this(null);
        }

        @Override
        public EntityDamageEvent.DamageCause deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
            final var data = parser.getText();

            try {
                return EntityDamageEvent.DamageCause.valueOf(data.toUpperCase());
            } catch(final IllegalArgumentException exception) {
                Formatter.error(String.format("Invalid damage cause: %s", data));
                return null;
            }
        }

    }

    public static class PotionEffectTypeDeserializer extends StdDeserializer<PotionEffectType> {

        public PotionEffectTypeDeserializer(final Class<?> vc) {
            super(vc);
        }

        public PotionEffectTypeDeserializer() {
            this(null);
        }

        @Override
        public PotionEffectType deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
            final var data = parser.getText();

            try {
                return PotionEffectType.getByName(data.toUpperCase());
            } catch(final IllegalArgumentException exception) {
                Formatter.error(String.format("Invalid potion effect type: %s", data));
                return null;
            }
        }

    }

}
