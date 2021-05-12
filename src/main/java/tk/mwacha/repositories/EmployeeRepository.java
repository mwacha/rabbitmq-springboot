package tk.mwacha.repositories;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tk.mwacha.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

  @Query("select e from Employee e")
  Page<Employee> list(Pageable pageable);
}
