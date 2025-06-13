package dat.controllers.impl;

import dat.DAOs.impl.AlbumDAO;
import dat.DTOs.AlbumDTO;

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
public class AlbumController implements IController<AlbumDTO, Long> {


    private final Logger log = LoggerFactory.getLogger(AlbumController.class);
    private EntityManagerFactory emf;
    private AlbumDAO albumDAO;

    public AlbumController(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @Override
    public void read(Context ctx) {
        try {
            Long id = ctx.pathParamAsClass("id", Long.class).get();

            AlbumDTO albumDTO = albumDAO.read(id);
            ctx.res().setStatus(200);
            ctx.json(albumDTO, AlbumDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void readByName(Context ctx) {
        try {
            String albumName = ctx.pathParam("Albumname");

            AlbumDTO albumDTO = albumDAO.readByName(albumName);
            ctx.res().setStatus(200);
            ctx.json(albumDTO, AlbumDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void readAll(Context ctx) {

        try {
            List<AlbumDTO> albumDTOS = albumDAO.readAll();
            // response
            ctx.res().setStatus(200);
            ctx.json(albumDTOS, AlbumDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void create(Context ctx) {
        try {
            AlbumDTO albumDTO = ctx.bodyAsClass(AlbumDTO.class);
            ctx.status(HttpStatus.CREATED);
            ctx.json(albumDAO.create(albumDTO));
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
            AlbumDTO albumDTO = ctx.bodyAsClass(AlbumDTO.class);

            // == querying ==
            Long id = ctx.pathParamAsClass("id", long.class).get();
            albumDAO.update(id, albumDTO);

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
            albumDAO.delete(id);
            // response
            ctx.res().setStatus(204);
        } catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

}
