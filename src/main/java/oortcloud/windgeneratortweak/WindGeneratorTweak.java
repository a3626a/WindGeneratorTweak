package oortcloud.windgeneratortweak;

import ic2.core.Ic2Items;
import ic2.core.item.ItemGradualInt;
import ic2.core.item.resources.ItemWindRotor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import oortcloud.windgeneratortweak.configuration.ConfigurationHandler;
import oortcloud.windgeneratortweak.core.proxy.CommonProxy;
import oortcloud.windgeneratortweak.libs.References;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MODID, name = References.MODNAME, version = References.VERSION)
public class WindGeneratorTweak {
	@Mod.Instance
	public static WindGeneratorTweak instance;

	@SidedProxy(clientSide = References.CLIENTPROXYLOCATION, serverSide = References.COMMONPROXYLOCATION)
	public static CommonProxy proxy;

	public static Logger logger;

	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		ConfigurationHandler.getInstance().init(event);
	}

	@Mod.EventHandler
	public static void Init(FMLInitializationEvent event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ConfigurationHandler.getInstance().sync();
	}

	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
	}
}
