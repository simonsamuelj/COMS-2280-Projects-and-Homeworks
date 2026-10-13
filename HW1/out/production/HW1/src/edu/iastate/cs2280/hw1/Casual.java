package edu.iastate.cs2280.hw1;


/**
 * @author Simon Samuel
 * A Casual customer, who uses a little bandwidth, so the ISP earns $1 profit
 * from this cell each billing cycle.
 */
public class Casual extends TownCell{


    public Casual(Town p, int r, int c){
        super(p, r, c);
    }

    @Override
    public State who(){
        return State.CASUAL;
    }

    @Override
    public TownCell next(Town tNew){
        census(nCensus);

        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, row, col);
        }
        if (nCensus[RESELLER] > 0) {
            return new Outage(tNew, row, col);
        }
        if (nCensus[STREAMER] > 0) {
            return new Streamer(tNew, row, col);
        }
        if (nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, row, col);
        }
        return new Casual(tNew, row, col);
    }
}
