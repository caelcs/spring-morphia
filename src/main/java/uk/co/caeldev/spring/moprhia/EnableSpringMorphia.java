package uk.co.caeldev.spring.moprhia;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({ MongoConfiguration.class, MorphiaConfiguration.class, MongoClientConfiguration.class})
public @interface EnableSpringMorphia {
}
