import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Параметризованные тесты")
public class GitRepositoriesTest {
    @ValueSource(strings = {
            "QAguru-lesson-6-homework",
            "QAguru-lesson-7-homework-",
            "QAguru-lesson-8-homework-"
    }
    )
    @DisplayName("Проверка репозиториев с помощью аннотации ValueSource")
    @ParameterizedTest(name = "Проверяем репозиторий {0}")
    void checkGitRepositories(String repository) {
        open("https://github.com");
        $("[data-test-selector=nav-search-input]").setValue(repository).pressEnter();
        $(byText(repository)).should(exist);
    }

    static Stream<Arguments> checkGitUserRepositoriesCountWithMethodSource() {
        return Stream.of(
                Arguments.of("idelkafatk", "14"),
                Arguments.of("ZholdassovaA", "11")
        );
    }

    @MethodSource
    @DisplayName("Проверка репозиториев c помощью аннотации MethodSource")
    @ParameterizedTest(name = "Проверяем количество репозиториев пользователя {0}")
    void checkGitUserRepositoriesCountWithMethodSource(String userName, String count) {
        open("https://github.com");
        $("[data-test-selector=nav-search-input]").setValue(userName).pressEnter();
        $(byText("Users")).click();
        sleep(2000);
        $(byText(userName)).click();
        $(".Counter").shouldHave(text(count));
    }

    @CsvSource(
            value = {
            "idelkafatk/ 14",
            "ZholdassovaA/ 11"
    },
    delimiter = '/'
    )
    @DisplayName("Проверка репозиториев c помощью аннотации CsvSource")
    @ParameterizedTest(name = "Проверяем количество репозиториев пользователя {0}")
    void checkGitUserRepositoriesCountWithCsvSource(String userName, String count) {
        open("https://github.com");
        $("[data-test-selector=nav-search-input]").setValue(userName).pressEnter();
        $(byText("Users")).click();
        sleep(2000);
        $(byText(userName)).click();
        $(".Counter").shouldHave(text(count));
    }
}
