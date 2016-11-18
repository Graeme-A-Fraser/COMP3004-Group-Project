package acmeindustries.boondoggletd.controller;

import acmeindustries.boondoggletd.model.Battleground;

public class Spawner {

    private Battleground bg;

    // time between creeps
    private int spawnTimer;
    private int maxSpawnTimer;

    // creeps to spawn
    private int creepsRemaining;

    public Spawner(Battleground bg){
        this.bg = bg;
        this.maxSpawnTimer = bg.TPS*2;
        this.spawnTimer = maxSpawnTimer;
    }

    public void startRound(){
        this.creepsRemaining= 10;
        bg.spawning = true;
    }

    public void update(){

        if(bg.spawning){
            this.spawnTimer--;
        }
        if(this.spawnTimer <= 0 ){
            // spawn creeps with 10hp - will need to customize this
            bg.addEnemyCreep(100);
            bg.addPlayerCreep(100);
            creepsRemaining--;
            this.spawnTimer = this.maxSpawnTimer;
        }
        if(this.creepsRemaining<=0){
            bg.spawning = false;
            this.spawnTimer = maxSpawnTimer;
        }
    }

}
