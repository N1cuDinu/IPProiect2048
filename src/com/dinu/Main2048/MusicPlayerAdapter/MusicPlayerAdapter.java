package com.dinu.Main2048.MusicPlayerAdapter;

    public class MusicPlayerAdapter implements MusicPlayerInterface {

        private MusicPlayer musicPlayer;

        public MusicPlayerAdapter(String musicPath) {
            musicPlayer = new MusicPlayer(musicPath);
        }

        @Override
        public void play() {
            musicPlayer.play();
        }

        @Override
        public void stop() {
            musicPlayer.stop();
        }

        @Override
        public void repeat() {
            musicPlayer.repeat();
        }

}
