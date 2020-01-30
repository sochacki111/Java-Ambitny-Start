package io.github.mat3e.todos;

import io.github.mat3e.HibernateUtil;
import io.github.mat3e.lang.Lang;

import java.util.List;
import java.util.Optional;

public class TodoRepository {

    public List<Todo> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        // begin transaction for read only
        var transaction = session.beginTransaction();

        List result = session.createQuery( "FROM Todo", Todo.class).list();

        transaction.commit();
        session.close();
        return result;
    }

    public Todo toggleTodo(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        Todo todo = session.get(Todo.class, id);
        todo.setDone(!todo.getDone());

        transaction.commit();
        session.close();

        return todo;
    }

    public Todo addTodo(Todo newTodo) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        session.persist(newTodo);

        transaction.commit();
        session.close();

        return newTodo;
    }

    /*
    public Optional<Lang> findById(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        // begin transaction for read only
        var transaction = session.beginTransaction();
        var result = session.get(Lang.class, id);
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
    */
}
