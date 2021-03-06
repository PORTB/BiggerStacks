package portb.biggerstacks.mixin.compat.cctweaked;

import dan200.computercraft.shared.util.DefaultInventory;
import org.spongepowered.asm.mixin.Mixin;
import portb.biggerstacks.util.StackSizeHelper;

@Mixin(DefaultInventory.class)
public interface DefaultInventoryMixin extends DefaultInventory
{
    @Override
    default int getMaxStackSize()
    {
        return StackSizeHelper.getNewStackSize();
    }
}
