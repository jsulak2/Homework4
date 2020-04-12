package com.homework4.homework4.controllers;

import com.homework4.homework4.Models.jokes;
import com.homework4.homework4.Models.jokesRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;


@Controller
public class MainController
{
    @Autowired
    jokesRepo jokesRepo;

    //I put these outside of the doHome method to help in saving to database
    String id = "";
    String thevalue  ="";
    String fullstring = "";
    jokes joke = new jokes();

    @RequestMapping(value = "/")
    public ModelAndView doHome(){
        ModelAndView mv = new ModelAndView("index");
        String id = "";
        String thevalue  ="";
        String apikey = "307f0f45cbmshfde14de2bb656c4p1326c4jsnbf7a76e08aa7";
        String fullstring = "";

        try
        {
            URL url = new URL("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com");
            connection.setRequestProperty("x-rapidapi-key", apikey);
            connection.connect();
            BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                json.append(line);
            }
            JSONObject obj = new JSONObject(json.toString());
            id = obj.getString("id");
            thevalue = obj.getString("value");

            fullstring = thevalue; //+ " \nThis joke has an ID of " + id; //Decided not to print the Joke ID.
            System.out.println("This is the full joke and id: " + fullstring); //printing Joke and its ID to the console.

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        joke.setId(id);
        joke.setThevalue(thevalue);

        mv.addObject("jokesList", jokesRepo.findAll());
        mv.addObject("id", id);
        mv.addObject("thevalue", thevalue);
        mv.addObject("fullstring", fullstring);
        return mv;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save()
    {
        jokesRepo.save(joke);
        ModelAndView mv = new ModelAndView("redirect:/");
        mv.addObject("jokesList", jokesRepo.findAll());
        return mv;
    }

    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("redirect:/");
        jokesRepo.deleteById(id);
        return mv;
    }


}
