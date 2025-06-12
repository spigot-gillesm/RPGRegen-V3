package com.gilles_m.rpg_regen.version;

import com.github.spigot_gillesm.format_lib.Formatter;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public enum WrapperProvider {

    V_1_21_4("VersionWrapper_1_21_R3", 1, 21, 4),

    V_1_17("VersionWrapper_1_17_R1", 1, 17);

    private static final String CLASS_PATH = "com.gilles_m.rpg_regen.version.";

    private final String wrapperClass;

    private final int[] bukkitVersion;

    WrapperProvider(String wrapperClass, final int... bukkitVersion) {
        this.wrapperClass = wrapperClass;
        this.bukkitVersion = bukkitVersion;
    }

    /**
     * Returns true if the given bukkit version is at least this instance's version. e.g., given [1, 19, 1],
     * V_1_21_4.isVersionAbove returns false but V_1_17.isVersionAbove returns true.
     *
     * @param version the bukkit server version to compare
     * @return a boolean
     */
    private boolean isVersionAbove(final int... version) {
        final int maxLength = Math.max(this.bukkitVersion.length, version.length);

        for(int i = 0; i < maxLength; i++) {
            //If the version array is too small, default to 0
            final int a = (i < this.bukkitVersion.length) ? this.bukkitVersion[i] : 0;
            final int b = (i < version.length) ? version[i] : 0;

            //As soon as the value from the same index is greater for b, then the given version is higher
            if(b > a) {
                return true;
            }
            //Otherwise, it is lower
            if(b < a) {
                return false;
            }
        }

        //Or equal
        return true;
    }

    /**
     * Finds the most recent version wrapper such that it is compatible.
     *
     * @param bukkitVersion the current bukkit server version
     * @return an implementation of VersionWrapper
     */
    public static VersionWrapper fetchWrapper(final int[] bukkitVersion) {
        for(final WrapperProvider provider : WrapperProvider.values()) {
            if(provider.isVersionAbove(bukkitVersion)) {
                final String path = CLASS_PATH + provider.wrapperClass;

                try {
                    return (VersionWrapper) Class.forName(path).getDeclaredConstructor().newInstance();
                } catch(ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException
                        | NoSuchMethodException exception) {

                    Formatter.error(String.format("Unable to instantiate version wrapper for version %s", Arrays.toString(bukkitVersion)));
                    Formatter.error(exception.getMessage());
                }
            }
        }
        Formatter.error(String.format("No version wrapper for version %s", Arrays.toString(bukkitVersion)));

        return null;
    }

}
