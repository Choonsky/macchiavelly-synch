package commands.server;

import java.net.URLEncoder;

public class PlayerLogin extends ServerCommand {
    /**
     * PRIVATES
     */
    private String playerName;

    /**
     * CONSTRUCTOR
     */
    public PlayerLogin() {
        super(CommandNames.PLAYER_LOGIN);
    }

    /**
     * CONSTRUCTOR
     *
     * @param playerName
     */
    public PlayerLogin(String playerName) {
        this();
        this.playerName = playerName;

        this.addParameter(playerName);
    }

    /**
     * @param commandStr
     */
    @Override
    public void doParse(String commandStr) {
        playerName = URLEncoder.encode(scanner.nextLine());
    }

    /**
     *
     */
    @Override
    public void doExecute() {
        // Do nothing.
    }

    /**
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }
}
