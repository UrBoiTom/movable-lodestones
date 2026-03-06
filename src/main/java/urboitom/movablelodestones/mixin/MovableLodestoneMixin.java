package urboitom.movablelodestones.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class MovableLodestoneMixin {

	// Inject our code at the very top (HEAD) of the getPistonBehavior method
	@Inject(method = "getPistonBehavior", at = @At("HEAD"), cancellable = true)
	private void makeLodestoneMovable(CallbackInfoReturnable<PistonBehavior> cir) {

		// Treat 'this' as the block state we are querying
		AbstractBlock.AbstractBlockState state = (AbstractBlock.AbstractBlockState) (Object) this;

		// If the block is a Lodestone, force the behavior to NORMAL
		if (state.isOf(Blocks.LODESTONE)) {
			cir.setReturnValue(PistonBehavior.NORMAL);
		}
	}
}