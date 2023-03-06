package com.marvel.core.services.impl;

import com.marvel.core.services.FirstService;
import org.apache.commons.lang.StringUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

@Component(
        immediate = true,
        service = {FirstService.class}
)
@Designate(ocd = FirstServiceImpl.Config.class)
public class FirstServiceImpl implements FirstService {

    @ObjectClassDefinition(
            name = "Wakanda Forever - CONF",
            description = "OSGi Service providing information about Black Panther Adventures & activities"
    )
     @interface Config {

        @AttributeDefinition(name = "Fruits Array", description = "Property for Fruits")
        String[] fruits() default {"apple","banana","mango"};

        @AttributeDefinition(name = "Hungry", description = "Property for Hungry")
        boolean hungry() default false;

        @AttributeDefinition(name = "Hero Name", description = "Property for Hero")
        String heroName() default "Black Panther";
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // define few firstNames and lastNames
    private static final String[] firstNames = {"John", "Emma", "Olivia", "Ava", "Isabella", "Sophia", "Robin", "James"};
    private static final String[] lastNames = {"Doe", "Smith", "Johnson", "Williams", "Jones", "Brown", "Hood"};


    private static final Random random = new Random();

    private String isHeroHungry;

    @Activate
    public void activate(Config config){
        logger.info("ACTIVATED FIRST SERVICE");
        logger.info(StringUtils.isNotBlank(config.heroName()) ? "CONFIG IS FINE" : "OH OH SOMETHING WRONG");
        this.isHeroHungry =  config.heroName() + " is" + (config.hungry() ? " hungry and eats "+ Arrays.toString(config.fruits()) : " not hungry");
    }
    @Deactivate
    public void deactivate(){
        logger.info("DEACTIVATED FIRST SERVICE");
    }

    @Override
    public String getRandomName() {
        return firstNames[random.nextInt(firstNames.length)] + " " + lastNames[random.nextInt(lastNames.length)];
    }

    @Override
    public String getIsHeroHungry() {
        return isHeroHungry;
    }


}
