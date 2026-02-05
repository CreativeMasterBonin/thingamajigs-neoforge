package net.rk.thingamajigs.network.payload;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.rk.thingamajigs.blockentity.custom.DJLaserLightBE;
import net.rk.thingamajigs.network.record.DJLaserLightUpdatePayload;

import java.util.logging.Logger;

@SuppressWarnings("deprecated,unused")
public class DJLaserLightUpdatePacket{
    public static final DJLaserLightUpdatePacket INSTANCE = new DJLaserLightUpdatePacket();

    public static DJLaserLightUpdatePacket get(){return INSTANCE;}

    public void handle(final DJLaserLightUpdatePayload payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Player ply = context.player();
            if(ply == null){
                return;
            }
            else{
                Level lvl = ply.level();
                if(!lvl.hasChunkAt(payload.bp())){
                    return;
                }
                BlockState bs = lvl.getBlockState(payload.bp());
                DJLaserLightBE be = (DJLaserLightBE) lvl.getBlockEntity(payload.bp());
                try{
                    if(lvl != null){
                        if(be != null){
                            be.lightMode = payload.lightMode();
                            be.lightModeData = payload.lightModeData();
                            be.laserTextureLoc = payload.laserTextureLoc();
                            be.hOsc = payload.hOsc();
                            be.vOsc = payload.vOsc();
                            be.hMulti = payload.hMulti();
                            be.vMulti = payload.vMulti();
                            be.angleOffset = payload.angleOffset();
                            be.vAngleOffset = payload.vAngleOffset();

                            if(be.laserCount < 1){
                                be.laserCount = 1;
                            }
                            else if(be.laserCount > 7){
                                be.laserCount = 7;
                            }
                            else{
                                be.laserCount = payload.laserCount();
                            }

                            if(be.height > 32){
                                be.height = 32;
                            }
                            else if (be.height < 0) {
                                be.height = 0;
                            }
                            else{
                                be.height = payload.height();
                            }


                            if(be.laserSize > 5.25f){
                                be.laserSize = 5.25f;
                            }
                            else if(be.laserSize < 0.25f){
                                be.laserSize = 0.25f;
                            }
                            else{
                                be.laserSize = payload.laserSize();
                            }

                            // 0123456789 ABCDEF
                            boolean containsUnallowedChar = false;

                            if(payload.colorstr().contains("`") ||
                                    payload.colorstr().contains("~") ||
                                    payload.colorstr().contains("!") ||
                                    payload.colorstr().contains("@") ||
                                    payload.colorstr().contains("#") ||
                                    payload.colorstr().contains("$") ||
                                    payload.colorstr().contains("%") ||
                                    payload.colorstr().contains("^") ||
                                    payload.colorstr().contains("&") ||
                                    payload.colorstr().contains("*") ||
                                    payload.colorstr().contains("(") ||
                                    payload.colorstr().contains(")") ||
                                    payload.colorstr().contains("_") ||
                                    payload.colorstr().contains("-") ||
                                    payload.colorstr().contains("+") ||
                                    payload.colorstr().contains("=") ||
                                    payload.colorstr().contains("G") ||
                                    payload.colorstr().contains("H") ||
                                    payload.colorstr().contains("I") ||
                                    payload.colorstr().contains("J") ||
                                    payload.colorstr().contains("K") ||
                                    payload.colorstr().contains("L") ||
                                    payload.colorstr().contains("M") ||
                                    payload.colorstr().contains("N") ||
                                    payload.colorstr().contains("O") ||
                                    payload.colorstr().contains("P") ||
                                    payload.colorstr().contains("Q") ||
                                    payload.colorstr().contains("R") ||
                                    payload.colorstr().contains("S") ||
                                    payload.colorstr().contains("T") ||
                                    payload.colorstr().contains("U") ||
                                    payload.colorstr().contains("V") ||
                                    payload.colorstr().contains("W") ||
                                    payload.colorstr().contains("X") ||
                                    payload.colorstr().contains("Y") ||
                                    payload.colorstr().contains("Z") ||
                                    payload.colorstr().contains("?") ||
                                    payload.colorstr().contains("[") ||
                                    payload.colorstr().contains("]") ||
                                    payload.colorstr().contains("|") ||
                                    payload.colorstr().contains("'") ||
                                    payload.colorstr().contains("\"") ||
                                    payload.colorstr().contains("\\") ||
                                    payload.colorstr().contains(";") ||
                                    payload.colorstr().contains(":") ||
                                    payload.colorstr().contains(",") ||
                                    payload.colorstr().contains(".") ||
                                    payload.colorstr().contains(" ") ||
                                    payload.colorstr().contains("<") ||
                                    payload.colorstr().contains(">") ||
                                    payload.colorstr().contains("{") ||
                                    payload.colorstr().contains("}") ||
                                    payload.colorstr().contains("g") ||
                                    payload.colorstr().contains("h") ||
                                    payload.colorstr().contains("i") ||
                                    payload.colorstr().contains("j") ||
                                    payload.colorstr().contains("k") ||
                                    payload.colorstr().contains("l") ||
                                    payload.colorstr().contains("m") ||
                                    payload.colorstr().contains("n") ||
                                    payload.colorstr().contains("o") ||
                                    payload.colorstr().contains("p") ||
                                    payload.colorstr().contains("q") ||
                                    payload.colorstr().contains("r") ||
                                    payload.colorstr().contains("s") ||
                                    payload.colorstr().contains("t") ||
                                    payload.colorstr().contains("u") ||
                                    payload.colorstr().contains("v") ||
                                    payload.colorstr().contains("w") ||
                                    payload.colorstr().contains("x") ||
                                    payload.colorstr().contains("y") ||
                                    payload.colorstr().contains("z")){
                                containsUnallowedChar = true;
                            }

                            if((!payload.colorstr().contains("a") ||
                                    !payload.colorstr().contains("b") ||
                                    !payload.colorstr().contains("c") ||
                                    !payload.colorstr().contains("d") ||
                                    !payload.colorstr().contains("e") ||
                                    !payload.colorstr().contains("f") ||
                                    !payload.colorstr().contains("0") ||
                                    !payload.colorstr().contains("1") ||
                                    !payload.colorstr().contains("2") ||
                                    !payload.colorstr().contains("3") ||
                                    !payload.colorstr().contains("4") ||
                                    !payload.colorstr().contains("5") ||
                                    !payload.colorstr().contains("6") ||
                                    !payload.colorstr().contains("7") ||
                                    !payload.colorstr().contains("8") ||
                                    !payload.colorstr().contains("9")) && containsUnallowedChar){
                                be.colorstr = "FFFFFF";
                            }
                            else if ((payload.colorstr().contains("a") ||
                                    payload.colorstr().contains("b") ||
                                    payload.colorstr().contains("c") ||
                                    payload.colorstr().contains("d") ||
                                    payload.colorstr().contains("e") ||
                                    payload.colorstr().contains("f") ||
                                    payload.colorstr().contains("0") ||
                                    payload.colorstr().contains("1") ||
                                    payload.colorstr().contains("2") ||
                                    payload.colorstr().contains("3") ||
                                    payload.colorstr().contains("4") ||
                                    payload.colorstr().contains("5") ||
                                    payload.colorstr().contains("6") ||
                                    payload.colorstr().contains("7") ||
                                    payload.colorstr().contains("8") ||
                                    payload.colorstr().contains("9")) && containsUnallowedChar){
                                be.colorstr = "FFFFFF";
                            }

                            if(containsUnallowedChar){
                                be.colorstr = "FFFFFF";
                                ply.displayClientMessage(Component.literal("Must be a valid HEX Color Code. Invalid codes are reset to FFFFFF."),false);
                            }
                            else{
                                be.colorstr = payload.colorstr();
                            }
                            be.updateBlock();
                            return;
                        }
                    }
                }
                catch (Exception e){
                    Logger.getAnonymousLogger().warning("Exception regarding DJ Laser Light Update Packet. Error: " + e.getMessage());
                }
            }
        });
    }
}
