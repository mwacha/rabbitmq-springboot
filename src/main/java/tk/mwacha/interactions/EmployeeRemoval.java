package tk.mwacha.interactions;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.exceptions.EntityNotFoundException;
import tk.mwacha.repositories.EmployeeRepository;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeRemoval {

  private final EmployeeRepository repository;

  @Transactional
  public void remove(UUID id) {
    var employee = repository.findById(id).orElseThrow(EntityNotFoundException::new);
    employee.disable();
    repository.save(employee);

    log.info("EMPLOYEE HAS BEEN REMOVED ID {} ", id);
  }
}
