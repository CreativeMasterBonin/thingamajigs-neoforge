package net.rk.thingamajigs.xtras.particletypes;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.rk.thingamajigs.xtras.TColors;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

public class IcyAirParticleType extends SimpleParticleType {
    public IcyAirParticleType(boolean overrideLimiter) {
        super(overrideLimiter);
    }

    @OnlyIn(Dist.CLIENT)
    public static class IcyAir extends TextureSheetParticle {
        public float alpha2 = 0.75f;

        public IcyAir(ClientLevel cl, double x, double y, double z, SpriteSet ss, double xs, double ys, double zs) {
            super(cl, x, y, z, xs, ys, zs);
            this.scale(1.25F);
            this.friction = 1.0f;
            this.xd = xs;
            this.yd = ys + (double)(this.random.nextFloat() / 500.0f);
            this.zd = zs;

            this.lifetime = 52;
            this.setSpriteFromAge(ss);
            this.gravity = 0.01f;

            this.rCol = 1f;
            this.gCol = 1f;
            this.bCol = 1f;
            this.alpha = alpha2;
        }

        @Override
        public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {
            Quaternionf quaternionf = new Quaternionf();
            Quaternionf quaternionf2 = new Quaternionf();
            Quaternionf quaternionf3 = new Quaternionf();
            Quaternionf quaternionf4 = new Quaternionf();
            Quaternionf billboardqua = new Quaternionf();

            this.getFacingCameraMode().setRotation(billboardqua, renderInfo, partialTicks);
            billboardqua.scale(0.75f);

            quaternionf.rotateY(1.55f);
            quaternionf2.rotateY(-1.55f);
            quaternionf3.rotateY(3.10f);
            quaternionf4.rotateY(0.00f);

            this.renderRotatedQuad(buffer, renderInfo, quaternionf, partialTicks);
            this.renderRotatedQuad(buffer, renderInfo, quaternionf2, partialTicks);
            this.renderRotatedQuad(buffer, renderInfo, quaternionf3, partialTicks);
            this.renderRotatedQuad(buffer, renderInfo, quaternionf4, partialTicks);
            this.renderRotatedQuad(buffer, renderInfo, billboardqua, partialTicks);
        }

        public void tick() {
            this.xo = this.x;
            this.yo = this.y;
            this.zo = this.z;
            if(xd == 0 && yd == 0 && zd == 0){
                spawnRemovalParticle();
                this.remove();
                return;
            }
            if(this.age++ < this.lifetime && !(this.alpha <= 0.0F)){
                this.xd += this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1);
                this.zd += this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1);
                this.yd -= this.gravity;
                this.move(this.xd, this.yd, this.zd);
                if(this.age >= this.lifetime - 40 && this.alpha > 0.01F){
                    this.alpha -= 0.01F;
                    this.xd -= 0.001f;
                    this.yd -= 0.001f;
                    this.zd -= 0.001f;
                }
                if(this.rCol > 0.0f){
                    this.rCol -= 0.01f;
                    this.gCol -= 0.01f;
                    this.bCol -= 0.01f;
                }
            }
            else{
                spawnRemovalParticle();
                this.remove();
                return;
            }
        }

        public void spawnRemovalParticle(){
            this.level.addParticle(ParticleTypes.SNOWFLAKE,this.x,this.y,this.z,0,0,0);
            if(this.level.random.nextInt() * 17 == 5){
                this.level.playLocalSound(this.x,this.y,this.z,
                        SoundEvents.POWDER_SNOW_HIT, SoundSource.BLOCKS,0.2f,
                        0.95f * level.random.nextFloat() * 0.57f,true);
            }
        }

        @Override
        public ParticleRenderType getRenderType() {
            return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class IcyAirParticleFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;
        public IcyAirParticleFactory(SpriteSet spriteSet){
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel lvl,
                                       double x, double y, double z,
                                       double xSpeed, double ySpeed, double zSpeed) {
            return new IcyAir(lvl,x,y,z,this.spriteSet,xSpeed,ySpeed,zSpeed);
        }
    }
}
