package io.github.xen0r_star.droneworks.client.renderer;

public enum DRONE_COLOR {
    DEFAULT(0),
    WHITE(1),
    LIGHT_GRAY(2),
    GRAY(3),
    BLACK(4),
    BROWN(5),
    RED(6),
    ORANGE(7),
    YELLOW(8),
    LIME(9),
    GREEN(10),
    CYAN(11),
    LIGHT_BLUE(12),
    BLUE(13),
    PURPLE(14),
    MAGENTA(15),
    PINK(16);

    private final int value;

    DRONE_COLOR(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DRONE_COLOR fromValue(int value) {
        for (DRONE_COLOR color : values()) {
            if (color.value == value) {
                return color;
            }
        }
        return null;
    }
}

