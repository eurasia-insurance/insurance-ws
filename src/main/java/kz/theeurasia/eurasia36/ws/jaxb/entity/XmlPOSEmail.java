package kz.theeurasia.eurasia36.ws.jaxb.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class XmlPOSEmail implements Serializable {
    private static final long serialVersionUID = 7897177732036917354L;

    protected String address;

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

}
