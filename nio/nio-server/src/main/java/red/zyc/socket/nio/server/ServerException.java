package red.zyc.socket.nio.server;

/**
 * @author zyc
 */
public class ServerException extends RuntimeException {

    public ServerException(String message) {
        super(message);
    }

    public ServerException(Throwable t) {
        super(t);
    }

    public ServerException(String message, Throwable t) {
        super(message, t);
    }

}
