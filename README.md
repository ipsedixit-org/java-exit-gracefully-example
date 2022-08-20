# Java exit gracefully

This program show how a Java program could be implemented in order to exit gracefully, for example receiving a SIGINT.

There two concept explained in this post:
* System exit
* Catch interrupt signal


## System exit
In hava there is a special instruction used to force a program to end immediately and it is:
```java
System.exit(exitValue);
```
Where:
* exitValue is a number >=0: 0 stands for end correctly, otherwise that was an error

Calling `System.exit` interrupt immediately the program and the are no more instruction execute, neither inside finally.
Example:
```java
try {
	System.out.println("Printed");
	System.exit(exitValue);
	System.out.println("Not printed");
} finally {
	System.out.println("Not executed");
}
```
So please pay attention to use `System.exit` carefully because could create a memory leak on opened resources.

## Catch interrupt signal
It is possible to register a shutdown hook using `Runtime.getRuntime().addShutdownHook(...)`.
The Java virtual machine shuts down in response to two kinds of events:
* The program exits normally, when the last non-daemon thread exits or when the exit (equivalently, System.exit) method is invoked, or
* The virtual machine is terminated in response to a user interrupt, such as typing ^C, or a system-wide event, such as user logoff or system shutdown. 

A shutdown hook is simply an initialized but unstarted thread. When the virtual machine begins its shutdown sequence it will start all registered shutdown hooks in some unspecified order and let them run concurrently. When all the hooks have finished it will then halt. Note that daemon threads will continue to run during the shutdown sequence, as will non-daemon threads if shutdown was initiated by invoking the exit method. 


## References
* [Javadoc for addShutdownHook](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Runtime.html#addShutdownHook(java.lang.Thread\))
* [Javadoc for System.exit](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#exit(int\))

