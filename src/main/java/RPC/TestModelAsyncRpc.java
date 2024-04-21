package RPC;

import java.util.concurrent.CompletableFuture;

public class TestModelAsyncRpc {
    private  static  final RpcClient rpcClient;

    static {
        try {
            rpcClient = new RpcClient();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(rpcClient.rpcAsynCall("who are you"+System.currentTimeMillis()));
        CompletableFuture<String> future=rpcClient.rpcAsynCall("who are you"+System.currentTimeMillis());
        future.whenComplete((v,t)->{
            if(t!=null){
                t.printStackTrace();
            }else {
                System.out.println(v);
            }
        });
        System.out.println("---async rpc call over"+System.currentTimeMillis());
    }

}
