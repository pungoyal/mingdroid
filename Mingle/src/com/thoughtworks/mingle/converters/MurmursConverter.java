package com.thoughtworks.mingle.converters;

import com.thoughtworks.mingle.domain.Murmur;
import com.thoughtworks.mingle.domain.Murmurs;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MurmursConverter implements Converter {

	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Murmurs murmurs = new Murmurs();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			Murmur project = (Murmur) context.convertAnother(murmurs, Murmur.class);
			murmurs.add(project);
			reader.moveUp();
		}

		return murmurs;
	}

	public boolean canConvert(Class clazz) {
		return Murmurs.class.equals(clazz);
	}

}
