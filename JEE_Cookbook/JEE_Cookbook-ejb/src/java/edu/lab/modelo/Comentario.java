/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Comentario implements Serializable{

    private Integer idcomment;
    private int idcommenttoothercomment;
    private String commentspublish;
    private Date datecreated;
    private Usuario email;
    private Comentario comment;

    public Comentario(Integer idcomment, int idcommenttoothercomment, String commentspublish, Date datecreated, Usuario email, Comentario comment) {
        this.idcomment = idcomment;
        this.idcommenttoothercomment = idcommenttoothercomment;
        this.commentspublish = commentspublish;
        this.datecreated = datecreated;
        this.email = email;
        this.comment = comment;
    }

    public Integer getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(Integer idcomment) {
        this.idcomment = idcomment;
    }

    public int getIdcommenttoothercomment() {
        return idcommenttoothercomment;
    }

    public void setIdcommenttoothercomment(int idcommenttoothercomment) {
        this.idcommenttoothercomment = idcommenttoothercomment;
    }

    public String getCommentspublish() {
        return commentspublish;
    }

    public void setCommentspublish(String commentspublish) {
        this.commentspublish = commentspublish;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Usuario getEmail() {
        return email;
    }

    public void setEmail(Usuario email) {
        this.email = email;
    }

    public Comentario getComment() {
        return comment;
    }

    public void setComment(Comentario comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return idcomment + " " + idcommenttoothercomment + " " + commentspublish + " " + datecreated + " " + email + " " + comment;
    }

}
