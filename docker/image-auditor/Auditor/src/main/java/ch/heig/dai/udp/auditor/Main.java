package ch.heig.dai.udp.auditor;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MultiCastReceiver multiCastReceiver = new MultiCastReceiver();
        TcpSendMusicians tcpSendMusicians = new TcpSendMusicians();
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        executor.submit(multiCastReceiver::run);
        executor.submit(tcpSendMusicians::run).get();



    }
}