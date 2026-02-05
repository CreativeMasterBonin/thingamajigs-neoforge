package net.rk.thingamajigs.xtras.particletypes;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class ChimneySmokeParticleType extends SimpleParticleType {
    public ChimneySmokeParticleType(boolean overrideLimiter) {
        super(overrideLimiter);
    }

    @OnlyIn(Dist.CLIENT)
    public static class ChimneySmoke extends TextureSheetParticle{
        public float alpha2 = 0.75f;

        public ChimneySmoke(ClientLevel cl, double x, double y, double z, double xs, double ys, double zs) {
            super(cl, x, y, z, xs, ys, zs);
            this.scale(2.0F);
            this.friction = 1.0f;
            this.xd = xs;
            this.yd = ys + (double)(this.random.nextFloat() / 500.0f);
            this.zd = zs;

            this.lifetime = 64;
            this.gravity = 3.0E-6F;

            this.rCol = 1f;
            this.gCol = 1f;
            this.bCol = 1f;
            this.alpha = alpha2;
        }

        public ChimneySmoke(ClientLevel cl, double x, double y, double z, SpriteSet ss, double xs, double ys, double zs) {
            super(cl, x, y, z, xs, ys, zs);
            this.scale(2.0F);
            this.friction = 1.0f;
            this.xd = xs;
            this.yd = ys + (double)(this.random.nextFloat() / 500.0f);
            this.zd = zs;

            this.lifetime = 64;
            this.setSpriteFromAge(ss);
            this.gravity = 3.0E-6F;

            this.rCol = 1f;
            this.gCol = 1f;
            this.bCol = 1f;
            this.alpha = alpha2;
        }

        public void tick() {
            this.xo = this.x;
            this.yo = this.y;
            this.zo = this.z;
            if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
                this.xd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
                this.zd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
                this.yd -= (double)this.gravity;
                this.move(this.xd, this.yd, this.zd);
                if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                    this.alpha -= 0.015F;
                    this.xd += 0.001f;
                    this.yd += 0.001f;
                    this.zd += 0.001f;
                }

            }
            else {
                this.remove();
            }
        }

        @Override
        public ParticleRenderType getRenderType() {
            return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class ChimneySmokeParticleFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;
        public ChimneySmokeParticleFactory(SpriteSet spriteSet){
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel lvl,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new ChimneySmoke(lvl,x,y,z,this.spriteSet,xSpeed,ySpeed,zSpeed);
        }
    }
}
