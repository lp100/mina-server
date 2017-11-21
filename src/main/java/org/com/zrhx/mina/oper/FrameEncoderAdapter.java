package org.com.zrhx.mina.oper;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.com.zrhx.mina.eneity.DataFrame;

/**
 * 
 * @author gs
 * 编码器
 */
public class FrameEncoderAdapter extends ProtocolEncoderAdapter {
	
	private Logger logger = Logger.getLogger(FrameEncoderAdapter.class);
	
	private final String charset;
	
	FrameEncoderAdapter(String charset) {
		this.charset = charset;
	}

	/**
	 * 编码器，对接收到的object进行编码工作，然后交由下一个过滤器处理
	 * @param session
	 * @param message
	 * @param out
	 * @throws Exception
	 */
	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		CharsetEncoder encoder = Charset.forName(charset).newEncoder();
		DataFrame mes=(DataFrame) message;
		int length = 64*1024-200;
		int endindex = mes.getIndex()+length;
		try{
			int clength= mes.getContentby().length;
			IoBuffer io = null;
			if(clength<length){
				io = IoBuffer.allocate(clength+12);
			}else{
				io = IoBuffer.allocate(64*1024);
			}
			if(clength<endindex){
				length =length- (endindex-clength) ;
		    }
			io.clear();
			// 清空缓存并重置
			io.position(0);
			if(mes.getIndex()==0){
				io.putString("zrhx", encoder);
				//命令类型
				io.putInt(mes.getMessageType());
				io.putInt(mes.getMessageLength());
				if(mes.getMessageLength()!=0){
					byte[] contentby = mes.getContentby();
					io.put(contentby,mes.getIndex() ,length);
				}
				logger.info("send remaining:" + io.limit());
				io.flip();
				out.write(io);
			}else{
				if(mes.getMessageLength()!=0){
					byte[] contentby = mes.getContentby();
					io.put(contentby,mes.getIndex() ,length);
				}
				io.flip();
				out.write(io);
			}
		}finally{
			mes.setIndex(endindex);
		}
	}

	public void dispose() throws Exception {
	}
}
