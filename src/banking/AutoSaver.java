package banking;

public class AutoSaver extends Thread {
	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
				System.out.println("5초 마다 내용을 저장합니다.");
			} catch(InterruptedException e) {
				System.out.println("자동 저장 시 오류 발생함.");
				Thread.currentThread().interrupt();
			}
		}
	}
	public static void main(String[] args) {
		
	}
}
