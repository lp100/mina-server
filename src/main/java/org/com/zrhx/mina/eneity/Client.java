package org.com.zrhx.mina.eneity;

import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;

/**
 * 客户端链接对象
 * @author gs
 *
 */
public class Client {
  /**
   * mark(终端号)
   */
	private String mark;
	/**
	 * ip,port 对应 客户端
	 */
	private String ipport;
	/**
	 * 回话对象
	 */
	private IoSession ioSession;
	
	

	public Client(String ipport, IoSession ioSession) {
		super();
		this.ipport = ipport;
		this.ioSession = ioSession;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getIpport() {
		return ipport;
	}

	public void setIpport(String ipport) {
		this.ipport = ipport;
	}

	public IoSession getIoSession() {
		return ioSession;
	}

	public void setIoSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}
	
	
	public boolean write(DataFrame frame){
		int length = frame.getContentby().length;
		while(frame.getIndex()<length){//如果协议太大将分包操作
			WriteFuture write = ioSession.write(frame);
//			System.out.println(write.isWritten()+"ss=====wswww============="+frame.getIndex()+":"+length);
//			if(write.isWritten()){
//				return true;
//			}else{
//				System.out.println(write.getException());
//				System.out.println(ioSession);
//				return false;
//			}
			
		}
		return true;
	}
	
}
