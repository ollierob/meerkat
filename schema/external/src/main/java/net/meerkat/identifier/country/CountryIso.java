package net.meerkat.identifier.country;

import net.meerkat.identifier.Iso;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * @author Ollie
 */
public record CountryIso(String country, String subdivision) implements Iso, CountryId {

    private static final Pattern PATTERN = Pattern.compile("[A-Z]{2}");

    private static final Map<String, CountryIso> cache = new ConcurrentHashMap<>(32);
    public static final CountryIso AU = valueOf("AU");
    public static final CountryIso EU = valueOf("EU");
    public static final CountryIso JP = valueOf("JP");
    public static final CountryIso GB = valueOf("GB");
    public static final CountryIso US = valueOf("US");

    @Nonnull
    public static CountryIso valueOf(final String iso) {
        return cache.computeIfAbsent(iso.toUpperCase(), CountryIso::new);
    }

    public CountryIso(final String country) {
        this(country, null);
    }

    public CountryIso {
        if (!PATTERN.matcher(country).matches()) {
            throw new IllegalArgumentException("Invalid country ISO: " + country);
        }
    }

    @Override
    public CountryIso countryId() {
        return this;
    }

    @Override
    public String value() {
        return country + (subdivision != null ? "-" + subdivision : "");
    }

    @Override
    public String standard() {
        return "3166-1";
    }

    public boolean isUserAssigned() {
        switch (this.value()) {
            case "AA":
            case "QM":
            case "QN":
            case "QO":
            case "QP":
            case "QQ":
            case "QR":
            case "QS":
            case "QT":
            case "QU":
            case "QV":
            case "QW":
            case "QX":
            case "QY":
            case "QZ":
            case "ZZ":
                return true;
            default:
                return this.first() == 'X';
        }
    }

}
