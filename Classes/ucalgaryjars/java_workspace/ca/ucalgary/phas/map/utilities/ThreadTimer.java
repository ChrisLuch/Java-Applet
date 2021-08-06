// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

public class ThreadTimer extends Thread
{
    private boolean haltRequested;
    private int interval;
    private int minSleepTime;
    private ThreadTimerListener lisner;
    
    public ThreadTimer(final int interval, final int minSleepTime, final ThreadTimerListener lisner) {
        this.interval = interval;
        this.minSleepTime = minSleepTime;
        this.lisner = lisner;
        this.haltRequested = false;
        this.setPriority(8);
        this.setDaemon(true);
    }
    
    public void halt() {
        this.haltRequested = true;
    }
    
    public synchronized void restart() {
        this.haltRequested = false;
        this.notify();
    }
    
    private synchronized void checkForHalt() throws InterruptedException {
        while (this.haltRequested) {
            this.wait();
        }
    }
    
    private synchronized void waitSmallInterval() throws InterruptedException {
        this.wait(1L);
    }
    
    public void run() {
        this.halt();
    Label_0007:
        while (true) {
            break Label_0007;
            while (true) {
                try {
                    while (true) {
                        this.checkForHalt();
                        final long currentTimeMillis = System.currentTimeMillis();
                        this.lisner.threadTimerSignal(this);
                        final long n = System.currentTimeMillis() - currentTimeMillis;
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        if (this.interval - n >= this.minSleepTime) {
                            while (System.currentTimeMillis() - currentTimeMillis2 <= this.interval - n) {
                                this.waitSmallInterval();
                            }
                        }
                        else {
                            while (System.currentTimeMillis() - currentTimeMillis2 <= this.interval - n) {
                                this.waitSmallInterval();
                            }
                        }
                    }
                }
                catch (InterruptedException ex) {
                    this.halt();
                    continue;
                }
                continue Label_0007;
            }
            break;
        }
    }
}
