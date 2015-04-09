/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.test.websockets;

import edu.lab.test.websockets.pojo.Shape;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author andresfelipegarciaduran
 */
public class FigureEncoder implements Encoder.Text<Shape> {

    @Override
    public String encode(Shape object) throws EncodeException {
        return object.getJsonObject().toString();
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

}
