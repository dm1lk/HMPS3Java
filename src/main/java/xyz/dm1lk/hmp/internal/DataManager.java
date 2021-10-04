package xyz.dm1lk.hmp.internal;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.dm1lk.hmp.Main;

public class DataManager {
    private static final FileConfiguration fileConfiguration = Main.getPlugin().getConfig();
    private static final String[] fireworks = {"-209 107 208 {Silent:yes,LifeTime:1,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:2,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:0,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]}]}}}}", "-209 103 208 {Silent:yes,LifeTime:1,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:2,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:0,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]}]}}}}", "-209 99 208 {Silent:yes,LifeTime:1,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:2,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:0,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]}]}}}}", "-209 95 208 {Silent:yes,LifeTime:1,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:2,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:0,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]}]}}}}", "-209 91 208 {Silent:yes,LifeTime:1,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:2,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:0,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]}]}}}}", "-209 87 208 {Silent:yes,LifeTime:1,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:2,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:0,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]}]}}}}", "-209 83 208 {Silent:yes,LifeTime:1,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:2,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:0,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]},{Type:1,Flicker:0,Trail:0,Colors:[I;2437522,2651799,6719955,15790320],FadeColors:[I;1973019]}]}}}}",};
    /*
    private static final String[] eastSoulFlameCoordLeft = {"-161 66 206", "-184 66 206", "-187 66 206", "-189 67 206", "-191 68 206", "-193 69 206", "-195 70 206", "-197 71 206"};
    private static final String[] eastSoulFlameCoordRight = {"-161 66 210", "-184 66 210", "-187 66 210", "-189 67 210", "-191 68 210", "-193 69 210", "-195 70 210", "-197 71 210"};
    private static final String[] westSoulFlameCoordLeft = {"-236 66 210", "-233 66 210", "-230 66 210", "-228 67 210", "-226 68 210", "-224 69 210", "-222 70 210", "-220 70 210"};
    private static final String[] westSoulFlameCoordRight = {"-236 66 206", "-233 66 206", "-230 66 206", "-228 67 206", "-226 68 206", "-224 69 206", "-222 70 206", "-220 70 206"};
    private static final String[] miscLanternCoord = {"-178 69 211", "-174 69 211", "-173 69 210", "-173 69 206", "-174 69 205", "-178 69 205"};
    private static final String totemLanternCoord = "-179 69 206";
    private static final String hotLanternCoord = "-179 69 207";
    private static final String gaLanternCoord = "-179 69 209";
    private static final String nsLanternCoord = "-179 69 210";
     */
    private static String[] RingCoordActivatedA;
    private static String[] RingCoordActivatedB;
    private static String[] RingCoordActivatedC;
    private static String[] RingCoordIdleA;
    private static String[] RingCoordIdleB;
    private static String[] RingCoordIdleC;
    private static boolean reviveMachineActive;

    // TODO: Implement coordType config value so the plugin takes it into consideration when deciding what coordinate arrays to return.

    public static void setupAnimationValues() {
        if (fileConfiguration.getString("settings.mode.type").equals("HMPrivate") || fileConfiguration.getString("settings.mode.coord-type").equals("HMPrivate")) {
            RingCoordActivatedA = new String[]{"-227 23 210 ", "-222 20 215 ", "-220 17 218 ", "-216 14 221 ", "-215 11 222 "};
            RingCoordActivatedB = new String[]{"-199 23 238 ", "-204 20 233 ", "-207 17 230 ", "-210 14 227 ", "-211 11 226 "};
            RingCoordActivatedC = new String[]{"-223 103 194", "-218 99 199", "-216 95 202", "-212 91 205", "-211 87 206"};
            RingCoordIdleA = new String[]{"-215 35 222 ", "-216 38 221 ", "-219 41 218 ", "-222 44 215 ", "-227 47 210 "};
            RingCoordIdleB = new String[]{"-211 35 226 ", "-210 38 227 ", "-207 41 230 ", "-204 44 233 ", "-199 47 238 "};
            RingCoordIdleC = new String[]{"-211 87 206", "-212 91 205", "-215 95 202", "-218 99 199", "-223 103 194"};
        } else if (fileConfiguration.getString("settings.mode.type").equals("HMPublic") || fileConfiguration.getString("settings.mode.coord-type").equals("HMPublic")) {
            RingCoordActivatedA = null;
            RingCoordActivatedB = null;
            RingCoordActivatedC = null;
            RingCoordIdleA = null;
            RingCoordIdleB = null;
            RingCoordIdleC = null;
        }
        AnimationManager.saveCoordinates(RingCoordActivatedA, RingCoordActivatedB, RingCoordActivatedC, RingCoordIdleA, RingCoordIdleB, RingCoordIdleC, fireworks);
    }

    public static String getPluginMode() {
        return fileConfiguration.getString("settings.mode.type");
    }

    public static boolean isReviveMachineActive() {
        return reviveMachineActive;
    }

    public static void setReviveMachineActive(boolean activity) {
        reviveMachineActive = activity;
    }
}
