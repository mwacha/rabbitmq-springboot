package tk.mwacha.interactions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.entities.Employee;
import tk.mwacha.repositories.EmployeeRepository;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeUpdate {

  private EmployeeRepository repository;

  @Transactional
  public void update(Employee employee) {
    repository.save(employee);

    log.info("EMPLOYEE HAS BEEN UPDATED ID {} ", employee.getId());
  }
}
