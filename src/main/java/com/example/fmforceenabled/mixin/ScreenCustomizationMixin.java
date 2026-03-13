package com.example.fmforceenabled.mixin;

import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Targets de.keksuccino.fancymenu.customization.ScreenCustomization.
 *
 * FancyMenu keeps a file (config/fancymenu/customizablemenus.txt) that lists which
 * screens have customization enabled.  If a screen is NOT in that list the method
 * isCustomizationEnabledForScreen returns false and FancyMenu skips that screen
 * entirely — no layouts are applied even when layouts exist for it.
 *
 * This Mixin intercepts the two-argument overload (which all code paths ultimately
 * call) and returns true for every non-null screen, effectively forcing FancyMenu to
 * treat ALL screens as customization-enabled.
 *
 * @Pseudo  — the target class is in FancyMenu, not in the vanilla/NeoForge classpath.
 *            @Pseudo prevents a MixinException when FancyMenu is absent.
 * require=0 — prevents a MixinException when the method signature changes between
 *            FancyMenu versions.
 */
@Pseudo
@Mixin(targets = "de.keksuccino.fancymenu.customization.ScreenCustomization", remap = false)
public class ScreenCustomizationMixin {

    /**
     * Method descriptor:
     *   (Lnet/minecraft/client/gui/screens/Screen;Z)Z
     * Matches:
     *   public static boolean isCustomizationEnabledForScreen(Screen screen, boolean ignoreAllowScreenCustomization)
     */
    @Inject(
        method = "isCustomizationEnabledForScreen(Lnet/minecraft/client/gui/screens/Screen;Z)Z",
        at = @At("HEAD"),
        cancellable = true,
        remap = false,
        require = 0
    )
    private static void forceEnableForAllScreens(
            Screen screen,
            boolean ignoreAllowScreenCustomization,
            CallbackInfoReturnable<Boolean> cir) {
        if (screen != null) {
            cir.setReturnValue(true);
        }
    }
}
