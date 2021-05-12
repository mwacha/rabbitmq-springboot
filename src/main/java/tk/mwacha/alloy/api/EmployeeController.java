package tk.mwacha.alloy.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mwacha.alloy.dto.EmployeeDTO;
import tk.mwacha.entities.Employee;
import tk.mwacha.interactions.EmployeeCreation;
import tk.mwacha.interactions.EmployeeFind;
import tk.mwacha.interactions.EmployeeRemoval;
import tk.mwacha.interactions.EmployeeUpdate;

import java.util.UUID;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/employees")
public class EmployeeController {

    private final EmployeeFind interactionFind;
    private final EmployeeUpdate interactionUpdate;
    private final EmployeeCreation creation;
    private final EmployeeRemoval removal;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Employee> list(@PageableDefault Pageable pageable) {
        return interactionFind.findActives(pageable);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object get(@PathVariable UUID id) {
        return interactionFind.findActive(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody EmployeeDTO dto) {
        creation.create(Employee.of(dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable UUID id) {
        removal.remove(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody EmployeeDTO dto) {
        interactionUpdate.update(Employee.of(dto));
    }
}
