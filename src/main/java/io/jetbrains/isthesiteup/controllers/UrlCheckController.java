package io.jetbrains.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String siteup = "Site is up!";
    private final String sitedown = "Site is down!";
    private final String incorrecturl = "URL is incorrect!";
    
    @GetMapping("/check")
public String getUrlStatusMeesage(@RequestParam String url){
    String returnMessage = "";


    try{
        URL urlobj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int respcode = conn.getResponseCode() / 100;
        if(respcode != 2 && respcode !=3){
            returnMessage = sitedown;
        }else{
            returnMessage = siteup;
        }

    }catch(MalformedInputException e){
        returnMessage=incorrecturl;
    }catch (IOException e){
        returnMessage=sitedown;
    }

    return returnMessage;

    }


}


