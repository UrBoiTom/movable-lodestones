package urboitom.movablelodestones.mixin;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MovableLodestoneMixin {

	// Inject our code at the very top (HEAD) of the getPistonBehavior method
	@Inject(method = "getPistonPushReaction", at = @At("HEAD"), cancellable = true)
	private void makeLodestoneMovable(CallbackInfoReturnable<PushReaction> cir) {

		// Treat 'this' as the block state we are querying
		BlockBehaviour.BlockStateBase state = (BlockBehaviour.BlockStateBase) (Object) this;

		// If the block is a Lodestone, force the behavior to NORMAL
		if (state.is(Blocks.LODESTONE)) {
			cir.setReturnValue(PushReaction.NORMAL);
		}
	}
}