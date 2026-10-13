package edu.iastate.cs2280.hw1;


/**
 * @author Simon Samuel
 * An Outage cell, the internet is down here this cycle, so the ISP earns no
 * profit. Service is always restored on the next cycle.
 **/
public class Outage extends TownCell{


    public Outage(Town p, int r, int c){
        super(p, r, c);
    }

    @Override
    public State who(){
        return State.OUTAGE;
    }

    @Override
    public TownCell next(Town tNew){
        return new Empty(tNew, row, col);
    }
}
