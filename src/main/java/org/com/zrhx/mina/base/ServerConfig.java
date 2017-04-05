package org.com.zrhx.mina.base;
import org.apache.mina.filter.codec.ProtocolCodecFactory;


/**
 * 服务配置
 * @author gs
 *
 */
public class ServerConfig {
	
	private int port;//端口号
	
	private ProtocolCodecFactory codecFactory;
	
	private DataReceivedHandler receivedHandler;
	
	private  AcceptHandler acceptHandler;
	
	private int timeOut = 300;//链路超时时间（秒）
	
	private int nThreads = 5;//默认线程池树

	public ServerConfig(Integer port)
	{
		this.port = port;
	}

	public ProtocolCodecFactory getCodecFactory() {
		return codecFactory;
	}

	public void setCodecFactory(ProtocolCodecFactory codecFactory) {
		this.codecFactory = codecFactory;
	}

	public DataReceivedHandler getReceivedHandler() {
		return receivedHandler;
	}

	public void setReceivedHandler(DataReceivedHandler receivedHandler) {
		this.receivedHandler = receivedHandler;
	}

	public AcceptHandler getAcceptHandler() {
		return acceptHandler;
	}

	public void setAcceptHandler(AcceptHandler acceptHandler) {
		this.acceptHandler = acceptHandler;
	}

	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public int getnThreads() {
		return nThreads;
	}
	
}
