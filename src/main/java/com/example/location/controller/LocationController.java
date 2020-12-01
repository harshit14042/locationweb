package com.example.location.controller;

import com.example.location.entities.Location;
import com.example.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    LocationService service;

    @RequestMapping("/showCreate")
    public String showCreate(){
        return "createLocation";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap){
        Location locationSaved = service.saveLocation(location);
        String msg="Location saved with id: "+locationSaved.getId();
        modelMap.addAttribute("msg",msg);
        return "createLocation";
    }

    @RequestMapping("/displayLocations")
    public String displayLocations(ModelMap modelMap){
        List<Location> locations=service.getAllLocation();
        modelMap.addAttribute("locations",locations);
        return "displayLocations";
    }

    @RequestMapping("/deleteLocation")
    public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap){
        service.deleteLocation(service.getLocationById(id));
        List<Location> locations=service.getAllLocation();
        modelMap.addAttribute("locations", locations);
        return "displayLocations";
    }

    @RequestMapping("/editLocation")
    public String editLocation(@RequestParam("id") int id, ModelMap modelMap){
        modelMap.addAttribute("location", service.getLocationById(id));
        return "editLocation";
    }

    @RequestMapping("/updateLoc")
    public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap){
        service.updateLocation(location);
        modelMap.addAttribute("locations",service.getAllLocation());
        return "displayLocations";
    }
}
