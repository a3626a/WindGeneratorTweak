package oortcloud.windgeneratortweak;

import oortcloud.windgeneratortweak.core.proxy.CommonProxy;
import oortcloud.windgeneratortweak.libs.References;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class WindGeneratorTweak {
	@Mod.Instance
	public static WindGeneratorTweak instance;

	@SidedProxy(clientSide = References.CLIENTPROXYLOCATION, serverSide = References.COMMONPROXYLOCATION)
	public static CommonProxy proxy;

	public static Logger logger;

	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
	}

	@Mod.EventHandler
	public static void Init(FMLInitializationEvent event) {
	}

	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
	}
}
