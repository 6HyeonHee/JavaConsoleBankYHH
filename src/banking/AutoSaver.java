package banking;


public class AutoSaver extends Thread {
	
	AccountManager handler;
	
	public AutoSaver(AccountManager handler) {
		
		this.handler = handler;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				handler.saveTxt();
				sleep(5000);
			}
		} catch(Exception e) {
			return;
		}
	}

}
