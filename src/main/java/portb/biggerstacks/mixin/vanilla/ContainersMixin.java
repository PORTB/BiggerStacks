package portb.biggerstacks.mixin.vanilla;

import net.minecraft.inventory.InventoryHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import portb.biggerstacks.config.AutoSidedConfig;

@Mixin(InventoryHelper.class)
public class ContainersMixin
{
    @ModifyConstant(method = "dropItemStack", constant = {@Constant(intValue = 21), @Constant(intValue = 10)})
    private static int scaleDroppedItemStackSize(int value)
    {
        //Avoid potentially returning 0 with math.max
        return Math.max(value * AutoSidedConfig.getMaxStackSize() / 64, 1);
    }
}
