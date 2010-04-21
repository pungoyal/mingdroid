package com.thoughtworks.mingle.converters;

import com.thoughtworks.mingle.domain.CardType;
import com.thoughtworks.mingle.domain.CardTypes;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CardTypesConverter implements Converter {
	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		CardTypes cardTypes = new CardTypes();

		while (reader.hasMoreChildren()) {
			reader.moveDown();

			CardType cardType = new CardType();
			while (reader.hasMoreChildren()) {

				reader.moveDown();
				if ("id".equals(reader.getNodeName())) {
					cardType.setId(Integer.parseInt(reader.getValue()));
				} else if ("number".equals(reader.getNodeName())) {
					cardType.setNumber(Integer.parseInt(reader.getValue()));
				} else if ("name".equals(reader.getNodeName())) {
					cardType.setName(reader.getValue());
				}
				reader.moveUp();

			}
			cardTypes.add(cardType);
			reader.moveUp();
		}

		return cardTypes;
	}

	public boolean canConvert(Class arg0) {
		return CardTypes.class.equals(arg0);
	}

}
