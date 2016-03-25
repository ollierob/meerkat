package net.ollie.meerkat.identifier.country;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.StringWrapper;
import net.ollie.meerkat.identifier.Iso;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class CountryIso extends StringWrapper implements Iso, CountryId, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Map<String, CountryIso> cache = new ConcurrentHashMap<>(32);
    private static final Pattern PATTERN = Pattern.compile("[a-zA-Z]{2}");

    public static CountryIso US = valueOf("US");
    public static CountryIso GB = valueOf("GB");

    public static CountryIso valueOf(final String iso) {
        if (!PATTERN.matcher(iso).matches()) {
            throw new IllegalArgumentException("Invalid ISO: " + iso);
        }
        return cache.computeIfAbsent(iso.toUpperCase(), CountryIso::new);
    }

    @Deprecated
    CountryIso() {
    }

    CountryIso(final String iso) {
        super(iso);
    }

    @Override
    public CountryIso countryId() {
        return this;
    }

    @Override
    public char first() {
        return super.first();
    }

    @Override
    public String standard() {
        return "3166-1";
    }

}
