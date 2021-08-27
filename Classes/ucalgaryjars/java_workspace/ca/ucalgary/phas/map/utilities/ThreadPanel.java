// 
// Decompiled by Procyon v0.5.36
// 

package ca.ucalgary.phas.map.utilities;

import javax.swing.JPanel;

public abstract class ThreadPanel extends JPanel implements Runnable
{
    protected Thread th;
    public long sleepTime;
    private boolean running;
    private boolean threadSuspended;
    
    public ThreadPanel() {
        this.th = null;
        this.sleepTime = 10L;
        this.running = false;
        this.threadSuspended = false;
    }
    
    public void play() {
        if (!this.running && this.th == null) {
            this.th = new Thread(this);
            this.running = true;
            this.th.start();
        }
        else if (this.threadSuspended) {
            this.resume();
        }
    }
    
    public void stop() {
        this.th = null;
        synchronized (this) {
            this.notify();
        }
        this.running = false;
        this.threadSuspended = false;
    }
    
    public void pause() {
        if (this.running) {
            this.threadSuspended = true;
        }
    }
    
    public void resume() {
        this.threadSuspended = false;
        synchronized (this) {
            this.notify();
        }
    }
    
    public void sleep(final long n) {
        final Thread currentThread = Thread.currentThread();
        try {
            Thread.sleep(n);
            synchronized (this) {
                while (this.threadSuspended && this.th == currentThread) {
                    this.wait();
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void sleep(final long millis, final int nanos) {
        final Thread currentThread = Thread.currentThread();
        try {
            Thread.sleep(millis, nanos);
            synchronized (this) {
                while (this.threadSuspended && this.th == currentThread) {
                    this.wait();
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void sleep() {
        final Thread currentThread = Thread.currentThread();
        try {
            Thread.sleep(this.sleepTime);
            synchronized (this) {
                while (this.threadSuspended && this.th == currentThread) {
                    this.wait();
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public long getSleepTime() {
        return this.sleepTime;
    }
    
    public void setSleepTime(final long sleepTime) {
        this.sleepTime = sleepTime;
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public boolean getRunning() {
        return this.running;
    }
    
    public void setRunning(final boolean b) {
        if (this.running) {
            if (!b) {
                this.stop();
            }
        }
        else if (b) {
            this.play();
        }
    }
    
    public boolean isThreadSuspended() {
        return this.threadSuspended;
    }
    
    public boolean getThreadSuspended() {
        return this.threadSuspended;
    }
    
    public void setThreadSuspended(final boolean b) {
        if (b) {
            this.pause();
        }
        else {
            this.resume();
        }
    }
    
    public abstract void run();
}
