package com.example.itemmanagement.Item;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableMapRepositories
public class ItemService {
    private final CrudRepository<Item, Long> repository;

    public ItemService(CrudRepository<Item, Long> repository) {
        this.repository = repository;
    }

    private static List<Item> Items() {
        return List.of(
                new Item("1", "a", "apple", 10),
                new Item("2", "b", "banana", 20),
                new Item("3", "c", "carrot", 30)
        );
    }

    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        Iterable<Item> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Item> find(Long id) {
        return repository.findById(id);
    }

    public Item create(Item item) {
        Item copy = new Item(
                item.getId(),
                item.getName(),
                item.getDesc(),
                item.getPrice()
        );
        return repository.save(copy);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}