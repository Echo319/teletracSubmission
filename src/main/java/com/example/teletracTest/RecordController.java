package com.example.teletracTest;

import com.example.teletracTest.Data.Record;
import com.example.teletracTest.Exceptions.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RecordController {

    private static final Logger log = LoggerFactory.getLogger(RecordController.class);

    private final RecordRepository repository;

    RecordController(RecordRepository repository) {
        this.repository = repository;
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Record postRecord(@Valid @RequestBody Record recordIn) {
        log.error("POST No postfix returning 400");
        return null;
    }

    @PostMapping("/echo")
    @ResponseStatus(value = HttpStatus.OK)
    public Record echoRecord(@Valid @RequestBody Record recordIn) {
        log.debug("POST /echo");
        Record savedRecord = repository.save(recordIn);
        log.info("Record " + savedRecord + " saved");
        return savedRecord;
    }

    @PostMapping("/device")
    @ResponseStatus(value = HttpStatus.OK)
    public String getDeviceId(@Valid @RequestBody Record recordIn) {
        log.debug("POST /device");
        Record savedRecord = repository.save(recordIn);
        log.info("Record " + savedRecord + " saved");
        return savedRecord.getDeviceId();
    }

    @PostMapping("/nocontent")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void noContent(@Valid @RequestBody Record recordIn) {
        log.debug("POST /nocontent");
        Record savedRecord = repository.save(recordIn);
        log.info("Record " + savedRecord + " saved");
    }

    // Helper functions for debug purposes
    @GetMapping()
    public List<Record> getAllRecords() {
        return repository.findAll();
    }

    @DeleteMapping("/all")
    private void deleteAll() {
        repository.deleteAll();
    }

    @GetMapping("/{id}")
    public Record getRecord(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
