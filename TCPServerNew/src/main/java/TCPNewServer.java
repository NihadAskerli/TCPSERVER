import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPNewServer {
    public static void main(String[] args) throws Throwable {
readasBytes();
    }
    private static void readasBytes() throws Throwable {
        ServerSocket outFirstServerSocket = new ServerSocket(6789);

        while(true){
            System.out.println("yeni socket  ve ya musteri gozleyir");
            Socket connection = outFirstServerSocket.accept();
            System.out.println("yeni musteri geldi");
            DataInputStream dataStream= new DataInputStream(connection.getInputStream());
            String result= readReguest(dataStream);
            System.out.println(result);
            OutputStream outputStream=connection.getOutputStream();
DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
            writeResponse(dataOutputStream,"Salam Nihad Necesen");

//            byte[] arr=readmessage(dataStream);
//            System.out.println("message length2="+arr.length);
//            FileUtility.writebyte("C:\\Users\\ASUS\\Desktop\\niko.png",arr);
            connection.close();

        }
    }
    private static void writeResponse(OutputStream ous,String s)throws Throwable{
        String response="HTTP/1.1 200 OK\r\n"
                +"Server: YarServer/2009-09-09\r\n"
                +"Content-Type: text/html\r\n"
                +"Content-Length:"+s.length()+"\r\n"
                +"Connection:close\r\n\r\n";
        String result=response+s;
        ous.write(result.getBytes());
        ous.flush();

    }

    public static String readReguest(InputStream sin)throws Exception{
        InputStreamReader ist=new InputStreamReader(sin);
        BufferedReader reader=new BufferedReader(ist);
        String msg="";
        while(true){
            String s=reader.readLine();
            if(s==null||s.trim().length()==0){
                break;
            }else{
                msg=msg+s+"\r\n";
            }
            System.out.println("Server request:"+s);
            System.out.println();
        }
        return msg;
    }
    public static byte[] readmessage(DataInputStream din)throws Exception {
        int msglen=din.readInt();
        System.out.println("message lengt1="+msglen);
        byte []msg = new byte[msglen];
        din.readFully(msg);
        return msg;
    }
}
