package com.evanho9.boxmatch.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.evanho9.boxmatch.BoxMatch;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "BoxMatch";
        config.width = 480;
        config.height = 854;
        new LwjglApplication(new BoxMatch(), config);
    }
}
