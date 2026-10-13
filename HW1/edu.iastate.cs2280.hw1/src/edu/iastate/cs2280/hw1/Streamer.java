package edu.iastate.cs2280.hw1;


/**
 * @author Simon Samuel
 * A Streamer customer, they use lots of bandwidth, so the ISP earns no profit
 * from this cell.
 **/
public class Streamer extends TownCell{

    public Streamer(Town p, int r, int c){
        super(p, r, c);
    }
    @Override
    public State who(){
        return State.STREAMER;
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
        if (nCensus[OUTAGE] > 0) {
            return new Empty(tNew, row, col);
        }

        return new Streamer(tNew, row, col);
    }
}
