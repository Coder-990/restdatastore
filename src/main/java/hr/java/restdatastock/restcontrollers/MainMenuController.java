package hr.java.restdatastock.restcontrollers;

import hr.java.restdatastock.model.dtos.FirmeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(MainMenuController.BASE_URL)
@RestController
public class MainMenuController {

    public static final String BASE_URL = "/main-menu";


    @GetMapping()
    public String getMainPage() {

        return "main-menu";
    }
}
