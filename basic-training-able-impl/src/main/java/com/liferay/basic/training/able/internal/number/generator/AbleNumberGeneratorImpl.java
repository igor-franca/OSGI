package com.liferay.basic.training.able.internal.number.generator;

import com.liferay.basic.training.able.number.generator.AbleNumberGenerator;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, service = {AbleNumberGenerator.class}
)
public class AbleNumberGeneratorImpl implements AbleNumberGenerator {

	public long generate() {
		return 30624700;
	}

}