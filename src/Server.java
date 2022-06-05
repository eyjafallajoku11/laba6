import utility.CommandManager;
import utility.Request;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Objects;

import static utility.Serialisation2.deserialize;
import static utility.Serialisation2.serialize;


public class Server {
    private static ServerSocketChannel serverChannel;

    private static SocketChannel channel;

    private static ByteBuffer bufferIn;
    private static ByteBuffer[] bufferOut;
    public static void run(int port){
        System.out.println("ждём подключения");
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            InetSocketAddress address = new InetSocketAddress(port);
            serverChannel.bind(address);
//            System.out.println(address);
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        System.out.println("канал открыт");
        while (true) {
            try {
                do {
                    channel = serverChannel.accept();
                } while (Objects.isNull(channel));
//                System.out.println(channel.getRemoteAddress());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("клиент подключен");
            while (channel.isConnected()) {
                try {
                    Request request = null;
//                    System.out.println("че-то происходит");
                    int[] requestData = getRequestData();
                    if (!Objects.isNull(requestData)) {
//                        System.out.println("данные получены");
                        request = getRequest(requestData);
                        System.out.println(request);
                        String answer;
                        answer = CommandManager.execute(request);
                        System.out.println(answer);
                        sendAnswer(answer);
                    }
                } catch (Exception e) {
                    try {
                        channel.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
//                    throw new RuntimeException(e);
                }
            }
            System.out.println("клиент отключен");
        }
    }

    private static void sendAnswer(String answer){
        int[] answerData = split(answer.getBytes());
//        System.out.println("разделили");
        try {
            channel.write(ByteBuffer.wrap(serialize(answerData)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int size = answerData[1];
//        System.out.println("отправили данные");
        try {
            for (int i=0; i<size; i++) {
                Thread.sleep(1);
                channel.write(bufferOut[i]);
                bufferOut[i].clear();
                System.out.println("пакет отправлен");
                System.out.println(Arrays.toString(bufferOut[i].array()));
            }
        } catch (IOException e) {
            System.out.println("не отправляет");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static Request getRequest(int[] bufferData) {
//        System.out.println("реквестим");
        Request request;
        bufferIn = ByteBuffer.allocate(bufferData[0]);
        int size = bufferData[1];
//        System.out.println(size);
        byte[] input = new byte[0];
        try {
            for (int i=0; i < size; i++) {
                bufferIn.clear();
                int length = 0;
//                System.out.println(bufferIn.position());
//                while (bufferIn.position() == 0) {
                Thread.sleep(5);
                length = channel.read(bufferIn);
//                }
//                System.out.println("прочитали канал");
                input = combineArray(input, bufferIn.array(), length);
            }
            request = deserialize(input);
//            System.out.println("дисериализовали");
//            System.out.println(request);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return request;
    }
    private static int[] getRequestData(){
        int[] data = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        try {
            byteBuffer.clear();
//            while (byteBuffer.position() == 0) {
                channel.read(byteBuffer);
//            }
            data = deserialize(byteBuffer.array());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
     return data;
    }
    private static byte[] combineArray(byte[] arr1, byte[] arr2, int length){
        byte[] arr = new byte[arr1.length+arr2.length];
        System.arraycopy(arr1, 0, arr, 0, arr1.length);
        System.arraycopy(arr2, 0, arr, arr1.length, length);
        return arr;
    }
    public static int[] split(byte[] dataArray) {
        int byteBufferSize = 1024;
        int size = (int) Math.ceil((double) dataArray.length / byteBufferSize);
        int stop = byteBufferSize;
        bufferOut = new ByteBuffer[size];
        for (int i = 0; i < size; i++){
            if (i+1 == size && dataArray.length % byteBufferSize != 0) {
                stop = (dataArray.length % byteBufferSize);
            }
            byte[] temp = new byte[stop];
            System.arraycopy(dataArray, i * byteBufferSize, temp, 0, stop);
            bufferOut[i] = ByteBuffer.wrap(temp);
        }
        return new int[] {byteBufferSize,size};
    }

}
