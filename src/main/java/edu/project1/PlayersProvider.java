package edu.project1;

import java.util.List;

public class PlayersProvider {
    private final List<Player> players;
    private int position = -1;
    private boolean isGiveUp = false;
    private boolean isWinner = false;

    public PlayersProvider(List<Player> players) {
        this.players = players;

    }

    public void setIsWinner() {
        isWinner = true;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void getNext() {
        position = ++position % players.size();
    }

    int getSize() {
        return players.size();
    }

    public Player getCurrent() {
        return players.get(position);
    }

    public void setPosition() {
        position--;
    }

    public void giveUp() {
        isGiveUp = true;
    }

    public boolean isGiveUp() {
        return isGiveUp;
    }
}
