package io.github.mat3e.hello;

import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

// Class responsible for business logic
class HelloService {

    public static final String FALLBACK_NAME = "world";
    public static final Lang FALLBACK_LANG = new Lang(1,"Hello", "en");
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

    String prepareGreeting(String name, String lang) {
        Integer langId;
        try {
            langId = Optional.ofNullable(lang).map(Integer::valueOf).orElse(FALLBACK_LANG.getId());
        } catch (NumberFormatException e) {
            logger.info("Non-numeric language id used: " + lang);
            langId = FALLBACK_LANG.getId();
        }
        var welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMsg + " " + nameToWelcome + "!";
    }
}
