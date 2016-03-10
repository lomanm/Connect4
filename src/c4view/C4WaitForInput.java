package c4view;

public class C4WaitForInput implements Runnable {
	private boolean paused = false;
	private final Object LOCK;
	
	public C4WaitForInput(){
		LOCK = new Object();
	}

    public void run() {
        while (true) {
            synchronized(LOCK) {
                if (paused) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                    }
                } else {
                    return;
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
    }

    public void pause() {
        synchronized(LOCK) {
            paused = true;
            LOCK.notifyAll();
        }
    }

    public void resume() {
        synchronized(LOCK) {
            paused = false;
            LOCK.notifyAll();
        }
    }	
	
	
}
