package Main.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "clients")
public class Client
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name", nullable = false, length = 20)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 20)
  private String lastName;

  @Column(name = "pather_name", length = 20)
  private String patherName;

  @Column(name = "passport_seria", nullable = false, length = 20)
  private String passportSeria;

  @Column(name = "passport_num", nullable = false, length = 20)
  private String passportNum;

  public Client(String lastName, String firstName, String patherName, String passportSeria, String passportNum)
  {
    this.lastName = lastName;
    this.firstName = firstName;
    this.patherName = patherName;
    this.passportSeria = passportSeria;
    this.passportNum = passportNum;
  }

  @Override
  public String toString()
  {
    return "Client{" +
      "id=" + id +
      ", firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", patherName='" + patherName + '\'' +
      ", passportSeria='" + passportSeria + '\'' +
      ", passportNum='" + passportNum + '\'' +
      '}';
  }
}
