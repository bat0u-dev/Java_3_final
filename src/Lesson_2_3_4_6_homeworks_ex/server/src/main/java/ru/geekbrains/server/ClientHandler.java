package ru.geekbrains.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private JChatServer server;
    private Socket clientSocket;
    private DataInputStream Input;
    private DataOutputStream Output;
    int clientID;
    String nickname;
    private static final Logger logger = LogManager.getLogger(ClientHandler.class);



    public ClientHandler(JChatServer server, Socket clientSocket, int clientID) {
        try {
            this.server = server;
            this.clientSocket = clientSocket;
            this.clientID = clientID;
            this.Input = new DataInputStream(clientSocket.getInputStream());
            this.Output = new DataOutputStream(clientSocket.getOutputStream());

            JChatServer.getExecutorService().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String inputMsg = Input.readUTF();
                            String[] tokens = inputMsg.split("\\s");
                            if (tokens[0].equals("/auth")) {
                                String nickFromDB = SQLHandler.getNickByLoginAnPassword(tokens[1], tokens[2]);
                                if (nickFromDB != null) {
                                    ClientHandler.this.sendMsg("/authOk");
                                    server.subscribe(ClientHandler.this);
                                    nickname = nickFromDB;
                                    Output.writeUTF("Authorization successful.");
                                    break;
                                }
                            }

                        }
                        while (true) {
                            String inputMsg = Input.readUTF();
                            String[] tokens = inputMsg.split("\\s");
                            if (tokens[0].equals("/changeNickTo")) {
                                logger.info("клиент прислал команду смены никнейма");
                                int res = SQLHandler.changeNick(ClientHandler.this.nickname, tokens[1]);
                                switch (res) {
                                    case 0:
                                        System.out.println("error");
                                    case 1:
                                        System.out.println("success");
                                        ClientHandler.this.nickname = tokens[1];
                                        logger.info("Клиент сменил никнейм на " + ClientHandler.this.nickname);
                                }
                                System.out.println(ClientHandler.this.nickname);
                            }
                            System.out.println(ClientHandler.this.nickname + " message :" + inputMsg);
                            if (inputMsg.equals("/end")) {
                                logger.info("клиент прислал служебное сообщение /end и был отключен от сервера");
                                System.out.println("Client aborted the connection.");
//                            Output.writeUTF("Connection has been aborted by client.");
                                break;
                            }
                            server.broadcastMessage(ClientHandler.this.nickname + " : " + inputMsg);
                        }
                    } catch (IOException e) {
                        logger.error(e.getMessage(), e);
                        e.printStackTrace();
                    } finally {
                        try {
                            Input.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(), e);
                            e.printStackTrace();
                        }
                        try {
                            Output.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(), e);
                            e.printStackTrace();
                        }
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage(), e);
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            });
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public void sendMsg(String inputMsg) {
        try {
            Output.writeUTF(inputMsg);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}



