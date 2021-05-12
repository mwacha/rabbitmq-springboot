package tk.mwacha.alloy.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

  private UUID id;
  private String employeeName;
  private String login;
  private String email;
  private String password;

}
