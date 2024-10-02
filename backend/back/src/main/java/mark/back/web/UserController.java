package mark.back.web;

import lombok.AllArgsConstructor;
import mark.back.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin(
        origins = "http://localhost:3000", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
)
@RequestMapping("/user")
public class UserController {
    private UserService userService;
}