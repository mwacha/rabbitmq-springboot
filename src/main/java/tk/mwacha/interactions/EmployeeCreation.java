package tk.mwacha.interactions;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.entities.Employee;
import tk.mwacha.repositories.EmployeeRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeCreation {

  private final EmployeeRepository repository;

  @Transactional
  public void create(Employee employee) {

    log.info("CREATING USER NAME {}", employee.getEmployeeName());

    repository.save(employee);

    log.info("EMPLOYEE CREATED ID {}", employee.getId());
  }
}
