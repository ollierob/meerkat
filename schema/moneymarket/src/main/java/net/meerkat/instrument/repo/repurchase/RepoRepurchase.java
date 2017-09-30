package net.meerkat.instrument.repo.repurchase;

import net.meerkat.Explainable;

/**
 *
 * @author ollie
 */
public interface RepoRepurchase extends Explainable {

    <R> R handleWith(Handler<R> handler);

    interface Handler<R> {

        R handle(OpenRepoRepurchase openRepurchase);

        R handle(ClassicRepoRepurchase classicRepurchase);

        R handle(BuySellBackRepurchase<?> buySellBack);

    }

}
