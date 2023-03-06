package com.marvel.core.services;

import com.marvel.core.services.impl.FirstServiceImpl;
import org.osgi.annotation.versioning.ProviderType;

import java.util.Map;

@ProviderType
public interface FirstService {
    public String getRandomName();

    public String getIsHeroHungry();
}
