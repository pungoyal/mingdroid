package com.thoughtworks.mingle.converters;

import com.thoughtworks.mingle.domain.Author;
import com.thoughtworks.mingle.domain.Murmur;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MurmurConverter implements Converter {

	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Murmur murmur = new Murmur();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if ("id".equals(reader.getNodeName())) {
				murmur.setId(reader.getValue());
			} else if ("author".equals(reader.getNodeName())) {
				Author author = (Author) context.convertAnother(murmur, Author.class);
				murmur.setAuthor(author);
			} else if ("body".equals(reader.getNodeName())) {
				murmur.setBody(reader.getValue());
			} else if ("created_at".equals(reader.getNodeName())) {
				murmur.setCreatedAtFrom(reader.getValue());
			}
			reader.moveUp();
		}
		return murmur;
	}

	public boolean canConvert(Class clazz) {
		return Murmur.class.equals(clazz);
	}

}
