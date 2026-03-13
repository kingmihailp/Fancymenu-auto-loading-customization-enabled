# FancyMenu Force Enabled

Client addon for **FancyMenu 3.8.1** on **NeoForge 1.21.1** that forces
`enabled = true` (customization) for **all screens** at client startup.

## What it does

FancyMenu 3.x has two independent "enabled" flags that can prevent layouts from
appearing on a screen:

| Level | Class | Default | Problem |
|---|---|---|---|
| **Screen-level** | `ScreenCustomization.isCustomizationEnabledForScreen()` | must be explicitly whitelisted in `customizablemenus.txt` | Screens not in the file are silently skipped |
| **Layout-level** | `Layout.isEnabled()` | `true`, but persisted per-file | A right-click "disable" in FancyMenu's UI writes `is_enabled = false` |

This mod applies two Mixins that intercept both checks and force them to return
`true`, so every screen FancyMenu knows about will have its layouts applied.

## How it works

### `ScreenCustomizationMixin`
Targets `de.keksuccino.fancymenu.customization.ScreenCustomization`.
Injects at the HEAD of `isCustomizationEnabledForScreen(Screen, boolean)` and
returns `true` for any non-null screen, bypassing the `customizablemenus.txt`
whitelist check.

### `LayoutMixin`
Targets `de.keksuccino.fancymenu.customization.layout.Layout`.
Injects at the HEAD of `isEnabled()` and always returns `true`, ignoring the
`is_enabled` flag stored in the layout file.

Both Mixins use `@Pseudo` (safe when FancyMenu is absent) and `require = 0`
(safe when the target method signature changes).

## Requirements

| Dependency | Version |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.47+ |
| FancyMenu | 3.8.1+ |
| Java | 21 |

## Building

```bash
# Make sure you have Gradle 8.8+ or use the wrapper
gradle wrapper          # only needed once if wrapper jar is missing
./gradlew build
```

The compiled JAR will be in `build/libs/`.

## Installation

Drop the JAR into your `.minecraft/mods/` folder alongside FancyMenu and its
dependencies (Konkrete, Melody, etc.).
