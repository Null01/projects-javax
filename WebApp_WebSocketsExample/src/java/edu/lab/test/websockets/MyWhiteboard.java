/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.test.websockets;

import edu.lab.test.websockets.pojo.Shape;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author andresfelipegarciaduran
 */
@ServerEndpoint(value = "/whiteboardendpoint", decoders = {FigureDecoder.class}, encoders = {FigureEncoder.class})
public class MyWhiteboard {

    private static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void broadcastFigure(Shape figure, Session session) throws IOException, EncodeException {
        System.out.println("broadcastFigure: " + figure);
        for (Session u : users) {
            if (!u.equals(session)) {
                u.getBasicRemote().sendObject(figure);
            }
        }
    }

    @OnOpen
    public void onOpen(Session peer) {
        users.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        users.remove(peer);
    }

    public static Set<Session> getUsers() {
        return users;
    }

}
