package hr.goran.sheepshop.web.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import hr.goran.sheepshop.model.Order;

/**
 * @author Goran
 */
public class OrderFieldSerializer extends SimpleBeanPropertyFilter{
	private Order order;
	
	public OrderFieldSerializer(Order order){
		this.order = order;
	}

	@Override
	protected boolean include(BeanPropertyWriter arg0) {
		return true;
	}

	@Override
	protected boolean include(PropertyWriter arg0) {
		return true;
	}

	@Override
	public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer)
			throws Exception {
		Order orderToSerialize = (Order) pojo;
		if("milk".equals(writer.getName())){
			if(this.order.getMilk() > orderToSerialize.getMilk()){
				writer.serializeAsOmittedField(pojo, jgen, provider);
			}
			else{
				super.serializeAsField(pojo, jgen, provider, writer);
			}
		}
		else{
			if(this.order.getSkins() > orderToSerialize.getSkins()){
				writer.serializeAsOmittedField(pojo, jgen, provider);
			}
			else{
				super.serializeAsField(pojo, jgen, provider, writer);
			}
		}
	}

}
