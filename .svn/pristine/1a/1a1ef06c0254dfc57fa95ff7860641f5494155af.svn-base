package org.com.zrhx.mina.eneity;

import java.io.UnsupportedEncodingException;

/**
 * 桢结构包装类
 * @author gs
 *
 */
public class DataFrame {
	
	private int frameLength;//协议长度
	
	private String ipport;//链路id  (ip+port)
	
//	private String mark;// mark(终端号)
	/**命令类型
	 * 0 cms,  1 mdu ,2 msu, 3 cu ,4,mu(解码器)
	 * 转发 mdu 存储 msu  PC 客户端 cu  手机客户端  mcu  管理平台 cms
	 */
	private int messageType;
	/**消息的长度**/
	private int messageLength;
	/***数据帧内容***/
	private String content;

	/***数据帧内容***/
	private byte[] contentby;
	
	/**
	 * contentby的下标
	 */
	private int index;
	/**
	 * 是否包含头
	 */
	private boolean isHead;
	
	public DataFrame() {
		this.frameLength=0;
		this.index=0;
	}

//	public String getMark() {
//		return mark;
//	}
//
//	public void setMark(String mark) {
//		this.mark = mark;
//	}


	public void setMessageType(byte messageType) {
		this.messageType = messageType;
	}
	
	public int getFrameLength() {
		return frameLength;
	}

	public void setFrameLength(int frameLength) {
		this.frameLength = frameLength;
	}

	public int getMessageLength() {
		return messageLength;
	}

	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public boolean isHead() {
		return isHead;
	}

	public void setHead(boolean isHead) {
		this.isHead = isHead;
	}
	public byte[] getContentby() {
		return contentby;
	}

	public void setContentby(byte[] contentby) {
		this.contentby = contentby;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public String getIpport() {
		return ipport;
	}

	public void setIpport(String ipport) {
		this.ipport = ipport;
	}

	/**
	 * 
	 * @Title: getDataFrame 
	 *
	 * @Description: 通过传递消息，和消息类型得到DataFrame
	 *
	 * @param @param messageTye 消息类型
	 * @param @param content 消息
	 * @param @return DataFrame
	 *
	 * @return DataFrame    返回类型 
	 *
	 */
	public synchronized static DataFrame getDataFrame(String ipport,int messageType,String content){
		DataFrame df=new DataFrame();
		try {
			//消息类型
			df.setMessageType(messageType);
			df.setIpport(ipport);
			//消息
//			df.setContent(content);
			df.setContentby(content.getBytes("UTF-8"));
			//消息的长度
			df.setMessageLength(df.getContentby().length);
			// 四个字节的魔数 "zrhx" +四个字节的命令类型 + 四个字节的数据长度
			int length=4+4+4+df.getMessageLength();
			df.setFrameLength(length);
//			ServiceUtil.print(DataFrame.class,"mark:"+mark);
//			ServiceUtil.print(DataFrame.class,messageType+":"+content);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return df;
	}

}
