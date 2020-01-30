package io.github.mat3e.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Todo", urlPatterns = {"/api/todos/*"})
public class TodoServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);

    private ObjectMapper mapper;
    private TodoRepository repository;
    private Todo newTodo;

    /**
     * Calling default Service which is "HelloService"
     * Servlet container needs it
     * After adding constructor, default constructor stop exist, so we need to create it
     */
    @SuppressWarnings("unused")
    public TodoServlet() {
        this(new ObjectMapper(), new TodoRepository());

    }

    TodoServlet(ObjectMapper mapper, TodoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Got request for finding all languages " + req.getParameterMap());
        // add header send json as a response
        resp.setContentType("application/json;charset=UTF-8");
        // write 'repository.findAll()' into response stream
        mapper.writeValue(resp.getOutputStream(), repository.findAll());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Got request for creating new Todo ");
        newTodo = mapper.readValue(req.getInputStream(), Todo.class);
        // add header send json as a response
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.addTodo(newTodo));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pathInfo = req.getPathInfo();
        logger.info("Got request for toggling todo status. Todo Id: " + pathInfo.substring(1));

        try {
            var todoId = Integer.valueOf(pathInfo.substring(1));
            // add header send json as a response
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(), repository.toggleTodo(todoId));
        } catch (NumberFormatException e) {
            logger.info("Wrong path used " + pathInfo);
        }



    }
}
