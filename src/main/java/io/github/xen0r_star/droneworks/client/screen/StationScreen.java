package io.github.xen0r_star.droneworks.client.screen;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.network.PlayButtonPayload;
import io.github.xen0r_star.droneworks.screen.StationScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class StationScreen extends HandledScreen<StationScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/container/station_block.png");
    private static final Identifier PLAY_BUTTON = Identifier.of(Main.MOD_ID, "textures/gui/play.png");
    private static final Identifier PAUSE_BUTTON = Identifier.of(Main.MOD_ID, "textures/gui/pause.png");

    private final Item[] items = {Items.WHEAT, Items.CARROT, Items.POTATO, Items.BEETROOT};
    private int displayedIndex = 0;
    private ItemStack displayedStack = new ItemStack(items[displayedIndex]);
    private Identifier textureButton;
    private boolean play;
    private final StationScreenHandler handler;


    public StationScreen(StationScreenHandler handler, PlayerInventory inv, Text title) {
        super(handler, inv, title);
        this.handler = handler;
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
        this.play = handler.isPlaying();
        this.textureButton = this.play ? PLAY_BUTTON : PAUSE_BUTTON;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;

        this.addDrawableChild(ButtonWidget.builder(
            Text.empty(), btn -> {
                displayedIndex = (displayedIndex + 1) % items.length;
                displayedStack = new ItemStack(items[displayedIndex]);
            }
        ).dimensions(this.width / 2 - 81, this.height / 2 - 33, 20, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            Text.empty(), btn -> {
                play = !play;

                if (textureButton == PLAY_BUTTON) {
                    textureButton = PAUSE_BUTTON;
                } else {
                    textureButton = PLAY_BUTTON;
                }

                if (client != null && client.player != null) {
                    ClientPlayNetworking.send(new PlayButtonPayload(this.handler.getBlockPos(), play));
                }
            }
        ).dimensions(this.width / 2 - 81, this.height / 2 - 67, 20, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);

        int x1 = this.width / 2 - 79;
        int y1 = this.height / 2 - 31;

        int x2 = this.width / 2 - 79;
        int y2 = this.height / 2 - 65;

        LivingEntity entity = MinecraftClient.getInstance().player;

        context.drawItem(entity, displayedStack, x1, y1, 0);
        context.drawTexture(
            RenderPipelines.GUI_TEXTURED_PREMULTIPLIED_ALPHA, textureButton,
            x2, y2,
            0, 0,
            16, 16,
            16, 16
        );
    }

    @Override
    protected void drawBackground(DrawContext ctx, float delta, int mouseX, int mouseY) {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        ctx.drawTexture(
            RenderPipelines.GUI_TEXTURED_PREMULTIPLIED_ALPHA,
            TEXTURE,
            x, y,
            0, 0,
            this.backgroundWidth, this.backgroundHeight,
            this.backgroundWidth, this.backgroundHeight
        );

        ctx.drawTextWithShadow(this.textRenderer, this.title.getString(), x + (this.backgroundWidth - this.textRenderer.getWidth(this.title.getString())) / 2, y + 6, 4210752);
        ctx.drawTextWithShadow(this.textRenderer, this.playerInventoryTitle.getString(), x + 8, y + this.backgroundHeight - 94 + 10, 4210752);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        int titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
        int titleY = 6;
        context.drawText(this.textRenderer, this.title, titleX, titleY, 4210752, false);

        int invX = 8;
        int invY = this.backgroundHeight - 94 + 10;
        context.drawText(this.textRenderer, this.playerInventoryTitle, invX, invY, 4210752, false);
    }


    public boolean getPlay() {
        return play;
    }

    public Item getItemSelected() {
        return items[displayedIndex];
    }
}
