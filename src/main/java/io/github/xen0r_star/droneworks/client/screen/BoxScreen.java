package io.github.xen0r_star.droneworks.client.screen;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.screen.BoxScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BoxScreen extends HandledScreen<BoxScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/container/box_block.png");

    public BoxScreen(BoxScreenHandler handler, PlayerInventory inv, Text title) {
        super(handler, inv, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 183;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
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
}
