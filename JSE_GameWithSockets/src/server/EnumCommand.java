/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author duran
 */
public enum EnumCommand {

    SCORE_USERNAME("score_username"),
    SAVE_SCORE("save_score"),
    ADD_NEW_SCORE("add_new_score");

    private EnumCommand(String commmand) {
        this.commmand = commmand;
    }

    private String commmand;

    public String getCommmand() {
        return commmand;
    }

    public void setCommmand(String commmand) {
        this.commmand = commmand;
    }

}
