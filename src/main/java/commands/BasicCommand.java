package commands;

public class BasicCommand extends Command {
    /**
     * CONSTRUCTOR
     *
     * @param name
     */
    public BasicCommand(CommandNames name) {
        this.name = name;
    }

    /**
     * CONSTRUCTOR
     *
     * @param commandStr
     */
    public BasicCommand(String commandStr) {
        super(commandStr);
    }

    /**
     * @param commandStr
     */
    @Override
    public void doParse(String commandStr) {
        System.out.println("Command parsing itself.");
    }

    /**
     *
     */
    @Override
    protected void doExecute() {
        System.out.println("Command executing itself.");
    }
}
