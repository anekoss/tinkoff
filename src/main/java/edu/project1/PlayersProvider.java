package edu.project1;

import java.util.List;

public class PlayersProvider {
    private final List<Player> players;
    private int position = -1;

    public PlayersProvider(List<Player> players) {
        this.players = players;
    }

    public void getNext(Status status) {
        if (status != Status.REPEAT_GUESS && status != Status.RETRY_GUESS) {
            position = ++position % players.size();
        }
    }

    int getSize() {
        return players.size();
    }

    public Player getCurrent() {
        return players.get(position);
    }

}
