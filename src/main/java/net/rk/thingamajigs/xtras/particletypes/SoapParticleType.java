package net.rk.thingamajigs.xtras.particletypes;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.rk.thingamajigs.xtras.TSoundEvent;
import org.jetbrains.annotations.Nullable;

public class SoapParticleType extends SimpleParticleType {
    public SoapParticleType(boolean overrideLimiter) {
        super(overrideLimiter);
    }

    public static class SoapParticle extends TextureSheetParticle {
        public float alpha2 = 1.0f;

        public SoapParticle(ClientLevel cl, double x, double y, double z, SpriteSet ss, double xs, double ys, double zs) {
            super(cl, x, y, z, xs, ys, zs);
            this.scale(4.0F);
            this.friction = 0.75f;
            this.xd = xs;
            this.yd = ys - 0.1D;
            this.zd = zs;

            this.lifetime = 24;
            this.setSpriteFromAge(ss);
            this.gravity = 0.03f;

            this.rCol = 1f;
            this.gCol = 1f;
            this.bCol = 1f;
            this.alpha = alpha2;
        }

        public void tick() {
            this.xo = this.x;
            this.yo = this.y;
            this.zo = this.z;
            if(xd == 0 && yd == 0 && zd == 0){
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
                if(this.alpha > 0.05f){
                    this.alpha -= 0.05F;
                    this.rCol -= 0.02f;
                    this.gCol -= 0.02f;
                    this.bCol -= 0.01f;
                    this.quadSize -= 0.002f;
                }
                this.roll = -0.003f + level.getRandom().nextFloat() * 0.003f;
            }
            else{
                this.level.playLocalSound(this.x,this.y,this.z,
                        SoundEvents.POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON,SoundSource.BLOCKS,
                        0.13f,2.0f + this.level.random.nextFloat() * 0.35f,true);
                this.remove();
                return;
            }
        }

        @Override
        public ParticleRenderType getRenderType() {
            return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class SoapParticleFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;
        public SoapParticleFactory(SpriteSet spriteSet){
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel lvl,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new SoapParticle(lvl,x,y,z,this.spriteSet,xSpeed,ySpeed,zSpeed);
        }
    }
}
