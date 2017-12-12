package bsuir.library.command;

import bsuir.library.command.exception.CommandException;

public interface Command {

	String execute(String request) throws CommandException;
}

