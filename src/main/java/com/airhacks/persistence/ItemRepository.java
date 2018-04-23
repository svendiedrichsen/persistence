package com.airhacks.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Stateless
public class ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Item> findById(long id) {
        return Optional.ofNullable(entityManager.find(Item.class, id));
    }

    public Item create(String content) {
        Item item = new Item();
        item.setContent(content);
        entityManager.persist(item);
        return item;
    }

    public Optional<Item> update(long id, String content) {
        Optional<Item> itemById = findById(id);
        itemById.ifPresent(i -> i.setContent(content));
        return itemById;
    }

}
