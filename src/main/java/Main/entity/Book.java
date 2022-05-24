package Main.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "books")
public class Book
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "cnt", nullable = false)
  private Integer cnt;

  @ManyToOne(optional = false, cascade = CascadeType.MERGE)
  @JoinColumn(name = "type_id")
  private BookType bookType;

  public Book(String name, Integer cnt, BookType bookType)
  {
    this.name = name;
    this.cnt = cnt;
    this.bookType = bookType;
  }

  @Override
  public String toString()
  {
    return "Book{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", cnt=" + cnt +
      ", bookType=" + bookType +
      '}';
  }

}
