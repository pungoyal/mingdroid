package com.thoughtworks.mingle.converters;

import com.thoughtworks.mingle.domain.Project;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ProjectConverter implements Converter {

	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Project project = new Project();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if ("name".equals(reader.getNodeName())) {
				project.setName(reader.getValue());
			} else if ("identifier".equals(reader.getNodeName())) {
				project.setIdentifier(reader.getValue());
			}
			reader.moveUp();
		}
		return project;
	}

	public boolean canConvert(Class clazz) {
		return Project.class.equals(clazz);
	}

}
