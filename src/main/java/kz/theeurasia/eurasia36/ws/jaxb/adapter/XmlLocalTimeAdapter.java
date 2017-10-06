package kz.theeurasia.eurasia36.ws.jaxb.adapter;

import java.time.LocalTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlLocalTimeAdapter extends XmlAdapter<String, LocalTime> {

    @Override
    public LocalTime unmarshal(String v) throws Exception {
	return LocalTime.parse(v);
    }

    @Override
    public String marshal(LocalTime v) throws Exception {
	return v.toString();
    }
}
