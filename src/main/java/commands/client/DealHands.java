package commands.client;

import server.models.CardSet;

public class DealHands extends ClientCommand {

    /**
     * PRIVATES
     */
    private int seatNumber;
    private CardSet hand;

    /**
     * CONSTRUCTOR
     */
    public DealHands() {
        super(CommandNames.DEAL_HANDS);
    }

    /**
     * CONSTRUCTOR
     *
     * @param seatNumber
     * @param hand
     */
    public DealHands(int seatNumber, CardSet hand) {
        this();
        this.seatNumber = seatNumber;
        this.hand = hand;

        this.addParameter(seatNumber);
        this.addParameter(hand);
    }

    /**
     * CONSTRUCTOR
     *
     * @param cmdString
     */
    public DealHands(String cmdString) {
        super(cmdString);
    }

    /**
     * @param commandStr
     */
    @Override
    public void doParse(String commandStr) {
        seatNumber = scanner.nextInt();
        hand = new CardSet(scanner.next());
    }

    /**
     *
     */
    public void doExecute() {
        manager.dealHand(seatNumber, hand);
    }
}
