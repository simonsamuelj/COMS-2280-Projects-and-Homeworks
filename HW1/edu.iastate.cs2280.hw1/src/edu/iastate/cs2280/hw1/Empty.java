package edu.iastate.cs2280.hw1;


/**
 * @author Simon Samuel
 * An Empty cell, nobody lives here yet, so the ISP earns no profit.
 */
public class Empty extends TownCell{

    public Empty(Town p, int r, int c){
        super(p, r, c);
    }

    @Override
    public State who(){
        return State.EMPTY;
    }

    @Override
    public TownCell next(Town tNew){
        census(nCensus);

        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, row, col);
        }
        return new Casual(tNew, row, col);
    }
}
