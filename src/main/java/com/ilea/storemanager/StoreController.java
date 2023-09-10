package com.ilea.storemanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class StoreController {

  List<Item> itemsList = new ArrayList<>();

  @GetMapping("/")
  public String getForm(Model model, @RequestParam(required = false) String id) {
    int itemToDisplayIndex = findItemByIndex(id);
    model.addAttribute("item", (itemToDisplayIndex >= 0) ? itemsList.get(itemToDisplayIndex) : new Item());
    return "form";
  }

  @PostMapping("/submitForm")
  public String submitFormHandler(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      return "form";
    }
    int oldItemIndex = findItemByIndex(item.getId());
    if (oldItemIndex >= 0) {
      if (within3Days(item.getDate(), itemsList.get(oldItemIndex).getDate())) {
        itemsList.set(oldItemIndex, item);
        redirectAttributes.addFlashAttribute("notification", Constants.SUCCESS_MESSAGE);
      } else {
        redirectAttributes.addFlashAttribute("notification", Constants.FAIL_MESSAGE);
      }

    } else {
      itemsList.add(item);
      redirectAttributes.addFlashAttribute("notification", Constants.SUCCESS_MESSAGE);
    }
    return "redirect:/inventory";
  }

  @GetMapping("/inventory")
  public String getInventory(Model model) {
    model.addAttribute("items", itemsList);
    return "inventory";
  }

  private int findItemByIndex(String id) {
    for (int i = 0; i < itemsList.size(); i++) {
      if (itemsList.get(i).getId().equals(id))
        return i;
    }
    return -1;
  }

  public boolean within3Days(Date newDate, Date oldDate) {
    long diff = Math.abs(newDate.getTime() - oldDate.getTime());
    return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 3;
  }

}
