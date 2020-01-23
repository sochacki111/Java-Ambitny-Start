package io.github.mat3e.hello;

import io.github.mat3e.hello.HelloService;
import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";
    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName(){
        // given
        var mockRepository = alwaysReturningHelloRepository();
        // Service Under Test (SUT)
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, "-1");

        // then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithName(){
        // given
        var mockRepository = alwaysReturningHelloRepository();
        // Service Under Test (SUT)
        var SUT = new HelloService(mockRepository);
        var name = "Michas";

        // when
        var result = SUT.prepareGreeting(name, "-1");

        // then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang(){
        // given
        var mockRepository = fallbackLangIdRepository();
        // Service Under Test (SUT)
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, null);

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang(){
        // given
        var lang = "abc";
        var mockRepository = fallbackLangIdRepository();
        // Service Under Test (SUT)
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, lang);

        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        // given
        var name = "Michal";
        var mockRepository = new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        // Service Under Test (SUT)
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(name, "-1");

        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + name + "!", result);

    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                if(id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of((new Lang(null, WELCOME, null)));
            }
        };
    }
}
