package io.group8.titan;

import io.group8.titan.gui.GuiMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TitanApplication {

    public static void main(String[] args) {
        SpringApplication.run(TitanApplication.class, args);
        GuiMain.run(args);
    }
}
