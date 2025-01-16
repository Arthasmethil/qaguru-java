package guru.qa.lesson18.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:tml/auth.properties",
        "classpath:auth.properties",
        "system:properties"
})
public interface AuthConfig extends Config {

    @Key("username")
    String username();

    @Key("password")
    String password();
}
