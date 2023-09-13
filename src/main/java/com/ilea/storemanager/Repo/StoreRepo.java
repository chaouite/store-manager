package com.ilea.storemanager.Repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ilea.storemanager.Item;

@Repository
public class StoreRepo {
  List<Item> itemsList = new ArrayList<>();

  public List<Item> getItems() {
    return itemsList;
  }

  public Item getItem(int index) {
    return itemsList.get(index);
  }

  public void updateItem(int index, Item item) {
    itemsList.set(index, item);
  }

  public void addItem(Item item) {
    itemsList.add(item);
  }

}
