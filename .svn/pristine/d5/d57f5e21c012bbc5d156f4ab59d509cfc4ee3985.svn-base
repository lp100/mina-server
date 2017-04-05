package org.com.zrhx.mina.oper;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;

/**
 * 解码/编码工厂
 * @author gs
 *此工厂只是用于小文件（几十mb吧，不然会抛内存溢出错误，对于大文件传输，必须边读边写）
 */
public class MinaProtocalCodecFactory   implements ProtocolCodecFactory { 
	
        private final ProtocolEncoderAdapter encoder;  //编码
        
        private final CumulativeProtocolDecoder decoder;  //解码
          
        public MinaProtocalCodecFactory() {  
            encoder=new FrameEncoderAdapter("utf-8");  
            decoder=new FrameProtocalDecoder("utf-8");  
        }  
          
        public MinaProtocalCodecFactory(ProtocolEncoderAdapter encoder,
				CumulativeProtocolDecoder decoder) {
			super();
			this.encoder = encoder;
			this.decoder = decoder;
		}

		public ProtocolEncoder getEncoder(IoSession session) {  
            return encoder;  
        }  
        public ProtocolDecoder getDecoder(IoSession session) {  
            return decoder;  
        }  
} 