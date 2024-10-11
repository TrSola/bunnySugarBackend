package websocket;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {

    private JTextArea messageArea;
    private JTextField messageInput;
    private StompSession stompSession;

    public ChatWindow() {
        setTitle("Chat Window");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        messageInput = new JTextField();
        add(messageInput, BorderLayout.SOUTH);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        add(sendButton, BorderLayout.EAST);

        connectWebSocket();
    }

    private void connectWebSocket() {
        // 直接使用 WebSocketClient 進行連接
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        String wsUrl = "ws://localhost:8080/ws/chat";
        stompClient.connect(wsUrl, new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                stompSession = session;
                messageArea.append("Connected to WebSocket.\n");
            }
        });
    }

    private void sendMessage() {
        String message = messageInput.getText().trim();
        if (!message.isEmpty() && stompSession != null) {
            stompSession.send("/app/send", message);
            messageArea.append("Sent: " + message + "\n");
            messageInput.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatWindow chatWindow = new ChatWindow();
            chatWindow.setVisible(true);
        });
    }
}
