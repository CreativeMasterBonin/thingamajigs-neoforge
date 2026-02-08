package net.rk.thingamajigs.xtras.particletypes;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.rk.thingamajigs.xtras.TCalcStuff;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

public class WaterSprayParticleType extends SimpleParticleType {
    public WaterSprayParticleType(boolean pOverrideLimiter) {
        super(pOverrideLimiter);
    }

    public static class WaterSprayParticle extends TextureSheetParticle {
        public WaterSprayParticle(ClientLevel cl, double x, double y, double z, SpriteSet ss, double xs, double ys, double zs) {
            super(cl, x, y, z, xs, ys, zs);
            this.scale(2.42f);
            this.friction = 0.12f;
            this.xd = xs;
            this.yd = ys - 0.1D;
            this.zd = zs;

            this.lifetime = 32;
            this.setSprite(ss.get(cl.getRandom()));
            this.gravity = 0.05f;

            this.rCol = 0.0f;
            this.gCol = TCalcStuff.nextFloatBetweenInclusive(0.347f,0.351f);
            this.bCol = 1.0f;
            this.alpha = TCalcStuff.nextFloatBetweenInclusive(0.14f,0.23f);
            if(this.alpha > 0.23f){
                this.alpha = 0.23f;
            }
        }

        public void tick() {
            if(this.onGround){
                this.age = this.lifetime;
                spawnRemovalParticle();
                this.remove();
                return;
            }
            this.xo = this.x;
            this.yo = this.y;
            this.zo = this.z;
            if(xd == 0 && yd == 0 && zd == 0){
                spawnRemovalParticle();
                this.remove();
                return;
            }

            if(this.age++ < this.lifetime){
                this.xd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
                this.zd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
                this.yd -= (double)this.gravity;
                this.move(this.xd, this.yd, this.zd);
                if(this.age >= this.lifetime - 30){
                    this.xd = -0.001f + level.getRandom().nextDouble() * 0.001D;
                    this.yd -= this.gravity;
                    this.zd = -0.001f + level.getRandom().nextDouble() * 0.001D;
                }
                float sinoffset = Mth.sin(this.age * 0.25f);
                this.bCol = Mth.clamp(sinoffset,0.98f,1.0f);
            }
            else{
                spawnRemovalParticle();
                this.remove();
                return;
            }
        }

        @Override
        public ParticleRenderType getRenderType() {
            return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
        }

        public void spawnRemovalParticle(){
            this.level.addParticle(ParticleTypes.RAIN,this.x,this.y,this.z,0,0,0);
            if(TCalcStuff.nextDoubleBetweenInclusive(0.0D,1.0D) <= 0.27D){
                this.level.playLocalSound(this.x,this.y,this.z,
                        SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS,0.25f,
                        TCalcStuff.nextFloatBetweenInclusive(0.97f,1.24f),true);
            }
            else if(TCalcStuff.nextDoubleBetweenInclusive(0.0D,1.0D) <= 0.14D){
                this.level.playLocalSound(this.x,this.y,this.z,
                        SoundEvents.PLAYER_SPLASH_HIGH_SPEED, SoundSource.BLOCKS,0.18f,
                        TCalcStuff.nextFloatBetweenInclusive(1.0f,1.24f),true);
            }
        }

        @Override
        public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {
            Quaternionf quaternionf = new Quaternionf();
            Quaternionf quaternionf2 = new Quaternionf();
            Quaternionf quaternionf3 = new Quaternionf();
            Quaternionf quaternionf4 = new Quaternionf();

            quaternionf.rotateY(1.55f);
            quaternionf2.rotateY(-1.55f);
            quaternionf3.rotateY(3.10f);
            quaternionf4.rotateY(0.00f);

            this.renderRotatedQuad(buffer, renderInfo, quaternionf, partialTicks);
            this.renderRotatedQuad(buffer, renderInfo, quaternionf2, partialTicks);
            this.renderRotatedQuad(buffer, renderInfo, quaternionf3, partialTicks);
            this.renderRotatedQuad(buffer, renderInfo, quaternionf4, partialTicks);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class WaterSprayParticleFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;
        public WaterSprayParticleFactory(SpriteSet spriteSet){
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel lvl,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new WaterSprayParticleType.WaterSprayParticle(lvl,x,y,z,this.spriteSet,xSpeed,ySpeed,zSpeed);
        }
    }
}
