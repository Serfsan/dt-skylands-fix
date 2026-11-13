# DT Skylands Fix

Fixes Dynamic Trees not growing any trees on Terralith's Skylands islands on 1.21.1.

Dynamic Trees picks a heightmap for each tree spot based on the biome there. Since DT commit 2ce0dfdd it
checks that biome at the bottom of the world instead of y=0, which under the Skylands isn't a Skylands
biome anymore. So Dynamic Trees - Terralith's ocean_floor setting for the Skylands never gets used and the
trees go on the ground under the islands. This moves that one check back to y=0.

Only affects newly generated chunks. Build with `build.ps1` (JDK 21).

Icon is based on the Dynamic Trees logo, Copyright (c) 2025 DynamicTreesTeam, used under the MIT License.
