package dat.controllers.impl;

import dat.DAOs.impl.TrackDAO;
import dat.DTOs.TrackDTO;
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
public class TrackController implements IController<TrackDTO, Long> {

    private final Logger log = LoggerFactory.getLogger(TrackController.class);
    private EntityManagerFactory emf;
    private TrackDAO trackDAO;

    public TrackController(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public void read(Context ctx) {
        try {
            Long id = ctx.pathParamAsClass("id", Long.class).get();

            TrackDTO trackDTO = trackDAO.read(id);
            ctx.res().setStatus(200);
            ctx.json(trackDTO, TrackDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void readByName(Context ctx) {
        try {
            String trackName = ctx.pathParam("trackname");

            TrackDTO trackDTO = trackDAO.readByName(trackName);
            ctx.res().setStatus(200);
            ctx.json(trackDTO, TrackDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void readAll(Context ctx) {

        try {
            List<TrackDTO> trackDTOS = trackDAO.readAll();
            // response
            ctx.res().setStatus(200);
            ctx.json(trackDTOS, TrackDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void create(Context ctx) {
        try {
            TrackDTO trackDTO = ctx.bodyAsClass(TrackDTO.class);
            ctx.status(HttpStatus.CREATED);
            ctx.json(trackDAO.create(trackDTO));
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
            TrackDTO trackDTO = ctx.bodyAsClass(TrackDTO.class);

            // == querying ==
            Long id = ctx.pathParamAsClass("id", long.class).get();
            trackDAO.update(id, trackDTO);

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
            trackDAO.delete(id);
            // response
            ctx.res().setStatus(204);
        } catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }


}
