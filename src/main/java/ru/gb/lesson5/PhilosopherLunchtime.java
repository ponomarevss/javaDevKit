package ru.gb.lesson5;

/**
 * Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * Вилки лежат на столе между каждой парой ближайших философов.
 * Каждый философ может либо есть, либо размышлять.
 * Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Можно брать только две вилки одновременно
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class PhilosopherLunchtime {
        private static final int PHILOSOPHERS_NUMBER = 3;
        private static final String[] FORKS_NAMES = {"Alpha", "Bravo", "Charlie", "Delta", "Echo"};
        private static final String[] PHILOSOPHERS_NAMES = {"Aristotle", "BenedictSpinoza", "Confucius", "Descartes", "EmmanuelKant"};

        private final Fork[] forks = new Fork[PHILOSOPHERS_NUMBER];
        private final Philosopher[] philosophers = new Philosopher[PHILOSOPHERS_NUMBER];

        public void initEvent(){
            initForks();
            initPhilosophers();
            for (Philosopher philosopher : philosophers) {
                philosopher.start();
            }
        }

        private void initForks() {
            for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
                forks[i] = new Fork(FORKS_NAMES[i]);
            }
        }

        private void initPhilosophers() {
            for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
                philosophers[i] = new Philosopher(
                        PHILOSOPHERS_NAMES[i],
                        new Fork[]{forks[i], forks[(i + 1) % PHILOSOPHERS_NUMBER]}
                );
            }
        }

}
