package net.rk.thingamajigs.render.model;

import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.QuadCollection;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;

public class TStandaloneModelKeys {
    public static StandaloneModelKey<QuadCollection> UHD_TV_MODEL_KEY = new StandaloneModelKey<>(
            new ModelDebugName() {
                @Override
                public String debugName() {
                    return "uhd_tv";
                }
            }
    );
    // tube man parts
    public static StandaloneModelKey<QuadCollection> TUBE_MAN_BASE_MODEL_KEY = new StandaloneModelKey<>(
            new ModelDebugName() {
                @Override
                public String debugName() {
                    return "tube_man_base";
                }
            }
    );
    public static StandaloneModelKey<QuadCollection> TUBE_MAN_BASE_COMPRESSED_MODEL_KEY = new StandaloneModelKey<>(
            new ModelDebugName() {
                @Override
                public String debugName() {
                    return "tube_man_base_compressed";
                }
            }
    );
    public static StandaloneModelKey<QuadCollection> TUBE_MAN_BODY_MODEL_KEY = new StandaloneModelKey<>(
            new ModelDebugName() {
                @Override
                public String debugName() {
                    return "tube_man_body_section";
                }
            }
    );
    public static StandaloneModelKey<QuadCollection> TUBE_MAN_HEAD_SECTION_MODEL_KEY = new StandaloneModelKey<>(
            new ModelDebugName() {
                @Override
                public String debugName() {
                    return "tube_man_head_section";
                }
            }
    );
}
