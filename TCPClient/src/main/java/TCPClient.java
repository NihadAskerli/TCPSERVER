

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPClient {

    public static void main(String[] args)throws Exception  {
        Socket socket= new Socket("localhost",6789);
        OutputStream outputstream =socket.getOutputStream();
        DataOutputStream dataOutputStream=new DataOutputStream(outputstream);
        byte[] bytes=FileUtility.readbytes("C:\\Users\\ASUS\\Documents\\şəkil.png");
        dataOutputStream.writeInt(bytes.length);
        dataOutputStream.write(bytes);
        socket.close();

    }
}
