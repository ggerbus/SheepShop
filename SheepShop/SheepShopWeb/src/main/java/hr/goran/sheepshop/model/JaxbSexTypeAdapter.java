package hr.goran.sheepshop.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Goran
 */
public class JaxbSexTypeAdapter extends XmlAdapter<String, SexType>{

	@Override
	public String marshal(SexType sexType) throws Exception {
		return sexType.getSex();
	}

	@Override
	public SexType unmarshal(String sex) throws Exception {
		return SexType.getSexType(sex);
	}

}
