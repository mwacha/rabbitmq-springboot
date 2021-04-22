package tk.mwacha.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mwacha.dto.Message;
import tk.mwacha.dto.UserDTO;
import tk.mwacha.service.BroadcastService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "api/test")
public class TestController {

private static List<UserDTO> users = List.of(UserDTO.builder().id(UUID.fromString("36382e6a-631f-4455-93d6-79058f1d97f7")).name("Marcelo Wacha").build(),
            UserDTO.builder().id(UUID.randomUUID()).name("Mariana Wacha").build(),
            UserDTO.builder().id(UUID.randomUUID()).name("Luiza Wacha").build(),
            UserDTO.builder().id(UUID.randomUUID()).name("Fabiana Dantas").build(),
            UserDTO.builder().id(UUID.randomUUID()).name("Rodrigo Wacha").build(),
            UserDTO.builder().id(UUID.randomUUID()).name("Milton Dantas").build());


    private final BroadcastService broadcastService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public List<UserDTO> list() {

        return users;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable UUID id) {
        this.users = users.stream().filter(e -> !e.getId().equals(id)).collect(Collectors.toList());

        broadcastService.sendMessage(Message.builder().text("Testando Stomp").build());
    }


}