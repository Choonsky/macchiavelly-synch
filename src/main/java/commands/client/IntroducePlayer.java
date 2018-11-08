package commands.client;

import commands.Command;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class IntroducePlayer extends ClientCommand {
    /**
     * PROCTECTS
     */
    protected String playerName;
    protected int playerId;
    protected int seatNumber;

    /**
     * CONSTRUCTOR
     */
    public IntroducePlayer() {
        super(Command.CommandNames.INTRODUCE_PLAYER);
    }

    /**
     * CONSTRUCTOR
     *
     * @param name
     */
    public IntroducePlayer(CommandNames name) {
        super(name);
    }

    /**
     * CONSTUCTOR
     *
     * @param playerName
     * @param playerId
     * @param seatNumber
     */
    public IntroducePlayer(String playerName, int playerId, int seatNumber) {
        this();

        this.seatNumber = seatNumber;
        this.playerName = URLEncoder.encode(playerName);
        this.playerId = playerId;

        this.addParameter(playerName);
        this.addParameter(playerId);
        this.addParameter(seatNumber);
    }

    /**
     * @param commandStr
     */
    @Override
    public void doParse(String commandStr) {
        playerName = URLDecoder.decode(scanner.next());
        playerId = scanner.nextInt();
        seatNumber = scanner.nextInt();
    }

    /**
     *
     */
    @Override
    public void doExecute() {
        manager.introducePlayer(playerName, playerId, seatNumber, false);
    }
}
