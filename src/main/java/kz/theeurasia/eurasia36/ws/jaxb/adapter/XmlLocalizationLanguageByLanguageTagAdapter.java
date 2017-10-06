package kz.theeurasia.eurasia36.ws.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.lapsa.international.localization.LocalizationLanguage;

public class XmlLocalizationLanguageByLanguageTagAdapter extends XmlAdapter<String, LocalizationLanguage> {

    @Override
    public LocalizationLanguage unmarshal(String v) throws Exception {
	if (v == null || v.trim().isEmpty())
	    return null;
	return LocalizationLanguage.byTag(v);
    }

    @Override
    public String marshal(LocalizationLanguage v) throws Exception {
	if (v == null)
	    return null;
	return v.getTag();
    }
}
