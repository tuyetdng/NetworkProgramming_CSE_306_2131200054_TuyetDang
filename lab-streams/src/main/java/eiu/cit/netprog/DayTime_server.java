package eiu.cit.netprog;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTime_server {
    public final static int PORT = 14;

    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
               try(Socket connection =  server.accept()) {
                Writer out = new OutputStreamWriter(connection.getOutputStream());
                Date now = new Date();
                out.write(now.toString() + "\r\n");
                out.flush();
                connection.close();
             
                
               } catch (Exception e) {
               } 
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
