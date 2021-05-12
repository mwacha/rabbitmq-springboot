package tk.mwacha.interactions;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tk.mwacha.entities.Employee;
import tk.mwacha.exceptions.EntityNotFoundException;
import tk.mwacha.repositories.EmployeeRepository;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeFind {


  private final EmployeeRepository repository;

  public Employee findActive(UUID id) {
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }


  public Page<Employee> findActives(Pageable pageable) {
  return repository.findAll(pageable);
  }
}
