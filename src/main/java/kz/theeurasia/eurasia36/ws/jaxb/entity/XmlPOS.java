package kz.theeurasia.eurasia36.ws.jaxb.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class XmlPOS implements Serializable {
    private static final long serialVersionUID = -3174271326978667664L;

    protected int id;

    protected String name;

    protected String address;

    protected boolean deliveryServiceEnable;

    protected boolean pickupServiceAvailable;

    protected XmlGeo geoPoint;

    protected XmlPOSPhone[] phones;

    protected XmlPOSEmail[] emails;

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public boolean isDeliveryServiceEnable() {
	return deliveryServiceEnable;
    }

    public void setDeliveryServiceEnable(boolean deliveryServiceEnable) {
	this.deliveryServiceEnable = deliveryServiceEnable;
    }

    public XmlGeo getGeoPoint() {
	return geoPoint;
    }

    public void setGeoPoint(XmlGeo geoPoint) {
	this.geoPoint = geoPoint;
    }

    public XmlPOSPhone[] getPhones() {
	return phones;
    }

    public void setPhones(XmlPOSPhone[] phones) {
	this.phones = phones;
    }

    public XmlPOSEmail[] getEmails() {
	return emails;
    }

    public void setEmails(XmlPOSEmail[] emails) {
	this.emails = emails;
    }

    public boolean isPickupServiceAvailable() {
	return pickupServiceAvailable;
    }

    public void setPickupServiceAvailable(boolean pickupServiceAvailable) {
	this.pickupServiceAvailable = pickupServiceAvailable;
    }

}
