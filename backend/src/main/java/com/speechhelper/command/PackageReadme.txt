Command Package
-This package contains the command interface as well as the command invoker.
-The command interface specifies executing and unexecuting some encapsulated action.
-The command invoker receives command objects and executes the execute method of the command. This is an object that is instantiated in the model and called whenever the model
"receives" a command through its receiveCommand method