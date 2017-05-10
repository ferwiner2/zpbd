package pl.pw.edu.elka.zpbd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pw.edu.elka.zpbd.databases.SearchEngineInterface;
import pl.pw.edu.elka.zpbd.databases.SearchEngines;

/**
 * Created by Jakub Lorenc on 08.04.17.
 */
//TODO dodać wyświetlanie stron
@Controller
public class SearchController {
    private final SearchEngines searchEngines;

    @Autowired
    public SearchController(final SearchEngines searchEngines) {
        this.searchEngines = searchEngines;
    }

    @RequestMapping("/search")
    public String greeting(
            @RequestParam(value="query", required = false, defaultValue = "") String queryParam,
            @RequestParam(value="engine", required = false, defaultValue = "") String engineParam,
            Model model) {
        final SearchEngineInterface engine = searchEngines.getEngine(engineParam);
        if (engine != null) {
            model.addAttribute("results", engine.search(queryParam));
        }

        model.addAttribute("searchEngines", searchEngines.getAllEngines());
        model.addAttribute("query", queryParam);
        model.addAttribute("engine", engineParam);
        return "search";
    }
}
