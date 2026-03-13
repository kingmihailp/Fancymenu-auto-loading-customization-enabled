package com.example.fmforceenabled;

import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Client addon for FancyMenu 3.8.1 (NeoForge 1.21.1).
 *
 * Forces "enabled = true" (customization) for every screen by applying two Mixins:
 *   1. ScreenCustomizationMixin — bypasses the per-screen whitelist check so that
 *      ScreenCustomization.isCustomizationEnabledForScreen() always returns true.
 *   2. LayoutMixin — makes Layout.isEnabled() always return true so that every
 *      saved layout is treated as enabled regardless of its stored state.
 *
 * Both Mixins are annotated @Pseudo, so the mod does not crash when FancyMenu is absent
 * (though the addon serves no purpose without it).
 */
@Mod(value = FMForceEnabledMod.MOD_ID, dist = net.neoforged.api.distmarker.Dist.CLIENT)
public class FMForceEnabledMod {

    public static final String MOD_ID = "fmforceenabled";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public FMForceEnabledMod() {
        LOGGER.info("[FMForceEnabled] Initialized — FancyMenu customization will be force-enabled for all screens.");
    }
}
