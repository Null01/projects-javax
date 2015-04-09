package edu.lab.test.websockets.pojo;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author andresfelipegarciaduran
 */
public class Shape {

    private JsonObject jsonObject;

    public Shape(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        Json.createWriter(writer).write(jsonObject);
        return writer.toString();
    }
}
