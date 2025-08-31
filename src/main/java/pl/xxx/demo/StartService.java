package pl.xxx.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartService {
    private final StartRepository startRepository;

    public StartService(StartRepository startRepository) {
        this.startRepository = startRepository;
    }

    public List<Start> getAll() {
        return startRepository.findAll();
    }
}
