package com.ilea.storemanager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ilea.storemanager.Constants;
import com.ilea.storemanager.Item;
import com.ilea.storemanager.Service.StoreService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class StoreController {

  StoreService service = new StoreService();

  @GetMapping("/")
  public String getForm(Model model, @RequestParam(required = false) String id) {
    model.addAttribute("item", service.findItem(id));
    return "form";
  }

  @PostMapping("/submitForm")
  public String submitFormHandler(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes) {
    if (item.getDiscount() != null && item.getPrice() != null) {
      if (item.getDiscount().compareTo(item.getPrice()) >= 0) {
        result.rejectValue("discount", "", "Discount can not be more than the price");
      }
    }
    if (result.hasErrors()) {
      return "form";
    }
    String res = service.submitForm(item);
    if (res.equals("ok")) {
      redirectAttributes.addFlashAttribute("notification", Constants.SUCCESS_MESSAGE);
    } else {
      redirectAttributes.addFlashAttribute("notification", Constants.FAIL_MESSAGE);
    }
    return "redirect:/inventory";
  }

  @GetMapping("/inventory")
  public String getInventory(Model model) {
    model.addAttribute("items", service.getItems());
    return "inventory";
  }

}
