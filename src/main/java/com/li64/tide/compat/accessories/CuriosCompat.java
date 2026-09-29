//? if neoforge || forge {
/*package com.li64.tide.compat.accessories;

import com.li64.tide.data.TideTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;
import com.li64.tide.data.informational.FishingInfoManager;

import java.util.ArrayList;

public class CuriosCompat {
    public static void addInformationalItems(Player player, ArrayList<Item> items) {
        var itemHandler = CuriosApi.getCuriosInventory(player);
        itemHandler.ifPresent(handler -> handler
                .findCurios(stack -> stack.is(TideTags.Items.INFORMATIONAL))
                .forEach(result -> {
                    Item item = result.stack().getItem();
                    FishingInfoManager.addInfoItem(items, item);
                }));
    }
}
*///?}
