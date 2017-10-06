package kz.theeurasia.eurasia36.ws.jaxb.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "ebillShort")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlEbillShort implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    protected String id;

    public XmlEbillShort() {
    }

    public XmlEbillShort(String id) {
	this.id = id;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

}
