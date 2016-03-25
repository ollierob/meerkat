package net.ollie.meerkat.utils.xml;

import java.util.function.Function;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Ollie
 */
public abstract class ExtendingXmlAdapter<X extends R, R> extends XmlAdapter<X, R> {

    public static <X extends R, R> XmlAdapter<X, R> of(final Function<? super R, ? extends X> convert) {
        return new ExtendingXmlAdapter<X, R>() {

            @Override
            protected X marshalNonNull(R regular) {
                return convert.apply(regular);
            }

        };
    }

    protected abstract X marshalNonNull(R regular);

    @Override
    public R unmarshal(final X xml) throws Exception {
        return xml;
    }

    @Override
    public X marshal(final R regular) throws Exception {
        return regular == null ? null : this.marshalNonNull(regular);
    }

}
