package com.thoughtworks.mingle.converters;
import com.thoughtworks.mingle.domain.Author;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class AuthorConverter implements Converter {

	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Author author = new Author();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if ("id".equals(reader.getNodeName())) {
				author.setId(reader.getValue());
			} else if ("name".equals(reader.getNodeName())) {
				author.setName(reader.getValue());
			}
			reader.moveUp();
		}
		return author;
	}

	public boolean canConvert(Class clazz) {
		return Author.class.equals(clazz);
	}
}
