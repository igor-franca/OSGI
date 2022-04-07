package com.liferay.basic.training.baker.web.internal.portlet;

import com.liferay.basic.training.able.number.generator.AbleNumberGenerator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.display-name=Basic Training Baker"
	},
	service = Portlet.class
)
public class BakerPortlet extends GenericPortlet {

	public BakerPortlet() {
		System.out.println("Constructing BakerPortlet");
	}

	@Override
	protected void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException {

		System.out.println(
			"Invoking BakerPortlet#doView " + _ableNumberGenerator.generate());

		PrintWriter printWriter = renderResponse.getWriter();

		printWriter.println("Hello, Basic Training Baker!");
	}

	@Reference
	private AbleNumberGenerator _ableNumberGenerator;

}