
package io.itch.awesomekalin.noob.item;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import java.util.Set;
import java.util.HashMap;

import io.itch.awesomekalin.noob.procedure.ProcedureMudAxeOnPlayerStoppedUsing;
import io.itch.awesomekalin.noob.procedure.ProcedureMudAxeMobIsHitWithTool;
import io.itch.awesomekalin.noob.creativetab.TabNoobTab;
import io.itch.awesomekalin.noob.ElementsTheGameofNoobs;

@ElementsTheGameofNoobs.ModElement.Tag
public class ItemMudPickaxe extends ElementsTheGameofNoobs.ModElement {
	@GameRegistry.ObjectHolder("noob:mudpickaxe")
	public static final Item block = null;
	public ItemMudPickaxe(ElementsTheGameofNoobs instance) {
		super(instance, 12);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemPickaxe(EnumHelper.addToolMaterial("MUDPICKAXE", 0, 10, 0.1f, -4f, 1)) {
			{
				this.attackSpeed = -3f;
			}
			public Set<String> getToolClasses(ItemStack stack) {
				HashMap<String, Integer> ret = new HashMap<String, Integer>();
				ret.put("pickaxe", 0);
				return ret.keySet();
			}

			@Override
			public boolean hitEntity(ItemStack itemstack, EntityLivingBase entity, EntityLivingBase entity2) {
				super.hitEntity(itemstack, entity, entity2);
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				World world = entity.world;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("itemstack", itemstack);
					ProcedureMudAxeMobIsHitWithTool.executeProcedure($_dependencies);
				}
				return true;
			}

			@Override
			public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityLivingBase entity, int time) {
				super.onPlayerStoppedUsing(itemstack, world, entity, time);
				int x = (int) entity.posX;
				int y = (int) entity.posY;
				int z = (int) entity.posZ;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("itemstack", itemstack);
					ProcedureMudAxeOnPlayerStoppedUsing.executeProcedure($_dependencies);
				}
			}
		}.setUnlocalizedName("mudpickaxe").setRegistryName("mudpickaxe").setCreativeTab(TabNoobTab.tab));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("noob:mudpickaxe", "inventory"));
	}
}
