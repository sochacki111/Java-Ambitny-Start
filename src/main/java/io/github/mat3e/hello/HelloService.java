package io.github.mat3e.hello;

import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import java.util.Optional;

// Class responsible for business logic
class HelloService {

    public static final String FALLBACK_NAME = "world";
    public static final Lang FALLBACK_LANG = new Lang(1,"Hello", "en", "🇺🇸");
    private final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private LangRepository repository;


    /**
     * Calling default Repository
     */
    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreeting(String name, Integer langId) {
        // if langId is null then return fallback Lang
        langId = Optional.ofNullable(langId).orElse(FALLBACK_LANG.getId());
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
