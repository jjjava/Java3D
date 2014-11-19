/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

/**
 *
 * @author bp12214
 */
public class Shutdown implements Runnable{

    public void start(){
        Thread t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        
        try{
            Thread.sleep(20000);
            System.exit(0);
        }
        catch(InterruptedException e){
            
        }
    }
    
}
