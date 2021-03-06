package elucent.rootsclassic.component.components;

import java.util.Random;
import elucent.rootsclassic.Util;
import elucent.rootsclassic.component.ComponentBase;
import elucent.rootsclassic.component.EnumCastType;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ComponentLilyPad extends ComponentBase {

  Random random = new Random();

  public ComponentLilyPad() {
    super("lilypad", Blocks.WATERLILY, 8);
  }

  @Override
  public void doEffect(World world, Entity caster, EnumCastType type, double x, double y, double z, double potency, double duration, double size) {
    if (type == EnumCastType.SPELL) {
      if (caster instanceof EntityPlayer && !world.isRemote) {
        BlockPos pos = Util.getRayTrace(world, (EntityPlayer) caster, 4 + 2 * (int) size);
        if (world.getBlockState(pos.up()).getBlock() == Blocks.AIR || world.getBlockState(pos.up()).getBlock() == Blocks.TALLGRASS || world.getBlockState(pos.up()).getBlock() == Blocks.FLOWING_WATER) {
          world.setBlockState(pos.up(), Blocks.FLOWING_WATER.getDefaultState().withProperty(BlockLiquid.LEVEL, 15), 3);
          world.setBlockState(pos.up().west(), Blocks.FLOWING_WATER.getDefaultState().withProperty(BlockLiquid.LEVEL, 15), 3);
          world.setBlockState(pos.up().east(), Blocks.FLOWING_WATER.getDefaultState().withProperty(BlockLiquid.LEVEL, 15), 3);
          world.setBlockState(pos.up().north(), Blocks.FLOWING_WATER.getDefaultState().withProperty(BlockLiquid.LEVEL, 15), 3);
          world.setBlockState(pos.up().south(), Blocks.FLOWING_WATER.getDefaultState().withProperty(BlockLiquid.LEVEL, 15), 3);
        }
      }
    }
  }
}
