package com.ilea.storemanager.UnitTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ilea.storemanager.Item;
import com.ilea.storemanager.Repo.StoreRepo;
import com.ilea.storemanager.Service.StoreService;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

  List<Item> itemsList;
  Date date;

  @Mock
  StoreRepo repo;

  @InjectMocks
  StoreService service;

  @Before
  public void setUp() throws ParseException {
    String date_string = "26-09-2023";
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    // Parsing the given String to Date object
    date = formatter.parse(date_string);

    itemsList = Arrays.asList(
        new Item("Furniture", "couch", BigDecimal.valueOf(100),
            BigDecimal.valueOf(15), date));
  }

  @Test
  public void findItemTest(){
    when(repo.getItems()).thenReturn(itemsList);

    Item item = service.findItem(itemsList.get(0).getId());

    assertEquals("Furniture", item.getCategory());
  }

  @Test
  public void findIndexItemByIdTest() {
    when(repo.getItems()).thenReturn(itemsList);

    int valid = service.findIndexItemById(itemsList.get(0).getId());
    int invalid = service.findIndexItemById("1789");

    assertEquals(0, valid);
    assertEquals(-1, invalid);
    verify(repo, atLeastOnce()).getItems();
  }

  @Test
  public void submitFormUpdateTest() {
    when(repo.getItem(0)).thenReturn(itemsList.get(0));
    when(repo.getItems()).thenReturn(itemsList);

    String result = service.submitForm(itemsList.get(0));
    verify(repo, times(1)).updateItem(0, itemsList.get(0));
    assertEquals("ok", result);
  }

  @Test
  public void submitFormAddTest() {
    when(repo.getItems()).thenReturn(itemsList);

    Item testItem = new Item("Tech", "phone", BigDecimal.valueOf(1249),
            BigDecimal.valueOf(50), date);
    String result = service.submitForm(testItem);
    verify(repo,times(1)).addItem(testItem);
    assertEquals("ok",result);
  }

}
