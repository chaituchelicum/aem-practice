package com.marvel.core.services;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface FirstService {
    public String getRandomName();

    public String getIsHeroHungry();
}
