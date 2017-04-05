package org.com.zrhx.mina.manager;

import org.apache.log4j.Logger;

/**
 * 接受数据线程
 * @author gs
 *
 */
public class ReceivedThread implements Runnable {
	
	private Logger log = Logger.getLogger(ReceivedThread.class);
	
	private boolean stoped;
	
	private ReceivedTask receivedTask;

	ReceivedThread(ReceivedTask receivedTask) {
		this.receivedTask = receivedTask;
		this.stoped = false;
	}


	void setStoped(boolean stoped) {
		this.stoped = stoped;
	}

	boolean isStoped() {
		return this.stoped;
	}

	@Override
	public void run() {
		this.stoped = false;
		while (!stoped) {
			try {
				Thread.sleep(10);
			    receivedTask.execute(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}
		}
	}

}
