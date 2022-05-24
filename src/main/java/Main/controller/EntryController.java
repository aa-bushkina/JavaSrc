package Main.controller;


import Main.entity.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Main.service.EntryServiceImpl;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController
{
  @Autowired
  private EntryServiceImpl entryService;

  //GET REQUESTS
  @GetMapping("/all")
  public List<Entry> getAllEntries()
  {
    return entryService.getAllEntries();
  }

  @GetMapping("/all/by-client")
  public List<Entry> getAllEntriesByClientId(@RequestParam Integer clientId)
  {
    return entryService.getAllEntriesByClient(clientId);
  }

  @GetMapping("/all/by-book")
  public List<Entry> getAllEntriesByBookId(@RequestParam Integer bookId)
  {
    return entryService.getAllEntriesByBook(bookId);
  }

  //POST REQUESTS
  @PostMapping("/entry/add")
  public Entry addEntry(@RequestParam Integer clientId, @RequestParam Integer bookId) throws ParseException

  {
    return entryService.addEntry(clientId, bookId);
  }

  //UPDATE REQUESTS
  @PutMapping("/entry/update/data-ret")
  Entry updateDataRet(@RequestParam Integer id)
  {
    return entryService.updateDataRet(id);
  }

  //DELETE REQUESTS
  @DeleteMapping("/entry/delete")
  Entry deleteEntry(@RequestParam Integer id)
  {
    return entryService.deleteEntry(id);
  }
}
