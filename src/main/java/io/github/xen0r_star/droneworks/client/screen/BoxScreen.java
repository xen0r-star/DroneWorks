package io.github.xen0r_star.droneworks.client.screen;

import io.github.xen0r_star.droneworks.Main;
import io.github.xen0r_star.droneworks.block.BoxBlockEntity;
import io.github.xen0r_star.droneworks.client.model.DroneModel;
import io.github.xen0r_star.droneworks.client.renderer.DRONE_COLOR;
import io.github.xen0r_star.droneworks.client.renderer.DroneRenderer;
import io.github.xen0r_star.droneworks.entity.DroneEntity;
import io.github.xen0r_star.droneworks.registry.ModEntities;
import io.github.xen0r_star.droneworks.registry.ModItems;
import io.github.xen0r_star.droneworks.screen.BoxScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Quaternionf;
import org.joml.Vector3f;


@Environment(EnvType.CLIENT)
public class BoxScreen extends HandledScreen<BoxScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Main.MOD_ID, "textures/gui/container/box_block1.png");
    private ButtonWidget startButton;
    private PlayerInventory inventory;

    public BoxScreen(BoxScreenHandler handler, PlayerInventory inv, Text title) {
        super(handler, inv, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 183;
        this.inventory = inv;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;

        startButton = ButtonWidget.builder(Text.translatable("button.text.box"), btn -> {
            if (handler.isCraftButtonActive()) {
                System.out.println("ready");
//                this.handler.startCraftingServerSide();

            } else if (handler.isRetrieveButtonActive()) {
//                handler.retrieveServerSide(inventory.player);
            }

        }).dimensions(this.width / 2 - 81, this.height / 2 - 23, 73, 18).build();

        this.addDrawableChild(startButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);

        if (this.client != null && this.client.world != null) {
            DroneEntity drone = new DroneEntity(ModEntities.DRONE, this.client.world);
            DroneRenderer renderer = (DroneRenderer) MinecraftClient.getInstance()
                    .getEntityRenderDispatcher()
                    .getRenderer(drone);
            DroneModel model = renderer.getModel();

            drone.setAngles(180, 180);
            drone.setPos(drone.getX(), drone.getY() + 0.5, drone.getZ());


            model.antenna.visible =    !this.handler.getSlot(0).getStack().isEmpty();
            model.propeller1.visible = !this.handler.getSlot(1).getStack().isEmpty();
            model.body.visible =       !this.handler.getSlot(2).getStack().isEmpty();
            model.propeller2.visible = !this.handler.getSlot(3).getStack().isEmpty();


            ItemStack stack = this.handler.getSlot(6).getStack();
            DRONE_COLOR color = DRONE_COLOR.fromItem(stack.getItem());
            if (color != null) drone.setColor(color);


            int x1 = this.width / 2 - 80;
            int y1 = this.height / 2 - 76;
            int x2 = x1 + 71;
            int y2 = y1 + 41;
            Vector3f droneTranslation = new Vector3f(0.45F, -1.0F, 0.0F);
            Quaternionf droneRotation = (new Quaternionf()).rotationXYZ(1.7F, 0.0F, 0.3F);

            InventoryScreen.drawEntity(context,
                x1, y1,
                x2, y2,
                35.0f,
                droneTranslation, droneRotation,
                null, drone
            );

            startButton.active = allFilled();
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


        int progressWidth = 0;
//        if (this.handler.getBlockEntity() instanceof BoxBlockEntity station) {
//            progressWidth = (int) ((double) station.getProgress() / station.getProgressMax() * 71);
//        }

        int x1 = this.width / 2 - 80;
        int y1 = this.height / 2 - 28;
        int x2 = x1 + progressWidth;
        int y2 = y1 + 22;
        ctx.fill(x1, y1, x2, y2, 0xFF00FF00);
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

    private boolean allFilled() {
        boolean allFilled = true;

        for (int i = 0; i < 6; i++) {
            if (handler.getSlot(i).getStack().isEmpty()) {
                allFilled = false;
                break;
            }
        }

        return allFilled;
    }
}