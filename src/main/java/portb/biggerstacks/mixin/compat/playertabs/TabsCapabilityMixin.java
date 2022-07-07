package portb.biggerstacks.mixin.compat.playertabs;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import portb.biggerstacks.config.AutoSidedConfig;
import portb.biggerstacks.util.StackSizeHelper;
import wolforce.playertabs.TabsCapability;

@Mixin(TabsCapability.class)
public class TabsCapabilityMixin
{
    @ModifyConstant(require = 0,
                    method = {"saveInvToCurrTab", "writeTabToInv"},
                    constant = @Constant(intValue = 64),
                    remap = false)
    private int increaseStackSize(int constant)
    {
        return StackSizeHelper.getNewStackSize();
    }
}
