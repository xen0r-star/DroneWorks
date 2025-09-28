package io.github.xen0r_star.droneworks.client.screen;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.client.renderer.DRONE_COLOR;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import io.github.xen0r_star.droneworks.registry.ModEntities;
import io.github.xen0r_star.droneworks.screen.BoxScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;
import org.joml.Vector3f;


@Environment(EnvType.CLIENT)
public class BoxScreen extends HandledScreen<BoxScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/container/box_block1.png");
    private Float counter = 0.0F;

    public BoxScreen(BoxScreenHandler handler, PlayerInventory inv, Text title) {
        super(handler, inv, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 183;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;

        this.addDrawableChild(
            ButtonWidget.builder(Text.literal("Start"), btn -> {
                System.out.println("Box Start button pressed!");
                counter -= 0.1F;
                System.out.println(counter);
            }).dimensions(this.width / 2 - 81, this.height / 2 - 23, 73, 18).build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);

        if (this.client != null && this.client.world != null) {
            DroneEntity drone = new DroneEntity(ModEntities.DRONE, this.client.world);

            drone.setColor(DRONE_COLOR.DEFAULT);
            drone.setAngles(180, 180);
            drone.setPos(drone.getX(), drone.getY() + 0.5, drone.getZ());


            int x1 = this.width / 2 - 80;
            int y1 = this.height / 2 - 76;
            int x2 = x1 + 71;
            int y2 = y1 + 49;
            Vector3f droneTranslation = new Vector3f(0.0F, counter, 0.0F);
            Quaternionf droneRotation = (new Quaternionf()).rotationXYZ(1.4F, 0.0F, 0.0F);

            InventoryScreen.drawEntity(context,
                x1, y1,
                x2, y2,
                30.0f,
                droneTranslation, droneRotation,
                null, drone
            );
        }
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
