package dat.controllers;

import io.javalin.http.Context;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public interface IController<T, D> {
    void read(Context ctx);
    void readByName(Context ctx);
    void readAll(Context ctx);
    void create(Context ctx);
    void update(Context ctx);
    void delete(Context ctx);
}
