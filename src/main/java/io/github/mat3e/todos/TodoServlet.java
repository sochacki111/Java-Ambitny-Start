package io.github.mat3e.todos;

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
}
