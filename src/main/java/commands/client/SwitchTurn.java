package commands.client;

public class SwitchTurn extends ClientCommand {
    /**
     * PRIVATES
     */
    private int seatNumber;


    /**
     * CONSTRUCTOR
     */
    public SwitchTurn() {
        super(CommandNames.SWITCH_TURN);
    }

    /**
     * CONSTRUCTOR
     *
     * @param commandStr
     */
    public SwitchTurn(String commandStr) {
        super(commandStr);
    }

    /**
     * CONSTRUCTOR
     *
     * @param seatNumber
     */
    public SwitchTurn(int seatNumber) {
        this();
        this.seatNumber = seatNumber;
        addParameter(seatNumber);
    }

    /**
     * @param commandStr
     */
    @Override
    public void doParse(String commandStr) {
        this.seatNumber = scanner.nextInt();
    }

    /**
     *
     */
    @Override
    public void doExecute() {
        manager.switchTurn(seatNumber);
    }
}
