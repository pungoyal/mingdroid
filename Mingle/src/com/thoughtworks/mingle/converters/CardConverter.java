package com.thoughtworks.mingle.converters;

import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.domain.CardProperties;
import com.thoughtworks.mingle.domain.CardType;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class CardConverter implements Converter {

	public void marshal(Object arg0, HierarchicalStreamWriter arg1, MarshallingContext arg2) {
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Card card = new Card();

		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if ("name".equals(reader.getNodeName())) {
				card.setName(reader.getValue());
			} else if ("card_type".equals(reader.getNodeName())) {
				CardType type = (CardType) context.convertAnother(card, CardType.class);
				card.setType(type);
			} else if ("properties".equals(reader.getNodeName())) {
				CardProperties properties = (CardProperties) context.convertAnother(card, CardProperties.class);
				card.setProperties(properties);
			} else if ("description".equals(reader.getNodeName())) {
				card.setDescription(reader.getValue());
			} else if ("number".equals(reader.getNodeName())) {
				int number = Integer.parseInt(reader.getValue());
				card.setNumber(number);
			}
			reader.moveUp();
		}
		return card;
	}

	public boolean canConvert(Class clazz) {
		return Card.class.equals(clazz);
	}

}
