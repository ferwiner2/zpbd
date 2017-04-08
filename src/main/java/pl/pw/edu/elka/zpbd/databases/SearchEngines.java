package pl.pw.edu.elka.zpbd.databases;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Jakub Lorenc on 08.04.17.
 */
@Component
public class SearchEngines {
    private final ListableBeanFactory beanFactory;
    private Map<String, SearchEngineInterface> engineMap;

    @Autowired
    public SearchEngines(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @PostConstruct
    private void fillEngineMap(){
        engineMap = beanFactory.getBeansOfType(SearchEngineInterface.class)
                .values()
                .stream()
                .collect(Collectors.toMap(
                    SearchEngineInterface::getKey,
                    Function.identity()
        ));
    }

    public SearchEngineInterface getEngine(String engineName){
        return engineMap.get(engineName);
    }

    public List<SearchEngineInterface> getAllEngines() {
        return engineMap.values()
                .stream()
                .sorted(Comparator.comparing(SearchEngineInterface::getName))
                .collect(Collectors.toList());
    }
}
