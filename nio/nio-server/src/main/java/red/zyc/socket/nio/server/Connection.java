package red.zyc.socket.nio.server;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;

/**
 * 客户端和服务端的tcp连接
 *
 * @author zyc
 */
@Slf4j
@Getter
@Setter
public class Connection {

    /**
     * 当前连接的id
     */
    private final String id;

    /**
     * 服务端与客户端的socket通道
     */
    private final SocketChannel socketChannel;

    /**
     * 于此连接通道关联的选择键
     */
    private SelectionKey selectionKey;

    /**
     * 当前socket通道的网络地址
     */
    private final InetSocketAddress inetSocketAddress;

    /**
     * 连接创建时间
     */
    private final LocalDateTime createdTime;

    /**
     * 请求数据
     */
    private ByteBuffer request;

    /**
     * 响应数据
     */
    private ByteBuffer response;

    public Connection(String id, SocketChannel socketChannel) throws IOException {
        this.id = id;
        this.socketChannel = socketChannel;
        this.inetSocketAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
        this.createdTime = LocalDateTime.now();
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @return 客户端地址信息
     */
    public String clientAddress() {
        return String.format("[%s:%s]", inetSocketAddress.getAddress().getHostAddress(), inetSocketAddress.getPort());
    }

}
