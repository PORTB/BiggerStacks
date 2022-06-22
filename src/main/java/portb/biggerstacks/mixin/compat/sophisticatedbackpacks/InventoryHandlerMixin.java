package portb.biggerstacks.mixin.compat.sophisticatedbackpacks;

import net.minecraft.world.item.ItemStack;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import portb.biggerstacks.config.AutoSidedConfig;

@Mixin(InventoryHandler.class)
public class InventoryHandlerMixin
{
    @ModifyVariable(method = "setSlotLimit", at = @At("HEAD"), ordinal = 0, remap = false, argsOnly = true)
    private int scaleSlotLimit(int slotLimit){
        return slotLimit * AutoSidedConfig.getMaxStackSize() / 64;
    }

    @ModifyConstant(method = "setSlotLimit", constant = @Constant(intValue = 64), remap = false)
    private int increaseStackLimit(int val){
        return AutoSidedConfig.getMaxStackSize();
    }
}