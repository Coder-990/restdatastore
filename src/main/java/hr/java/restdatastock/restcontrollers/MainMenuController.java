package hr.java.restdatastock.restcontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(MainMenuController.BASE_URL)
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainMenuController {

    public static final String BASE_URL = "/main-menu";


    @GetMapping()
    public String getMainPage() {

        return "main-menu";
    }
}
