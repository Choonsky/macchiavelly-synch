package commands.server;

import commands.Command;
import server.models.Machiavelli;

public abstract class ServerCommand extends Command {
    /**
     * PROTECTS
     */
    protected Machiavelli machiavelli = Machiavelli.getInstance();

    /**
     * CONSTRUCTOR
     *
     * @param name
     */
    public ServerCommand(CommandNames name) {
        super(name);
    }

    /**
     * CONSTRUCTOR
     *
     * @param cmdString
     */
    public ServerCommand(String cmdString) {
        super(cmdString);
    }

    /**
     *
     */
    @Override
    public void execute() {
        doExecute();
    }
}
