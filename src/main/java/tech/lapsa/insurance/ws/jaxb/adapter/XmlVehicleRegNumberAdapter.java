package tech.lapsa.insurance.ws.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import tech.lapsa.java.commons.function.MyObjects;
import tech.lapsa.java.commons.function.MyStrings;
import tech.lapsa.kz.vehicle.VehicleRegNumber;

public class XmlVehicleRegNumberAdapter extends XmlAdapter<String, VehicleRegNumber> {

    @Override
    public VehicleRegNumber unmarshal(String v) throws Exception {
	if (MyStrings.empty(v))
	    return null;
	return VehicleRegNumber.of(v);
    }

    @Override
    public String marshal(VehicleRegNumber v) throws Exception {
	return MyObjects.requireNonNull(v, "vehicleRegNumber").getNumber();
    }

}
