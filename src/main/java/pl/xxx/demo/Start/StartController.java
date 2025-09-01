package pl.xxx.demo.Start;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StartController {
    private final StartService startService;
    public StartController(StartService startService) {
        this.startService = startService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello Maria";
    }

    @GetMapping("/start")
    public List<Start> starts() {
        return startService.getAll();

    }

}
