package net.rk.thingamajigs.xtras.particletypes;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class BallPitParticleType extends SimpleParticleType {
    public BallPitParticleType(boolean overrideLimiter) {
        super(overrideLimiter);
    }

    @OnlyIn(Dist.CLIENT)
    public static class BallPitParticle extends TextureSheetParticle {
        public float alpha2 = 1.0f;
        public int tileIndex = 0;

        public BallPitParticle(ClientLevel cl, double x, double y, double z, double xs, double ys, double zs) {
            super(cl, x, y, z, xs, ys, zs);
            this.scale(1.0F);
            this.friction = 1.0f;
            this.xd = xs;
            this.yd = ys + (double)(this.random.nextFloat() / 500.0f);
            this.zd = zs;

            this.lifetime = 40;
            this.gravity = 0.1f;

            this.rCol = cl.random.nextFloat();
            this.gCol = cl.random.nextFloat();
            this.bCol = cl.random.nextFloat();
            this.alpha = alpha2;
        }

        public BallPitParticle(ClientLevel cl, double x, double y, double z, SpriteSet ss, double xs, double ys, double zs) {
            super(cl, x, y, z, xs, ys, zs);
            this.scale(1.0F);
            this.friction = 0.75f;
            this.xd = xs;
            this.yd = ys + (double)(this.random.nextFloat() / 500.0f);
            this.zd = zs;

            this.lifetime = 40;
            this.setSpriteFromAge(ss);
            this.gravity = 0.1f;

            this.rCol = cl.random.nextFloat();
            this.gCol = cl.random.nextFloat();
            this.bCol = cl.random.nextFloat();
            this.alpha = alpha2;
        }

        public void setSpriteFromAge(SpriteSet sprite) {
            RandomSource rs = this.level.getRandom();
            if (!this.removed) {
                this.setSprite(sprite.get(rs));
            }
        }

        public void tick() {
            this.xo = this.x;
            this.yo = this.y;
            this.zo = this.z;
            if(xd == 0 && yd == 0 && zd == 0){
                this.remove();
                return;
            }
            if (this.age++ < this.lifetime) {
                this.xd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
                this.zd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
                this.yd -= (double)this.gravity;
                this.move(this.xd, this.yd, this.zd);
                if (this.age >= this.lifetime - 40) {
                    this.xd += 0.001f;
                    this.yd += 0.001f;
                    this.zd += 0.001f;
                }
            }
            else {
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
    public static class BallPitParticleFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;
        public BallPitParticleFactory(SpriteSet spriteSet){
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel lvl,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new BallPitParticle(lvl,x,y,z,this.spriteSet,xSpeed,ySpeed,zSpeed);
        }
    }
}
