package org.com.zrhx.mina.manager;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.com.zrhx.mina.UseNet;
import org.com.zrhx.mina.base.AcceptHandler;
import org.com.zrhx.mina.base.DataReceivedHandler;
import org.com.zrhx.mina.base.InitNet;
import org.com.zrhx.mina.base.ReceivedService;
import org.com.zrhx.mina.base.ServerConfig;
import org.com.zrhx.mina.eneity.Client;
import org.com.zrhx.mina.eneity.DataFrame;
import org.com.zrhx.mina.oper.MinaProtocalCodecFactory;
import org.com.zrhx.mina.service.MinaAcceptHander;

/**
 * 连接服务管理类
 * @author gs
 *
 */
public class NetManager implements InitNet,UseNet{
	/**ip,port 对应 客户端*/
	private Map<String, Client> clients;
	/**mark(终端号) 对应 ip,port*/
	private Map<String, String> marks;
	/**执行业务*/
	private ReceivedTask receivedTask;
	
	protected Logger logger = Logger.getLogger(NetManager.class);
	
	
	private static NetManager instance=null;
	
	private NetManager(){
		clients =  new ConcurrentHashMap<String,Client>();
		marks =  new ConcurrentHashMap<String,String>();
	}
	
	synchronized static NetManager getInstance() {
		if(null==instance){
			instance = new NetManager();
		}
		return instance;
	}

	@Override
	public void putReceivedService(ReceivedService receivedServices) {
		receivedTask.putReceivedService(receivedServices);
		
	}

	@Override
	public Client getClientByIpPort(String ipport) {
		 return clients.get(ipport);
	}

	@Override
	public void sendClientByIpPort(String ipport, DataFrame frame) {
		Client ic = getClientByIpPort(ipport);
		if(null!=ic){
			ic.write(frame);
		}
		
	}

	@Override
	public boolean sendClientByMark(String mark, DataFrame frame) {
		Client ic = getClientByMark(mark);
		if(null!=ic){
			boolean b = ic.write(frame);
			if(b){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	@Override
	public Client getClientByMark(String mark) {
		String ipport = marks.get(mark);
		if(null!=ipport){
			return clients.get(ipport);
		}
		return null;
	}
	
	@Override
	public void putMark(String mark, String ipport) {
		if(marks.containsKey(mark)){
			return;
		}
		marks.put(mark, ipport);
		clients.get(ipport).setMark(mark);
	}
	
	@Override
	public void init(Integer port) {
		init(port, 5,new MinaProtocalCodecFactory());		
	}
	
	@Override
	public void init(Integer port, int nThreads) {
		init(port, nThreads,new MinaProtocalCodecFactory());		
		
	}
	@Override
	public void init(Integer port,int nThreads,
			MinaProtocalCodecFactory codecFactory) {
		ServerConfig config;
		if(null==port){
			return;
		}else {
			config = new ServerConfig(port);
		}
		receivedTask = new ReceivedTask(nThreads);
		config.setReceivedHandler(new DataReceivedHandler() {
			
			@Override
			public void onReceived(DataFrame frame) {
				receivedTask.received(frame);
				
			}
		});
		config.setCodecFactory(codecFactory);
		config.setAcceptHandler(new AcceptHandler() {
			
			@Override
			public void lostConnection(IoSession c) {
				InetSocketAddress inetSocketAddress = (InetSocketAddress)c.getRemoteAddress();
				String clientIP = inetSocketAddress.getAddress().getHostAddress();
//				c.getId();//由于它不保证唯一性的关键
				int port = inetSocketAddress.getPort();
				Client client = getClientByIpPort(clientIP+port);
				if(null!=client){
					if(null!=client.getMark()&&!"".equals(client.getMark())){
						marks.remove(client.getMark());
					}
					clients.remove(clientIP+port);
				}
				
				
			}
			
			@Override
			public void accept(Client c) {
				clients.put(c.getIpport(), c);
				
			}
		});
		NioSocketAcceptor acceptor=new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("code",new ProtocolCodecFilter(config.getCodecFactory()));
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, config.getTimeOut());
		acceptor.setHandler(new MinaAcceptHander(config));
		try {
			acceptor.bind(new InetSocketAddress(port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		receivedTask.start();
	}
	
	@Override
	public void stop() {
		//receivedTask.setStopedAll(true);
	}


}
