package Players;

import GameLogic.Team;

abstract public class Player {
    Team team;
public Player(Team team){
    this.team = team;
}
}
