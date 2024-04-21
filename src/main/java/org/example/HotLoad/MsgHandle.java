package org.example.HotLoad;

import org.example.server.HttpRequestParse;

public class MsgHandle implements Runnable {

    @Override
    public void run() {
        while (true){
           HttpRequestParse manager = (HttpRequestParse) ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
//            manager.logic();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
