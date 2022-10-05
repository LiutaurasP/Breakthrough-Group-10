package Players;

import GameLogic.Team;

import java.util.ArrayList;

abstract public class Player {
    Team team;
public Player(Team team){
    this.team = team;
}
public abstract Move getMove();

public Team getTeam(){
  return this.team;
}}