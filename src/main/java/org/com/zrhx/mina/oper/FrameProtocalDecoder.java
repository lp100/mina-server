package org.com.zrhx.mina.oper;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.com.zrhx.mina.eneity.DataFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gs
 *解码器
 */

public class  FrameProtocalDecoder extends CumulativeProtocolDecoder {
	
	private Logger logger = LoggerFactory.getLogger(FrameProtocalDecoder.class);
	
	private final String charset;

	FrameProtocalDecoder(String charset) {
		this.charset = charset;
	}

	@Override
	/**
	 *解码器，对传入的iobuffer 进行解码工作，注意顺序是先进先出原则。
	 */
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		CharsetDecoder decoder = Charset.forName(charset).newDecoder();
		int smsLength = 0;
		int remaining = in.remaining();
		try {
			logger.info("协议开始解析：");
			if (remaining < 8) {
				InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
				String clientIP = inetSocketAddress.getAddress().getHostAddress();
				int port = inetSocketAddress.getPort();
				logger.error("{}:{}接收到客户端指令长度不够…………长度：{}",clientIP,port,remaining);
				return false;
			}
			in.mark();//标记当前位置，以便reset
			DataFrame frame=new DataFrame();
			byte [] sizeBytes = new byte[4];
			in.get(sizeBytes);
			String zrhx = new String(sizeBytes, "UTF-8");//获取zrhx头
			if(!"zrhx".equals(zrhx)){
				InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
				String clientIP = inetSocketAddress.getAddress().getHostAddress();
				int port = inetSocketAddress.getPort();
				in.clear();
				in.flip();
				logger.error("{}:{}接收到客户端指令…………长度：{}……指令不正确.......{}====:{}====:{}====:{}====:{}",clientIP,port,remaining,zrhx,sizeBytes[0],sizeBytes[1],sizeBytes[2],sizeBytes[3]);
				//父类接收新数据，以拼凑成完整数据
				return false;
			}
			//获取协议类型
			frame.setMessageType(in.getInt());
			// 够获取总长度时，由于总长度采用int类型保存，直接获取总长度存入smsLength
			smsLength = in.getInt();
			if (remaining < smsLength || smsLength < 0) {
				InetSocketAddress inetSocketAddress = (InetSocketAddress)session.getRemoteAddress();
				String clientIP = inetSocketAddress.getAddress().getHostAddress();
				int port = inetSocketAddress.getPort();
				in.reset();
				logger.error("{}:{}接收到客户端指令长度不够…………长度：{}",clientIP,port,remaining);
				//父类接收新数据，以拼凑成完整数据
				return false;
			}
			
			frame.setMessageLength(smsLength);
			frame.setFrameLength(4+4+4+smsLength);
			frame.setContent(in.getString(smsLength, decoder));
			out.write(frame);
		    if(in.remaining() > 0){//如果读取内容后还粘了包，就让父类再重读  一次，进行下一次解析 
		      logger.info("接收到客户端指令粘了包："+remaining);
              return true; 
            } 

		} catch (Exception e) {
			in.clear();
			in.flip();
			logger.error(e.getMessage(), e);
			return false;
		}
		return false;
	}
}
