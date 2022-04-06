package com.liferay.basic.training.able.internal.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class AbleBundleActivator implements BundleActivator {

	public AbleBundleActivator() {
		System.out.println("Constructing AbleBundleActivator");
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting AbleBundleActivator");
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stopping AbleBundleActivator");
	}

}