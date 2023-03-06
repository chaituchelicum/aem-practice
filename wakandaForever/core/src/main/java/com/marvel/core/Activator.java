package com.marvel.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        logger.info("^^^^^^^^^^^BUNDLE STARTED^^^^^^^^^^^^^^");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        logger.info("^^^^^^^^^^^BUNDLE STOPPED^^^^^^^^^^^^^^");
    }
}
