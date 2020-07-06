package com.hmg.demo.app.booklibrary;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.hmg.demo.app.booklibrary.resources.LibraryResource;

import io.bootique.Bootique;
import io.bootique.cayenne.CayenneModule;
import io.bootique.jersey.JerseyModule;


public class Application implements Module {
	  public static void main(String[] args) {
		  
	        Bootique
	            .app(args)
	            .autoLoadModules()
	            .module(Application.class)
	            .exec()
	            .exit();
	    }

	@Override
	public void configure(Binder binder) {
		
		JerseyModule.extend(binder)
        	.addResource(LibraryResource.class);
		
		 CayenneModule.extend(binder)
		    .addProject("cayenne-project.xml");
	}
}
