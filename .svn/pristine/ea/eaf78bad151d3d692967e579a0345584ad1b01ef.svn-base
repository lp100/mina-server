package org.com.zrhx.mina;

import org.com.zrhx.mina.base.ReceivedService;
import org.com.zrhx.mina.eneity.Client;
import org.com.zrhx.mina.eneity.DataFrame;
/**
 * 外部接口使用类
 * @author gs
 *
 */
public interface UseNet {
	/**
	 * 
	 * @Title: putReceivedService
	 * @Description:  注册数据使用服务
	 * @param receivedServices  处理上报的数据服务类对象
	 * @return: void
	 */
	public void putReceivedService(ReceivedService receivedServices);
	/**
	 * 
	 * @Title: getClientByIpPort
	 * @Description:  通过ip+端口号的到通信连接(不建议使用)
	 * @param ipport ip+端口号
	 * @return
	 * @return: Client 返回类型
	 */
	@Deprecated
	public Client getClientByIpPort(String ipport);
	/**
	 * 
	 * @Title: sendClientByIpPort
	 * @Description:  通过ip+端口号发送消息
	 * @param ipport  ip+端口号
	 * @param frame  封装好的对象协议
	 * @return: void
	 */
	public void sendClientByIpPort(String ipport, DataFrame frame);
	/**
	 * 
	 * @Title: getClientByMark
	 * @Description: 通过端口号的到通信连接(不建议使用)
	 * @param mark 终端编号
	 * @return
	 * @return: Client 返回类型
	 */
	@Deprecated
	public Client getClientByMark(String mark);
	/**
	 * 
	 * @Title: sendClientByMark 
	 * @Description: 通过终端标识发送消息
	 * @param mark 终端编号
	 * @param frame  封装好的对象协议
	 * @return
	 * @return: boolean
	 */
	public boolean sendClientByMark(String mark, DataFrame frame);

	/**
	 * 
	 * @Title: putMark
	 * @Description: 维护设备终端编号和客户端（ip+端口）对应关系
	 * @param mark  终端编号
	 * @param ipport ip+端口号
	 * @return: void
	 */
	public void putMark(String mark, String ipport);

}
