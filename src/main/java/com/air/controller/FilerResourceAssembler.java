/**
 * 
 */
package com.air.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.air.entity.Filer;

/**
 * @author BhabaniShankar
 *
 */
@Component
public class FilerResourceAssembler extends ResourceAssemblerSupport<Filer, ResourceSupport> {

	public FilerResourceAssembler() {
		super(FilerController.class, ResourceSupport.class);
	}

	@Override
	public Resource<Filer> toResource(Filer filer) {
		List<Link> links=new ArrayList<>();
		Link link = linkTo(methodOn(FilerController.class).saveFiler(new Filer(), null)).withRel("save");
		links.add(link);
		link = linkTo(methodOn(FilerController.class).showFilerDetails(filer.getId())).withRel("edit");
		links.add(link);

		return new Resource<>(filer, links);
	}

	public List<Resource<Filer>> toResource(List<Filer> filerList) {
		List<Resource<Filer>> list = new ArrayList<>();
		for (Filer filer : filerList) {
			list.add(toResource(filer));
		}
		return list;

	}

}
