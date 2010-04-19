package com.thoughtworks.mingle.converters;

import com.thoughtworks.mingle.domain.CardType;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CardTypeConverter implements Converter {

	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		CardType cardType = new CardType();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if ("name".equals(reader.getNodeName())) {
				cardType.setName(reader.getValue());
			}
			reader.moveUp();
		}
		return cardType;
	}

	public boolean canConvert(Class clazz) {
		return CardType.class.equals(clazz);
	}

}
