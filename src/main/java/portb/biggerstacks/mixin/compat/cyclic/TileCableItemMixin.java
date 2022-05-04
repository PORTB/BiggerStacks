package portb.biggerstacks.mixin.compat.cyclic;

import com.lothrazar.cyclic.block.cable.item.TileCableItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import portb.biggerstacks.config.ServerConfig;

@Mixin(value = TileCableItem.class, remap = false)
public class TileCableItemMixin
{
    @Inject(method = "<init>", at = @At("TAIL"), require = 0)
    private void fixExtractQty(CallbackInfo callbackInfo)
    {
        //may need a world restart to take effect properly
        //as far as i can tell, this isn't actually changed anywhere,
        //i think it's a legacy thing anyway, you used to be able to set the extract
        //rate in the gui, but that doesn't seem like an option anymore
        if(ServerConfig.INSTANCE.increaseTransferRate.get())
            ((TileCableItemAccessor)this).setExtractQty(ServerConfig.INSTANCE.maxStackCount.get());
    }

    @ModifyConstant(method = "normalFlow", constant = @Constant(intValue = 64), require = 0)
    private int increaseNormalFlow(int value)
    {
        if(ServerConfig.INSTANCE.increaseTransferRate.get())
        {
            return ServerConfig.INSTANCE.maxStackCount.get();
        }
        else
        {
            return value;
        }
    }
}