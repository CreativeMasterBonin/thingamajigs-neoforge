package net.rk.thingamajigs.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.entity.TEntity;

public class Stool extends Entity{
    public Stool(EntityType<?> et, Level lvl) {
        super(TEntity.STOOL.get(), lvl);
        this.noPhysics = true;
    }

    public Stool(Level lvl, BlockPos pos, Direction dir) {
        this(TEntity.STOOL.get(),lvl);
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.9;
        double z = pos.getZ() + 0.5;
        this.setPos(x, y, z);
        this.setRot(dir.getOpposite().toYRot(), 0F);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity se) {
        return new ClientboundAddEntityPacket(this,se);
    }

    @Override
    protected boolean canRide(Entity e) {
        if(this.getPassengers().isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder p_326003_) {}

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {}

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity le) {
        Direction dir1 = this.getDirection();
        // the four directions of the block
        // "...north, south, east and west, a book, can be your passport..."
        Direction secDir = dir1.getClockWise();
        Direction thiDir = dir1.getCounterClockWise();
        Direction finDir = dir1.getOpposite();

        Direction[] dirArr = {
                dir1, secDir, thiDir, finDir
        }; // an easy way to group all directions for input later

        // do this for the four directions in the array (this will place the entity riding at a valid good block location next to this entity)
        for(Direction dir : dirArr){
            Vec3 vector3all = DismountHelper.findSafeDismountLocation(le.getType(), this.level(), this.blockPosition().relative(dir),false);
            if(vector3all != null){
                return vector3all.add(0,0.25,0);
            }
        }
        return super.getDismountLocationForPassenger(le);
    }

    @Override
    public void tick() {
        super.tick(); // do the base entity tick stuff
        if(!this.level().isClientSide){
            // get rid of this entity if when ticked this entity has no rider or block in its position
            if(this.getPassengers().isEmpty() || this.level().isEmptyBlock(this.blockPosition())){
                this.remove(RemovalReason.DISCARDED); // tell me WHY this was removed
                this.level().updateNeighbourForOutputSignal(blockPosition(),this.level().getBlockState(blockPosition()).getBlock());
            }
        }
    }

    @Override
    protected void addPassenger(Entity e) {
        super.addPassenger(e);
        e.setYRot(this.getYRot());
    }

    @Override
    public Component getName() {
        return Component.literal("stool_entity");
    }
}
