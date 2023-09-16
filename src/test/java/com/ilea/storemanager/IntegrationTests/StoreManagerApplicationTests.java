package com.ilea.storemanager.IntegrationTests;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.ilea.storemanager.Controller.StoreController;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class StoreManagerApplicationTests {

  @Autowired
  MockMvc mockMvc;

  @Test
  void contextLoads() {
  }

  @Test
  public void getFormTest() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.get("/?id='123'");
    mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(view().name("form"))
        .andExpect(model().attributeExists("item"));

  }

  @Test
  public void submitFormSuccessTest() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.post("/submitForm")
        .param("category", "Tech")
        .param("name", "iPhone pro")
        .param("price", "1249")
        .param("discount", "25")
        .param("date", "2023-02-14");

    mockMvc.perform(request)
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/inventory"))
        .andExpect(flash().attribute("notification", "success"));
  }

  @Test
  public void submitFormFailTest() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.post("/submitForm")
        .param("category", "   ")
        .param("name", "iPhone pro")
        .param("price", "1249")
        .param("discount", "25")
        .param("date", "2023-02-14");

    mockMvc.perform(request)
        .andExpect(view().name("form"));
  }

  @Test
  public void getInventoryTest() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.get("/inventory");
    mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(view().name("inventory"))
        .andExpect(model().attributeExists("items"));

  }
}
