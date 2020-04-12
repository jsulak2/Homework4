package com.homework4.homework4.Models;
import javax.persistence.*;


@Entity
@Table(name = "jokes")
public class jokes
{
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "thevalue")
    private String thevalue;

    public jokes(){ }

    public jokes(String id, String thevalue)
    {
        this.id=id;
        this.thevalue=thevalue;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setThevalue(String thevalue) {
        this.thevalue = thevalue;
    }

    public String getId() {
        return id;
    }

    public String getThevalue() {
        return thevalue;
    }
}
