package uk.co.caeldev.spring.moprhia;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix="mongo")
public class MongoSettings {

    private List<MongoServer> servers;

    private String database;

    private String username;

    private String password;


    public List<MongoServer> getServers() {
        return servers;
    }

    public void setServers(List<MongoServer> servers) {
        this.servers = servers;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
