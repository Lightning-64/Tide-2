## Changelog - Tide v2.0 Playtest 6

---
### Changes
- The alternate textures displayed for large fish in the journal are now data-driven (defined in the "journal" section of the fish data file)
- Catch dates are now displayed in the system date time format
- Text entries from the fishing stats section now have proper translation keys
- Pressing an arrow key while viewing a fish profile no longer kicks you out
- ItemFishedEvent is now called on both Forge and Neoforge (read-only)
- Added a period at the end of the smallmouth bass description
- Added an item tooltip for the new Chasm Eel item
- Added a weather info area for the Coelacanth
- Added a luck info area for the Midas Fish
- Fixed a texturing bug on certain crates when opened

---
### Known Issues
- Fish display animation issues with vanilla entities when using Fresh Animations
- Issues with some fish entities spinning really quickly?
- Animation issue with the fishing rods when using Hold My Items

## Changelog - Tide v2.0 Playtest 5

---
### Changes
- Added the Chasm Eel, a fish that allows the player to remove a single block of bedrock
- Reintroduced the Fish Satchel, a utility item for portable fish storage
- Escape or E can be pressed in the journal to go back or exit
- The height of the fishable void is now configurable per-dimension
- Fixed a crash when putting certain modded items in fish displays
- Fixed incorrect display data for Nether Depths Upgrade fish
- Semi-transparent entities now render properly on 1.20.1
- Chain effects of the pocket watch are no longer invisible on 1.20.1
- Limbs of enemies frozen by the pocket watch no longer shake on forge 1.20.1

---
### Known Issues
- Texturing bug on certain crates
- Fish display animation issues with vanilla entities when using Fresh Animations

---

## Changelog - Tide v2.0 Playtest 4

---
### Changes
- Added compatibility with Nether Depths Upgrade
- Added compatibility with Fishing Real
- Added compatibility with Fish of Thieves
- Fixed compatibility with Stardew Fishing
- Fixed compatibility with Hybrid Aquatic
- Fixed a rendering bug with emissive texture parts
- Fixed a rendering bug with dying entities frozen by the enchanted pocket watch
- Fixed a bug where length wouldn't display on bucket tooltips in 1.20.1
- Fixed some grammar errors in fish descriptions
- Fixed voidseeker not being able to be displayed
- Added some miscellaneous fish crafting recipes
- Gave additional eating effects for some fish
- The Midas Fishing Rod is now created via a smithing recipe
- Dragonfin Boots are now created via a smithing recipe
- The Netherite Fishing Rod now requires a netherite upgrade template
- Added "Override Vanilla Fishing Rod" config option
- Made the Sleepy Carp entity sleepier
- Snook is now caught on beaches

---
### Known Issues
- On 1.20.1, chain effects of the Enchanted Pocket Watch don't appear
- On forge 1.20.1, limbs of enemies frozen by the Enchanted Pocket Watch sometimes shake rapidly
- Fish satchel doesn't do anything
- Golden fishing line doesn't do anything

---

## Changelog - Tide v2.0 Playtest 3

---
### Changes
- Fixed a crash when using the Little Joys mod (and likely many others)
- Fixed several crashes upon dedicated server startup
- Changed the format of the `tide:found_in` fishing condition to mirror biome tag JSONs

---
### Known Issues
Same as previous

---

## Changelog - Tide v2.0 Playtest 2

---
### Changes
- Added support for 1.20.1 (forge and fabric)
- Added compatibility with Ecliptic Seasons
- Bait is no longer consumed when catching crates or loot items
- Added shooting star projectile to the `minecraft:arrows` tag
- Adjusted poses of the fish display to match the vanilla item frame
- Added a yellow highlight around newly discovered fish in the journal
- Removed unused convention tags from the data folder

---
### Known Issues
- Chain effects of the Enchanted Pocket Watch don't appear
- Limbs of enemies frozen by the Enchanted Pocket Watch on forge will sometimes shake rapidly
- I still don't have a name for the void fishing advancement
- Fish satchel doesn't do anything
- Golden fishing line doesn't do anything

---

## Tide v2.0 - 1/9/26

---
### Fishing Loot
- Fishing loot is now controlled by a custom data-driven system, consisting of the following:

**Fish Data**
- Fish catch conditions, rarities, rarity modifiers, journal data, minigame stats, display data, and more are now controlled using fish-specific JSON files located at `data/tide/fishing/fish`

**Crate Data**
- Crate catch conditions, rarities, rarity modifiers, and more are now controlled using crate-specific JSON files located at `data/tide/fishing/crates`

**Fishing Loot Data**
- External loot tables for fishing (such as the vanilla junk and treasure tables) can now be configured or added via JSON files located at `data/tide/fishing/loot`

More extensive documentation for these new data-driven formats will be available later after the full release.

---
### Fish
- Significant changes to the fish lineup have taken place. These include:
  - A few fish have been removed that either didn't fit with the mod's theme or were originally added to serve as filler content.
  - Added many new fish to discover throughout the world, bringing the total to around 100 fish in total.
- The vast majority of existing fish items were retextured (finally).
- Each fish can now be cooked and eaten, regardless of how healthy it might seem.
- Every single fish in the mod now has a corresponding entity version to go with it.
- Reworked the entity spawning system to be partially based off of the fish's Fish Data to automatically determine spawn conditions.
- Added natural spawning for each fish entity (except for a select few).
- By default, fish items can no longer be stacked up to 64 like before (see below, or look at the config tab titled "Items" for more information).

**Fish Lengths**
- Each fish you catch is now assigned a length in centimeters based on configurable parameters from the fish's Fish Data.
- Config options have been created to allow the user to disable this mechanic entirely, or show their extreme catches only in the journal and not affect item stacks.
- The fish's length will serve as an extra collectible aspect, but may affect other things (such as the quality of the fish food item if Quality Food is installed).

**Bucketable Fish**
- Each fish item you catch is now assigned a timestamp that is used to determine if the fish is "alive" or not.
- If the fish item is "alive", you can click on the fish in the inventory GUI with a water bucket (or lava bucket) in order to transform the items into a fish bucket item to allow access to the fish's entity form.
- If the fish is "alive" and bucketable, a tooltip saying "Bucketable" will be shown below the fish's name.
- Config options have been added so users can either never allow bucketable fish, allow them all the time, or change how long a fish item should be "alive" for after being caught.

---
### Fishing Journal
- Mechanics and visuals for the Fishing Journal have been reworked. Some of the major differences are listed below
- The fishing journal is now deeply integrated into the aforementioned "Fish Data" system to dynamically create profiles for any fish with a proper data file. Developers will no longer have to create separate data files for journal entries if a fish data file is defined.
- The fish "profile" GUI has also been overhauled with a fresh new layout.
- Removed the old "page" system in favor of categorizing fish more loosely. This also means that all fish, whether locked or unlocked, are visible in the journal GUI at all times.
- Additionally, many fish that were originally split up into smaller categories have been merged into corresponding larger categories.
- Fish can be assigned rarities, profile descriptions, and location summaries via the Fish Data system.
- **Simply collecting a fish item will no longer unlock its profile in the fishing journal!** Each fish you unlock _must_ be caught while fishing in order to unlock the profile.

---
### Mechanics
- The time it takes to catch a fish now also varies based on the type of liquid you're fishing in (ex. x1.0 for water, x1.6 for lava, x1.8 for void). This multiplier is applied before fishing speed modifications like bait or lure enchantments.
- Fish caught in specific biomes will be more lenient about where they are catchable. For example, if you're fishing in a river biome that cuts through a desert biome, you're still able to catch desert-themed fish.
- The temperature preferences of fish are no longer based on specific biomes. Instead, they are based on the internal temperature values from the worldgen noise router. This means that fishing in the middle of a desert may favor more warm fish than if you were to fish on the border between a desert and a more temperate biome.
- A few additional core mechanics have been added or modified:

**Minigame**
- The fishing minigame has received many tweaks, such as the following:
- The minigame no longer operates on a probability-based catch system, instead having two distinct zones on the minigame bar.
- The lighter zone in the bar will successfully catch the fish if clicked over, but the darker zone will cause the catch to fail and the minigame to end.
- Each fish has a configurable "strength" and "speed" parameter in their Fish Data that influences how much space the dark zone covers and how quickly the vertical bar moves, respectively.
- Not every fish will move the vertical bar in a "sine wave" pattern anymore, with some fish having much stranger movements. Stay alert!
- The fish's movement pattern is defined in its Fish Data as well, being set to one of the following:
  - `sine`
  - `plateau`
  - `jitter`
  - `darts`
  - `linear`
  - `linear_wrap`
- Additionally, the lighter bar will change color depending on what liquid you're fishing in (ex. blue for water, orange for lava, purple for void).

**Void Fishing**
- In order to fish in the void, you must first craft a Hook of the Depths and apply it to any fishing rod via the Angling Table.
- With the hook applied, fishing in the void operates as if the entire void is a fishable liquid. Note that the fishable void does not line up with the death barrier, and may extend significantly farther up in the world (ex. the End dimension)
- New fish have been added for both the End void and the void beneath the overworld bedrock.
- A few new particles have been added that are displayed on the fishable void surface.

---
### Items
- Added a few "profiling" items such as the thermometer and pocket watch, which can assist the user in finding the precise locations of fish when paired with the fishing journal or fishy notes.
- Most legendary tier fish have late-game craftable items, each filling a different role in player progression.
- Some other utility items were also added:

**Fish Satchel**
- Added the Fish Satchel, which is basically a bundle that can store fish.
- Right-clicking while holding the fish satchel will open or close the satchel. When open, any fish you catch or pick up from the ground will be automatically transferred to the fish satchel.
- This item should be especially helpful for dealing with the new unstackable fish.

**Fishy Notes**
- Pre-2.0 "Torn Notes" have been replaced by a new item, called the Fishy Note.
- Fishy Notes can be found in crates, with similar drop chances compared to the aforementioned Torn Notes.
- A Fishy Note for a specific fish can also be crafted using the desired fish and a single piece of paper.
- Each Fishy Note will display the catch conditions, rarity, and relative size of a single fish, usually one that has not been discovered yet.
- The conditions are displayed in the same manner as you would see them in the Fishing Journal, except the name and sprite of the fish are not visible.
- A checkmark will be displayed in the Fishy Note GUI if the fish it references is already discovered.

**Fish Display**
- The Fish Display is a placeable block that allows the user to mount a fish item to the wall for decoration purposes.
- Once a display is placed, right-clicking it with any fish item that has display data defined will display the entity version of the fish item on the wall. Right-clicking again will retrieve the item stack.
- Display data such as rotation and offsets are defined in the Fish Data files.

---
### Miscellaneous
- Fishing rods can now be repaired at an anvil using string in the same way that vanilla tools and armor are upgraded.
- Most of the old advancements have been renamed, and many new advancements have been added.
- Two new utility commands have been added, `/journal` and `/fishing`.
- Removed the old fishing boat structure, I don't think it's really necessary.
- Many item and block IDs have been changed to reflect updated English translation names. For example, the Angling Table (previously having an ID of `tide:angler_workshop`) has been renamed to `tide:angling_table`. **This may cause issues if Tide 2 is loaded on a world that was previously loaded with Tide 1!**

I won't list bug fixes here because so many things got reworked, and it's likely that many old bugs are now patched.