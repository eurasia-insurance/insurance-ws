package kz.theeurasia.eurasia36.ws.jaxb.entity;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.lapsa.commons.function.MyNumbers;
import com.lapsa.commons.function.MyStrings;
import com.lapsa.validation.NotEmptyString;
import com.lapsa.validation.NotNullValue;

@XmlRootElement(name = "ebillPurposeItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlEbillPurposeItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    @NotNullValue
    @NotEmptyString
    protected String title;

    @XmlAttribute
    @NotNullValue
    @Min(1)
    private Double price;

    @XmlAttribute
    @NotNullValue
    @Min(1)
    protected Integer quantity;

    public XmlEbillPurposeItem() {
    }

    public XmlEbillPurposeItem(String title, Double price, Integer quantity) {
	setTitle(title);
	setPrice(price);
	setQuantity(quantity);
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, Constants.DEFAULT_TO_STRING_STYLE);
    }

    @XmlAttribute
    public Double getAmount() {
	return price * quantity;
    }

    //

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = MyStrings.requireNonEmpty(title, "title");
    }

    public Integer getQuantity() {
	return quantity;
    }

    public void setQuantity(Integer quantity) {
	this.quantity = MyNumbers.requireNonZero(quantity, "quantity");
    }

    public Double getPrice() {
	return price;
    }

    public void setPrice(Double price) {
	this.price = MyNumbers.requireNonZero(price, "price");
    }

}
