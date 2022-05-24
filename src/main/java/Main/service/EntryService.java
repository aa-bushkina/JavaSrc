package Main.service;

import Main.entity.Entry;

import java.text.ParseException;
import java.util.List;

public interface EntryService
{
  List<Entry> getAllEntries();

  List<Entry> getAllEntriesByClient(Integer clientId);

  List<Entry> getAllEntriesByBook(Integer bookId);

  Entry addEntry(Integer clientId, Integer bookId) throws ParseException;

  Entry updateDataRet(Integer id) throws ParseException;

  Entry deleteEntry(Integer id) throws ParseException;
}
