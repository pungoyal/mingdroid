package com.thoughtworks.mingle.converters;

import com.thoughtworks.mingle.domain.CardProperties;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CardPropertiesConverter implements Converter {

	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		CardProperties properties = new CardProperties();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if ("property".equals(reader.getNodeName())) {
				reader.moveDown();
				if ("name".equals(reader.getNodeName()) && "Assignee".equals(reader.getValue())) {
					reader.moveUp();
					reader.moveDown();
					// String attribute = reader.getAttribute("nil");
					// if ("true".equals(attribute)) {
					// properties.setAssignee("NOT SET");
					// break;
					// }
					reader.moveDown();
					properties.setAssignee(reader.getValue());
					reader.moveUp();
				} else if ("name".equals(reader.getNodeName()) && "Status".equals(reader.getValue())) {
					reader.moveUp();
					// String attribute = reader.getAttribute("nil");
					// if ("true".equals(attribute)) {
					// properties.setStatus("NOT SET");
					// reader.moveDown();
					// break;
					// }

					reader.moveDown();
					properties.setStatus(reader.getValue());
				}
				reader.moveUp();
			}
			reader.moveUp();
		}

		return properties;
	}

	public boolean canConvert(Class clazz) {
		return CardProperties.class.equals(clazz);
	}
}
