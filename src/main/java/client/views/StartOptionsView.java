package client.views;

import client.ClientManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 */
public class StartOptionsView extends View {
    /**
     * FXML INJECTIONS
     */
    @FXML
    private TextField joinIp;
    @FXML
    private TextField joinPort;
    @FXML
    private TextField newGamePort;
    @FXML
    private TextField numOfPlayers;
    @FXML
    private TextField userName;
    @FXML
    private TextField adminUserName;
    @FXML
    private Text messageText;


    /************************
     **** PRIVATE STATICS ***
     ************************/
    private static StartOptionsView ourInstance = new StartOptionsView();


    /***********************
     ***** CONSTRUCTOR *****
     ***********************/
    private StartOptionsView() {
        super();
        fxml = "/fxml/StartOptions.fxml";

        try {
            loadFxml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * GETTERS
     */
    /**
     * get the instance
     *
     * @return
     */
    public static StartOptionsView getInstance() {
        return ourInstance;
    }

    /**
     * handles new game action event
     *
     * @param ae
     */
    @FXML
    public void onNewGame(ActionEvent ae) {
        int numberOfPlayers = Integer.parseInt(numOfPlayers.getText());
        int port = Integer.parseInt(newGamePort.getText());

        try {
            ClientManager.getInstance().startServer(port, numberOfPlayers, adminUserName.getText());
            messageText.setText("Started server at port " + port);
        } catch (Exception e) {
            messageText.setText("Couldn't start server:" + e.getMessage());
        }
    }

    /**
     * handles join game action event
     *
     * @param ae
     */
    @FXML
    public void onJoinGame(ActionEvent ae) {
        int port = Integer.parseInt(joinPort.getText());
        String ip = joinIp.getText();

        String userNameText = userName.getText();

        ClientManager.getInstance().loginServer(ip, port, userNameText);
    }
}
