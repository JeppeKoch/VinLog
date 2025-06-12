package dat.controllers.impl;

import dat.DAOs.impl.UserDAO;
import dat.DTOs.UserDTO;
import dat.Exceptions.ApiException;
import dat.controllers.IController;


import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class UserController implements IController<UserDTO, Integer> {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private EntityManagerFactory emf;
    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void read(Context ctx) {
        try {
            Long id = ctx.pathParamAsClass("id", Long.class).get();

            UserDTO userDTO = userDAO.read(id);
            ctx.res().setStatus(200);
            ctx.json(userDTO, UserDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void readByName(Context ctx) {
        try {
            String username = ctx.pathParam("username");

            UserDTO userDTO = userDAO.readByName(username);
            ctx.res().setStatus(200);
            ctx.json(userDTO, UserDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void readAll(Context ctx) {

        try {
            List<UserDTO> userDTOS = userDAO.readAll();
            // response
            ctx.res().setStatus(200);
            ctx.json(userDTOS, UserDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void create(Context ctx) {
        try {
            UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
            ctx.status(HttpStatus.CREATED);
            ctx.json(userDAO.create(userDTO));
            // == response ==
            ctx.res().setStatus(201);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void update(Context ctx) {
        try {
            UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);

            // == querying ==
            Long id = ctx.pathParamAsClass("id", long.class).get();
            userDAO.update(id, userDTO);

            // == response ==
            ctx.res().setStatus(200);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void delete(Context ctx) {
        try {
            Long id = ctx.pathParamAsClass("id", Long.class).get();
            // entity
            userDAO.delete(id);
            // response
            ctx.res().setStatus(204);
        } catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }


}

