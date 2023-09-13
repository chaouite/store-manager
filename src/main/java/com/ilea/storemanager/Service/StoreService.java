package com.ilea.storemanager.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilea.storemanager.Item;
import com.ilea.storemanager.Repo.StoreRepo;

@Service
public class StoreService {
  @Autowired
  StoreRepo repo ;

  public List<Item> getItems() {
    return repo.getItems();
  }

  public Item getItem(int index) {
    return repo.getItem(index);
  }

  public void updateItem(int index, Item item) {
    repo.updateItem(index, item);
  }

  public void addItem(Item item) {
    repo.addItem(item);
  }

  public Item findItem(String id) {
    int itemToDisplayIndex = findIndexItemById(id);
    return (itemToDisplayIndex >= 0) ? getItems().get(itemToDisplayIndex) : new Item();
  }

  public String submitForm(Item item) {
    int oldItemIndex = findIndexItemById(item.getId());
    if (oldItemIndex >= 0) {
      if (in3Days(item.getDate(), getItem(oldItemIndex).getDate())) {
        updateItem(oldItemIndex, item);
         return "ok";
      } else {
        return "notOk";
      }
    } else {
      addItem(item);
      return "ok";
    }
  }

  private int findIndexItemById(String id) {
    for (int i = 0; i < getItems().size(); i++) {
      if (getItems().get(i).getId().equals(id))
        return i;
    }
    return -1;
  }

  public boolean in3Days(Date newDate, Date oldDate) {
    long diff = Math.abs(newDate.getTime() - oldDate.getTime());
    return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 3;
  }

}
