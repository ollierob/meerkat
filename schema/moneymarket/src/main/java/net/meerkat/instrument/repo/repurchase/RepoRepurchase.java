package net.meerkat.instrument.repo.repurchase;

/**
 *
 * @author ollie
 */
public interface RepoRepurchase {

    <R> R handleWith(Handler<R> handler);

    interface Handler<R> {

        R handle(OpenRepoRepurchase openRepurchase);

        R handle(ClassicRepoRepurchase classicRepurchase);

        R handle(BuySellBackRepurchase<?> buySellBack);

    }

}
