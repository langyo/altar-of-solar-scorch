package com.corgames.langyo.altarofsolarscorch;

import com.mojang.logging.LogUtils;
import java.util.stream.Collectors;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("altarofsolarscorch.langyo.corgames.com")
public class Entry {
  // Directly reference a slf4j logger
  private static final Logger LOGGER = LogUtils.getLogger();

  public Entry() {
    // Register the setup method for modloading
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    // Register the enqueueIMC method for modloading
    FMLJavaModLoadingContext
      .get()
      .getModEventBus()
      .addListener(this::enqueueIMC);
    // Register the processIMC method for modloading
    FMLJavaModLoadingContext
      .get()
      .getModEventBus()
      .addListener(this::processIMC);

    // Register ourselves for server and other game events we are interested in
    MinecraftForge.EVENT_BUS.register(this);
  }

  private void setup(final FMLCommonSetupEvent event) {
    LOGGER.info("Preload Corona Altar of Solar Scorch Mod.");
  }

  private void enqueueIMC(final InterModEnqueueEvent event) {
    InterModComms.sendTo(
      "altarofsolarscorch",
      "register",
      () -> {
        LOGGER.info("Corona Altar of Solar Scorch Mod");
        return "Corona Altar of Solar Scorch Mod";
      }
    );
  }

  private void processIMC(final InterModProcessEvent event) {
    // Some example code to receive and process InterModComms from other mods
    LOGGER.info(
      "Got IMC {}",
      event
        .getIMCStream()
        .map(m -> m.messageSupplier().get())
        .collect(Collectors.toList())
    );
  }

  // You can use SubscribeEvent and let the Event Bus discover methods to call
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    // Do something when the server starts
    LOGGER.info("Loaded Corona Altar of Solar Scorch Mod on server.");
  }

  @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class RegistryEvents {

    @SubscribeEvent
    public static void onBlocksRegistry(
      final RegistryEvent.Register<Block> blockRegistryEvent
    ) {
      LOGGER.info("HELLO from Register Block");
    }
  }
}
