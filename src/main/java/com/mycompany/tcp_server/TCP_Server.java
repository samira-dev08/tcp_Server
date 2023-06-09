/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tcp_server;

import com.mycompany.fileutil.FileUtil;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 *
 * @author SamNar
 */
public class TCP_Server {

    public static void main(String[] args) throws Exception {
        readAsByte();
    }

    public static void readAsByte() throws Exception {
        try {
            ServerSocket ourFirstServerSocket = new ServerSocket(6789);
            while (true) {
                System.out.println("musteri gozleyirem ");
                Socket connection = ourFirstServerSocket.accept();
                //System.out.println("musteri geldi");
                DataInputStream dataStream = new DataInputStream(connection.getInputStream());

               String result = readRequest(dataStream);
                System.out.println(result);
                //byte[] arr = readMessage(dataStream);
                //System.out.println("message lenght2=" + arr.length);
                // FileUtil.writeBytes(arr, "C:\\Users\\SamNar\\Desktop\\Foto\\cvfotojpg.jpg");
                OutputStream outputStream = connection.getOutputStream();

                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                writeResponse(dataOutputStream, "Hey I am working!");
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println("port doludu");
        }

    }

    public static byte[] readMessage(DataInputStream din) throws IOException {
        int msgLen = din.readInt();
        System.out.println("message lebght1= " + msgLen);
        byte[] msg = new byte[msgLen];
        din.readFully(msg);
        return msg;
    }

    public static String readRequest(InputStream sin) throws Exception {
        InputStreamReader isr = new InputStreamReader(sin);
        BufferedReader reader = new BufferedReader(isr);
        String msg = "";
        while (true) {
            String s = reader.readLine();
            if (s == null || s.trim().length() == 0) {
                break;
            } else {
                msg = msg + s + "\r\n";
            }
            System.out.println("Server request: " + s);
            System.out.println();
        }
        return msg;
    }

    public static void writeResponse(OutputStream out, String s) throws Exception {
        String response = "HTTP/1.1 200 OK\r\n"
               // + "Server: YarServer/2089-09-09\r\n" //onemli deyiller
                //+ "Content-Type: text/html\r\n"
                //+ "Content-Lenght: " + s.length() + "\r\n"
                + "Connection: close\r\n\r\n";
        String result = response + s;
        out.write(result.getBytes());
        out.flush();
    }

    public static void readAsString() throws Exception {
        try {
            ServerSocket ourFirstServerSocket = new ServerSocket(6789);
            while (true) {
                System.out.println("musteri gozleyirem ");
                Socket connection = ourFirstServerSocket.accept();
                System.out.println("musteri geldi");
                InputStream is = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);
                BufferedReader bReader = new BufferedReader(reader);

                String messageFromClient = bReader.readLine();
                System.out.println("client message: " + messageFromClient);
            }
        } catch (Exception ex) {
            System.out.println("port doludu");
        }
    }
}
