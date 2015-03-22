/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lab.modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author andresfelipegarciaduran
 */
public class Publicacion {

    private Integer idpublish;
    private String tittle;
    private String description;
    private Date datecreated;
    private String email;
    private List<Comentario> comment;

    private Publicacion() {

    }

    public Publicacion(Integer idpublish, String tittle, String description, Date datecreated, String email, List<Comentario> comment) {
        this.idpublish = idpublish;
        this.tittle = tittle;
        this.description = description;
        this.datecreated = datecreated;
        this.email = email;
        this.comment = comment;
    }

    public Integer getIdpublish() {
        return idpublish;
    }

    public void setIdpublish(Integer idpublish) {
        this.idpublish = idpublish;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Comentario> getComment() {
        return comment;
    }

    public void setComment(List<Comentario> comment) {
        this.comment = comment;
    }

}
