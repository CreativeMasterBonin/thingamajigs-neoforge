package net.rk.thingamajigs.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.custom.*;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.world.TTreeGrower;
import net.rk.thingamajigs.xtras.TSoundEvent;
import net.rk.thingamajigs.xtras.TSoundType;
import net.rk.thingamajigs.xtras.TWeatheringCopperOther;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class TBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Thingamajigs.MODID);

    public static final DeferredBlock<Block> NOT_QUITE = register("not_quite",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
                    .strength(1f,5f).noOcclusion()
                    .sound(SoundType.METAL).mapColor(MapColor.COLOR_BLUE)));
    public static final DeferredBlock<Block> BASIC_BATHROOM_TILE = register("basic_bathroom_tile",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1f).requiresCorrectToolForDrops().sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> MINIGOLF_GRASS_BLOCK = register("minigolf_grass_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).strength(1.1f).sound(SoundType.GRASS)));
    public static final DeferredBlock<Block> MINIGOLF_HOLE = register("minigolf_hole",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).strength(1.1f).sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> FAN_BLOCK_ULTRASONIC = register("fan_block_ultrasonic",
            () -> new FanBlock(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> FAN_BLOCK_FAST = register("fan_block_fast",
            () -> new FanBlock(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> FAN_BLOCK = register("fan_block",
            () -> new FanBlock(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> FAN_BLOCK_OFF = register("fan_block_off",
            () -> new FanBlock(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<Block> TECHNO_PILLAR = register("techno_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(1.2f,24F)
                    .requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)
                    .emissiveRendering(TBlocks::always)));
    public static final DeferredBlock<Block> TECHNO_CORE = register("techno_core",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f,32F).requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)
                    .emissiveRendering(TBlocks::always)));
    public static final DeferredBlock<Block> NEON_BLOCK = register("neon_block",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel(customLitBlockEmission(15))));
    public static final DeferredBlock<Block> ALT_NEON_BLOCK = register("alternative_neon_block",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops().sound(SoundType.METAL).lightLevel(customLitBlockEmission(15))));
    public static final DeferredBlock<Block> OLD_TEAL_WOOL = register("old_teal_wool",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> LOVE_SEAT_WOOL = register("love_seat_wool",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> OAK_LANE = register("oak_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().ignitedByLava()));
    public static final DeferredBlock<Block> GRATE = register("grate",
            () -> new Grate(BlockBehaviour.Properties.of().isRedstoneConductor(TBlocks::never).isSuffocating(TBlocks::never).isViewBlocking(TBlocks::never)));
    public static final DeferredBlock<Block> BRICK_SIDEWALK = register("brick_sidewalk",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(1f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BRICK_SIDEWALK_HB = register("brick_sidewalk_hb",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(1f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> STORE_FLOORING = register("store_flooring",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1f).requiresCorrectToolForDrops().sound(SoundType.STONE)));


    // torches
    public static final DeferredBlock<Block> GROUND_CLEAR_BULB = registerBlockWithoutItem("standing_clear_bulb",
            () -> new NoParticlesTorchBlock(ParticleTypes.FLAME,BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel(s -> 12)){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.CLEAR_BULB_ITEM.asItem().getDefaultInstance();
                }
            });
    public static final DeferredBlock<Block> WALL_CLEAR_BULB = registerBlockWithoutItem("wall_clear_bulb",
            () -> new NoParticlesWallTorchBlock(ParticleTypes.FLAME,BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel(s -> 12))
            {
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.CLEAR_BULB_ITEM.asItem().getDefaultInstance();
                }
            });

    public static final DeferredBlock<Block> GROUND_FULL_BULB = registerBlockWithoutItem("standing_full_bulb",
            () -> new NoParticlesTorchBlock(ParticleTypes.FLAME,BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel(s -> 12))
            {
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.FULL_BULB_ITEM.asItem().getDefaultInstance();
                }
            });
    public static final DeferredBlock<Block> WALL_FULL_BULB = registerBlockWithoutItem("wall_full_bulb",
            () -> new NoParticlesWallTorchBlock(ParticleTypes.FLAME,BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel(s -> 12))
            {
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.FULL_BULB_ITEM.asItem().getDefaultInstance();
                }
            });

    public static final DeferredBlock<Block> GROUND_CLEAR_LANTERN = registerBlockWithoutItem("standing_clear_lantern",
            () -> new TorchBlock(ParticleTypes.FLAME,BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel(s -> 12))
            {
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.CLEAR_LANTERN_ITEM.asItem().getDefaultInstance();
                }
            });
    public static final DeferredBlock<Block> WALL_CLEAR_LANTERN = registerBlockWithoutItem("wall_clear_lantern",
            () -> new WallTorchBlock(ParticleTypes.FLAME,BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel(s -> 12))
            {
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.CLEAR_LANTERN_ITEM.asItem().getDefaultInstance();
                }
            });

    public static final DeferredBlock<Block> GROUND_FULL_LANTERN = registerBlockWithoutItem("standing_full_lantern",
            () -> new NoParticlesTorchBlock(ParticleTypes.FLAME,BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel(s -> 12))
            {
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.FULL_LANTERN_ITEM.asItem().getDefaultInstance();
                }
            });
    public static final DeferredBlock<Block> WALL_FULL_LANTERN = registerBlockWithoutItem("wall_full_lantern",
            () -> new NoParticlesWallTorchBlock(ParticleTypes.FLAME,BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel(s -> 12))
            {
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.FULL_LANTERN_ITEM.asItem().getDefaultInstance();
                }
            });


    public static final DeferredBlock<Block> TV = register("tv",
            () -> new TV(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(2f).sound(SoundType.WOOD).noOcclusion()));
    public static final DeferredBlock<Block> BIG_TV = register("big_tv",
            () -> new BigTV(BlockBehaviour.Properties.of()
                    .strength(2f).sound(SoundType.LANTERN).noOcclusion()));
    public static final DeferredBlock<Block> AIR_CONDITIONER = register("air_conditioner",
            () -> new AirConditioner(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> VHS_PLAYER = register("vhs_player",
            () -> new VhsPlayer(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> DVD_PLAYER = register("dvd_player",
            () -> new DvdPlayer(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> BLUEMAN_CONSOLE = register("blueman_console",
            () -> new BluemanConsole(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> WHITE_FAN = register("white_fan",
            () -> new StandingFanBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> GRAY_FAN = register("gray_fan",
            () -> new StandingFanBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> BLACK_FAN = register("black_fan",
            () -> new StandingFanBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> THEATER_SEAT = register("theater_seat",
            () -> new TheaterSeat(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> THEATER_SEAT_CONTINUOUS = register("theater_seat_cont",
            () -> new TheaterSeat(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> POPCORN_MACHINE = register("popcorn_machine",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).lightLevel(s -> 5)));
    public static final DeferredBlock<Block> TICKET_TELLER_WINDOW = register("ticket_teller_window",
            () -> new TicketTellerWindowBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> VELVET_ROPE_FENCE = register("velvet_rope_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .sound(SoundType.WOOL).strength(1.1F,2.5F)));

    // General Purpose Decorative Blocks
    public static final DeferredBlock<Block> REFRESHMENT_MACHINE = register("refreshment_machine",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).lightLevel(s -> 7)));
    public static final DeferredBlock<Block> BLUEYBOX = register("blueybox",
            () -> new BlueyBox(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).lightLevel(s -> 12)));
    public static final DeferredBlock<Block> BLUE_SODA_MACHINE = register("blue_soda_machine",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).lightLevel(s -> 12)));
    public static final DeferredBlock<Block> RED_SODA_MACHINE = register("red_soda_machine",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).lightLevel(s -> 12)));
    public static final DeferredBlock<Block> CASH_REGISTER = register("cash_register",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).lightLevel(s -> 5)){
                public static final VoxelShape NORTH = Stream.of(
                        Block.box(4, 1, 0.97, 12, 2, 0.97),
                        Block.box(3, 18, 10, 13, 25, 17),
                        Block.box(1, 0, 1, 15, 14, 15),
                        Block.box(7, 14, 13, 9, 18, 15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape EAST = Stream.of(
                        Block.box(15.030000000000001, 1, 4, 15.030000000000001, 2, 12),
                        Block.box(-1, 18, 3, 6, 25, 13),
                        Block.box(1, 0, 1, 15, 14, 15),
                        Block.box(1, 14, 7, 3, 18, 9)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape SOUTH = Stream.of(
                        Block.box(4, 1, 15.030000000000001, 12, 2, 15.030000000000001),
                        Block.box(3, 18, -1, 13, 25, 6),
                        Block.box(1, 0, 1, 15, 14, 15),
                        Block.box(7, 14, 1, 9, 18, 3)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape WEST = Stream.of(
                        Block.box(0.9699999999999989, 1, 4, 0.9699999999999989, 2, 12),
                        Block.box(10, 18, 3, 17, 25, 13),
                        Block.box(1, 0, 1, 15, 14, 15),
                        Block.box(13, 14, 7, 15, 18, 9)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                protected VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch (bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });
    public static final DeferredBlock<Block> BLUE_VENDING_MACHINE = register("blue_vending_machine",
            () -> new VendingMachine(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).lightLevel(s -> 12)));
    public static final DeferredBlock<Block> RED_VENDING_MACHINE = register("red_vending_machine",
            () -> new VendingMachine(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).lightLevel(s -> 12)));
    public static final DeferredBlock<Block> COFFEE_MACHINE = register("coffee_machine",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> PAYPHONE = register("payphone",
            () -> new Payphone(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> PAYPHONE_SEETHROUGH = register("payphone_seethrough",
            () -> new Payphone(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> GAS_PUMP = register("gas_pump",
            () -> new GasPump(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> GAS_CAN = register("gas_can",
            () -> new GasCan(BlockBehaviour.Properties.of().sound(SoundType.CANDLE)));
    public static final DeferredBlock<Block> STORE_SHELF = register("store_shelf",
            () -> new StoreShelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> STORE_FREEZER = register("store_freezer",
            () -> new StoreFreezer(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> FRIER = register("frier",
            () -> new ToggledStateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BLACK_TELEPHONE = register("black_telephone",
            () -> new OldTelephone(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> WHITE_TELEPHONE = register("white_telephone",
            () -> new OldTelephone(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> STORE_NUMBER_SIGN = register("store_number_sign",
            () -> new StoreNumberSign(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).lightLevel(s -> 15)));
    public static final DeferredBlock<Block> FREEZER = register("freezer",
            () -> new Freezer(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> FRENCH_PRESS = register("french_press",
            () -> new FrenchPress(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> FRIDGE = register("fridge",
            () -> new Fridge(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> GRAPHICS_CARD = register("graphics_card",
            () -> new GraphicsCard(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> MOBILE_PHONE = register("mobile_phone",
            () -> new MobilePhone(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> HARD_DRIVE = register("hard_drive",
            () -> new GraphicsCard(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> DRYER = register("dryer",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> WASHER = register("washer",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> HOTTUB = register("hottub",
            () -> new Hottub(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SATELLITE_DISH = register("satellite_dish",
            () -> new Satellite(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ANTENNA = register("antenna",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> BLUEYTOSH_LAPTOP = register("blueytosh_laptop",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).noCollission()));
    public static final DeferredBlock<Block> BLUEYTOSH_LAPTOP_OLD = register("blueytosh_laptop_old",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).noCollission()));
    public static final DeferredBlock<Block> BLUEYDOWS_LAPTOP = register("blueydows_laptop",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).noCollission()));
    public static final DeferredBlock<Block> TOASTER = register("toaster",
            () -> new Toaster(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> MICROWAVE = register("microwave",
            () -> new Microwave(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> WHEELBARROW = register("wheelbarrow",
            () -> new Wheelbarrow(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> STOVE_HOOD = register("stove_hood",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).lightLevel(s -> 5).noCollission()));
    public static final DeferredBlock<Block> STOVE = register("stove",
            () -> new Stove(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> SOLAR_PANEL = register("solar_panel",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> STORE_STAND = register("store_stand",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> OLD_FLAT_COMPUTER = register("old_flat_computer",
            () -> new OldFlatComputer(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SHOPPING_CART_MOVER = register("shopping_cart_mover",
            () -> new Wheelbarrow(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SHOPPING_CART = register("shopping_cart",
            () -> new Wheelbarrow(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> MINIGOLF_FLAG = register("minigolf_flag",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).noCollission()));
    public static final DeferredBlock<Block> OVEN = register("oven",
            () -> new Oven(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ICECREAM_MACHINE = register("icecream_machine",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> INTERNET_MODEM = register("internet_modem",
            () -> new InternetRouter(BlockBehaviour.Properties.of().lightLevel(s -> 5)));
    public static final DeferredBlock<Block> INTERNET_ROUTER = register("internet_router",
            () -> new InternetRouter(BlockBehaviour.Properties.of().lightLevel(s -> 5)));
    public static final DeferredBlock<Block> NEWER_INTERNET_ROUTER = register("internet_router_newer",
            () -> new InternetRouter(BlockBehaviour.Properties.of().lightLevel(s -> 5)));
    public static final DeferredBlock<Block> WIFI_ROUTER = register("wifi_router",
            () -> new OldFlatComputer(BlockBehaviour.Properties.of().lightLevel(s -> 5)));
    public static final DeferredBlock<Block> OPEN_SIGN = register("open_sign",
            () -> new OpenSign(BlockBehaviour.Properties.of()
                    .lightLevel(openSignLitEmission(15))));
    public static final DeferredBlock<Block> RECYCLE_BIN = register("recycle_bin",
            () -> new RecycleBin(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(1F,1F)));
    public static final DeferredBlock<Block> SERVER_RACK = register("server_rack",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> MODERN_PC_MONITOR = register("pc_screen",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> OLD_PC_MONITOR = register("pc_monitor",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> OLD_PC = register("old_pc",
            () -> new OldPC(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> PAPER_TOWEL = register("paper_towel",
            () -> new HazardSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> AISLE_SIGN = register("aisle_sign",
            () -> new AisleSign(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> TRAFFIC_CONTROL_BOX = register("traffic_control_box",
            () -> new TrafficControlBox(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> CELL_MICROWAVE_TRANSMITTER = register("microwave_transmitter",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> CELL_TRANSMITTER = register("cell_transmitter",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> CELL_MULTI_TRANSMITTER = register("cell_multi_transmitter",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> CELL_MULTI_ANGLED_TRANSMITTER = register("cell_multi_angled_transmitter",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> OLD_MICROWAVE_TRANSMITTER = register("old_microwave_transmitter",
            () -> new MicrowaveTransmitter(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> STONE_TABLE = register("stone_table",
            () -> new ConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GOLD_TABLE = register("gold_table",
            () -> new ConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> SCULK_TABLE = register("sculk_table",
            () -> new ConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.SCULK_CATALYST)));
    public static final DeferredBlock<Block> NETHER_BRICK_TABLE = register("nether_brick_table",
            () -> new ConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)));
    public static final DeferredBlock<Block> PRISMARINE_TABLE = register("prismarine_table",
            () -> new ConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE)));
    public static final DeferredBlock<Block> PURPUR_TABLE = register("purpur_table",
            () -> new ConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK)));
    public static final DeferredBlock<Block> QUARTZ_TABLE = register("quartz_table",
            () -> new ConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final DeferredBlock<Block> DUCK_STATUE = register("duck_statue",
            () -> new Podium(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    // Bathroom/Restroom Stuff
    public static final DeferredBlock<Block> TOILET = register("toilet",
            () -> new Toilet(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final DeferredBlock<Block> SMALL_SINK = register("small_sink",
            () -> new SmallSink(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final DeferredBlock<Block> FANCY_SINK = register("fancy_sink",
            () -> new FancySink(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final DeferredBlock<Block> SHOWER_HANDLES = register("shower_handles",
            () -> new ShowerHandles(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> SHOWER_HEAD = register("shower_head",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).noCollission()));
    public static final DeferredBlock<Block> TOILET_PAPER = register("toilet_paper",
            () -> new HazardSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    // Misc
    public static final DeferredBlock<Block> DOG_HOUSE = register("dog_house",
            () -> new DogHouse(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> LOVE_SEAT = register("love_seat",
            () -> new LoveSeat(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> LOVE_COUCH = register("love_couch",
            () -> new LoveCouch(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> BLUEMAN_STATUE = register("blueman_statue",
            () -> new BluemanStatue(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> MRPUPPY = register("mrpuppy",
            () -> new MrPuppy(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> HOME_BREAKER = register("home_breaker",
            () -> new HomeBreaker(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> PRINTER = register("printer",
            () -> new Printer(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> PROJECTOR = register("projector",
            () -> new Projector(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> MINI_FRIDGE = register("mini_fridge",
            () -> new ToggledStateBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CRIB = register("crib",
            () -> new Crib(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> THERMOMETER = register("thermometer",
            () -> new Thermometer(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> CHEMICAL_TUBE = register("chemical_tube",
            () -> new ChemicalTube(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).strength(0.5F,1F).sound(SoundType.GLASS).noOcclusion()));
    public static final DeferredBlock<Block> WATER_FOUNTAIN = register("water_fountain",
            () -> new WaterFountain(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TOASTER_OVEN = register("toaster_oven",
            () -> new ToasterOven(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> DISHWASHER_WALL = register("dishwasher_wall",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1F,10F)));
    public static final DeferredBlock<Block> OFFICE_PHONE = register("office_phone",
            () -> new OfficePhone(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,2F)));
    public static final DeferredBlock<Block> PORTABLE_DISH_WASHER = register("portable_dish_washer",
            () -> new PortableDishwasher(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(0.95F,5F)));
    public static final DeferredBlock<Block> STANDING_VACUUM = register("vacuum_standing",
            () -> new StandingVacuum(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(0.5F,1F)));
    public static final DeferredBlock<Block> SHOP_VACUUM = register("shop_vac",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(1F,1F)));
    public static final DeferredBlock<Block> BLENDER = register("blender",
            () -> new Blender(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(1F,1F)));
    public static final DeferredBlock<Block> FOOD_PROCESSOR = register("food_processor",
            () -> new Blender(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(1F,1F)));
    public static final DeferredBlock<Block> INSTANT_POT = register("instant_pot",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1.1F,2.5F)));
    public static final DeferredBlock<Block> RICE_COOKER = register("rice_cooker",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1.05F,1.5F)));
    public static final DeferredBlock<Block> SLOW_COOKER = register("slow_cooker",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,1.25F)));
    public static final DeferredBlock<Block> STAND_MIXER = register("stand_mixer",
            () -> new StandMixer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> JUICER = register("juicer",
            () -> new StandMixer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> WAFFLE_IRON = register("waffle_iron",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,1F).noCollission()));
    public static final DeferredBlock<Block> BREAD_MACHINE = register("bread_machine",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,1F)));
    public static final DeferredBlock<Block> ICE_CREAM_MAKER = register("ice_cream_maker",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,1F)));
    public static final DeferredBlock<Block> YOGURT_MAKER = register("yogurt_maker",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,1F)));
    public static final DeferredBlock<Block> COFFEE_GRINDER = register("coffee_grinder",
            () -> new Blender(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,1F)));
    public static final DeferredBlock<Block> PANINI_MAKER = register("panini_maker",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,1F)));
    public static final DeferredBlock<Block> FOOD_DEHYDRATOR = register("food_dehydrator",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,1F)));
    public static final DeferredBlock<Block> KITCHEN_SINK = register("kitchen_sink",
            () -> new KitchenSink(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> GARDEN_GNOME = register("garden_gnome",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).strength(1.25F)));
    public static final DeferredBlock<Block> WATER_SOFTENER = register("water_softener",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1F,2F)));
    public static final DeferredBlock<Block> SALT_TANK = register("salt_tank",
            () -> new SaltTank(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1F,2F)));
    public static final DeferredBlock<Block> SEWING_MACHINE = register("sewing_machine",
            () -> new StandMixer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> AUDIO_CONTROLLER = register("audio_controller",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(1F,2.25F)));
    public static final DeferredBlock<Block> GENERATOR = register("generator",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1.1F,5.25F)));
    public static final DeferredBlock<Block> IRONING_TABLE = register("ironing_table",
            () -> new OperationTable(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F)));
    public static final DeferredBlock<Block> STEAM_CLEANER = register("steam_cleaner",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F)));
    public static final DeferredBlock<Block> HUMIDIFIER = register("humidifier",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.DEEPSLATE_TILES).strength(1F)));
    public static final DeferredBlock<Block> DEHUMIDIFIER = register("dehumidifier",
            () -> new Dehumidifier(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> AIR_PURIFIER = register("air_purifier",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.DEEPSLATE_TILES).strength(1F)));
    public static final DeferredBlock<Block> SPACE_HEATER = register("space_heater",
            () -> new SpaceHeater(BlockBehaviour.Properties.of().sound(SoundType.DEEPSLATE_TILES).strength(1F).lightLevel(s -> 7)));
    public static final DeferredBlock<Block> CEILING_FAN = register("ceiling_fan",
            () -> new CeilingFan(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.DEEPSLATE).strength(1F,2F)));
    public static final DeferredBlock<Block> SMOKER_GRILL = register("smoker_grill",
            () -> new SmokerGrill(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.DEEPSLATE_BRICKS).strength(1.25F,2F).lightLevel(enabledLitBlockEmission(10))));
    public static final DeferredBlock<Block> COTTON_CANDY_MAKER = register("cotton_candy_maker",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F)));
    public static final DeferredBlock<Block> CARNIVAL_AWNING = register("carnival_awning",
            () -> new CarnivalAwning(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> PORTA_POTTY = register("porta_potty",
            () -> new PortaPotty(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> WARDEN_TROPHY = register("warden_trophy",
            () -> new WardenTrophy(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    // Finale Update #1 Thingamajigs Features
    public static final DeferredBlock<Block> FOOSBALL_TABLE = register("foosball_table",
            () -> new AirConditioner(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> CLAW_MACHINE = register("claw_machine",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> GUMBALL_MACHINE = register("gumball_machine",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> HAMMER_MACHINE = register("hammer_machine",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> WACK_MACHINE = register("wack_machine",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> AIR_HOCKEY_TABLE = register("air_hockey_table",
            () -> new AirConditioner(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> BUTTER_CHURNER = register("butter_churner",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> FIRE_ESCAPE_LADDER = register("fire_escape_ladder",
            () -> new FireEscapeLadder(BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<Block> CATWALK_CENTER = register("catwalk_center",
            () -> new CatWalkCenter(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CATWALK = register("catwalk",
            () -> new CatWalk(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> MYSTERIOUS_ONE_COUCH = register("mysterious_couch",
            () -> new LoveCouch(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> GENERAL_DIGITAL_PHONE = register("general_digital_phone",
            () -> new OfficePhone(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)));
    public static final DeferredBlock<Block> ZOMBIE_PLUSHIE = register("zombie_plushie",
            () -> new ReindeerPlush(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).sound(SoundType.WOOL)){
                public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                    Direction direction = pState.getValue(FACING);
                    switch(direction){
                        case NORTH: return NS_ALT;
                        case SOUTH: return SS_ALT;
                        case EAST: return ES_ALT;
                        case WEST: return WS_ALT;
                        default: return Shapes.block();
                    }
                }
            });
    public static final DeferredBlock<Block> STEVE_PLUSHY = register("steve_plushy",
            () -> new ReindeerPlush(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).sound(SoundType.WOOL)){
                public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                    Direction direction = pState.getValue(FACING);
                    switch(direction){
                        case NORTH: return NS_ALT;
                        case SOUTH: return SS_ALT;
                        case EAST: return ES_ALT;
                        case WEST: return WS_ALT;
                        default: return Shapes.block();
                    }
                }
            });
    public static final DeferredBlock<Block> VHS_COLLECTION = register("vhs_collection",
            () -> new VHSCollection(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(1F,5F)));


    public static final DeferredBlock<Block> DVD_COLLECTION = register("dvd_collection",
            () -> new VHSCollection(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(1F,5F)));
    public static final DeferredBlock<Block> SHOPPING_BASKET = register("shopping_basket",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1F)));
    public static final DeferredBlock<Block> SHOPPING_BASKET_PILE = register("shopping_basket_pile",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1.25F)));
    public static final DeferredBlock<Block> BLUEYSNAP_CONSOLE = register("blueysnap_console",
            () -> new BlueySnapBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1F)));
    public static final DeferredBlock<Block> BLUEYSNAP_BASE = register("blueysnap_base",
            () -> new BlueySnapBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1F)));
    public static final DeferredBlock<Block> BEAKER = register("beaker",
            () -> new SmallGlassStorageThing(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final DeferredBlock<Block> FLASK = register("flask",
            () -> new SmallGlassStorageThing(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
    public static final DeferredBlock<Block> MICROSCOPE = register("microscope",
            () -> new StandMixer(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1F)));
    public static final DeferredBlock<Block> OLD_WOODEN_PHONE = register("old_wooden_phone",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(1F,5F)));
    public static final DeferredBlock<Block> BATHTUB_NOZZLE = register("bathtub_nozzle",
            () -> new BathTubNozzle(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> GARDEN_HOSE = register("garden_hose",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.CALCITE).noCollission()));
    public static final DeferredBlock<Block> WOOD_DUCK = register("wood_duck",
            () -> new ReindeerPlush(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission()){
                public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                    Direction direction = pState.getValue(FACING);
                    switch(direction){
                        case NORTH: return NS_ALT;
                        case SOUTH: return SS_ALT;
                        case EAST: return ES_ALT;
                        case WEST: return WS_ALT;
                        default: return Shapes.block();
                    }
                }
            });
    public static final DeferredBlock<Block> WOOD_CAR = register("wood_car",
            () -> new ReindeerPlush(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission()){
                public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                    Direction direction = pState.getValue(FACING);
                    switch(direction){
                        case NORTH: return NS_ALT;
                        case SOUTH: return SS_ALT;
                        case EAST: return ES_ALT;
                        case WEST: return WS_ALT;
                        default: return Shapes.block();
                    }
                }
            });
    public static final DeferredBlock<Block> PHONE_SWITCHER = register("phone_switcher",
            () -> new PhoneSwitcher(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> HOTDOG_ROTATOR = register("hotdog_rotator",
            () -> new HotdogRotator(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> LAWN_MOWER = register("lawn_mower",
            () -> new Wheelbarrow(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> DIVING_BOARD = register("diving_board",
            () -> new DivingBoard(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> WATER_SLIDE = register("water_slide",
            () -> new WaterSlide(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SLUSHY_MACHINE = register("slushy_machine",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).strength(1F,2F)));
    public static final DeferredBlock<Block> TOY_BOX = register("toy_box",
            () -> new StorageDecoration(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD),"blockEntities.storage_decoration.toy_box.name"){
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH,SOUTH -> {
                            return AirConditioner.NORTHSOUTH;
                        }
                        case EAST,WEST -> {
                            return AirConditioner.EASTWEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> BABY_CARRIAGE = register("baby_carriage",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> CONVENIENCE_SHELF = register("convenience_shelf",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.DEEPSLATE_BRICKS)));
    public static final DeferredBlock<Block> CREEPER_PLUSHY = register("creeper_plushy",
            () -> new ReindeerPlush(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).sound(SoundType.WOOL).noCollission()){
                public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                    Direction direction = pState.getValue(FACING);
                    switch(direction){
                        case NORTH: return NS_ALT;
                        case SOUTH: return SS_ALT;
                        case EAST: return ES_ALT;
                        case WEST: return WS_ALT;
                        default: return Shapes.block();
                    }
                }
            });
    public static final DeferredBlock<Block> FEATURED_CORDLESS_PHONE = register("featured_cordless_phone",
            () -> new CordlessPhoneBase(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> SMARTPHONE = register("smartphone",
            () -> new MobilePhone(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.CALCITE)));
    public static final DeferredBlock<Block> POOPSHELF = register("poopshelf",
            () -> new PoopBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> FULL_POOP_BLOCK = register("full_poop_block",
            () -> new FullPoopBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK)));
    public static final DeferredBlock<Block> FIRE_DETECTOR = register("fire_detector",
            () -> new FireDetector(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> FIRE_EXTINGUISHER = register("fire_extinguisher",
            () -> new FireExtinguisher(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> HISTORIAN_BOOKSHELF = register("historian_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> CHECKBOARD_WOOL = register("checkerboard_wool",
            () -> new CheckerboardWool(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).mapColor(MapColor.TERRACOTTA_WHITE)));
    public static final DeferredBlock<Block> STONE_PILLAR = register("stone_pillar",
            () -> new ConnectingVerticalPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> STONE_BRICK_PILLAR = register("stone_brick_pillar",
            () -> new ConnectingVerticalPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> CHISELED_STONE_BRICK_PILLAR = register("chiseled_stone_brick_pillar",
            () -> new ConnectingVerticalPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> CRYSTALINE_STONE = register("crystaline_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> INDENTED_STONE = register("indented_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> PANEL_STONE = register("panel_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));
    //
    public static final DeferredBlock<Block> PANEL_STONE_BRICKS = register("panel_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> MOSSY_PANEL_STONE_BRICKS = register("mossy_panel_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> CRACKED_PANEL_STONE_BRICKS = register("cracked_panel_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));
    public static final DeferredBlock<Block> CHISELED_PANEL_STONE_BRICKS = register("chiseled_panel_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 6.0F)));

    public static final DeferredBlock<Block> STOP_GATE = register("stop_gate",
            () -> new StopGate(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> ARROW_BOARD = register("arrow_board",
            () -> new ArrowBoard(BlockBehaviour.Properties.of().lightLevel(modeLitBlockEmission(5))));

    // HAWK Signal stuff
    public static final DeferredBlock<Block> HAWK_SIGNAL = register("hawk_signal",
            () -> new HawkSignal(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> HAWK_SIGNAL_YELLOW = registerBlockWithoutItem("hawk_signal_y",
            () -> new HawkYellow(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> HAWK_SIGNAL_FLASHING_YELLOW = registerBlockWithoutItem("hawk_signal_fy",
            () -> new HawkFlashingYellow(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> HAWK_SIGNAL_RED = registerBlockWithoutItem("hawk_signal_r",
            () -> new HawkRed(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> HAWK_SIGNAL_FLASHING_RED = registerBlockWithoutItem("hawk_signal_fr",
            () -> new HawkFlashingRed(BlockBehaviour.Properties.of()));

    // Traffic Beacons
    public static final DeferredBlock<Block> YELLOW_BEACON = register("yellow_beacon",
            () -> new TrafficBeacon(BlockBehaviour.Properties.of().lightLevel(s -> 10)));
    public static final DeferredBlock<Block> RED_BEACON = register("red_beacon",
            () -> new TrafficBeacon(BlockBehaviour.Properties.of().lightLevel(s -> 10)));
    public static final DeferredBlock<Block> ARROW_BEACON = register("arrow_beacon",
            () -> new ArrowBeacon(BlockBehaviour.Properties.of().lightLevel(s -> 10)));

    // horizontal traffic signals
    // main signal (green to red & red to green)
    public static final DeferredBlock<Block> HORIZONTAL_TRAFFIC_SIGNAL_1 = register("horizontal_traffic_signal",
            () -> new HorizontalTrafficSignal(BlockBehaviour.Properties.of().lightLevel(s -> 10)));
    // flashing red variant
    public static final DeferredBlock<Block> HORIZONTAL_TRAFFIC_SIGNAL_2 = register("horizontal_traffic_signal_fr",
            () -> new HorizontalTrafficSignal(BlockBehaviour.Properties.of().lightLevel(s -> 10)));
    // flashing yellow variant
    public static final DeferredBlock<Block> HORIZONTAL_TRAFFIC_SIGNAL_3 = register("horizontal_traffic_signal_fy",
            () -> new HorizontalTrafficSignal(BlockBehaviour.Properties.of().lightLevel(s -> 10)));

    public static final DeferredBlock<Block> FIREOUS_GLAZED_TERRACOTTA = register("fireous_glazed_terracotta",
            () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_GLAZED_TERRACOTTA)));
    public static final DeferredBlock<Block> DARK_FIREOUS_GLAZED_TERRACOTTA = register("dark_fireous_glazed_terracotta",
            () -> new GlazedTerracottaBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_GLAZED_TERRACOTTA)));
    public static final DeferredBlock<Block> SCREEN = register("screen",
            () -> new ScreenBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .sound(SoundType.SCAFFOLDING).strength(1.1F)));
    // Flowers and Pots
    public static final DeferredBlock<Block> BULBY_FLOWER = register("bulby_flower",
            () -> new FlowerBlock(MobEffects.INVISIBILITY, 5,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)
                            .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final DeferredBlock<Block> DROOPY_FLOWER = register("droopy_flower",
            () -> new DroopyFlower(MobEffects.CONFUSION,BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ORCHID)
                    .noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)));

    public static final DeferredBlock<Block> POTTED_BULBY_FLOWER = registerBlockWithoutItem("potted_bulby_flower",
            () -> new FlowerPotBlock(()->(FlowerPotBlock)Blocks.FLOWER_POT,TBlocks.BULBY_FLOWER,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).instabreak().noOcclusion()));
    public static final DeferredBlock<Block> POTTED_DROOPY_FLOWER = registerBlockWithoutItem("potted_droopy_flower",
            () -> new FlowerPotBlock(()->(FlowerPotBlock)Blocks.FLOWER_POT,TBlocks.DROOPY_FLOWER,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_BLUE_ORCHID).instabreak().noOcclusion()));

    public static final DeferredBlock<Block> LIBRARY_STOOL = register("library_stool",
            () -> new LibraryStool(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BIOHAZARD_BIN = register("biohazard_bin",
            () -> new BiohazardBin(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> RADIOACTIVE_BARREL = register("radioactive_barrel",
            () -> new RadioactiveBarrel(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TEACHING_BOARD = register("teaching_board",
            () -> new TeachingBoard(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> LOCKER = register("locker",
            () -> new Locker(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SCHOOL_DESK = register("school_desk",
            () -> new SchoolDesk(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TALL_LAMP = register("tall_lamp",
            () -> new TallLamp(BlockBehaviour.Properties.of().lightLevel(onLitBlockEmission(10))));
    public static final DeferredBlock<Block> PUNCHING_BAG = register("punching_bag",
            () -> new PunchingBag(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final DeferredBlock<Block> CLASSIC_TV = register("classic_tv",
            () -> new ClassicTV(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final DeferredBlock<Block> CROSSWALK_BUTTON = register("crosswalk_button",
            () -> new CrosswalkButton(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> VERTICAL_T_POLE = register("vertical_t_pole",
            () -> new VerticalTPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TL_CONNECTOR = register("tl_connector",
            () -> new PlusPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TRANSPARENT_OFF_FAN_BLOCK = register("transparent_off_fan_block",
            () -> new TransparentFanBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TRANSPARENT_FAN_BLOCK = register("transparent_fan_block",
            () -> new TransparentFanBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TRANSPARENT_FAST_FAN_BLOCK = register("transparent_fast_fan_block",
            () -> new TransparentFanBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> METAL_SCAFFOLDING = register("metal_scaffolding",
            () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion()));
    public static final DeferredBlock<Block> ULTRA_HD_TV = register("uhd_tv",
            () -> new UltraHDTV(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> RAILROAD_CROSSING_BLOCKER = register("rr_blocker",
            () -> new RailroadCrossingBlocker(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CHISELED_TECHNO_BLOCK = register("chiseled_techno_block",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.NETHERITE_BLOCK).strength(1F,75F).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BROWN_PATHWAY = register("brown_pathway",
            () -> new Pathway(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT).sound(SoundType.CANDLE)));
    public static final DeferredBlock<Block> FAX_MACHINE = register("fax_machine",
            () -> new FaxMachine(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> NETHER_CHISELED_BOOKSHELF = register("nether_chiseled_bookshelf",
            () -> new NetherChiseledBookshelf(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NETHER)
                    .strength(1.5F,5.5F)
                    .requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> CRYSTAL_BLOCK = register("crystal_block",
            () -> new AncientRelicCrystalBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CIRCUITS = register("circuits",
            () -> new Block(BlockBehaviour.Properties.of().strength(1F,10F)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> MYSTERIOUS_ONE_WOOL = register("mysterious_one_wool",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_WOOL)));
    public static final DeferredBlock<Block> PIZZA_BOX = register("pizza_box",
            () -> new PizzaBox(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_BLOCK)));
    public static final DeferredBlock<Block> PLATE = register("plate",
            () -> new Plate(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CUP = register("cup",
            () -> new Cup(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> DECORATIVE_PORTAL = register("decorative_portal",
            () -> new DecorativePortalBlock(BlockBehaviour.Properties.of()
                    .lightLevel(s -> 11)
                    .hasPostProcess(TBlocks::always)
                    .emissiveRendering(TBlocks::always)));
    public static final DeferredBlock<Block> COOKIE_JAR = register("cookie_jar",
            () -> new Cup(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> EATING_UTENCILS = register("eating_utencils",
            () -> new EatingUtencils(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> VERTICAL_POLE_REDSTONE = register("vertical_pole_redstone",
            () -> new VerticalPoleRedstone(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BLUEYTOSH_STUDIO = register("blueytosh_studio",
            () -> new BlueytoshStudioBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TENTH_ANNIVERSARY_CAKE = register("tenth_anniversary_cake",
            () -> new TenthAnniversaryCake(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).noOcclusion()));
    public static final DeferredBlock<Block> CHEESE_BLOCK = register("cheese",
            () -> new CheeseBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> GLOWING_CHEESE_BLOCK = register("glowing_cheese",
            () -> new GlowingCheeseBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.WOOD)));

    public static final DeferredBlock<Block> FLOWERING_LILY_PAD = register("flowering_lily_pad",
            () -> new CustomWaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD)){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.FLOWERING_LILY_PAD_ITEM.get().getDefaultInstance();
                }
            });
    public static final DeferredBlock<Block> TRIPLE_LILY_PAD = register("triple_lily_pad",
            () -> new CustomWaterlilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LILY_PAD)){
                @Override
                public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
                    return TItems.TRIPLE_LILY_PAD_ITEM.get().getDefaultInstance();
                }
            });

    public static final DeferredBlock<Block> COLORED_GLASS = register("colored_glass",
            () -> new ColoredGlass(BlockBehaviour.Properties.of()
                    .isRedstoneConductor(TBlocks::never)
                    .isViewBlocking(TBlocks::never)
                    .isSuffocating(TBlocks::never)
                    .strength(0.3F,20F)
                    .instrument(NoteBlockInstrument.HAT)
                    .sound(SoundType.GLASS)){
                @Override
                public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
                    list.add(Component.translatable("block.thingamajigs.colored_glass.desc"));
                }
            });

    // Poles
    public static final DeferredBlock<Block> STRAIGHT_POLE = register("straight_pole",
            () -> new VerticalPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> STRAIGHT_HORIZONTAL_POLE = register("straight_horizontal_pole",
            () -> new HorizontalPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> L_POLE = register("l_pole",
            () -> new LPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> AXIS_POLE = register("axis_pole",
            () -> new AxisPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> HOLDER_POLE = register("holder_pole",
            () -> new Pole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> PLUS_POLE = register("plus_pole",
            () -> new PlusPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> T_POLE = register("t_pole",
            () -> new TPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> T_POLE_B = register("t_pole_b",
            () -> new InvertTPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> T_POLE_C = register("t_pole_c",
            () -> new TPoleC(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TRI_POLE = register("tri_pole",
            () -> new LPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> L_ONLY_POLE = register("l_only_pole",
            () -> new LOnlyPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> T_HORZ_ONLY_POLE = register("t_horz_only_pole",
            () -> new THorizontalOnlyPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TRI_POLE_B = register("tri_pole_b",
            () -> new TriPoleB(BlockBehaviour.Properties.of()));

    // Light Source Poles
    public static final DeferredBlock<Block> LIGHT_POLE = register("light_pole",
            () -> new Pole(BlockBehaviour.Properties.of().lightLevel(s -> 15)));

    public static final DeferredBlock<Block> VERTICAL_AXIS_POLE = register("vertical_axis_pole",
            () -> new VerticalAxisPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> THREE_WAY_POLE = register("three_way_pole",
            () -> new ThreeWayPole(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> ALL_WAY_POLE = register("all_way_pole",
            () -> new AllWayPole(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> LAVA_LAMP = register("lava_lamp",
            () -> new LavaLamp(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> MAILBOX = register("mailbox",
            () -> new BasicMailbox(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> BLACK_MAILBOX = register("black_mailbox",
            () -> new BasicMailbox(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> GREY_MAILBOX = register("grey_mailbox",
            () -> new BasicMailbox(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.LANTERN)));

    // Gothic (alternative meaning) or Death Related Decorative Blocks
    public static final DeferredBlock<Block> CROSS_GRAVESTONE = register("cross_gravestone",
            () -> new CrossGravestone(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> STANDARD_GRAVESTONE = register("standard_gravestone",
            () -> new StandardGravestone(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> PLACARD_GRAVESTONE = register("placard_gravestone",
            () -> new PlacardGravestone(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).noCollission()));
    public static final DeferredBlock<Block> COFFIN = register("coffin",
            () -> new CoffinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).sound(SoundType.WOOD)));

    // Utility Decorative Blocks
    public static final DeferredBlock<Block> AC_DUCT = register("ac_duct",
            () -> new AcDuctStraight(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> AC_DUCT_CORNER = register("ac_duct_corner",
            () -> new AcDuctCorner(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> AC_DUCT_ALLWAY = register("ac_duct_allway",
            () -> new AcDuctAllWay(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> US_OUTLET = register("us_outlet",
            () -> new WallOutletBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> T_US_OUTLET = register("t_us_outlet",
            () -> new WallOutletBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> UNGROUNDED_US_OUTLET = register("ungrounded_us_outlet",
            () -> new WallOutletBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> USB_OUTLET = register("usb_outlet",
            () -> new WallOutletBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> INTERNET_JACK_OUTLET = register("internet_jack_outlet",
            () -> new WallOutletBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> UK_OUTLET = register("uk_outlet",
            () -> new WallOutletBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> AUSTRALIAN_OUTLET = register("australian_outlet",
            () -> new WallOutletBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));
    public static final DeferredBlock<Block> GERMAN_OUTLET = register("german_outlet",
            () -> new WallOutletBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));

    // Switches & Buttons
    public static final DeferredBlock<Block> BUTTON_SWITCH = register("button_switch",
            () -> new LeverBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)
                    .strength(1F,1F).sound(SoundType.STONE).noCollission().noOcclusion()){
                @Override
                public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {}
            });
    public static final DeferredBlock<Block> ROCKER_SWITCH = register("rocker_switch",
            () -> new LeverBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)
                    .strength(1F,1F).sound(SoundType.STONE).noCollission().noOcclusion()){
                @Override
                public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {}
            });

    public static final DeferredBlock<Block> CHAINLINK_FENCE = register("chainlink_fence",
            () -> new ChainlinkFence(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CULVERT = register("culvert",
            () -> new CulvertBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> STONE_CULVERT = register("stone_culvert",
            () -> new CulvertBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> DIRT_CULVERT = register("dirt_culvert",
            () -> new CulvertBlock(BlockBehaviour.Properties.of().sound(SoundType.GRAVEL)));
    public static final DeferredBlock<Block> SAND_CULVERT = register("sand_culvert",
            () -> new CulvertBlock(BlockBehaviour.Properties.of().sound(SoundType.SAND)));
    public static final DeferredBlock<Block> SANDSTONE_CULVERT = register("sandstone_culvert",
            () -> new CulvertBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BRICK_CULVERT = register("brick_culvert",
            () -> new CulvertBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> STONE_BRICK_CULVERT = register("stone_brick_culvert",
            () -> new CulvertBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> TERRACOTTA_CULVERT = register("terracotta_culvert",
            () -> new CulvertBlock(BlockBehaviour.Properties.of().sound(SoundType.STONE)));

    // Alarms
    public static final DeferredBlock<Block> HORN_FIRE_ALARM = register("horn_fire_alarm",
            () -> new HornFireAlarm(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BEEP_FIRE_ALARM = register("beep_fire_alarm",
            () -> new BeepingFireAlarm(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> LOUD_FIRE_ALARM = register("loud_fire_alarm",
            () -> new LoudFireAlarm(BlockBehaviour.Properties.of()));

    // Security Cameras
    public static final DeferredBlock<Block> FILM_SECURITY_CAMERA = register("film_cam",
            () -> new SecurityCameraMultidirectional(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SECURE_SECURITY_CAMERA = register("secure_cam",
            () -> new SecurityCameraMultidirectional(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BOX_SECURITY_CAMERA = register("box_cam",
            () -> new SecurityCameraQuintDirectional(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> DOME_SECURITY_CAMERA = register("dome_cam",
            () -> new DomeSecurityCamera(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).noCollission()));
    public static final DeferredBlock<Block> ROBOT_SECURITY_CAMERA = register("robot_cam",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));

    // Hazard Signs
    public static final DeferredBlock<Block> GENERAL_HAZARD_SIGN = register("general_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> FALLING_HAZARD_SIGN = register("falling_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> HARDHAT_HAZARD_SIGN = register("hardhat_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> POISON_HAZARD_SIGN = register("poison_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> DEATH_HAZARD_SIGN = register("death_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> FIRE_HAZARD_SIGN = register("fire_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> RADIATION_HAZARD_SIGN = register("radiation_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BIO_HAZARD_SIGN = register("bio_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CRYO_HAZARD_SIGN = register("cryo_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BLAST_HAZARD_SIGN = register("blast_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SHOCK_HAZARD_SIGN = register("shock_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> NOENTRY_HAZARD_SIGN = register("noentry_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> WORKERS_HAZARD_SIGN = register("workers_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> RADIOWAVES_HAZARD_SIGN = register("radiowaves_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> OXYGEN_HAZARD_SIGN = register("oxygen_hazard",
            () -> new HazardSignBlock(BlockBehaviour.Properties.of()));

    // Arcade Decorative Blocks
    public static final DeferredBlock<Block> BASKETBALL_MACHINE = register("basketball_machine",
            () -> new BasketballMachine(BlockBehaviour.Properties.of().sound(SoundType.METAL).lightLevel(s -> 12)));
    public static final DeferredBlock<Block> PINBALL_MACHINE = register("pinball_machine",
            () -> new BasketballMachine(BlockBehaviour.Properties.of().sound(SoundType.METAL).lightLevel(s -> 12)));
    public static final DeferredBlock<Block> LIGHTUP_MACHINE = register("lightup_machine",
            () -> new LightupMachine(BlockBehaviour.Properties.of().sound(SoundType.METAL).lightLevel(s -> 12)));

    // Bowling Alley Decorative Blocks
    public static final DeferredBlock<Block> PINK_BOWLING_BALL = register("pink_bowling_ball",
            () -> new BowlingBall(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> PURPLE_BOWLING_BALL = register("purple_bowling_ball",
            () -> new BowlingBall(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BROWN_BOWLING_BALL = register("brown_bowling_ball",
            () -> new BowlingBall(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> YELLOW_BOWLING_BALL = register("yellow_bowling_ball",
            () -> new BowlingBall(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BLUE_BOWLING_BALL = register("blue_bowling_ball",
            () -> new BowlingBall(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LIGHT_BLUE_BOWLING_BALL = register("light_blue_bowling_ball",
            () -> new BowlingBall(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GREEN_BOWLING_BALL = register("green_bowling_ball",
            () -> new BowlingBall(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LIME_BOWLING_BALL = register("lime_bowling_ball",
            () -> new BowlingBall(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BOWLING_FLOORING = register("bowling_flooring",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(0.5f).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> BOWLING_ALLEY_OILER = register("bowling_alley_oiler",
            () -> new BowlingAlleyOiler(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> PIN_SETTER = register("pin_setter",
            () -> new BowlingAlleyPinSetter(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BOWLING_PIN = register("bowling_pin",
            () -> new BowlingPin(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> RED_BOWLING_PIN = register("red_bowling_pin",
            () -> new BowlingPin(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> DIAMOND_BOWLING_PIN = register("diamond_bowling_pin",
            () -> new BowlingPin(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> GOLD_BOWLING_PIN = register("gold_bowling_pin",
            () -> new BowlingPin(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> BOWLING_BALL_RETRIEVER = register("bowling_ball_retriever",
            () -> new BowlingBallRetriever(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BOWLING_GAME_CONTROLLER = register("bowling_game_controller",
            () -> new BowlingBallController(BlockBehaviour.Properties.of()));

    // Christmas Decorative Blocks
    public static final DeferredBlock<Block> SANTA_STATUE = register("santa_statue",
            () -> new SantaStatue(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> SANTA_INFLATABLE = register("santa_inflatable",
            () -> new SantaStatue(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).sound(SoundType.WOOL)));
    public static final DeferredBlock<Block> SNOWMAN = register("snowman",
            () -> new Snowman(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).sound(SoundType.POWDER_SNOW)));
    public static final DeferredBlock<Block> REINDEER_PLUSHY = register("reindeer_plushy",
            () -> new ReindeerPlush(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).sound(SoundType.WOOL).noCollission()));
    public static final DeferredBlock<Block> CHRISTMAS_TREE = register("christmas_tree",
            () -> new ChristmasTree(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(1.25F,10F).sound(SoundType.AZALEA_LEAVES).noOcclusion().lightLevel(s -> 10)));
    public static final DeferredBlock<Block> CHRISTMAS_WREATH = register("christmas_wreath",
            () -> new ChristmasWreath(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).sound(SoundType.AZALEA_LEAVES).noCollission()));
    public static final DeferredBlock<Block> AMBER_STRING_LIGHTS = register("amber_string_lights",
            () -> new FlatWallPlaneBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.LADDER).noCollission().lightLevel(s -> 3)
                    .hasPostProcess(TBlocks::always).emissiveRendering(TBlocks::always)));
    public static final DeferredBlock<Block> BLUE_STRING_LIGHTS = register("blue_string_lights",
            () -> new FlatWallPlaneBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.LADDER).noCollission().lightLevel(s -> 3)
                    .hasPostProcess(TBlocks::always).emissiveRendering(TBlocks::always)));
    public static final DeferredBlock<Block> CHRISTMAS_LIGHTS = register("christmas_lights",
            () -> new FlatWallPlaneBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.LADDER).noCollission().lightLevel(s -> 4)
                    .hasPostProcess(TBlocks::always).emissiveRendering(TBlocks::always)));
    public static final DeferredBlock<Block> CHRISTMAS_LIGHTS_ALT = register("christmas_lights_alt",
            () -> new FlatWallPlaneBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.LADDER).noCollission().lightLevel(s -> 4)
                    .hasPostProcess(TBlocks::always).emissiveRendering(TBlocks::always)));
    public static final DeferredBlock<Block> NORTH_POLE = register("north_pole",
            () -> new NorthPole(BlockBehaviour.Properties.of().strength(1f,2f).sound(SoundType.STONE).noCollission().noOcclusion()));
    public static final DeferredBlock<Block> LIGHTED_DEER = register("lighted_deer",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().strength(1.5f,2f).sound(SoundType.LADDER).noCollission().lightLevel(s -> 7)));
    public static final DeferredBlock<Block> LIGHTED_CHRISTMAS_TREE = register("lighted_christmas_tree",
            () -> new ChristmasTree(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.AZALEA_LEAVES).noOcclusion().strength(1.25F,10F).lightLevel(s -> 10)));
    public static final DeferredBlock<Block> SMALL_CHRISTMAS_TREE = register("small_christmas_tree",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.AZALEA_LEAVES).noOcclusion().strength(1.15F,8F).lightLevel(s -> 7)));
    public static final DeferredBlock<Block> CHRISTMAS_FIREPLACE = register("christmas_fireplace",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final DeferredBlock<Block> SLEIGH = register("sleigh",
            () -> new Wheelbarrow(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> SNOWMAN_BLUEMAN_STATUE = register("blueman_snowman",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)
                    .noOcclusion().sound(SoundType.POWDER_SNOW).strength(0.5F,0.25F)){
                @SuppressWarnings("deprecated")
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return Optional.of(Block.box(4, 0, 4, 12, 23, 12)).get();
                }
            });
    public static final DeferredBlock<Block> PRESENT_PILE = register("present_pile",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noCollission().sound(SoundType.WOOL).strength(1.0F,2.0F)));
    public static final DeferredBlock<Block> NUTCRACKER = register("nutcracker",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(1.0F,15F).sound(SoundType.BAMBOO)){
                @SuppressWarnings("deprecated")
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return Optional.of(Block.box(4, 0, 4, 12, 28, 12)).get();
                }
            });
    public static final DeferredBlock<Block> GINGERBREAD_HOUSE = register("gingerbread_house",
            () -> new GingerbreadHouse(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
    public static final DeferredBlock<Block> SNOWMAN_PLUSHY = register("snowman_plushy",
            () -> new ReindeerPlush(BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK).sound(SoundType.WOOL)));

    // Misc Decorative Blocks
    public static final DeferredBlock<Block> BROKEN_COMPUTER = register("broken_computer",
            () -> new BrokenComputer(BlockBehaviour.Properties.of().sound(SoundType.METAL).lightLevel(s -> 5)));
    public static final DeferredBlock<Block> DOOR_BELL = register("door_bell",
            () -> new Doorbell(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> REINDEER_WALL_HEAD = register("reindeer_wall_head",
            () -> new ReindeerWallHead(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> GAS_HEATER = register("gas_heater",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> AC_THERMOSTAT = register("ac_thermostat",
            () -> new AcThermostat(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> BLUEY_DESKTOP_COMPUTER = register("bluey_desktop_computer",
            () -> new BlueyDesktopComputer(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> BLUEYCUBE_CONSOLE = register("blueycube_console",
            () -> new BlueyCubeConsole(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> BULK_PRODUCT = register("bulk_product",
            () -> new BulkProduct(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    // poop block
    public static final DeferredBlock<Block> POOP = register("poop",
            () -> new PoopBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().noOcclusion()));
    public static final DeferredBlock<Block> TOOL_STATION = register("tool_station",
            () -> new ToolStation(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> WATER_BOTTLE_PACK = register("water_bottle_pack",
            () -> new WaterBottlePack(BlockBehaviour.Properties.of().strength(1.25f).sound(SoundType.CALCITE)));

    public static final DeferredBlock<Block> POOPOO = registerBlockWithoutItem("poopoo",
            () -> new BetterPoop(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .sound(TSoundType.POOP).noCollission().noOcclusion()));

    // Hospital Decorative Blocks
    public static final DeferredBlock<Block> IV = register("iv",
            () -> new MultipurposeIVPump(BlockBehaviour.Properties.of().sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> HOSPITAL_BED = register("hospital_bed",
            () -> new CustomBedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> HOSPITAL_COVER = register("hospital_cover",
            () -> new HospitalCover(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL).sound(SoundType.CALCITE).noCollission()));
    public static final DeferredBlock<Block> HEART_MONITOR = register("heart_monitor",
            () -> new HospitalPortable(BlockBehaviour.Properties.of().sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> HOSPITAL_COMPUTER = register("hospital_computer",
            () -> new HospitalPortable(BlockBehaviour.Properties.of().sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> OPERATION_TABLE = register("operation_table",
            () -> new OperationTable(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> OPERATION_TOOLS = register("operation_tools",
            () -> new OperationToolsBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)));

    // Road Barriers Decorative Blocks
    public static final DeferredBlock<Block> ROAD_BARRIER_CLOSED = register("road_barrier_closed",
            () -> new RoadBarrier(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ROAD_BARRIER_THRU_CLOSED = register("road_barrier_thru_closed",
            () -> new RoadBarrier(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ROAD_BARRIER_BRIDGE_CLOSED = register("road_barrier_bridge_closed",
            () -> new RoadBarrier(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ROAD_BARRIER_BRIDGE_THRU_CLOSED = register("road_barrier_bridge_thru_closed",
            () -> new RoadBarrier(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> CONCRETE_BARRIER = register("concrete_barrier",
            () -> new BasicConcreteBarrier(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> REINFORCED_CONCRETE_BARRIER = register("reinforced_concrete_barrier",
            () -> new BasicConcreteBarrier(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> REBAR_CONCRETE_BARRIER = register("rebar_concrete_barrier",
            () -> new BasicConcreteBarrier(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> BRIDGE_BARRIER = register("bridge_barrier",
            () -> new ConcreteBarrier(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> ROAD_COVER = register("road_cover",
            () -> new RoadCoveringBlock(BlockBehaviour.Properties.of().noOcclusion().noCollission()));
    public static final DeferredBlock<Block> ALT_ROAD_COVER = register("alt_road_cover",
            () -> new RoadCoveringBlock(BlockBehaviour.Properties.of().noOcclusion().noCollission()));
    public static final DeferredBlock<Block> ROAD_PANEL_COVER = register("road_panel_cover",
            () -> new RoadCoveringBlock(BlockBehaviour.Properties.of().noOcclusion().noCollission()));
    public static final DeferredBlock<Block> ALT_ROAD_PANEL_COVER = register("alt_road_panel_cover",
            () -> new RoadCoveringBlock(BlockBehaviour.Properties.of().noOcclusion().noCollission()));
    public static final DeferredBlock<Block> BIG_ROAD_CONE = register("big_road_cone",
            () -> new BigRoadCone(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> ROAD_BARREL = register("road_barrel",
            () -> new RoadBarrel(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> ROAD_CHANNELIZER = register("road_channelizer",
            () -> new Channelizer(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> ROAD_BARRIER_LIGHTED = register("road_barrier_lighted",
            () -> new RoadBarrier(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ROAD_BARRIER_SMALL = register("road_barrier_small",
            () -> new SmallRoadBarrier(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ROAD_BARRIER_SMALL_LIGHTED = register("road_barrier_small_lighted",
            () -> new SmallRoadBarrier(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ROAD_PANEL = register("road_panel",
            () -> new RoadPanel(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> FIREWORKS_DISPLAY = register("firework_display",
            () -> new FireworksDisplay(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .sound(SoundType.WOOD).strength(2F,1F).noOcclusion()));
    public static final DeferredBlock<Block> ARCADE_MACHINE_OPENABLE = register("arcade_machine_openable",
            () -> new ArcadeMachineOpenable(BlockBehaviour.Properties.of().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> ARCADE_MACHINE = register("arcade_machine",
            () -> new ArcadeMachineMultipleTypesBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL).lightLevel(s -> 12)));

    // Traffic Signals
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_NORMAL_1 = register("traffic_signal_normal_1",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_NORMAL_2 = register("traffic_signal_left",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_NORMAL_3 = register("traffic_signal_right",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_NORMAL_4 = register("u_turn_signal",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_SYMBOL_1 = register("no_left_turn_signal",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_DOGHOUSE_1 = register("traffic_signal_doghouse_left",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_DOGHOUSE_2 = register("traffic_signal_doghouse_right",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_RED_FLASH = register("red_signal_flash",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_YELLOW_FLASH = register("yellow_signal_flash",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> TRAFFIC_SIGNAL_ALLWAY_STOP_BEACON = register("red_allway",
            () -> new RedStopAllwayBeacon(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.METAL)));

    // Pedestrian Signals
    public static final DeferredBlock<Block> PED_SIGNAL_SYMBOLS = register("ped_signal",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> PED_SIGNAL_WORDED = register("ped_signal_worded",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> PED_SIGNAL_MAN_1 = register("ped_signal_man_1",
            () -> new TrafficSignalBase(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> PED_FLASHERS = register("ped_flashers",
            () -> new PedFlashers(BlockBehaviour.Properties.of().strength(1f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    // Mini-City Blocks
    public static final DeferredBlock<Block> MINI_ROAD = register("mini_road",
            () -> new RailBlock(BlockBehaviour.Properties.of().sound(SoundType.DEEPSLATE_TILES).noOcclusion()));
    public static final DeferredBlock<Block> MINI_RAIL = register("mini_rail",
            () -> new RailBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).noOcclusion()));
    public static final DeferredBlock<Block> MINI_BLUE_BUILDING = register("blue_building",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> MINI_RED_BUILDING = register("red_building",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> MINI_GREEN_BUILDING = register("green_building",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> MINI_YELLOW_BUILDING = register("yellow_building",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> MINI_TALL_YELLOW_BUILDING = register("tall_yellow_building",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    // Custom Doors
    public static final DeferredBlock<Block> FESTIVE_DOOR = register("festive_door",
            () -> new DoorBlock(BlockSetType.CHERRY,BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_DOOR)
                    .strength(1F,2F)));
    public static final DeferredBlock<Block> METALLIC_DOOR = register("metallic_door",
            () -> new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)
                    .requiresCorrectToolForDrops().strength(4.75F,15F).sound(SoundType.LANTERN).noOcclusion()));
    public static final DeferredBlock<Block> BUBBLE_DOOR = register("bubble_door",
            () -> new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)
                    .requiresCorrectToolForDrops().strength(2.25F,32F).sound(SoundType.COPPER).noOcclusion()));
    public static final DeferredBlock<Block> STONE_DOOR = register("stone_door",
            () -> new DoorBlock(BlockSetType.IRON,BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)
                    .requiresCorrectToolForDrops().strength(2F,25F).sound(SoundType.STONE).noOcclusion()));
    public static final DeferredBlock<Block> WHITE_WOOD_DOOR = register("white_wood_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final DeferredBlock<Block> ALARMED_DOOR = register("alarmed_door",
            () -> new AlarmedDoor(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));
    public static final DeferredBlock<Block> SCREEN_DOOR = register("screen_door",
            () -> new DoorBlock(BlockSetType.BAMBOO,BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_DOOR).noOcclusion().strength(1F,2F)));
    public static final DeferredBlock<Block> SNOWMAN_DOOR = register("snowman_door",
            () -> new DoorBlock(BlockSetType.OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_DOOR).strength(3F,3F)));

    // Bookshelves
    public static final DeferredBlock<Block> BLANK_BOOKSHELF = register("blank_bookshelf",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .strength(1.5F).sound(SoundType.WOOD).mapColor(MapColor.WOOD)));
    public static final DeferredBlock<Block> ABANDONED_BOOKSHELF = register("abandoned_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> BONE_BOOKSHELF = register("bone_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> BRICK_BOOKSHELF = register("brick_bookshelf",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> GLOWSTONE_BOOKSHELF = register("glowstone_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> EXPERIENCE_BOOKSHELF = register("experience_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> EXPLORER_BOOKSHELF = register("explorer_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> SCARY_BOOKSHELF = register("scary_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> RED_TOME_BOOKSHELF = register("red_tome_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> BLUE_TOME_BOOKSHELF = register("blue_tome_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> GREEN_TOME_BOOKSHELF = register("green_tome_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> YELLOW_TOME_BOOKSHELF = register("yellow_tome_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> EXPENSIVE_BOOKSHELF = register("expensive_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> POTION_BOOKSHELF = register("potion_bookshelf",
            () -> new CustomBookshelf(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> ANCIENT_BOOKSHELF = register("ancient_bookshelf",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(5f).sound(SoundType.ANCIENT_DEBRIS).requiresCorrectToolForDrops().mapColor(MapColor.NETHER)){
                @Override
                public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos) {
                    return 1.0F;
                }
            });

    // Red and White Paper Lanterns
    public static final DeferredBlock<Block> RED_LANTERN = registerBlockWithoutItem("red_lantern",
            () -> new TorchBlock(ParticleTypes.END_ROD,BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel(s -> 13)){
                @Override
                public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs){}
            });
    public static final DeferredBlock<Block> WALL_RED_LANTERN = registerBlockWithoutItem("wall_red_lantern",
            () -> new WallTorchBlock(ParticleTypes.END_ROD,BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel(s -> 13)){
                @Override
                public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs){}
            });
    public static final DeferredBlock<Block> PAPER_LANTERN = registerBlockWithoutItem("paper_lantern",
            () -> new TorchBlock(ParticleTypes.ELECTRIC_SPARK,BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel(s -> 12)){
                @Override
                public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs){}
            });
    public static final DeferredBlock<Block> WALL_PAPER_LANTERN = registerBlockWithoutItem("wall_paper_lantern",
            () -> new WallTorchBlock(ParticleTypes.ELECTRIC_SPARK,BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel(s -> 12)){
                @Override
                public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs){}
            });

    // chains
    public static final DeferredBlock<Block> SCULK_CHAIN = register("sculk_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN).sound(SoundType.SCULK)));
    // lantern like blocks (using vanilla system)
    public static final DeferredBlock<Block> SCULK_LANTERN = register("sculk_lantern",
            () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .lightLevel(s -> 15).sound(SoundType.SCULK_CATALYST)));

    // chairs
    public static final DeferredBlock<Block> STONE_CHAIR = register("stone_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE),
                    SoundEvents.STONE_STEP));
    public static final DeferredBlock<Block> GOLD_CHAIR = register("gold_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK),
                    SoundEvents.METAL_STEP));
    public static final DeferredBlock<Block> QUARTZ_CHAIR = register("quartz_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK),
                    SoundEvents.STONE_STEP));
    public static final DeferredBlock<Block> NETHER_BRICK_CHAIR = register("nether_brick_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS),
                    SoundEvents.NETHER_BRICKS_STEP));
    public static final DeferredBlock<Block> PRISMARINE_CHAIR = register("prismarine_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE),
                    SoundEvents.STONE_STEP));
    public static final DeferredBlock<Block> PURPUR_CHAIR = register("purpur_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPUR_BLOCK),
                    SoundEvents.STONE_STEP));
    public static final DeferredBlock<Block> SCULK_CHAIR = register("sculk_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.SCULK_CATALYST),
                    SoundEvents.SCULK_CATALYST_STEP));

    public static final DeferredBlock<Block> ROAST_TURKEY = register("roast_turkey",
            () -> new RoastTurkey(BlockBehaviour.Properties.of().strength(1f,5f)));

    public static final DeferredBlock<Block> POOP_CHAIR = register("poop_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.of().strength(0.75F,2F)
                    .sound(TSoundType.POOP),
                    TSoundEvent.POOP_STEP.get()));

    public static final DeferredBlock<Block> COMPUTER_CONTROLS = register("computer_controls",
            () -> new ComputerControls(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> PC_CONTROLS = register("pc_controls",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.LANTERN)
                    .strength(0.95F,2F)));

    public static final DeferredBlock<Block> RGB_PC_CONTROLS = register("rgb_pc_controls",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.LANTERN)
                    .strength(0.95F,2F)));

    public static final DeferredBlock<Block> DARKENED_STONE = register("darkened_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> GRADIENT_DARKENED_STONE = register("gradient_darkened_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final DeferredBlock<Block> DARK_DARKENED_STONE = register("dark_darkened_stone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    public static final DeferredBlock<Block> CORNER_COMPUTER = register("corner_computer",
            () -> new CornerComputer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CORNER_COMPUTER_WM = register("corner_computer_wm",
            () -> new CornerComputerMonitorized(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> AQUARIUM = register("aquarium",
            () -> new Aquarium(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> DIAMOND_CHAIR = register("diamond_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK),SoundEvents.METAL_HIT));

    public static final DeferredBlock<Block> IRON_CHAIR = register("iron_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.of().strength(1.1f,2f),SoundEvents.METAL_HIT));

    public static final DeferredBlock<Block> COPPER_CHAIR = register("copper_chair",
            () -> new WeatheringCopperChairBlock(
                    TWeatheringCopperOther.State.UNAFFECTED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> EXPOSED_COPPER_CHAIR = register("exposed_copper_chair",
            () -> new WeatheringCopperChairBlock(
                    TWeatheringCopperOther.State.EXPOSED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY)));
    public static final DeferredBlock<Block> WEATHERED_COPPER_CHAIR = register("weathered_copper_chair",
            () -> new WeatheringCopperChairBlock(
                    TWeatheringCopperOther.State.WEATHERED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM)));
    public static final DeferredBlock<Block> OXIDIZED_COPPER_CHAIR = register("oxidized_copper_chair",
            () -> new WeatheringCopperChairBlock(
                    TWeatheringCopperOther.State.OXIDIZED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_NYLIUM)));
    public static final DeferredBlock<Block> WAXED_COPPER_CHAIR = register("waxed_copper_chair",
            () -> new WaxedCopperChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> WAXED_EXPOSED_COPPER_CHAIR = register("waxed_exposed_copper_chair",
            () -> new WaxedCopperChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> WAXED_WEATHERED_COPPER_CHAIR = register("waxed_weathered_copper_chair",
            () -> new WaxedCopperChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_STEM).requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> WAXED_OXIDIZED_COPPER_CHAIR = register("waxed_oxidized_copper_chair",
            () -> new WaxedCopperChairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_NYLIUM).requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)));

    public static final DeferredBlock<Block> WINE_BOTTLE = register("wine_bottle",
            () -> new WineBottle(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CLOCK_RADIO = register("clock_radio",
            () -> new ClockRadioBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> DARK_CRYSTAL_BLOCK = register("dark_crystal_block",
            () -> new DarkCrystalBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> OPEN_SIGN_ALT = register("open_sign_alt",
            () -> new OpenSign(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN)
                    .lightLevel(openSignLitEmission(15))));
    public static final DeferredBlock<Block> OPEN_SIGN_ALT_TWO = register("open_sign_alt_two",
            () -> new OpenSign(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE)
                    .lightLevel(openSignLitEmission(15))));
    public static final DeferredBlock<Block> BASKETBALL_HOOP = register("basketball_hoop",
            () -> new BasketballHoop(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> ACACIA_LANE = register("acacia_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().ignitedByLava()));
    public static final DeferredBlock<Block> BIRCH_LANE = register("birch_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().ignitedByLava()));
    public static final DeferredBlock<Block> SPRUCE_LANE = register("spruce_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().ignitedByLava()));
    public static final DeferredBlock<Block> DARK_OAK_LANE = register("dark_oak_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().ignitedByLava()));
    public static final DeferredBlock<Block> WARPED_LANE = register("warped_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHER_WOOD)){
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){return 0;}
            });
    public static final DeferredBlock<Block> CRIMSON_LANE = register("crimson_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().sound(SoundType.NETHER_WOOD)){
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){return 0;}
            });
    public static final DeferredBlock<Block> CHERRY_LANE = register("cherry_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.CHERRY_WOOD)));
    public static final DeferredBlock<Block> MANGROVE_LANE = register("mangrove_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().ignitedByLava()));
    public static final DeferredBlock<Block> JUNGLE_LANE = register("jungle_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().ignitedByLava()));
    public static final DeferredBlock<Block> AIRDUCT_VENT = register("airduct_vent",
            () -> new AirductVent(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> ESCALATOR = register("escalator",
            () -> new EscalatorBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> ESCALATOR_DOWN = register("escalator_down",
            () -> new EscalatorDownBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> ALT_US_OUTLET = register("alt_us_outlet",
            () -> new WallOutletBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> REINFORCED_GLASS = register("reinforced_glass",
            () -> new ReinforcedGlass(BlockBehaviour.Properties.of()
                    .strength(2f,100f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> DARKENED_STONE_BRICKS = register("darkened_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> PANEL_DARKENED_STONE_BRICKS = register("panel_darkened_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> CHISELED_PANEL_DARKENED_STONE_BRICKS = register("chiseled_panel_darkened_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> SWIRLY_TECHNO_BLOCK = register("swirly_techno",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.5f,32F).requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)
                    .emissiveRendering(TBlocks::always)));
    public static final DeferredBlock<Block> SUNSTONE_BLOCK = register("sunstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> MOONSTONE_BLOCK = register("moonstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> RUNICSTONE_BLOCK = register("runicstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> RUNICSTONE_BRICKS = register("runicstone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> EXPOSED_RUNICSTONE_BLOCK = register("exposed_runicstone",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));
    public static final DeferredBlock<Block> TRI_CANDLE_HOLDER_BLOCK = register("tri_candle_holder",
            () -> new TriCandleHolder(BlockBehaviour.Properties.of().lightLevel(tricandleblkem(12))));
    public static final DeferredBlock<Block> MIRROR = register("mirror",
            () -> new MirrorBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CALENDAR = register("calendar",
            () -> new CalendarBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> GEARS_BLOCK = register("gears_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> MOVING_GEARS_BLOCK = register("moving_gears_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> TISSUE_BOX = register("tissue_box",
            () -> new TissueBox(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> METAL_VENTS = register("metal_vents",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> YELLOW_CAUTION = register("yellow_caution",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> ORANGE_CAUTION = register("orange_caution",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> ALT_ORANGE_CAUTION = register("alt_orange_caution",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> GREEN_CAUTION = register("green_caution",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> LIGHT_BLUE_CAUTION = register("light_blue_caution",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> RED_CAUTION = register("red_caution",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> FRENCH_BRICK = register("french_brick",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final DeferredBlock<Block> ALT_FRENCH_BRICK = register("alt_french_brick",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS)));
    public static final DeferredBlock<Block> SCROLLING_YELLOW_CAUTION = register("scrolling_yellow_caution",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> BARREL_KEG = register("barrel_keg",
            () -> new BarrelKeg(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> PICNIC_TABLE = register("picnic_table",
            () -> new PicnicTable(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> POWDER_KEG = register("powder_keg",
            () -> new PowderKeg(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> MALE_BATHROOM_SIGN = register("male_bathroom_sign",
            () -> new BathroomSign(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> FEMALE_BATHROOM_SIGN = register("female_bathroom_sign",
            () -> new BathroomSign(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BOTH_BATHROOM_SIGN = register("both_bathroom_sign",
            () -> new BathroomSign(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> FAN_BLOCK_SPARK = register("fan_block_spark",
            () -> new FanBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SCRAP_PANELS = register("scrap_panels",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.COPPER)));

    // rubber
    public static final DeferredBlock<Block> RUBBER_LOG = register("rubber_log",
            () -> new TFlammableRPBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG).strength(3.5f)));
    public static final DeferredBlock<Block> RUBBER_WOOD = register("rubber_wood",
            () -> new TFlammableRPBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD).strength(3.5f)));
    public static final DeferredBlock<Block> STRIPPED_RUBBER_LOG = register("stripped_rubber_log",
            () -> new TFlammableRPBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG).strength(3.5f)));
    public static final DeferredBlock<Block> STRIPPED_RUBBER_WOOD = register("stripped_rubber_wood",
            () -> new TFlammableRPBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD).strength(3.5f)));
    public static final DeferredBlock<Block> RUBBER_PLANKS = register("rubber_planks",
            () -> new TFlammableNormalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));
    public static final DeferredBlock<Block> RUBBER_LEAVES = register("rubber_leaves",
            () -> new CustomLeavesBlock(BlockBehaviour.Properties.of()
                    .isSuffocating(TBlocks::never)
                    .isViewBlocking(TBlocks::never)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(TBlocks::never)
                    .mapColor(MapColor.PLANT)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
                    .isValidSpawn(TBlocks::onlyPlayersSpawnAllow)
            ));

    public static final DeferredBlock<Block> RUBBER_WOOD_SLAB = register("rubber_wood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SLAB)));

    public static final DeferredBlock<Block> RUBBER_WOOD_STAIRS = register("rubber_wood_stairs",
            () -> new StairBlock(TBlocks.RUBBER_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS)));

    public static final DeferredBlock<Block> RUBBER_WOOD_DOOR = register("rubber_wood_door",
            () -> new DoorBlock(BlockSetType.SPRUCE,BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR)));
    public static final DeferredBlock<Block> RUBBER_WOOD_TRAPDOOR = register("rubber_wood_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.SPRUCE,BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR)));
    public static final DeferredBlock<Block> RUBBER_WOOD_BUTTON = register("rubber_wood_button",
            () -> new ButtonBlock(BlockSetType.SPRUCE,25,BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_BUTTON)
                    .noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> RUBBER_WOOD_FENCE = register("rubber_wood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE)));
    public static final DeferredBlock<Block> RUBBER_WOOD_FENCE_GATE = register("rubber_wood_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN,SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredBlock<Block> RUBBER_WOOD_PRESSURE_PLATE = register("rubber_wood_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.SPRUCE,BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PRESSURE_PLATE)
                    .mapColor(MapColor.TERRACOTTA_YELLOW)));
    public static final DeferredBlock<Block> RUBBER_SAPLING = register("rubber_sapling",
            () -> new SaplingBlock(TTreeGrower.RUBBER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

    public static final DeferredBlock<Block> TINY_CROSSING = register("tiny_crossing",
            () -> new DirectionalFlatTwoSidedBlock(BlockBehaviour.Properties.of()
                    .strength(0.2f,1f).sound(SoundType.DEEPSLATE_TILES)
                    .mapColor(MapColor.METAL).instabreak().noCollission()));
    public static final DeferredBlock<Block> OUTLET_BLOCK = register("outlet",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> PAPER_WALL_BLOCK = register("paper_wall",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_MOSAIC)));
    public static final DeferredBlock<Block> PAPER_FLOWER_WALL_BLOCK = register("paper_flower_wall",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_MOSAIC)));
    public static final DeferredBlock<Block> DUMPSTER = register("dumpster",
            () -> new DumpsterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAULDRON).noOcclusion()));
    public static final DeferredBlock<Block> COMMERCIAL_LIQUID_DISPENSER = register("commercial_liquid_dispenser",
            () -> new CommercialLiquidDispenser(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredBlock<Block> COMMERCIAL_CONDIMENT_DISPENSER = register("commercial_condiment_dispenser",
            () -> new CommercialCondimentDispenser(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredBlock<Block> COMMERCIAL_JUICE_DISPENSER = register("commercial_juice_dispenser",
            () -> new CommercialJuiceDispenser(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> COMMERCIAL_UTENCIL_DISPENSER = register("commercial_utencil_dispenser",
            () -> new CommercialUtencilDispenser(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> METALLIC_DOOR_BELL = register("metallic_door_bell",
            () -> new Doorbell(TSoundEvent.METALLIC.get(),BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> OLD_DOOR_BELL = register("old_door_bell",
            () -> new Doorbell(TSoundEvent.OLD.get(),BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS)));
    public static final DeferredBlock<Block> PLUCK_DOOR_BELL = register("pluck_door_bell",
            () -> new Doorbell(TSoundEvent.PLUCK.get(),BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    // ATMs
    public static final DeferredBlock<Block> ATM = register("atm",
            () -> new ATMBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .sound(SoundType.NETHERITE_BLOCK).lightLevel((s) -> 5)));
    public static final DeferredBlock<Block> INSET_ATM = register("inset_atm",
            () -> new InsetATM(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .sound(SoundType.NETHERITE_BLOCK).lightLevel((s) -> 5)));

    public static final DeferredBlock<Block> TRASH_CAN = register("trash_can",
            () -> new TrashCan(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CHANGE_MACHINE = register("change_machine",
            () -> new ChangeMachine(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> PARTICULAR_STATUE = register("particular_statue",
            () -> new Podium(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> VIDEO_CAMERA = register("video_camera",
            () -> new VideoCamera(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> PROFESSIONAL_TV_CAMERA = register("professional_tv_camera",
            () -> new ProfessionalTVCamera(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> STUDIO_CAMERA = register("studio_camera",
            () -> new StudioCamera(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> COMMERCIAL_WASHER = register("commercial_washer",
            () -> new ToggledStateBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.LANTERN).strength(1f,5f).noOcclusion()));
    public static final DeferredBlock<Block> COMMERCIAL_DRYER = register("commercial_dryer",
            () -> new ToggledStateBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.LANTERN).strength(1f,5f).noOcclusion()));
    public static final DeferredBlock<Block> GOBO_LIGHT = register("gobo_light",
            () -> new DJGoboLight(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> TURNTABLE = register("turntable",
            () -> new Turntable(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> ITEM_DISPLAY_BLOCK = register("item_displayer",
            () -> new ItemDisplayBlock(BlockBehaviour.Properties.of().lightLevel((s) -> 15)));

    // glow block
    public static final DeferredBlock<Block> GLOW_BLOCK = register("glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.WOOL).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> LIGHT_GRAY_GLOW_BLOCK = register("light_gray_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_LIGHT_GRAY).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> GRAY_GLOW_BLOCK = register("gray_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_GRAY).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> BLACK_GLOW_BLOCK = register("black_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_BLACK).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> BROWN_GLOW_BLOCK = register("brown_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_BROWN).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> RED_GLOW_BLOCK = register("red_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_RED).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> ORANGE_GLOW_BLOCK = register("orange_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_ORANGE).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> YELLOW_GLOW_BLOCK = register("yellow_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_YELLOW).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> LIME_GLOW_BLOCK = register("lime_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> GREEN_GLOW_BLOCK = register("green_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_GREEN).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> CYAN_GLOW_BLOCK = register("cyan_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_CYAN).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> LIGHT_BLUE_GLOW_BLOCK = register("light_blue_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> BLUE_GLOW_BLOCK = register("blue_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_BLUE).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> PURPLE_GLOW_BLOCK = register("purple_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_PURPLE).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> MAGENTA_GLOW_BLOCK = register("magenta_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_MAGENTA).strength(1.1f,2.5f)));
    public static final DeferredBlock<Block> PINK_GLOW_BLOCK = register("pink_glow_block",
            () -> new Block(BlockBehaviour.Properties.of().emissiveRendering(TBlocks::always)
                    .sound(SoundType.COPPER).mapColor(MapColor.COLOR_PINK).strength(1.1f,2.5f)));

    // lab blocks
    public static final DeferredBlock<Block> GRAY_SCREEN = register("gray_screen",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .mapColor(MapColor.COLOR_GRAY).lightLevel((s) -> 5)));
    public static final DeferredBlock<Block> BLUE_SCREEN = register("blue_screen",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .mapColor(MapColor.COLOR_BLUE).lightLevel((s) -> 5)));

    public static final DeferredBlock<Block> RUBBER_LANE = register("rubber_lane",
            () -> new LaneBlock(BlockBehaviour.Properties.of().ignitedByLava()));

    public static final DeferredBlock<Block> BRAMBLE = register("bramble",
            () -> new DeadBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH)){
                @Override
                protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
                    if(pState.is(BlockTags.DEAD_BUSH_MAY_PLACE_ON)){
                        return true;
                    }
                    return false;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 80;
                }
            });
    public static final DeferredBlock<Block> POTTED_BRAMBLE = registerBlockWithoutItem("potted_bramble",
            () -> new FlowerPotBlock(()->(FlowerPotBlock)Blocks.FLOWER_POT,TBlocks.BRAMBLE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_DEAD_BUSH).instabreak().noOcclusion()));
    public static final DeferredBlock<Block> CONVEYOR_BELT = register("conveyor_belt",
            () -> new ConveyorBelt(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)));
    public static final DeferredBlock<Block> ANALOG_CLOCK = register("clock",
            () -> new AnalogClock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY)));

    // fire hydrants
    public static final DeferredBlock<Block> RED_FIRE_HYDRANT = register("red_fire_hydrant",
            () -> new FireHydrant(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> YELLOW_FIRE_HYDRANT = register("yellow_fire_hydrant",
            () -> new FireHydrant(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> SILVER_FIRE_HYDRANT = register("silver_fire_hydrant",
            () -> new FireHydrant(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)));

    public static final DeferredBlock<Block> PARKING_METER = register("parking_meter",
            () -> new ParkingMeter(BlockBehaviour.Properties.of()));
    // copper tables
    public static final DeferredBlock<Block> WAXED_COPPER_TABLE = register("waxed_copper_table",
            () -> new WaxedCopperConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_COPPER_BLOCK)));
    public static final DeferredBlock<Block> WAXED_EXPOSED_COPPER_TABLE = register("waxed_exposed_copper_table",
            () -> new WaxedCopperConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_EXPOSED_COPPER)));
    public static final DeferredBlock<Block> WAXED_WEATHERED_COPPER_TABLE = register("waxed_weathered_copper_table",
            () -> new WaxedCopperConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_WEATHERED_COPPER)));
    public static final DeferredBlock<Block> WAXED_OXIDIZED_COPPER_TABLE = register("waxed_oxidized_copper_table",
            () -> new WaxedCopperConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WAXED_OXIDIZED_COPPER)));
    public static final DeferredBlock<Block> COPPER_TABLE = register("copper_table",
            () -> new WeatheringCopperTableBlock(
                    TWeatheringCopperOther.State.UNAFFECTED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> EXPOSED_COPPER_TABLE = register("exposed_copper_table",
            () -> new WeatheringCopperTableBlock(
                    TWeatheringCopperOther.State.EXPOSED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> WEATHERED_COPPER_TABLE = register("weathered_copper_table",
            () -> new WeatheringCopperTableBlock(
                    TWeatheringCopperOther.State.WEATHERED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN)));
    public static final DeferredBlock<Block> OXIDIZED_COPPER_TABLE = register("oxidized_copper_table",
            () -> new WeatheringCopperTableBlock(
                    TWeatheringCopperOther.State.OXIDIZED,
                    BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN)));

    public static final DeferredBlock<Block> IRON_TABLE = register("iron_table",
            () -> new ConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> DIAMOND_TABLE = register("diamond_table",
            () -> new ConnectedTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredBlock<Block> DOOR_BLOCKADE = register("door_blockade",
            () -> new DoorBlockade(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> WINDOW_BLOCKADE = register("window_blockade",
            () -> new DoorBlockade(BlockBehaviour.Properties.of()){
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    Direction direction = bs.getValue(FACING);
                    switch(direction){
                        case NORTH: return DoorBlockade.WINDOW_N;
                        case SOUTH: return DoorBlockade.WINDOW_S;
                        case EAST: return DoorBlockade.WINDOW_E;
                        case WEST: return DoorBlockade.WINDOW_W;
                        default: return Shapes.block();
                    }
                }
            });
    public static final DeferredBlock<Block> CINDER_BLOCK = register("cinder_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.25f,20f).sound(SoundType.DEEPSLATE_TILES)));
    public static final DeferredBlock<Block> CINDER_BLOCK_SMALL = register("cinder_block_small",
            () -> new CinderBlockSmall(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CINDER_BLOCK_SLAB = register("cinder_block_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.25f,20f).sound(SoundType.DEEPSLATE_TILES)));
    public static final DeferredBlock<Block> I_BEAM = register("i_beam",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of()
                    .strength(1f,12f).sound(SoundType.METAL)
                    .mapColor(MapColor.METAL).pushReaction(PushReaction.BLOCK)));
    public static final DeferredBlock<Block> CONCRETE = register("concrete",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CONCRETE)));
    public static final DeferredBlock<Block> CONCRETE_BRICKS = register("concrete_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CONCRETE)));
    public static final DeferredBlock<Block> COBBLED_CONCRETE = register("cobbled_concrete",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CONCRETE)));
    public static final DeferredBlock<Block> STEEL_HOARDING = register("steel_hoarding",
            () -> new Hoarding(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> STEEL = register("steel_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(6.0F, 10.0F).sound(SoundType.NETHERITE_BLOCK)));

    public static final DeferredBlock<Block> LADDER_RAILING = register("ladder_railing",
            () -> new CurvedRailing(BlockBehaviour.Properties.of()){
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    Direction direction = bs.getValue(FACING);
                    switch(direction){
                        case NORTH:
                            return NS2;
                        case SOUTH:
                            return SS2;
                        case EAST:
                            return ES2;
                        case WEST:
                            return WS2;
                        default:
                            return Shapes.block();
                    }
                }
            });

    public static final DeferredBlock<Block> CURVED_RAILING = register("curved_railing",
            () -> new CurvedRailing(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> RED_PATHWAY = register("red_pathway",
            () -> new Pathway(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)
                    .mapColor(MapColor.TERRACOTTA_RED).sound(SoundType.CANDLE)));
    public static final DeferredBlock<Block> GRAY_PATHWAY = register("gray_pathway",
            () -> new Pathway(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)
                    .mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).sound(SoundType.CANDLE)));
    public static final DeferredBlock<Block> CONCRETE_STAIRS = register("concrete_stairs",
            () -> new StairBlock(TBlocks.CONCRETE.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CONCRETE)));
    public static final DeferredBlock<Block> CONCRETE_SLAB = register("concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CONCRETE)));
    public static final DeferredBlock<Block> CONCRETE_BRICKS_STAIRS = register("concrete_bricks_stairs",
            () -> new StairBlock(TBlocks.CONCRETE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CONCRETE)));
    public static final DeferredBlock<Block> CONCRETE_BRICKS_SLAB = register("concrete_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CONCRETE)));
    public static final DeferredBlock<Block> COBBLED_CONCRETE_STAIRS = register("cobbled_concrete_stairs",
            () -> new StairBlock(TBlocks.COBBLED_CONCRETE.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CONCRETE)));
    public static final DeferredBlock<Block> COBBLED_CONCRETE_SLAB = register("cobbled_concrete_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CONCRETE)));
    public static final DeferredBlock<Block> DISCO_BALL = register("disco_ball",
            () -> new DiscoBall(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> LAUNDRY_PILE = register("laundry_pile",
            () -> new LaundryPile(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> LAUNDRY_BASKET = register("laundry_basket",
            () -> new LaundryBasket(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BAR_STOOL = register("bar_stool",
            () -> new LibraryStool(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN)));
    public static final DeferredBlock<Block> SPEAKER = register("speaker",
            () -> new Speaker(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> AUDIO_MIXER = register("audio_mixer",
            () -> new AudioMixer(BlockBehaviour.Properties.of()));






    // redone features (experimental)
    public static final DeferredBlock<Block> DJ_LASER_LIGHT = register("dj_laser_light",
            () -> new DJLaserLight(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> LOCKABLE_DOOR = register("lockable_door",
            () -> new LockableDoor(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));

    // Car Wash Decorative Blocks
    public static final DeferredBlock<Block> CAR_WASH_DRYER = register("car_wash_dryer",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).noCollission()));
    public static final DeferredBlock<Block> CAR_WASH_MITTER_CURTAIN = register("car_wash_mitter_curtain",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).noCollission()));
    public static final DeferredBlock<Block> CAR_WASH_MIXED_BRUSH = register("car_wash_mixed_brush",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).noCollission()));
    public static final DeferredBlock<Block> CAR_WASH_SIGNAGE = register("car_wash_signage",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).noCollission()));
    public static final DeferredBlock<Block> CAR_WASH_SIGNAL = register("car_wash_signal",
            () -> new CarWashSignal(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CAR_WASH_DRIPPER = register("car_wash_dripper",
            () -> new CarWashDripper(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CAR_WASH_SPRAYER = register("car_wash_sprayer",
            () -> new CarWashSprayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CAR_WASH_SOAPER = register("car_wash_soaper",
            () -> new CarWashSoaper(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CAR_WASH_WAXER = register("car_wash_waxer",
            () -> new CarWashWaxer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CAR_WASH_TRIFOAMER = register("car_wash_trifoamer",
            () -> new CarWashMultiFoamSprayer(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> CAR_WASH_BLUE_BRUSH = register("car_wash_blue_brush",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().noCollission()));
    public static final DeferredBlock<Block> CAR_WASH_RED_BRUSH = register("car_wash_red_brush",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().noCollission()));
    public static final DeferredBlock<Block> CAR_WASH_YELLOW_BRUSH = register("car_wash_yellow_brush",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().noCollission()));
    public static final DeferredBlock<Block> CAR_WASH_TIRE_SCRUBBER = register("car_wash_tire_scrubber",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().noCollission()));


    //1.7.5
    public static final DeferredBlock<Block> BARBER_POLE = register("barber_pole",
            () -> new BarberPole(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)
                    .strength(1f,5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.LANTERN)));

    public static final DeferredBlock<Block> WALL_TV = register("wall_tv",
            () -> new WallTV(BlockBehaviour.Properties.of()
                    .sound(SoundType.LANTERN)));

    public static final DeferredBlock<Block> BARBER_CHAIR = register("barber_chair",
            () -> new ChairBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)
                    .requiresCorrectToolForDrops().mapColor(MapColor.TERRACOTTA_BLACK).noOcclusion().strength(1f,2f)){
                public static final VoxelShape NORTH = Stream.of(
                        Block.box(4, 0, 4, 12, 1, 12),
                        Block.box(7, 1, 7, 9, 9, 9),
                        Block.box(3, 1, 0, 13, 2, 4),
                        Block.box(3, 2, 4, 13, 9, 5),
                        Block.box(3, 9, 5, 13, 10, 13),
                        Block.box(3, 9, 13, 13, 19, 15),
                        Block.box(5, 18, 11, 11, 22, 13),
                        Block.box(3, 8, 5, 13, 9, 6),
                        Block.box(3, 1, 4, 13, 2, 5),
                        Block.box(1, 11, 4, 3, 13, 14),
                        Block.box(13, 11, 4, 15, 13, 14)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape EAST = Stream.of(
                        Block.box(4, 0, 4, 12, 1, 12),
                        Block.box(7, 1, 7, 9, 9, 9),
                        Block.box(12, 1, 3, 16, 2, 13),
                        Block.box(11, 2, 3, 12, 9, 13),
                        Block.box(3, 9, 3, 11, 10, 13),
                        Block.box(1, 9, 3, 3, 19, 13),
                        Block.box(3, 18, 5, 5, 22, 11),
                        Block.box(10, 8, 3, 11, 9, 13),
                        Block.box(11, 1, 3, 12, 2, 13),
                        Block.box(2, 11, 1, 12, 13, 3),
                        Block.box(2, 11, 13, 12, 13, 15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape SOUTH = Stream.of(
                        Block.box(4, 0, 4, 12, 1, 12),
                        Block.box(7, 1, 7, 9, 9, 9),
                        Block.box(3, 1, 12, 13, 2, 16),
                        Block.box(3, 2, 11, 13, 9, 12),
                        Block.box(3, 9, 3, 13, 10, 11),
                        Block.box(3, 9, 1, 13, 19, 3),
                        Block.box(5, 18, 3, 11, 22, 5),
                        Block.box(3, 8, 10, 13, 9, 11),
                        Block.box(3, 1, 11, 13, 2, 12),
                        Block.box(13, 11, 2, 15, 13, 12),
                        Block.box(1, 11, 2, 3, 13, 12)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape WEST = Stream.of(
                        Block.box(4, 0, 4, 12, 1, 12),
                        Block.box(7, 1, 7, 9, 9, 9),
                        Block.box(0, 1, 3, 4, 2, 13),
                        Block.box(4, 2, 3, 5, 9, 13),
                        Block.box(5, 9, 3, 13, 10, 13),
                        Block.box(13, 9, 3, 15, 19, 13),
                        Block.box(11, 18, 5, 13, 22, 11),
                        Block.box(5, 8, 3, 6, 9, 13),
                        Block.box(4, 1, 3, 5, 2, 13),
                        Block.box(4, 11, 13, 14, 13, 15),
                        Block.box(4, 11, 1, 14, 13, 3)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> BARBER_HAIR_DRYER = register("barber_hair_dryer",
            () -> new BarberHairDryer(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> BOXY_CONSOLE = register("boxy_console",
            () -> new BoxyConsole(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> ORANGE_BOXY_CONSOLE = register("orange_boxy_console",
            () -> new BoxyConsole(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> TALL_BOXY_CONSOLE = register("tall_boxy_console",
            () -> new BoxyConsole(BlockBehaviour.Properties.of()){
                public static final VoxelShape ALL =
                        Block.box(4, 0, 4, 12, 14, 12);
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> DENTAL_CUP = register("dental_cup",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .sound(SoundType.CANDLE).strength(0.5f,1f).noOcclusion()){
                public static final VoxelShape NORTH = Stream.of(
                        Block.box(6, 0, 6, 10, 5, 10),
                        Block.box(7, 0, 8, 8, 7, 9),
                        Block.box(8, 5, 8, 8.55, 7, 9),
                        Block.box(7, 0, 7, 9, 5.75, 7.75)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape EAST = Stream.of(
                        Block.box(6, 0, 6, 10, 5, 10),
                        Block.box(7, 0, 7, 8, 7, 8),
                        Block.box(7, 5, 8, 8, 7, 8.55),
                        Block.box(8.25, 0, 7, 9, 5.75, 9)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape SOUTH = Stream.of(
                        Block.box(6, 0, 6, 10, 5, 10),
                        Block.box(8, 0, 7, 9, 7, 8),
                        Block.box(7.449999999999999, 5, 7, 8, 7, 8),
                        Block.box(7, 0, 8.25, 9, 5.75, 9)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape WEST = Stream.of(
                        Block.box(6, 0, 6, 10, 5, 10),
                        Block.box(8, 0, 8, 9, 7, 9),
                        Block.box(8, 5, 7.449999999999999, 9, 7, 8),
                        Block.box(7, 0, 7, 7.75, 5.75, 9)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> GRAY_GAME_CONSOLE = register("gray_game_console",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .noOcclusion().strength(1f,10f)){
                public static final VoxelShape NORTHSOUTH = Block.box(2, 0, 4, 14, 2, 12);
                public static final VoxelShape EASTWEST = Block.box(4, 0, 2, 12, 2, 14);
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH,SOUTH -> {
                            return NORTHSOUTH;
                        }
                        case EAST,WEST -> {
                            return EASTWEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> BLACK_GAME_CONSOLE = register("black_game_console",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().noOcclusion().strength(1f,10f)
                    .mapColor(MapColor.COLOR_BLACK)){
                public static final VoxelShape NORTH = Shapes.join(
                        Block.box(4, 0, 3, 16, 1, 13),
                        Block.box(0, 1, 3, 16, 3, 13),
                        BooleanOp.OR);

                public static final VoxelShape EAST = Shapes.join(
                        Block.box(3, 0, 4, 13, 1, 16),
                        Block.box(3, 1, 0, 13, 3, 16),
                        BooleanOp.OR);

                public static final VoxelShape SOUTH = Shapes.join(
                        Block.box(0, 0, 3, 12, 1, 13),
                        Block.box(0, 1, 3, 16, 3, 13),
                        BooleanOp.OR);

                public static final VoxelShape WEST = Shapes.join(
                        Block.box(3, 0, 0, 13, 1, 12),
                        Block.box(3, 1, 0, 13, 3, 16),
                        BooleanOp.OR);

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc){
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });
    // 1.7.6
    public static final DeferredBlock<Block> AIR_FRYER = register("air_fryer",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN)
                    .sound(SoundType.LANTERN)));
    public static final DeferredBlock<Block> WATER_DISPENSER = register("water_dispenser",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of()){
                public static final VoxelShape ALL = Stream.of(
                        Block.box(4, 14, 4, 12, 15, 12),
                        Block.box(3, 18, 3, 13, 32, 13),
                        Block.box(5, 15, 5, 11, 16, 11),
                        Block.box(4, 16, 4, 12, 18, 12),
                        Block.box(2, 0, 2, 14, 14, 14)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });
    public static final DeferredBlock<Block> CAT_TREE = register("cat_tree",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL)
                    .strength(0.75f,2f).mapColor(MapColor.COLOR_GRAY)){
                public static final VoxelShape NORTH = Stream.of(
                        Block.box(0, 16, 0, 16, 17, 16),
                        Block.box(0, 31, 0, 16, 32, 16),
                        Block.box(0, 17, 15, 16, 31, 16),
                        Block.box(15, 17, 0, 16, 31, 15),
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(0, 15, 0, 16, 16, 16),
                        Block.box(0, 1, 0, 1, 15, 16),
                        Block.box(15, 1, 0, 16, 15, 16),
                        Block.box(1, 1, 15, 15, 15, 16)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape EAST = Stream.of(
                        Block.box(0, 16, 0, 16, 17, 16),
                        Block.box(0, 31, 0, 16, 32, 16),
                        Block.box(0, 17, 0, 1, 31, 16),
                        Block.box(1, 17, 15, 16, 31, 16),
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(0, 15, 0, 16, 16, 16),
                        Block.box(0, 1, 0, 16, 15, 1),
                        Block.box(0, 1, 15, 16, 15, 16),
                        Block.box(0, 1, 1, 1, 15, 15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape SOUTH = Stream.of(
                        Block.box(0, 16, 0, 16, 17, 16),
                        Block.box(0, 31, 0, 16, 32, 16),
                        Block.box(0, 17, 0, 16, 31, 1),
                        Block.box(0, 17, 1, 1, 31, 16),
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(0, 15, 0, 16, 16, 16),
                        Block.box(15, 1, 0, 16, 15, 16),
                        Block.box(0, 1, 0, 1, 15, 16),
                        Block.box(1, 1, 0, 15, 15, 1)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape WEST = Stream.of(
                        Block.box(0, 16, 0, 16, 17, 16),
                        Block.box(0, 31, 0, 16, 32, 16),
                        Block.box(15, 17, 0, 16, 31, 16),
                        Block.box(0, 17, 0, 15, 31, 1),
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(0, 15, 0, 16, 16, 16),
                        Block.box(0, 1, 15, 16, 15, 16),
                        Block.box(0, 1, 0, 16, 15, 1),
                        Block.box(15, 1, 1, 16, 15, 15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> TRASH_BAG = register("trash_bag",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.CANDLE)
                    .strength(0.2f,0.5f).mapColor(MapColor.COLOR_BLACK)){
                public static final VoxelShape ALL = Shapes.join(Block.box(2, 0, 2, 14, 10, 14),
                        Block.box(5, 10, 5, 11, 12, 11), BooleanOp.OR);
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> GAMING_PC = register("gaming_pc",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL).mapColor(MapColor.COLOR_BLACK)){
                public static final VoxelShape NORTHSOUTH = Block.box(
                        4, 0, 0, 12, 16, 16);
                public static final VoxelShape EASTWEST = Block.box(
                        0, 0, 4, 16, 16, 12);

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc){
                    switch(bs.getValue(FACING)){
                        case NORTH,SOUTH -> {
                            return NORTHSOUTH;
                        }
                        case EAST,WEST -> {
                            return EASTWEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> LITTER_BOX = register("litter_box",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of()){
                public static final VoxelShape ALL = Stream.of(
                        Block.box(0, 0, 0, 16, 3, 1),
                        Block.box(0, 0, 15, 16, 3, 16),
                        Block.box(0, 0, 1, 1, 3, 15),
                        Block.box(15, 0, 1, 16, 3, 15),
                        Block.box(1, 0, 1, 15, 1, 15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> DESK_LAMP = register("desk_lamp",
            () -> new ToggledStateBlock(BlockBehaviour.Properties.of().lightLevel(enableToggleBlockLitBlockEmission(7))
                    .noOcclusion().mapColor(MapColor.COLOR_BLACK).sound(SoundType.LANTERN)){
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    Direction direction = bs.getValue(FACING);
                    switch(direction){
                        case NORTH,SOUTH -> {
                            return BlueyDesktopComputer.NORTHSOUTH_SHAPE;
                        }
                        case EAST,WEST -> {
                            return BlueyDesktopComputer.EASTWEST_SHAPE;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> REFINED_THINGAMAJIG_BLOCK = register("refined_thingamajig_block",
            () -> new RedstoneLampBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)
                    .lightLevel(s -> 0).requiresCorrectToolForDrops().strength(1f,175f)));

    public static final DeferredBlock<Block> CAMPING_FUEL_CAN = register("camping_fuel_can",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)
                    .strength(1f,2f)){
                public static final VoxelShape ALL = Shapes.join(Block.box(6, 0, 6, 10, 6, 10),
                        Block.box(7, 6, 7, 9, 7, 9), BooleanOp.OR);
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> ICECREAM_DISPLAY = register("icecream_display",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)
                    .strength(1.1f,5f)));

    public static final DeferredBlock<Block> PTAC_AC = register("ptac_ac",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).mapColor(MapColor.METAL)){
                public static final VoxelShape NORTH = Block.box(-8, 0, 8, 24, 14, 16);
                public static final VoxelShape EAST = Block.box(0, 0, -8, 8, 14, 24);
                public static final VoxelShape SOUTH = Block.box(-8, 0, 0, 24, 14, 8);
                public static final VoxelShape WEST = Block.box(8, 0, -8, 16, 14, 24);

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> CLOTHES_RACK = register("clothes_rack",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).mapColor(MapColor.COLOR_GRAY)){
                public static final VoxelShape ALL = Stream.of(
                        Block.box(0, 0, 0, 2, 2, 2),
                        Block.box(14, 0, 0, 16, 2, 2),
                        Block.box(14, 0, 14, 16, 2, 16),
                        Block.box(0, 0, 14, 2, 2, 16),
                        Block.box(0, 2, 0, 16, 3, 16),
                        Block.box(7, 3, 7, 9, 23, 9)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> CARD_READER = register("card_reader",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.LANTERN).mapColor(MapColor.COLOR_LIGHT_GRAY)){
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return Block.box(2, 0, 2, 14, 9, 14);
                }
            });

    public static final DeferredBlock<Block> CURVED_MONITOR = registerBlockWithoutItem("curved_monitor",
            () -> new CurvedMonitor(BlockBehaviour.Properties.of().lightLevel(s -> 7)));

    // 1.7.7

    public static final DeferredBlock<Block> SECURITY_METAL_DETECTOR = register("security_metal_detector",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of().sound(SoundType.METAL)){
                public static final VoxelShape NORTHSOUTH = Stream.of(
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(0, 1, 0, 2, 31, 16),
                        Block.box(14, 1, 0, 16, 31, 16),
                        Block.box(0, 31, 0, 16, 32, 16)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape EASTWEST = Stream.of(
                        Block.box(0, 0, 0, 16, 1, 16),
                        Block.box(0, 1, 14, 16, 31, 16),
                        Block.box(0, 1, 0, 16, 31, 2),
                        Block.box(0, 31, 0, 16, 32, 16)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                @Override
                public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                    switch(pState.getValue(FACING)){
                        case NORTH,SOUTH -> {
                            return NORTHSOUTH;
                        }
                        case EAST,WEST -> {
                            return EASTWEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> TRIPLE_SHELF = register("triple_shelf",
            () -> new StorageDecoration(BlockBehaviour.Properties.of().strength(1f,10f).sound(SoundType.CANDLE),
                    SoundEvents.CANDLE_STEP){
                public static final VoxelShape ALL = Stream.of(
                        Block.box(0, 0, 0, 16, 2, 16),
                        Block.box(0, 12, 0, 16, 14, 16),
                        Block.box(0, 24, 0, 16, 26, 16),
                        Block.box(1, 2, 1, 3, 24, 3),
                        Block.box(13, 2, 1, 15, 24, 3),
                        Block.box(13, 2, 13, 15, 24, 15),
                        Block.box(1, 2, 13, 3, 24, 15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> CLEVER_BLACKBOARD = registerBlockWithoutItem("clever_blackboard",
            () -> new CleverBlackboard(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> UMBRELLA = registerBlockWithoutItem("umbrella",
            () -> new UmbrellaBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> THEATER_PROJECTOR = registerBlockWithoutItem("theater_projector",
            () -> new TheaterProjector(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> SUPERMARKET_CONVEYOR = register("supermarket_conveyor",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of()){
                public static final VoxelShape NORTH = Stream.of(
                        Block.box(0, 0, 0, 32, 16, 16),
                        Block.box(0, 16, 0, 32, 17, 1),
                        Block.box(0, 16, 15, 32, 17, 16),
                        Block.box(-16, 12, 0, 0, 14, 16),
                        Block.box(-2.2685027311320196, 13.349854383963653, 1, 2.7314972688679804, 15.349854383963653, 15),
                        Block.box(-16, 14, 0, 0, 15, 1),
                        Block.box(-16, 14, 15, 0, 15, 16),
                        Block.box(-16, 14, 1, -15, 15, 15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape EAST = Stream.of(
                        Block.box(0, 0, 0, 16, 16, 32),
                        Block.box(15, 16, 0, 16, 17, 32),
                        Block.box(0, 16, 0, 1, 17, 32),
                        Block.box(0, 12, -16, 16, 14, 0),
                        Block.box(1, 13.349854383963653, -2.2685027311320205, 15, 15.349854383963653, 2.7314972688679804),
                        Block.box(15, 14, -16, 16, 15, 0),
                        Block.box(0, 14, -16, 1, 15, 0),
                        Block.box(1, 14, -16, 15, 15, -15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape SOUTH = Stream.of(
                        Block.box(-16, 0, 0, 16, 16, 16),
                        Block.box(-16, 16, 15, 16, 17, 16),
                        Block.box(-16, 16, 0, 16, 17, 1),
                        Block.box(16, 12, 0, 32, 14, 16),
                        Block.box(13.26850273113202, 13.349854383963653, 1, 18.26850273113202, 15.349854383963653, 15),
                        Block.box(16, 14, 15, 32, 15, 16),
                        Block.box(16, 14, 0, 32, 15, 1),
                        Block.box(31, 14, 1, 32, 15, 15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape WEST = Stream.of(
                        Block.box(0, 0, -16, 16, 16, 16),
                        Block.box(0, 16, -16, 1, 17, 16),
                        Block.box(15, 16, -16, 16, 17, 16),
                        Block.box(0, 12, 16, 16, 14, 32),
                        Block.box(1, 13.349854383963653, 13.26850273113202, 15, 15.349854383963653, 18.26850273113202),
                        Block.box(0, 14, 16, 1, 15, 32),
                        Block.box(15, 14, 16, 16, 15, 32),
                        Block.box(1, 14, 31, 15, 15, 32)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH ->  {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> STRING_BASS = register("string_bass",
            () -> new DoubleTallDecorationBlock(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> BASS_DRUM = register("bass_drum",
            () -> new InstrumentDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD),TSoundEvent.KICK_DRUM.get()){
                public static final VoxelShape NORTHSOUTH = Stream.of(
                        Block.box(1, 3, 0, 15, 17, 16),
                        Block.box(0, 0, 3, 1, 4, 5),
                        Block.box(0, 0, 11, 1, 4, 13),
                        Block.box(15, 0, 3, 16, 4, 5),
                        Block.box(15, 0, 11, 16, 4, 13)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape EASTWEST = Stream.of(
                        Block.box(0, 3, 1, 16, 17, 15),
                        Block.box(3, 0, 15, 5, 4, 16),
                        Block.box(11, 0, 15, 13, 4, 16),
                        Block.box(3, 0, 0, 5, 4, 1),
                        Block.box(11, 0, 0, 13, 4, 1)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH,SOUTH -> {
                            return NORTHSOUTH;
                        }
                        case EAST,WEST -> {
                            return EASTWEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> SNARE_DRUM = register("snare_drum",
            () -> new InstrumentDecorativeBlock(BlockBehaviour.Properties.of().sound(TSoundType.RATTLE_INSTRUMENT),TSoundEvent.SNARE_DRUM.get()){
                public static final VoxelShape ALL = Stream.of(
                        Block.box(6.5, 2, 6.5, 9.5, 11, 9.5),
                        Block.box(2, 0, 2, 14, 5, 14),
                        Block.box(2, 8, 2, 14, 18, 14)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> CYMBAL_CRASH = register("cymbal_crash",
            () -> new InstrumentDecorativeBlock(BlockBehaviour.Properties.of().sound(TSoundType.METALLIC_INSTRUMENT),TSoundEvent.CRASH_CYMBAL.get()){
                public static final VoxelShape ALL = Stream.of(
                        Block.box(6.5, 2, 6.5, 9.5, 24, 9.5),
                        Block.box(2, 0, 2, 14, 5, 14),
                        Block.box(0, 19, 0, 16, 26, 16)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> FLOOR_TOM = register("floor_tom",
            () -> new InstrumentDecorativeBlock(BlockBehaviour.Properties.of().sound(TSoundType.NATURAL_INSTRUMENT),TSoundEvent.FLOOR_TOM_DRUM.get()){
                public static final VoxelShape ALL = Stream.of(
                        Block.box(0, 0, 0, 2, 4, 2),
                        Block.box(14, 0, 0, 16, 4, 2),
                        Block.box(0, 0, 14, 2, 4, 16),
                        Block.box(14, 0, 14, 16, 4, 16),
                        Block.box(2, 3, 2, 14, 19, 14),
                        Block.box(0, 4, 0, 16, 4.01, 16)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> RACK_TOM = register("rack_tom",
            () -> new InstrumentDecorativeBlock(BlockBehaviour.Properties.of().sound(TSoundType.NATURAL_INSTRUMENT),TSoundEvent.TOM_DRUM.get()){
                public static final VoxelShape NORTH = Stream.of(
                        Block.box(3, 0, 3, 13, 3, 13),
                        Block.box(5, 0, 13, 11, 18, 15),
                        Block.box(2, 5, 2, 14, 20, 14)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape EAST = Stream.of(
                        Block.box(3, 0, 3, 13, 3, 13),
                        Block.box(1, 0, 5, 3, 18, 11),
                        Block.box(2, 5, 2, 14, 20, 14)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape SOUTH = Stream.of(
                        Block.box(3, 0, 3, 13, 3, 13),
                        Block.box(5, 0, 1, 11, 18, 3),
                        Block.box(2, 5, 2, 14, 20, 14)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape WEST = Stream.of(
                        Block.box(3, 0, 3, 13, 3, 13),
                        Block.box(13, 0, 5, 15, 18, 11),
                        Block.box(2, 5, 2, 14, 20, 14)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> BONGOS = register("bongos",
            () -> new InstrumentDecorativeBlock(BlockBehaviour.Properties.of().sound(TSoundType.PAPER_INSTRUMENT),TSoundEvent.BONGO.get()){
                public static final VoxelShape NORTH = Stream.of(
                        Block.box(0, 0, 4, 2, 3, 6),
                        Block.box(10, 3, 4, 16, 8, 10),
                        Block.box(0, 3, 4, 8, 9, 12),
                        Block.box(14, 0, 4, 16, 3, 6),
                        Block.box(14, 0, 8, 16, 3, 10),
                        Block.box(0, 0, 10, 2, 3, 12),
                        Block.box(8, 4, 5, 10, 6, 8),
                        Block.box(6, 0, 10, 8, 3, 12),
                        Block.box(10, 0, 4, 12, 3, 6)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape SOUTH = Stream.of(
                        Block.box(14, 0, 10, 16, 3, 12),
                        Block.box(0, 3, 6, 6, 8, 12),
                        Block.box(8, 3, 4, 16, 9, 12),
                        Block.box(0, 0, 10, 2, 3, 12),
                        Block.box(0, 0, 6, 2, 3, 8),
                        Block.box(14, 0, 4, 16, 3, 6),
                        Block.box(6, 4, 8, 8, 6, 11),
                        Block.box(8, 0, 4, 10, 3, 6),
                        Block.box(4, 0, 10, 6, 3, 12)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape EAST = Stream.of(
                        Block.box(10, 0, 0, 12, 3, 2),
                        Block.box(6, 3, 10, 12, 8, 16),
                        Block.box(4, 3, 0, 12, 9, 8),
                        Block.box(10, 0, 14, 12, 3, 16),
                        Block.box(6, 0, 14, 8, 3, 16),
                        Block.box(4, 0, 0, 6, 3, 2),
                        Block.box(8, 4, 8, 11, 6, 10),
                        Block.box(4, 0, 6, 6, 3, 8),
                        Block.box(10, 0, 10, 12, 3, 12)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape WEST = Stream.of(
                        Block.box(4, 0, 14, 6, 3, 16),
                        Block.box(4, 3, 0, 10, 8, 6),
                        Block.box(4, 3, 8, 12, 9, 16),
                        Block.box(4, 0, 0, 6, 3, 2),
                        Block.box(8, 0, 0, 10, 3, 2),
                        Block.box(10, 0, 14, 12, 3, 16),
                        Block.box(5, 4, 6, 8, 6, 8),
                        Block.box(10, 0, 8, 12, 3, 10),
                        Block.box(4, 0, 4, 6, 3, 6)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> HI_HAT = register("hi_hat",
            () -> new InstrumentDecorativeBlock(BlockBehaviour.Properties.of().sound(TSoundType.METALLIC_INSTRUMENT),TSoundEvent.HIHAT.get()){
                public static final VoxelShape ALL = Stream.of(
                        Block.box(7, 2, 7, 9, 25, 9),
                        Block.box(2, 20, 2, 14, 21, 14),
                        Block.box(2, 18, 2, 14, 19, 14),
                        Block.box(2, 0, 2, 14, 6, 14)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });


    public static final DeferredBlock<Block> CONGAS = register("congas",
            () -> new InstrumentDecorativeBlock(BlockBehaviour.Properties.of().sound(TSoundType.PAPER_INSTRUMENT),TSoundEvent.CONGA.get()){
                public static final VoxelShape NORTH = Stream.of(
                        Block.box(0, 0, 4, 2, 3, 6),
                        Block.box(14, 0, 4, 16, 3, 6),
                        Block.box(10, 0, 4, 12, 3, 6),
                        Block.box(14, 0, 8, 16, 3, 10),
                        Block.box(6, 0, 10, 8, 3, 12),
                        Block.box(0, 0, 10, 2, 3, 12),
                        Block.box(10, 3, 4, 16, 14, 10),
                        Block.box(0, 3, 4, 8, 16, 12),
                        Block.box(8, 4, 5, 10, 6, 8)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape SOUTH = Stream.of(
                        Block.box(14, 0, 10, 16, 3, 12),
                        Block.box(0, 0, 10, 2, 3, 12),
                        Block.box(4, 0, 10, 6, 3, 12),
                        Block.box(0, 0, 6, 2, 3, 8),
                        Block.box(8, 0, 4, 10, 3, 6),
                        Block.box(14, 0, 4, 16, 3, 6),
                        Block.box(0, 3, 6, 6, 14, 12),
                        Block.box(8, 3, 4, 16, 16, 12),
                        Block.box(6, 4, 8, 8, 6, 11)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape EAST = Stream.of(
                        Block.box(10, 0, 0, 12, 3, 2),
                        Block.box(10, 0, 14, 12, 3, 16),
                        Block.box(10, 0, 10, 12, 3, 12),
                        Block.box(6, 0, 14, 8, 3, 16),
                        Block.box(4, 0, 6, 6, 3, 8),
                        Block.box(4, 0, 0, 6, 3, 2),
                        Block.box(6, 3, 10, 12, 14, 16),
                        Block.box(4, 3, 0, 12, 16, 8),
                        Block.box(8, 4, 8, 11, 6, 10)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape WEST = Stream.of(
                        Block.box(4, 0, 14, 6, 3, 16),
                        Block.box(4, 0, 0, 6, 3, 2),
                        Block.box(4, 0, 4, 6, 3, 6),
                        Block.box(8, 0, 0, 10, 3, 2),
                        Block.box(10, 0, 8, 12, 3, 10),
                        Block.box(10, 0, 14, 12, 3, 16),
                        Block.box(4, 3, 0, 10, 14, 6),
                        Block.box(4, 3, 8, 12, 16, 16),
                        Block.box(5, 4, 6, 8, 6, 8)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> CAJON = register("cajon",
            () -> new CajonInstrument(BlockBehaviour.Properties.of().sound(SoundType.BAMBOO_WOOD)){
                public static final VoxelShape ALL = Block.box(2, 0, 2, 14, 14, 14);
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> VOICE_MICROPHONE = register("voice_microphone",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of().sound(SoundType.LANTERN)){
                public static final VoxelShape ALL = Stream.of(
                        Block.box(7, 1, 7, 9, 10, 9),
                        Block.box(6, 0, 6, 10, 1, 10),
                        Block.box(6, 6, 6, 10, 12, 10)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    return ALL;
                }
            });

    public static final DeferredBlock<Block> TEDDY_BEAR = register("teddy_bear",
            () -> new ReindeerPlush(BlockBehaviour.Properties.of().sound(SoundType.WOOL).noOcclusion()
                    .strength(1f,3f)
                    .mapColor(MapColor.COLOR_BROWN).ignitedByLava().instabreak()));

    public static final DeferredBlock<Block> CHIMNEY = register("chimney",
            () -> new ChimneyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).noOcclusion()));

    public static final DeferredBlock<Block> MYSTERIOUS_PILLAR = register("mysterious_pillar",
            () -> new ConnectingVerticalPillarBlock(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops().strength(2.0F, 700.0F)
                    .mapColor(MapColor.COLOR_MAGENTA).sound(SoundType.GILDED_BLACKSTONE)
                    .instrument(NoteBlockInstrument.BELL)){

                @Override
                public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> lc, TooltipFlag p_49819_) {
                    lc.add(Component.translatable("block.thingamajigs.mysterious_pillar.desc").withStyle(ChatFormatting.GRAY));
                }
            });

    public static final DeferredBlock<Block> GOAL = register("goal",
            () -> new ThingamajigsDecorativeBlock(BlockBehaviour.Properties.of()){
                public static final VoxelShape NORTH = Stream.of(
                        Block.box(-16, 0, 0, -14, 32, 2),
                        Block.box(30, 0, 0, 32, 32, 2),
                        Block.box(-14, 30, 0, 30, 32, 2),
                        Block.box(30, 0, 14, 32, 16, 16),
                        Block.box(-16, 0, 14, -14, 16, 16),
                        Block.box(-14, 1, 15, 30, 11.5, 15),
                        Block.box(-15, 0, 2, -15, 16, 14),
                        Block.box(31, 0, 2, 31, 16, 14),
                        Block.box(-16, 13, 9, 32, 16, 14),
                        Block.box(-16, 16, 8, 32, 19, 13),
                        Block.box(-16, 19, 7, 32, 22, 12),
                        Block.box(-16, 22, 4, 32, 25, 9),
                        Block.box(-16, 25, 1, 32, 28, 6)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape EAST = Stream.of(
                        Block.box(14, 0, -16, 16, 32, -14),
                        Block.box(14, 0, 30, 16, 32, 32),
                        Block.box(14, 30, -14, 16, 32, 30),
                        Block.box(0, 0, 30, 2, 16, 32),
                        Block.box(0, 0, -16, 2, 16, -14),
                        Block.box(1, 1, -14, 1, 11.5, 30),
                        Block.box(2, 0, -15, 14, 16, -15),
                        Block.box(2, 0, 31, 14, 16, 31),
                        Block.box(2, 13, -16, 7, 16, 32),
                        Block.box(3, 16, -16, 8, 19, 32),
                        Block.box(4, 19, -16, 9, 22, 32),
                        Block.box(7, 22, -16, 12, 25, 32),
                        Block.box(10, 25, -16, 15, 28, 32)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape SOUTH = Stream.of(
                        Block.box(30, 0, 14, 32, 32, 16),
                        Block.box(-16, 0, 14, -14, 32, 16),
                        Block.box(-14, 30, 14, 30, 32, 16),
                        Block.box(-16, 0, 0, -14, 16, 2),
                        Block.box(30, 0, 0, 32, 16, 2),
                        Block.box(-14, 1, 1, 30, 11.5, 1),
                        Block.box(31, 0, 2, 31, 16, 14),
                        Block.box(-15, 0, 2, -15, 16, 14),
                        Block.box(-16, 13, 2, 32, 16, 7),
                        Block.box(-16, 16, 3, 32, 19, 8),
                        Block.box(-16, 19, 4, 32, 22, 9),
                        Block.box(-16, 22, 7, 32, 25, 12),
                        Block.box(-16, 25, 10, 32, 28, 15)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape WEST = Stream.of(
                        Block.box(0, 0, 30, 2, 32, 32),
                        Block.box(0, 0, -16, 2, 32, -14),
                        Block.box(0, 30, -14, 2, 32, 30),
                        Block.box(14, 0, -16, 16, 16, -14),
                        Block.box(14, 0, 30, 16, 16, 32),
                        Block.box(15, 1, -14, 15, 11.5, 30),
                        Block.box(2, 0, 31, 14, 16, 31),
                        Block.box(2, 0, -15, 14, 16, -15),
                        Block.box(9, 13, -16, 14, 16, 32),
                        Block.box(8, 16, -16, 13, 19, 32),
                        Block.box(7, 19, -16, 12, 22, 32),
                        Block.box(4, 22, -16, 9, 25, 32),
                        Block.box(1, 25, -16, 6, 28, 32)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                public static final VoxelShape NORTH_INTERACTION = Shapes.join(NORTH,DoubleTallDecorationBlock.BLOCK_SHAPE,BooleanOp.OR);
                public static final VoxelShape SOUTH_INTERACTION = Shapes.join(SOUTH,DoubleTallDecorationBlock.BLOCK_SHAPE,BooleanOp.OR);
                public static final VoxelShape EAST_INTERACTION = Shapes.join(EAST,DoubleTallDecorationBlock.BLOCK_SHAPE,BooleanOp.OR);
                public static final VoxelShape WEST_INTERACTION = Shapes.join(WEST,DoubleTallDecorationBlock.BLOCK_SHAPE,BooleanOp.OR);

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH_INTERACTION;
                        }
                        case SOUTH -> {
                            return SOUTH_INTERACTION;
                        }
                        case EAST -> {
                            return EAST_INTERACTION;
                        }
                        case WEST -> {
                            return WEST_INTERACTION;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }

                @Override
                public VoxelShape getCollisionShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH -> {
                            return NORTH;
                        }
                        case SOUTH -> {
                            return SOUTH;
                        }
                        case EAST -> {
                            return EAST;
                        }
                        case WEST -> {
                            return WEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    // 1.8.0-1.8.4
    public static final DeferredBlock<Block> ANIMATED_ICE_RINK = registerBlockWithoutItem("animated_ice_rink",
            () -> new AnimatedIceRink(BlockBehaviour.Properties.of().lightLevel(enabledLitBlockEmission(5))));

    public static final DeferredBlock<Block> NEWSPAPER_DISPENSER = register("newspaper_dispenser",
            () -> new NewspaperDispenser(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> RESTAURANT_TRASH_CAN = register("restaurant_trash_can",
            () -> new NewspaperDispenser(BlockBehaviour.Properties.of()){
                @Override
                protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    switch(state.getValue(FACING)){
                        case NORTH -> {
                            return NewspaperDispenser.NORTH_TRASH_SHAPE;
                        }
                        case EAST -> {
                            return NewspaperDispenser.EAST_TRASH_SHAPE;
                        }
                        case SOUTH -> {
                            return NewspaperDispenser.SOUTH_TRASH_SHAPE;
                        }
                        case WEST -> {
                            return NewspaperDispenser.WEST_TRASH_SHAPE;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> SPECIAL_STATUE = register("special_statue",
            () -> new Podium(BlockBehaviour.Properties.of().sound(SoundType.METAL).mapColor(MapColor.TERRACOTTA_WHITE)){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("statue.thingamajigs.author.blueman")
                            .withStyle(ChatFormatting.GREEN));
                }
            });

    public static final DeferredBlock<Block> SNOW_MACHINE = register("snow_machine",
            () -> new SnowMachine(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> BALL_PIT = register("ball_pit",
            () -> new BallPit(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> BONDING_STATUE = register("bonding_statue",
            () -> new BondingStatue(BlockBehaviour.Properties.of()));

    public static final DeferredBlock<Block> CATCHING_STATUE = register("catching_statue",
            () -> new Podium(BlockBehaviour.Properties.of().sound(SoundType.METAL).mapColor(MapColor.EMERALD).requiresCorrectToolForDrops()){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("statue.thingamajigs.author.blueman")
                            .withStyle(ChatFormatting.GREEN));
                }
            });

    public static final DeferredBlock<Block> STRANGE_STATUE = registerBlockWithoutItem("strange_statue",
            () -> new Podium(BlockBehaviour.Properties.of().sound(SoundType.NETHERITE_BLOCK).mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops()){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("statue.thingamajigs.author.cmb")
                            .withStyle(ChatFormatting.BLUE));
                }
            });

    public static final DeferredBlock<Block> ANIMATED_DEER = registerBlockWithoutItem("animated_deer",
            () -> new AnimatedDeer(BlockBehaviour.Properties.of().lightLevel(enabledLitBlockEmission(7))));

    public static final DeferredBlock<Block> VALIANT_STATUE = registerBlockWithoutItem("valiant_statue",
            () -> new Podium(BlockBehaviour.Properties.of().sound(SoundType.AMETHYST).mapColor(MapColor.COLOR_PURPLE).requiresCorrectToolForDrops()){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("statue.thingamajigs.author.blueman")
                            .withStyle(ChatFormatting.GREEN));
                }

                @Override
                public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                    return ALL_SHORT;
                }
            });
    public static final DeferredBlock<Block> ROUND_BUSH = register("round_bush",
            () -> new BushLikeBlock(BlockBehaviour.Properties.of().sound(SoundType.SWEET_BERRY_BUSH)){
                @Override
                protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    Vec3 stateOffset = state.getOffset(level,pos);
                    return SAPLING_ALL.move(stateOffset.x,stateOffset.y,stateOffset.z);
                }
            });
    public static final DeferredBlock<Block> BULBLET = register("bulblet",
            () -> new BushLikeBlock(BlockBehaviour.Properties.of().sound(SoundType.GRASS)){
                @Override
                protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    Vec3 stateOffset = state.getOffset(level,pos);
                    return FLOWER_ALL.move(stateOffset.x,stateOffset.y,stateOffset.z);
                }
            });
    public static final DeferredBlock<Block> WISPY_WEED = register("wispy_weed",
            () -> new BushLikeBlock(BlockBehaviour.Properties.of().sound(SoundType.AZALEA_LEAVES)){
                @Override
                protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    Vec3 stateOffset = state.getOffset(level,pos);
                    return FLOWER_ALL.move(stateOffset.x,stateOffset.y,stateOffset.z);
                }
            });

    public static final DeferredBlock<Block> FOOD_COOLER = register("food_cooler",
            () -> new OpenableContainerBlock(BlockBehaviour.Properties.of().sound(SoundType.CALCITE)
                    .strength(1f,15f).noOcclusion().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)){
                public static final VoxelShape NORTHSOUTH = Stream.of(
                        Block.box(14, 6, 6, 15, 7, 10),
                        Block.box(2, 0, 3, 14, 10, 13),
                        Block.box(1, 6, 6, 2, 7, 10)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
                public static final VoxelShape EASTWEST = Stream.of(
                        Block.box(6, 6, 14, 10, 7, 15),
                        Block.box(3, 0, 2, 13, 10, 14),
                        Block.box(6, 6, 1, 10, 7, 2)
                ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

                @Override
                public void playCustomSound(Level lvl, BlockPos bp) {
                    lvl.playSound(null,bp,SoundEvents.CALCITE_FALL, SoundSource.BLOCKS,1.0f,1.0f);
                }

                @Override
                public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
                    switch(bs.getValue(FACING)){
                        case NORTH,SOUTH -> {
                            return NORTHSOUTH;
                        }
                        default -> {
                            return EASTWEST;
                        }
                    }
                }
            });

    public static final DeferredBlock<Block> FOOTBALL_GOAL = registerBlockWithoutItem("football_goal",
            () -> new FootballGoal(BlockBehaviour.Properties.of().sound(SoundType.LANTERN).noOcclusion()){
                public static VoxelShape makeShape(){
                    VoxelShape shape = Shapes.empty();
                    shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.375, 0.625, 3, 0.625), BooleanOp.OR);
                    shape = Shapes.join(shape, Shapes.box(-1.5, 3, 0.375, 2.5, 3.125, 0.625), BooleanOp.OR);
                    shape = Shapes.join(shape, Shapes.box(-1.5, 3.125, 0.375, -1.375, 6.125, 0.625), BooleanOp.OR);
                    shape = Shapes.join(shape, Shapes.box(2.375, 3.125, 0.375, 2.5, 6.125, 0.625), BooleanOp.OR);
                    return shape;
                }

                public static VoxelShape makeAltShape(){
                    VoxelShape shape = Shapes.empty();
                    shape = Shapes.join(shape, Shapes.box(0.375, 3.125, -1.5, 0.625, 6.125, -1.375), BooleanOp.OR);
                    shape = Shapes.join(shape, Shapes.box(0.375, 0, 0.375, 0.625, 3.125, 0.625), BooleanOp.OR);
                    shape = Shapes.join(shape, Shapes.box(0.375, 3, -1.5, 0.625, 3.125, 2.5), BooleanOp.OR);
                    shape = Shapes.join(shape, Shapes.box(0.375, 3.125, 2.375, 0.625, 6.125, 2.5), BooleanOp.OR);
                    return shape;
                }

                public static final VoxelShape NORTHSOUTH = makeShape();
                public static final VoxelShape EASTWEST = makeAltShape();


                @Override
                public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
                    switch(state.getValue(FACING)){
                        case NORTH,SOUTH -> {
                            return NORTHSOUTH;
                        }
                        case EAST,WEST -> {
                            return EASTWEST;
                        }
                        default -> {
                            return Shapes.block();
                        }
                    }
                }
            });





    // test features
    public static final DeferredBlock<Block> FAKE_FLUID_PUMP = register("fake_fluid_pump",
            () -> new FakeFluidPump(BlockBehaviour.Properties.of()));

    private static DeferredBlock<Block> register(String name, Supplier<Block> block) {
        DeferredBlock<Block> blk = BLOCKS.register(name,block);
        DeferredItem<Item> itm = TItems.ITEMS.register(name,() -> new BlockItem(blk.get(),new Item.Properties()));
        return blk;
    }

    private static DeferredBlock<Block> registerBlockWithoutItem(String name, Supplier<Block> block){
        return BLOCKS.register(name,block);
    }

    private static boolean always(BlockState bs, BlockGetter bg, BlockPos bp){return true;}
    private static boolean never(BlockState bs, BlockGetter bg, BlockPos bp){return false;}

    private static ToIntFunction<BlockState> customLitBlockEmission(int lv) {
        return (properties) -> {
            return properties.getValue(BlockStateProperties.LIT) ? lv : 0;
        };
    }

    private static ToIntFunction<BlockState> openSignLitEmission(int lv) {
        return (properties) -> {
            return properties.getValue(OpenSign.TOGGLED) ? 0 : lv;
        };
    }

    private static ToIntFunction<BlockState> modeLitBlockEmission(int i) {
        return (properties) -> {
            return properties.getValue(ArrowBoard.MODE);
        };
    }

    private static ToIntFunction<BlockState> tricandleblkem(int lv) {
        return (properties) -> {
            return properties.getValue(TriCandleHolder.LIT) ? lv : 0;
        };
    }

    private static ToIntFunction<BlockState> enabledLitBlockEmission(int i) {
        return (properties) -> {
            return properties.getValue(BlockStateProperties.ENABLED) ? i : 0;
        };
    }

    private static ToIntFunction<BlockState> enableToggleBlockLitBlockEmission(int i) {
        return (properties) -> {
            return properties.getValue(ToggledStateBlock.TOGGLED) ? i : 0;
        };
    }

    private static ToIntFunction<BlockState> onLitBlockEmission(int i) {
        return (properties) -> {
            return properties.getValue(TallLamp.ON) ? i : 0;
        };
    }

    public static Boolean onlyPlayersSpawnAllow(BlockState bs, BlockGetter bg, BlockPos bp, EntityType<?> entt){
        if(bs.is(TBlocks.RUBBER_LEAVES)){
            if(bs.getValue(CustomLeavesBlock.PERSISTENT)){
                return false;
            }
        }
        return entt == EntityType.PLAYER;
    }

    /*
    private static ToIntFunction<BlockState> rrCrossingLightsEmission(int i) {
        return (properties) -> {
            return properties.getValue(RailroadCrossingLights.POWERED) ? i : 0;
        };
    }

    private static ToIntFunction<BlockState> rrCrossingCantileverLightEmission(int i) {
        return (properties) -> {
            return properties.getValue(RailroadCrossingCantilever.POWERED) ? i : 0;
        };
    }

     */
}
