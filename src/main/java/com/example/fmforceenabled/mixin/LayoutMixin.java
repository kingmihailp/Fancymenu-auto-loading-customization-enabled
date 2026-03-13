package com.example.fmforceenabled.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Targets de.keksuccino.fancymenu.customization.layout.Layout.
 *
 * Each Layout object stores a boolean field "enabled" (serialised as "is_enabled" in
 * the layout file).  When a user right-clicks a layout in FancyMenu's UI and disables
 * it, that flag is written as false.  FancyMenu then skips that layout when rendering
 * the screen.
 *
 * This Mixin intercepts Layout.isEnabled() and always returns true so that every
 * layout — regardless of its saved state — is treated as enabled.
 *
 * @Pseudo  — target class lives in FancyMenu; prevents crash when FancyMenu is absent.
 * require=0 — prevents crash on method-signature mismatch between FancyMenu versions.
 */
@Pseudo
@Mixin(targets = "de.keksuccino.fancymenu.customization.layout.Layout", remap = false)
public class LayoutMixin {

    /**
     * Method descriptor: ()Z
     * Matches: public boolean isEnabled()
     */
    @Inject(
        method = "isEnabled()Z",
        at = @At("HEAD"),
        cancellable = true,
        remap = false,
        require = 0
    )
    private void forceLayoutEnabled(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
