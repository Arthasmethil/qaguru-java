package guru.qa.lesson18.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "file:tml/auth.properties",
        "classpath:auth.properties"

})
public interface AuthConfig extends Config {

    @Key("username")
    String username();

    @Key("password")
    String password();
}
