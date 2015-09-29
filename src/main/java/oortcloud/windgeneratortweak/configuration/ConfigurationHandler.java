package oortcloud.windgeneratortweak.configuration;

import ic2.core.Ic2Items;
import ic2.core.item.ItemGradualInt;
import ic2.core.item.resources.ItemWindRotor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import oortcloud.windgeneratortweak.WindGeneratorTweak;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigurationHandler {

	private static ConfigurationHandler instance;
	
	private Configuration config;
	
	private ConfigurationHandler() {}
	
	public static ConfigurationHandler getInstance() {
		if (instance==null) {
			instance = new ConfigurationHandler();
		}
		return instance;
	}
	
	public void init(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
	}
	
	public void sync() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field maxDmg = ItemGradualInt.class.getDeclaredField("maxDmg");
		Field efficiency = ItemWindRotor.class.getDeclaredField("efficiency");
		
		Field modfield = Field.class.getDeclaredField("modifiers");
        modfield.setAccessible(true);
        modfield.setInt(maxDmg, maxDmg.getModifiers() & ~Modifier.FINAL);
		maxDmg.setAccessible(true);
		modfield.setInt(efficiency, efficiency.getModifiers() & ~Modifier.FINAL);
		efficiency.setAccessible(true);
		
		setMaxDamage((ItemWindRotor)Ic2Items.woodrotor.getItem(), maxDmg, config.get("WOOD ROTOR", "Max Damage", 10800).getInt());
		setMaxDamage((ItemWindRotor)Ic2Items.ironrotor.getItem(), maxDmg, config.get("IRON ROTOR", "Max Damage", 21600).getInt());
		setMaxDamage((ItemWindRotor)Ic2Items.steelrotor.getItem(), maxDmg, config.get("STEEL ROTOR", "Max Damage", 43200).getInt());
		setMaxDamage((ItemWindRotor)Ic2Items.carbonrotor.getItem(), maxDmg, config.get("CARBON ROTOR", "Max Damage", 86400).getInt());
		
		setEfficiency((ItemWindRotor)Ic2Items.woodrotor.getItem(), efficiency, config.get("WOOD ROTOR", "Efficiency", 0.0625).getDouble());
		setEfficiency((ItemWindRotor)Ic2Items.ironrotor.getItem(), efficiency, config.get("IRON ROTOR", "Efficiency", 0.125).getDouble());
		setEfficiency((ItemWindRotor)Ic2Items.steelrotor.getItem(), efficiency, config.get("STEEL ROTOR", "Efficiency", 0.1875).getDouble());
		setEfficiency((ItemWindRotor)Ic2Items.carbonrotor.getItem(), efficiency, config.get("CARBON ROTOR", "Efficiency", 0.25).getDouble());
		
		printRotorStat((ItemWindRotor)Ic2Items.woodrotor.getItem());
		printRotorStat((ItemWindRotor)Ic2Items.ironrotor.getItem());
		printRotorStat((ItemWindRotor)Ic2Items.steelrotor.getItem());
		printRotorStat((ItemWindRotor)Ic2Items.carbonrotor.getItem());
		
		config.save();
	}
	
	public static void setMaxDamage(ItemWindRotor rotor, Field maxDmg, int damage) throws IllegalArgumentException, IllegalAccessException {
		maxDmg.set(rotor, damage);
		rotor.setMaxDamage(damage);
	}
	
	public static void setEfficiency(ItemWindRotor rotor, Field efficiency, double value) throws IllegalArgumentException, IllegalAccessException {
		efficiency.set(rotor, (float)value);
	}

	public static void printRotorStat(ItemWindRotor rotor) {
		WindGeneratorTweak.logger.info(rotor.getUnlocalizedName()+"'s Max Damage : "+rotor.getMaxDamage());
		//WindGeneratorTweak.logger.info(rotor.getUnlocalizedName()+"'s Max Custom Damage : "+rotor.getMaxCustomDamage(null));
		//WindGeneratorTweak.logger.info(rotor.getUnlocalizedName()+"'s Min Wind Strength : "+rotor.getMinWindStrength(null));
		WindGeneratorTweak.logger.info(rotor.getUnlocalizedName()+"'s Efficiency : "+rotor.getEfficiency(null));
	}
	
 }
