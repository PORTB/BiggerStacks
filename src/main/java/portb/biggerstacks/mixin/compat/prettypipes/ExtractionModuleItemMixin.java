package portb.biggerstacks.mixin.compat.prettypipes;

import de.ellpeck.prettypipes.pipe.modules.extraction.ExtractionModuleItem;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import portb.biggerstacks.config.AutoSidedConfig;
import portb.biggerstacks.util.StackSizeHelper;

@Mixin(ExtractionModuleItem.class)
public class ExtractionModuleItemMixin
{
    @Redirect(method = "tick",
              at = @At(value = "FIELD",
                       target = "Lde/ellpeck/prettypipes/pipe/modules/extraction/ExtractionModuleItem;maxExtraction:I",
                       opcode = Opcodes.GETFIELD),
              require = 0,
              remap = false)
    private int increaseTransferRate(ExtractionModuleItem instance)
    {
        int rate = ((ExtractionModuleItemAccessor) instance).getMaxExtractionRate();
    
        if (AutoSidedConfig.increaseTransferRate())
            return StackSizeHelper.scaleTransferRate(rate, false);
        else
            return rate;
    }
}
