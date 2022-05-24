package Main.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book_types")
public class BookType
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "cnt", nullable = false)
  private Integer cnt;

  @Column(name = "fine", nullable = false, precision = 20, scale = 2)
  private Double fine;

  @Column(name = "day_count", nullable = false)
  private Integer dayCount;

  public BookType(String name, Integer cnt, Double fine, Integer dayCount)
  {
    this.name = name;
    this.cnt = cnt;
    this.fine = fine;
    this.dayCount = dayCount;
  }

  @Override
  public String toString()
  {
    return "BookType{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", cnt=" + cnt +
      ", fine=" + fine +
      ", dayCount=" + dayCount +
      '}';
  }
}
