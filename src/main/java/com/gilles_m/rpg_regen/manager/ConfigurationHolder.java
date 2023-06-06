package com.gilles_m.rpg_regen.manager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigurationHolder {

    @Getter
    @JsonProperty("period")
    private float period = 2;

    @Getter
    @JsonProperty("amount")
    private float amount = 1;

    @Getter
    @JsonProperty("combat-duration")
    private float combatDuration = 5;

    @Getter
    @JsonProperty("use-food-level")
    private boolean useFoodLevel = true;

}
