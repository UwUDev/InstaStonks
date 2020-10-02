package me.uwu.utils;


import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class Discord {

    public static void startup(){
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {}).build();
        DiscordRPC.discordInitialize("760182558926700586", handlers, true);
    }

    public static void update(int subs, String username) {

        if(username == null)
            username = "Stonksing";

        DiscordRichPresence rich;
        if (subs < 0) {
            rich = new DiscordRichPresence.Builder("Flexing").setDetails("Idle ...").setBigImage("stonks", username).build();
        } else {
            rich = new DiscordRichPresence.Builder("Flexing").setDetails(subs + " followers \uD83D\uDC9C").setBigImage("stonks", username).build();
        }
        DiscordRPC.discordUpdatePresence(rich);
    }

}