package net.meerkat.identifier.country;

/**
 *
 * @author Ollie
 */
public class CountrySubdivisionIso extends CountryIso {

    private final String subdivision;

    public CountrySubdivisionIso(final CountryIso country, final String subdivision) {
        super(country.value());
        this.subdivision = subdivision;
    }

    @Override
    public String value() {
        return super.value() + '-' + subdivision;
    }

    @Override
    public String standard() {
        return "3166-2";
    }

}
