package org.com.zrhx.mina.base;

import org.com.zrhx.mina.eneity.DataFrame;


/**
 * 处理业务接口 (外部具体实现)
 * @author gs
 *
 */
public interface ReceivedService {
	
	public void received(DataFrame frame);
}
