package org.com.zrhx.mina.base;

import org.apache.mina.core.session.IoSession;
import org.com.zrhx.mina.eneity.Client;

/**
 * 回调一个通信链接
 * @author gs
 *
 */
public interface AcceptHandler {
	/**
	 * 
	 * @Title: accept 
	 *
	 * @Description: TODO(接受连接链路) 
	 *
	 * @param @param client
	 *
	 * @return void    返回类型 
	 *
	 */
	void accept(Client client);
	/**
	 * 
	 * @Title: lostConnection 
	 *
	 * @Description: TODO(关闭通信链路) 
	 *
	 * @param @param client
	 *
	 * @return void    返回类型 
	 *
	 */
	void lostConnection(IoSession client);
}
