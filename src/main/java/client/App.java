package client;

import client.views.GameView;
import client.views.LoginView;
import client.views.StartOptionsView;
import client.views.View;
import commands.Command;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tests.testController.GameViewControllerTest;
import tests.testController.LoginViewControllerTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class App extends Application {

    /*******************************************************************
     * *************************PRIVATE STATIC FINALS*******************
     ******************************************************************/
    private static final String TITLE = "Machiavelli";
    private static final int MIN_WIDTH = 800;
    public static final int MIN_HEIGHT = (int) (MIN_WIDTH * 0.66);


    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Application.launch(App.class);
    }

    /*******************************************************************
     * *************************PRIVATES********************************
     ******************************************************************/
    private Thread clientThread;

    private Stage primaryStage;

    private Scene scene;

    private View activeView;

    private LoginView loginView;

    @FXML
    private StackPane rootLayout;

    /*******************************************************************
     **************************PROTECTEDS********************************
     ******************************************************************/
    protected BufferedReader in;
    protected PrintWriter out;

    public App() throws IOException {
    }

    /**
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        initRootLayout();

        ClientManager.getInstance().setStage(primaryStage);
        ClientManager.getInstance().setApp(this);

        ClientManager.getInstance().showView(StartOptionsView.getInstance());

//        showView();
//        showLoginView();
//        showGameView(4);
//        showWaitingView();

        //this is for networked version
//        loginView = new LoginView();
//        loginView.addBtnLoginAction(event -> {
//            ClientManager.getInstance().loginServer(loginView.getIp(), loginView.getPort());
//        });
    }

    /**
     *
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/fxml/RootLayout.fxml"));
            rootLayout = (StackPane) loader.load();


            // Show the scene containing the root layout.
            scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.setTitle(TITLE);
            primaryStage.setMaximized(true);
            // primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*******************************************************************
     **************************GETTERS********************************
     ******************************************************************/
    /**
     * @return
     */
    public View getActiveView() {
        return activeView;
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public synchronized void showView(View view) {
        scene.setRoot(view.getRoot());
        activeView = view;
        view.setMainApp(this);
    }

    /*******************************************************************
     **************************DISPLAY METHODS**************************
     ******************************************************************/
    /**
     * @param numOfPlayers
     */
    public synchronized void showGameView(int numOfPlayers) {
        try {
            if (!(activeView instanceof GameView)) {
                // Load person overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("/fxml/GameView.fxml"));
                BorderPane gameViewLayout = (BorderPane) loader.load();

                rootLayout.getChildren().add(gameViewLayout);

                gameViewLayout.prefWidthProperty().bind(rootLayout.widthProperty());
                gameViewLayout.prefHeightProperty().bind(rootLayout.heightProperty());

                // Give the controller access to the main app.
                GameView gameView = loader.getController();
                activeView = gameView;

                new GameViewControllerTest(this, gameView);

                gameView.setMainApp(this);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public synchronized void showWaitingView() {
//        WaitingForOtherPlayersView waitingForOthersView = new WaitingForOtherPlayersView();
//        activeView = waitingForOthersView;
//
//        VBox waitingViewLayout = waitingForOthersView.getLayout();
//
//        rootLayout.getChildren().add(waitingViewLayout);
//
//        waitingViewLayout.prefWidthProperty().bind(rootLayout.widthProperty());
//        waitingViewLayout.prefHeightProperty().bind(rootLayout.heightProperty());
//
//        waitingForOthersView.setMainApp(this);
    }

    /**
     *
     */
    public synchronized void showLoginView() {
        activeView = loginView;
        VBox loginViewLayout = loginView.getLayout();
        rootLayout.getChildren().add(loginViewLayout);

        loginViewLayout.prefWidthProperty().bind(rootLayout.widthProperty());
        loginViewLayout.prefHeightProperty().bind(rootLayout.heightProperty());

        new LoginViewControllerTest(this, loginView);

        loginView.setMainApp(this);
    }

//TODO: uncomment for networked version

    public void sendCommandToServer(Command cmd) {
        this.out.println(cmd.serialize());
    }
}
