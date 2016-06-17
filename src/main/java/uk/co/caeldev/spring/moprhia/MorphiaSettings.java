package uk.co.caeldev.spring.moprhia;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="morphia")
public class MorphiaSettings {
    private String entityPackage;

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }
}
