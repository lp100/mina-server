package org.com.zrhx.mina.base;

import org.com.zrhx.mina.oper.MinaProtocalCodecFactory;

/**
 * 初始化通信
 * @author gs
 *
 */
public interface InitNet {
	/**
	 * 
	 * @Title: init
	 * @Description: 初始化连接服务
	 * @param port 初始化端口号
	 * @return: void
	 */
	public void init(Integer port);
	
	/**
	 * 
	 * @Title: init
	 * @Description: 初始化连接服务
	 * @param port socket端口号
	 * @param nThreads 处理收到的数据开启的最大线程数
	 * @return: void
	 */
	public void init(Integer port,int nThreads);
	
	/**
	 * 
	 * @Title: init 初始化连接服务
	 * @Description: TODO
	 * @param port socket端口号
	 * @param nThreads 处理收到的数据开启的最大线程数
	 * @param codecFactory 自定义解码编码工厂（）
	 * @return: void
	 */
	public void init(Integer port,int nThreads,MinaProtocalCodecFactory codecFactory);
	/**
	 * 
	 * @Title: stop 
	 *
	 * @Description: 关闭服务
	 *
	 * @param 
	 *
	 * @return void    返回类型 
	 *
	 * @throws 
	 *
	 */
	public void stop();
}
