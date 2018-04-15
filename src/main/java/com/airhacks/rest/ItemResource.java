package com.airhacks.rest;

import com.airhacks.persistence.Item;
import com.airhacks.persistence.ItemRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("items")
public class ItemResource {

    @Inject
    private ItemRepository repository;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return repository.findById(id)
            .map(item -> Response.ok(toJson(item), MediaType.APPLICATION_JSON_TYPE).build())
            .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("{content}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("content") String content) {
        Item item = repository.create(content);
        return Response.ok(toJson(item), MediaType.APPLICATION_JSON_TYPE).build();
    }

    private JsonObject toJson(Item item) {
        return Json.createObjectBuilder()
                .add("id", item.getId())
                .add("content", item.getContent())
                .build();
    }

}
