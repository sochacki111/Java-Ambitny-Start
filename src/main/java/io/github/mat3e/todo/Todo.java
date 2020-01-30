package io.github.mat3e.todo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todos")
public class Todo {

    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy = "increment")
    private Integer id;
    private String text;
    private boolean done;

    /**
     * Hibernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    Todo() {

    }

    public Todo(String text, Boolean done) {
        this.text = text;
        this.done = done;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
