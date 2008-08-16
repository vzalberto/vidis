package modules.bullyElectionAlgorithm;

import data.AUserPacket;

public class ElectionWinPacket extends AUserPacket {

    private double id;

    public ElectionWinPacket(double id) {
	this.id = id;
    }

    public double getId() {
	return id;
    }
}