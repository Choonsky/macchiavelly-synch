package commands.client;

import client.ClientManager;
import commands.Command;
import javafx.application.Platform;

public abstract class ClientCommand extends Command {
    /**
     * PROTECTS
     */
    protected final ClientManager manager = ClientManager.getInstance();

    /**
     * CONSTRUCTOR
     *
     * @param name
     */
    public ClientCommand(CommandNames name) {
        super(name);
    }

    /**
     * CONSTRUCTOR
     *
     * @param commandStr
     */
    public ClientCommand(String commandStr) {
        super(commandStr);
    }

    /**
     *
     */
    @Override
    public void execute() {
        Platform.runLater(this::doExecute);
    }

}
