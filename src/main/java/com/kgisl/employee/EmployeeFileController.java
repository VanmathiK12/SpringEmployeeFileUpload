package com.kgisl.employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EmployeeFileController {

    @PostMapping("/upload") 
    public String uploadFile(@RequestParam("empid") String empid,@RequestParam("docname[]") String docname,@RequestParam("document[]") MultipartFile[] files) 
    { 
        try 
        { 
            for (MultipartFile file : files) { 
                // Get the file name and save it to the server path 
                String fileName = empid+"_"+docname+"_"+file.getOriginalFilename(); 
                Path path = Paths.get("C:/uploads/" + fileName); 
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING); 
            }  return "redirect:/success";
        } catch (IOException e) { 
            e.printStackTrace(); 
            return "redirect:/error"; 
        } 
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
