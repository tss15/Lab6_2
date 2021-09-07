package commands.seriallizedCommands;

import commands.Command;

import java.io.Serializable;

public class SerializedObjectCommand implements Serializable {

    private final Command command;
    private final Object object;
    private static final long serialVersionUID = 1502L;

    public SerializedObjectCommand(Command command, Object object){
        this.command = command;
        this.object = object;
    }

    public Command getCommand() {
        return command;
    }

    public Object getObject(){
        return object;
    }
}