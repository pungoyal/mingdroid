package com.thoughtworks.mingle.converters;

import com.thoughtworks.mingle.domain.Project;
import com.thoughtworks.mingle.domain.Projects;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ProjectsConverter implements Converter {

	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Projects projects = new Projects();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			Project project = (Project) context.convertAnother(projects, Project.class);
			projects.add(project);
			reader.moveUp();
		}

		return projects;
	}

	public boolean canConvert(Class clazz) {
		return Projects.class.equals(clazz);
	}

}
