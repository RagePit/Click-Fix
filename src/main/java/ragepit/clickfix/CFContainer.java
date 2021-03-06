package ragepit.clickfix;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.Collections;

public class CFContainer extends DummyModContainer {

    public static final String MOD_ID = "clickfix";
    public static final String MOD_NAME = "ClickFix";
    public static final String VERSION = "1.1";
    public static final String URL = "https://github.com/RagePit/Click-Fix";
    public static final String DESCRIPTION = "Changes Minecraft.class to remove the server side CPS cap.";
    public CFContainer() {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = MOD_ID;
        meta.name = MOD_NAME;
        meta.version = VERSION;
        meta.url = URL;
        meta.authorList = Collections.singletonList("RagePit");
        meta.description = DESCRIPTION;
    }


    
	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Subscribe
	public void modConstruction(FMLConstructionEvent evt){

	}

	@Subscribe
	public void init(FMLInitializationEvent evt) throws IllegalArgumentException, IllegalAccessException {
	}
	
	@Subscribe
	public void preInit(FMLPreInitializationEvent evt) {

	}

	@Subscribe
	public void postInit(FMLPostInitializationEvent evt) {

	}
}
