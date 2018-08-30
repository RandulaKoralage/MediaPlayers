/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.floor;
import static java.lang.String.format;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Randula
 */
public class MediaPlayerSof extends javax.swing.JFrame {

    private MediaPlayer mediaPlayer; //Player Object
    private MediaView mediaView; //Node that provides a view 
    //of Media being played by a MediaPlayer.
    private Media media;    // Media that playing in Media Player
    private Button playButton; // Button to start palying
    private Button pauseButton; //Button to puase playing
    private Button stopButton;  //Button to stop playing
    private Button screenshotButton; //Button to snip media view
    private Slider slider;  //Slider that shows playing time
    private Label time; //Lable that shows processing time
    private Duration duration;  //processing time
    private int i = 1; //uses for naming screenshots as 1,2,3...
    private JLabel lblTitle;    //Lable that shows title
    private JPanel panel; //Panel on JFarame
    private boolean isPaused = false;

    /**
     * Create a MediaPlayerSof
     * @param path the path of Media
     */
    public MediaPlayerSof(String path) {
        initComponents();
        media = new Media(new File(path).toURI().toString());
        setSize(883, 484);
        setTitle("Maze Media Player");
        setBackground(java.awt.Color.black);
        setLocationRelativeTo(null);
        final JFXPanel fxPanel = new JFXPanel();

        fxPanel.setSize(600, 660);
        panel.add("Center", fxPanel);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }

        });
    }

    /**
     * Initialize the FXPanel
     *
     * @param fxPanel the panel holds FX components
     */
    private void initFX(JFXPanel fxPanel) {
        Scene scene = createScene();
        lblTitle.setVisible(false);
        fxPanel.setScene(scene);
    }

    /**
     * Initialize the JFrame components
     */
    private void initComponents() {

        panel = new JPanel();
        lblTitle = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(java.awt.Color.black);

        panel.setBackground(new java.awt.Color(51, 51, 51));
        panel.setLayout(new java.awt.BorderLayout());

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 36));
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("MAZE | Media");
        panel.add(lblTitle, java.awt.BorderLayout.CENTER);

        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        pack();
        setVisible(true);

    }

    /**
     * Create scene with FX components
     *
     * @return scene
     */
    private Scene createScene() {

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView = new MediaView(mediaPlayer);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(mediaView);
        borderPane.setBottom(addToolBar());

        borderPane.setStyle("-fx-background-color: Black");
        borderPane.autosize();
        Scene scene = new Scene(borderPane, 600, 600);
        scene.setFill(Color.BLACK);

        DropShadow dropshadow = new DropShadow();
        dropshadow.setOffsetY(2.0);
        dropshadow.setOffsetX(2.0);
        dropshadow.setColor(Color.WHITE);

        mediaView.setEffect(dropshadow);
        System.out.println("scene 2 " + scene);
        return scene;
    }

    /**
     * create ToolBar with Start,Pause,Stop,ScreenShot Buttons
     *
     * @return toolBar
     */
    private Node addToolBar() {
        HBox toolBar = new HBox();
        toolBar.setPadding(new Insets(20));
        toolBar.setAlignment(Pos.CENTER);
        toolBar.alignmentProperty().isBound();
        toolBar.setSpacing(0);
        toolBar.setStyle("-fx-background-color: Black");

        Image playButtonImage
                = new Image(getClass().getResourceAsStream(
                                "../mediaplayerimages/Play.png"));
        playButton = new Button();
        playButton.setGraphic(new ImageView(playButtonImage));
        playButton.setStyle("-fx-background-color: Black");

        playButton.setOnAction((ActionEvent e) -> {

        });
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            playButton.setStyle("-fx-background-color: Black");
            playButton.setStyle("-fx-body-color: Black");
        });
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            playButton.setStyle("-fx-background-color: Black");
        });
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            mediaPlayer.play();
        });

        Image pausedButtonImage
                = new Image(getClass().getResourceAsStream(
                                "../mediaplayerimages/Pause.png"));
        pauseButton = new Button();
        pauseButton.setGraphic(new ImageView(pausedButtonImage));
        pauseButton.setStyle("-fx-background-color: Black");

        pauseButton.setOnAction((ActionEvent e) -> {
        });

        pauseButton.addEventHandler(
                MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                    pauseButton.setStyle("-fx-background-color: Black");
                    pauseButton.setStyle("-fx-body-color: Black");
                });
        pauseButton.addEventHandler(
                MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                    pauseButton.setStyle("-fx-background-color: Black");
                });
        pauseButton.addEventHandler(
                MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (!isPaused) {
                        mediaPlayer.pause();
                        isPaused = true;
                    } else {
                        mediaPlayer.play();
                        isPaused = false;
                    }
                });

        Image stopButtonImage
                = new Image(getClass().getResourceAsStream(
                                "../mediaplayerimages/Stop.png"));
        stopButton = new Button();
        stopButton.setGraphic(new ImageView(stopButtonImage));
        stopButton.setStyle("-fx-background-color: Black");

        stopButton.setOnAction((ActionEvent e) -> {

        });
        stopButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            stopButton.setStyle("-fx-background-color: Black");
            stopButton.setStyle("-fx-body-color: Black");
        });
        stopButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            stopButton.setStyle("-fx-background-color: Black");
        });
        stopButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            mediaPlayer.stop();
        });

        Image screenButtonImage
                = new Image(getClass().getResourceAsStream(
                                "../mediaplayerimages/Screenshot.png"));
        screenshotButton = new Button();
        screenshotButton.setGraphic(new ImageView(screenButtonImage));
        screenshotButton.setStyle("-fx-background-color: Black");

        screenshotButton.setOnAction((ActionEvent e) -> {

        });
        screenshotButton.addEventHandler(
                MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                    screenshotButton.setStyle("-fx-background-color: Black");
                    screenshotButton.setStyle("-fx-body-color: Black");
                });
        screenshotButton.addEventHandler(
                MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                    screenshotButton.setStyle("-fx-background-color: Black");
                });
        screenshotButton.addEventHandler(
                MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    WritableImage wi = new WritableImage(650, 370);
                    mediaView.snapshot(new SnapshotParameters(), wi);

                    Color c = wi.getPixelReader().getColor(100, 100);
                    String fName = "Snapshot";
                    File file = new File(fName + ".png");

                    if (file.exists()) {
                        String name = fName + i;
                        System.out.println(name);
                        file = new File(name + ".png");
                        i++;

                        try {
                            ImageIO.write(
                                    SwingFXUtils.fromFXImage(wi, null), "png", file);
                        } catch (IOException ex) {
                            // TODO: handle exception here
                        }

                    } else {

                        try {
                            ImageIO.write(
                                    SwingFXUtils.fromFXImage(wi, null), "png", file);
                        } catch (IOException ex) {
                            // TODO: handle exception here
                        }
                    }
                });

        slider = new Slider();
        HBox.setHgrow(slider, Priority.ALWAYS);
        slider.setMinSize(300, 50);

        slider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(javafx.beans.Observable observable) {
                if (slider.isValueChanging()) {
// multiply duration by percentage calculated by slider position
                    if (duration != null) {
                        mediaPlayer.seek(
                                duration.multiply(slider.getValue() / 100.0));
                    }
                    updateValues();

                }
            }
        });

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable,
                    Object oldValue, Object newValue) {
                updateValues();
            }

        });

        time = new Label();
        time.setTextFill(Color.YELLOW);
        time.setPrefWidth(80);

        /*  mediaPlayer.currentTimeProperty().addListener((Observable ov) -> {
         updateValues();
         });*/
        mediaPlayer.setOnReady(() -> {
            duration = mediaPlayer.getMedia().getDuration();
            updateValues();
        });
        toolBar.getChildren().addAll(playButton,
                pauseButton, stopButton, screenshotButton, slider, time);
        return toolBar;
    }

    /**
     * Update processing time
     */
    protected void updateValues() {
        if (time != null && slider != null && duration != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    time.setText(formatTime(currentTime, duration));
                    slider.setDisable(duration.isUnknown());
                    if (!slider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !slider.isValueChanging()) {
                        slider.setValue(currentTime.divide(duration)
                                .toMillis() * 100.0);
                    }

                }

            });
        }
    }

    /**
     * Format processing time
     *
     * @return time as String
     */
    private String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60
                    - durationMinutes * 60;
            if (durationHours > 0) {
                return format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return format("%02d:%02d", elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }

}
