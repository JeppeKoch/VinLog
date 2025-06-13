package dat.controllers.impl;

import dat.DAOs.impl.ArtistDAO;
import dat.DTOs.ArtistDTO;
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
public class ArtistController implements IController<ArtistDTO, Long> {

    private final Logger log = LoggerFactory.getLogger(ArtistController.class);
    private EntityManagerFactory emf;
    private ArtistDAO artistDAO;

    public ArtistController(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @Override
    public void read(Context ctx) {
        try {
            Long id = ctx.pathParamAsClass("id", Long.class).get();

            ArtistDTO artistDTO = ArtistDAO.read(id);
            ctx.res().setStatus(200);
            ctx.json(artistDTO, ArtistDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void readByName(Context ctx) {
        try {
            String artistName = ctx.pathParam("artistname");

            ArtistDTO artistDTO = artistDAO.readByName(artistName);
            ctx.res().setStatus(200);
            ctx.json(artistDTO, ArtistDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void readAll(Context ctx) {

        try {
            List<ArtistDTO> artistDTOS = artistDAO.readAll();
            // response
            ctx.res().setStatus(200);
            ctx.json(artistDTOS, ArtistDTO.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void create(Context ctx) {
        try {
            ArtistDTO ArtistDTO = ctx.bodyAsClass(ArtistDTO.class);
            ctx.status(HttpStatus.CREATED);
            ctx.json(artistDAO.create(ArtistDTO));
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
            ArtistDTO ArtistDTO = ctx.bodyAsClass(ArtistDTO.class);

            // == querying ==
            Long id = ctx.pathParamAsClass("id", long.class).get();
            artistDAO.update(id, ArtistDTO);

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
            artistDAO.delete(id);
            // response
            ctx.res().setStatus(204);
        } catch (Exception e) {
            log.error("400{}", e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }


}
