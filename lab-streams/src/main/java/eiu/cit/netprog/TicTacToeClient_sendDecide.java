package eiu.cit.netprog;

import java.net.Socket;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.*;

public class TicTacToeClient_sendDecide {

	public static void main(String[] args) throws ParseException {
		String hostname = "localhost";
		Socket socket = null;
		try {
			socket = new Socket(hostname, 17);
			socket.setSoTimeout(15000);
			InputStream in = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader bif = new BufferedReader(reader);
			//bytes => characters => line by line
			OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
			BufferedWriter bout = new BufferedWriter(out);

			// Enter data using BufferReader
			BufferedReader terminal = new BufferedReader(new InputStreamReader(System.in));

			String move = terminal.readLine();
			bout.write(args[0] + "\r\n");
			bout.flush();

			while (!(move.equals("quit"))) {
				bout.write(move + "\r\n");
				bout.flush();
				readBoard(bif);
				move = terminal.readLine();
			}
			bout.write("quit" + "\r\n");
			bout.flush();
			// socket.close();

		} catch (IOException ex) {
			System.err.println(ex);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException ex) {
					System.out.println("WHY");
				}
			}
		}
	}

	static void readBoard(BufferedReader bif) {
		try {
			String encodedBoard = bif.readLine();
			System.out.println(encodedBoard);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}