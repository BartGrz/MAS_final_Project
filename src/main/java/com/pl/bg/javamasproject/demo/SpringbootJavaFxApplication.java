package com.pl.bg.javamasproject.demo;


import com.pl.bg.javamasproject.demo.controller.VisitController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


/**
 * First javaFx thread start, while starting it boot the springboot application class (witn @SpringBootApplication annotation)
 */
@Component
public class SpringbootJavaFxApplication extends Application {

    private ConfigurableApplicationContext context ;

    /**
     * runnning spring part of app
     */
    public void init()   {

        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.context = new SpringApplicationBuilder()
                .sources(DemoApplication.class)
                .run(args);
    }

    /**
     * Initializing main stage, thanks to fxWeaver FxApp is able to use all spring possibilities
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

     FxWeaver fxWeaver = this.context.getBean(FxWeaver.class);
     Parent root = fxWeaver.loadView(VisitController.class);
     Scene scene = new Scene(root);
     stage.setTitle("VetSystem2021");
     stage.setResizable(false);
     stage.setWidth(905);
     stage.setHeight(520);
     stage.setScene(scene);
     stage.show();
    }

    /**
     * After window is closed the context is also closed and whole program end
     */

    @Override
    public void stop()   {
       this.context.close();
        Platform.exit();
    }

}
