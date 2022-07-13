package com.downtime.demo.controller;

import com.downtime.demo.exception.ResourceNotFoundException;
import com.downtime.demo.model.Downtime;
import com.downtime.demo.model.BodyData;
import com.downtime.demo.repository.DowntimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/dms")

public class DowntimeController {

    @Autowired
    private DowntimeRepo downtimerepo;

    @GetMapping("/display-downtime")
    public List<Downtime> getAllDowntimes(){
        return downtimerepo.findAll();
    }

    // build create employee REST API
    @PostMapping("/create-downtime")
    public Downtime createDowntime(@RequestParam String provider, @RequestParam String flow, @RequestBody BodyData data) {
        //CR: variable naming could be something better
        Downtime d = new Downtime();
        d.setProvider(provider);
        d.setFlow(flow);
        d.setDown_from(data.getDown_from());
        d.setDown_to(data.getDown_to());
        return downtimerepo.save(d);
    }

    // build get employee by id REST API
    @GetMapping("/display-downtime/{id}")
    public ResponseEntity<Downtime> getDowntimeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        Downtime downtime = downtimerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Downtime not exist with id:" + id));
        return ResponseEntity.ok().body(downtime);
    }


    @GetMapping("/display-downtime/{provider}")
    public ResponseEntity<Downtime> getDowntimeByName(@PathVariable(value = "provider") String provider) throws ResourceNotFoundException{

        //CR Please follow same convention
        if(downtimerepo.findByPName(provider)!=null)
        {
            Downtime downtime = downtimerepo.findByPName(provider);
            return ResponseEntity.ok().body(downtime);
        }
        ResourceNotFoundException er = new ResourceNotFoundException("Provider does not exist with name: " + provider);
        throw er;
    }


    //CR: Please wrap the code in 80/120 chars so that it is readable without scrollung.
    // build update employee REST API
    @PutMapping("/update-downtime/{id}")
    public ResponseEntity<Downtime> updateDowntime(@PathVariable(value = "id") Long id, @RequestBody Downtime downtimeDetails) throws ResourceNotFoundException{
        Downtime updateDowntime = downtimerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No downtime with id: " + id));

        //CR: Please use braces always
        if(downtimeDetails.getProvider()!=null)
            updateDowntime.setProvider(downtimeDetails.getProvider());
        if(downtimeDetails.getFlow()!=null)
            updateDowntime.setFlow(downtimeDetails.getFlow());
        if(downtimeDetails.getDown_from()!=null)
            updateDowntime.setDown_from(downtimeDetails.getDown_from());
        if(downtimeDetails.getDown_to()!=null)
            updateDowntime.setDown_to(downtimeDetails.getDown_to());

        downtimerepo.save(updateDowntime);

        return ResponseEntity.ok(updateDowntime);
    }

    @PutMapping("/update-downtime/{provider}")
    public ResponseEntity<Downtime> updateDowntime(@PathVariable(value = "provider") String provider, @RequestBody Downtime downtimeDetails) throws ResourceNotFoundException{

        if(downtimerepo.findByPName(provider)!=null)
        {
            Downtime updateDowntime = downtimerepo.findByPName(provider);

            //CR: Please use braces always
            if(downtimeDetails.getProvider()!=null)
                updateDowntime.setProvider(downtimeDetails.getProvider());
            if(downtimeDetails.getFlow()!=null)
                updateDowntime.setFlow(downtimeDetails.getFlow());
            if(downtimeDetails.getDown_from()!=null)
                updateDowntime.setDown_from(downtimeDetails.getDown_from());
            if(downtimeDetails.getDown_to()!=null)
                updateDowntime.setDown_to(downtimeDetails.getDown_to());

            downtimerepo.save(updateDowntime);

            return ResponseEntity.ok(updateDowntime);
        }
        //CR: what would be visible in error scenarios to the API client? Please think on that part and remodel this response
        ResourceNotFoundException er = new ResourceNotFoundException("Provider does not exist with name: " + provider);
        throw er;

    }

    // build delete employee REST API
    @DeleteMapping("/delete-downtime/{id}")
    public Map<String, Boolean> deleteDowntime(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{

        Downtime downtime = downtimerepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not exist with id: " + id));

        downtimerepo.delete(downtime);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @DeleteMapping("/delete-downtime/{provider}")
    public Map<String, Boolean> deleteDowntimeByName(@PathVariable(value = "provider") String provider) throws ResourceNotFoundException {

        if(downtimerepo.findByPName(provider)!=null)
        {
            Downtime downtime = downtimerepo.findByPName(provider);
                    //.orElseThrow(() -> new ResourceNotFoundException("Provider not exist with name: " + provider));

            downtimerepo.delete(downtime);

            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);

            return response;
        }
        //CR: what would be visible in error scenarios to the API client? Please think on that part and remodel this response
        ResourceNotFoundException er = new ResourceNotFoundException("Provider does not exist with name: " + provider);
        throw er;
    }

}
