package Main.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "journal")
public class Entry
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @ManyToOne(optional = false, cascade = CascadeType.MERGE)
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne(optional = false, cascade = CascadeType.MERGE)
  @JoinColumn(name = "client_id")
  private Client client;

  @Column(name = "data_beg", nullable = false)
  private java.sql.Timestamp dataBeg;

  @Column(name = "data_end", nullable = false)
  private java.sql.Timestamp dataEnd;

  @Column(name = "data_ret")
  private java.sql.Timestamp dataRet;

  public Entry(Book book, Client client, Timestamp dataBeg, Timestamp dataEnd, Timestamp dataRet)
  {
    this.book = book;
    this.client = client;
    this.dataBeg = dataBeg;
    this.dataEnd = dataEnd;
    this.dataRet = dataRet;
  }

  @Override
  public String toString()
  {
    return "EntryController{" +
      "id=" + id +
      ", book=" + book +
      ", client=" + client +
      ", dataBeg=" + dataBeg +
      ", dataEnd=" + dataEnd +
      ", dataRet=" + dataRet +
      '}';
  }
}
