package Elio.model.arena;

import Elio.model.Arena;
import Elio.model.Position;
import Elio.model.enemy.Enemy;
import Elio.model.enemy.EnemyFactory;

import java.util.Random;

import static Elio.model.enemy.Enemy.EnemyType.*;

public class WaveManager {
    private int currentWave = 0;
    private final Arena arena;
    private static final Random random = new Random();
    private  static final int MAX_WAVES = 30;
    private boolean finished = false;

    //some time between waves
    private static final long WAVE_DELAY_MILLIS = 2000;
    private long waitTimer = 0;

    public WaveManager(Arena arena) {
        this.arena = arena;
    }



    //chamar no game loop
    public void update(long delta) {
        if (finished) return;

        //se ainda há inimigos, a wave continua
        if (!arena.getEnemies().isEmpty()) return;

        //esperar um bocado antes da próxima wave
        waitTimer += delta;
        if (waitTimer < WAVE_DELAY_MILLIS) return;

        //passar à próxima wave
        waitTimer = 0;
        currentWave++;

        if (currentWave > MAX_WAVES) {
            finished = true;
            return;
        }

        spawnWave(currentWave);
    }

    private void spawnWave(int waveNumber) {

        //Boss wave
        if (waveNumber % 10 == 0) {
            spawnBossWave(waveNumber);
            return;
        }

        //Normal wave
        int enemyCount = 5 + waveNumber * 2;

        for (int i = 0; i < enemyCount; i++) {
            arena.addEnemy(EnemyFactory.createEnemy(randomNormalEnemy(), arena.randomFreePosition(), waveNumber));
        }
    }

    private void spawnBossWave(int waveNumber) {
        arena.addEnemy(EnemyFactory.createEnemy(BOSS, arena.randomFreePosition(), waveNumber));
    }

    private Enemy.EnemyType randomNormalEnemy() {
        Enemy.EnemyType[] types = {BRUISER, CRAWLER, RUNNER};
        return types[random.nextInt(types.length)];
    }

    public boolean isFinished() {
        return currentWave > MAX_WAVES;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void setCurrentWave(int wave) { this.currentWave = wave; }

}



