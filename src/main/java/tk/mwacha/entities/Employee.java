package tk.mwacha.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import tk.mwacha.alloy.dto.EmployeeDTO;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Where(clause = "deleted_at is null")
public class Employee implements Serializable {
  private static final long serialVersionUID = 2000979603923137378L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "pg-uuid")
  @GenericGenerator(name = "pg-uuid", strategy = "uuid2")
  private UUID id;
  private String employeeName;
  private String login;
  private String email;
  private String password;
  private LocalDateTime deletedAt;

  public void disable() {
    this.deletedAt = LocalDateTime.now();
  }

  public static Employee of(EmployeeDTO dto) {
    return Employee.builder()
        .id(dto.getId())
        .employeeName(dto.getEmployeeName())
        .login(dto.getLogin())
        .email(dto.getEmail())
        .password((dto.getPassword()))
        .build();
  }

}
