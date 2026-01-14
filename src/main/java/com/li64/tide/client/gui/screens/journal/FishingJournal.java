package com.li64.tide.client.gui.screens.journal;

import com.li64.tide.Tide;
import com.li64.tide.client.TideCoreShaders;
import com.li64.tide.client.TideRenderTypes;
import com.li64.tide.data.TideData;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.journal.JournalGroup;
import com.li64.tide.data.player.TidePlayerData;
import com.li64.tide.network.messages.ReadProfileMsg;
import com.li64.tide.registries.TideSoundEvents;
import com.li64.tide.util.TideUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.*;
import java.util.stream.Collectors;

public class FishingJournal extends Screen {
    public static final int TEXT_COLOR = 0xc09473;

    static final ResourceLocation BACKGROUND = Tide.resource("textures/gui/journal/journal_bg.png");
    static final int BG_WIDTH = 400;
    static final int BG_HEIGHT = 260;

    static final ResourceLocation LINE_TOP = TideUtils.sprite("journal/line_top");
    static final ResourceLocation LINE_BOTTOM = TideUtils.sprite("journal/line_bottom");

    private static final int AREA_HEIGHT = 190;
    private static final int CELL_SIZE = 22;
    private static final int GROUP_SPACING = 10;
    private static final int FISH_PER_ROW = 14;

    private final Player player;
    private final List<Map<JournalGroup, List<Holder<Item>>>> fishByPage;
    private int page = 0;

    private @Nullable ItemStack activeFish = null;
    private @Nullable FishProfile profile = null;
    private @Nullable Button xButton = null;
    private @Nullable Button leftButton = null;
    private @Nullable Button rightButton = null;
    private float yOffset = 3f;
    private boolean didClick;

    public FishingJournal(Player player) {
        super(GameNarrator.NO_TITLE);
        this.player = player;
        this.fishByPage = paginate(TideData.FISH.get().valueStream()
                .filter(FishData::hasJournalEntry)
                .sorted(Comparator.comparing((FishData a) -> a.profile().group().ordinal())
                        .thenComparing(a -> a.profile().rarity().ordinal())
                        .thenComparingDouble(d -> -d.weight()))
                .collect(Collectors.groupingBy(
                        data -> data.profile().group(),
                        LinkedHashMap::new,
                        Collectors.mapping(FishData::fish, Collectors.toList())
                )));

        this.init();
        player.playSound(TideSoundEvents.JOURNAL_OPEN, 0.9f, 1.0f + new Random().nextFloat() * 0.2f);
    }

    public static List<Map<JournalGroup, List<Holder<Item>>>> paginate(Map<JournalGroup, List<Holder<Item>>> groupedFish) {
        List<Map<JournalGroup, List<Holder<Item>>>> pages = new ArrayList<>();
        Map<JournalGroup, List<Holder<Item>>> currentPage = new LinkedHashMap<>();
        int usedHeight = 0;

        for (var entry : groupedFish.entrySet()) {
            JournalGroup group = entry.getKey();
            List<Holder<Item>> fish = entry.getValue();

            int totalRows = (int) Math.ceil(fish.size() / (double) FISH_PER_ROW);
            int start = 0;
            for (int row = 0; row < totalRows; row++) {
                int end = Math.min(start + FISH_PER_ROW, fish.size());
                List<Holder<Item>> rowFish = fish.subList(start, end);

                if (usedHeight + CELL_SIZE > AREA_HEIGHT) {
                    pages.add(currentPage);
                    currentPage = new LinkedHashMap<>();
                    usedHeight = 0;
                }

                currentPage.computeIfAbsent(group, g -> new ArrayList<>()).addAll(rowFish);
                usedHeight += CELL_SIZE;
                start = end;
            }

            if (usedHeight > 0 && usedHeight + GROUP_SPACING <= AREA_HEIGHT)
                usedHeight += GROUP_SPACING;
        }

        if (!currentPage.isEmpty()) pages.add(currentPage);
        return pages;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void pageChanged() {
        yOffset = 3f;
        player.playSound(TideSoundEvents.PAGE_FLIP, 1.0f, 1.0f + new Random().nextFloat() * 0.2f);
        this.init();
    }

    @Override
    protected void init() {
        this.clearWidgets();
        this.leftButton = null;
        this.rightButton = null;
        int xX = Math.min(this.width - 19, (width + BG_WIDTH) / 2 - 27);
        int xY = Math.max(3, (height - BG_HEIGHT) / 2 + 12);
        int leftX = Math.max(3, (width - BG_WIDTH) / 2 + 11);
        int rightX = Math.min(this.width - 19, (width + BG_WIDTH) / 2 - 27);
        if (activeFish != null) {
            this.profile = new FishProfile(activeFish, this);
            this.xButton = Button.builder(Component.literal("X"), button -> {
                this.activeFish = null;
                this.pageChanged();
            }).bounds(xX, xY, 16, 16).build();
        }
        else {
            this.profile = null;
            this.xButton = Button.builder(Component.literal("X"), button -> onClose())
                    .bounds(xX, xY, 16, 16).build();
            if (page > 0) {
                this.leftButton = Button.builder(Component.literal("<<"), button -> pageLeft())
                        .bounds(leftX, this.height / 2 - 8, 16, 16).build();
                this.addWidget(leftButton);
            }
            if (page < fishByPage.size() - 1) {
                this.rightButton = Button.builder(Component.literal(">>"), button -> pageRight())
                        .bounds(rightX, this.height / 2 - 8, 16, 16).build();
                this.addWidget(rightButton);
            }
        }
        this.addWidget(xButton);
    }

    private void pageLeft() {
        page--;
        if (page < 0) page = fishByPage.size() - 1;
        this.pageChanged();
    }

    private void pageRight() {
        page++;
        if (page == fishByPage.size()) page = 0;
        this.pageChanged();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!super.mouseClicked(mouseX, mouseY, button)) this.didClick = true;
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return switch (keyCode) {
            // escape
            case 256, 69 -> {
                if (this.activeFish == null) this.onClose();
                else {
                    this.activeFish = null;
                    this.pageChanged();
                }
                yield true;
            }
            // right arrow
            case 262 -> {
                if (this.activeFish == null) pageRight();
                yield true;
            }
            // left arrow
            case 263 -> {
                if (this.activeFish == null) pageLeft();
                yield true;
            }
            default -> super.keyPressed(keyCode, scanCode, modifiers);
        };
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void onClose() {
        super.onClose();
        player.playSound(TideSoundEvents.JOURNAL_CLOSE, 0.9f, 1.0f + new Random().nextFloat() * 0.2f);
    }

    public Font getFont() {
        return this.font;
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        //? if >=1.21 {
        float deltaTicks = Minecraft.getInstance().getTimer().getRealtimeDeltaTicks();
        //?} else {
        /*float deltaTicks = Minecraft.getInstance().getDeltaFrameTime();
        this.renderBackground(graphics);
        *///?}
        super.render(graphics, mouseX, mouseY, partialTick);

        boolean updatePage = false;

        this.yOffset = Math.max(yOffset - yOffset * 0.65f * deltaTicks, 0f);
        graphics.pose().pushPose();
        graphics.pose().translate(0f, -(int) yOffset, 0f);

        int tlX = (width - BG_WIDTH) / 2;
        int tlY = (height - BG_HEIGHT) / 2;
        graphics.blit(BACKGROUND, tlX, tlY, 0, 0, BG_WIDTH, BG_HEIGHT, BG_WIDTH, BG_HEIGHT);

        if (profile != null) profile.render(graphics, mouseX, mouseY, partialTick);
        else {
            // render fish grid
            final int hoverTolerance = 3;
            int cursorY = 0;

            int groupIndex = 0;
            for (var fish : fishByPage.get(page).values()) {
                int gridX = tlX + 44;
                int gridY = tlY + 39 + cursorY;
                int i = 0;

                for (Holder<Item> fishItem : fish) {
                    int cellX = i % FISH_PER_ROW;
                    int cellY = Mth.floor((float) i / FISH_PER_ROW);
                    int x = gridX + cellX * CELL_SIZE;
                    int y = gridY + cellY * CELL_SIZE;
                    ItemStack stack = new ItemStack(fishItem);
                    boolean isUnlocked = TidePlayerData.CLIENT_DATA.isFishUnlocked(fishItem);
                    boolean isUnread = TidePlayerData.CLIENT_DATA.isUnread(stack);
                    boolean isHovering = false;

                    if (mouseX > x - hoverTolerance && mouseY > y - hoverTolerance
                            && mouseX < x + 16 + hoverTolerance && mouseY < y + 16 + hoverTolerance) {
                        isHovering = true;
                        if (isUnread) {
                            TidePlayerData.CLIENT_DATA.markAsRead(stack);
                            Tide.NETWORK.sendToServer(new ReadProfileMsg(stack));
                        }
                        if (isUnlocked) {
                            graphics.renderTooltip(this.font, stack.getHoverName(), mouseX, mouseY);
                            if (didClick) {
                                activeFish = stack;
                                updatePage = true;
                            }
                        }
                    }

                    // render shadow
                    graphics.flush();
                    RenderSystem.setShaderColor(0.8431f, 0.7098f, 0.5804f, 1f);
                    renderItemSilhouette(graphics, stack, x + 1, y + 1);

                    // render outline
                    if (isHovering) {
                        graphics.flush();
                        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                        drawOutline(graphics, stack, x, y);
                    }
                    else if (Tide.CONFIG.journal.showUnread && isUnlocked && isUnread) {
                        graphics.flush();
                        RenderSystem.enableBlend();
                        RenderSystem.setShaderColor(1f, 0.88f, 0f, 1f);
                        drawOutline(graphics, stack, x, y);
                        RenderSystem.disableBlend();
                    }

                    // render fish
                    graphics.flush();
                    if (isUnlocked) {
                        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                        graphics.renderItem(stack, x, y);
                    } else {
                        RenderSystem.setShaderColor(0f, 0f, 0f, 1f);
                        graphics.renderItem(stack, x, y);
                        graphics.flush();
                        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
                    }

                    i++;
                }

                cursorY += Mth.floor((float) (i - 1) / FISH_PER_ROW + 1) * CELL_SIZE + GROUP_SPACING;

                groupIndex++;
                if (groupIndex < fishByPage.get(page).size())
                    //? if >=1.21 {
                    graphics.blitSprite(LINE_BOTTOM, tlX + 40, tlY + 36 + cursorY - GROUP_SPACING / 2, 327, 2);
                     //?} else {
                    /*TideUtils.blitNineSliced(graphics, LINE_BOTTOM, tlX + 40,
                            tlY + 36 + cursorY - GROUP_SPACING / 2, 327, 2, 3, 0,
                            71, 2, 0, 0, 71, 2);
                    *///?}
            }
        }

        if (xButton != null) xButton.render(graphics, mouseX, mouseY, partialTick);
        if (leftButton != null) leftButton.render(graphics, mouseX, mouseY, partialTick);
        if (rightButton != null) rightButton.render(graphics, mouseX, mouseY, partialTick);
        graphics.pose().popPose();

        if (updatePage) pageChanged();
        this.didClick = false;
    }

    private void drawOutline(@NotNull GuiGraphics graphics, ItemStack stack, int x, int y) {
        final int[][] offs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] off : offs) renderItemSilhouette(graphics, stack, x + off[0], y + off[1]);
    }

    public static void renderTextureSilhouette(GuiGraphics graphics, ResourceLocation texture, int x, int y, int w, int h) {
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(TideCoreShaders::fullWhite);
        Matrix4f pose = graphics.pose().last().pose();

        //? if >=1.21 {
        BufferBuilder builder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        builder.addVertex(pose, x, y, 0).setUv(0, 0);
        builder.addVertex(pose, x, y + h, 0).setUv(0, 1);
        builder.addVertex(pose, x + w, y + h, 0).setUv(1, 1);
        builder.addVertex(pose, x + w, y, 0).setUv(1, 0);
        BufferUploader.drawWithShader(builder.buildOrThrow());
        //?} else {
        /*BufferBuilder builder = Tesselator.getInstance().getBuilder();
        builder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        builder.vertex(pose, x, y, 0).uv(0, 0).endVertex();
        builder.vertex(pose, x, y + h, 0).uv(0, 1).endVertex();
        builder.vertex(pose, x + w, y + h, 0).uv(1, 1).endVertex();
        builder.vertex(pose, x + w, y, 0).uv(1, 0).endVertex();
        BufferUploader.drawWithShader(builder.end());
        *///?}
    }

    public static void renderItemSilhouette(GuiGraphics graphics, ItemStack stack, int x, int y) {
        Minecraft minecraft = Minecraft.getInstance();
        BakedModel model = minecraft.getItemRenderer().getModel(stack, minecraft.level, minecraft.player, 0);

        graphics.pose().pushPose();
        graphics.pose().translate(x + 8f, y + 8f, 150f);
        graphics.pose().scale(16.0f, -16.0f, 16.0f);

        graphics.pose().pushPose();

        model.getTransforms().getTransform(ItemDisplayContext.GUI).apply(false, graphics.pose());
        graphics.pose().translate(-0.5F, -0.5F, -0.5F);

        RenderType renderType = TideRenderTypes.singleColorItem();
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(graphics.bufferSource(), renderType, true, stack.hasFoil());

        Minecraft.getInstance().getItemRenderer().renderModelLists(
                model, stack, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY,
                graphics.pose(), vertexConsumer
        );

        graphics.pose().popPose();

        graphics.flush();
        graphics.pose().popPose();
    }
}