package com.blackparade.coronamap.localizecoronaonmap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MapController {

//Tested connection with ThymyLeaf


    /**  RequestMapping(method = RequestMethod.GET)
    public String getMap(Model model, @RequestParam String x, @RequestParam String y){
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        return "map";
  }**/

  private Covid19Confirmed covid19Confirmed;

    public MapController(Covid19Confirmed covid19Confirmed) {
        this.covid19Confirmed = covid19Confirmed;
    }

  @GetMapping
    public String getMap(Model model) throws IOException {
      model.addAttribute("points", covid19Confirmed.getCovidData());
      return "map";
  }

}
