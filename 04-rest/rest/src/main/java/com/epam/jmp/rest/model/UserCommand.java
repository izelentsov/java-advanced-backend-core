package com.epam.jmp.rest.model;


import com.epam.jmp.rest.model.UserCommand.UserCreateCommand;
import com.epam.jmp.rest.model.UserCommand.UserDeleteCommand;
import com.epam.jmp.rest.model.UserCommand.UserLocationUpdateCommand;


public sealed interface UserCommand
        permits UserCreateCommand,
        UserLocationUpdateCommand,
        UserDeleteCommand {


    record UserCreateCommand(String firstName, String lastName, String location) implements UserCommand {
    }

    record UserLocationUpdateCommand(long id, String newLocation) implements UserCommand{
    }

    record UserDeleteCommand(long id) implements UserCommand {
    }
}
