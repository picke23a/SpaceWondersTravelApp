import java.awt.Color;
    import java.awt.Graphics2D;
    import java.awt.Shape;
    import java.awt.geom.Ellipse2D;

    import animation.AbstractAnimation;
    import animation.AnimatedObject;

    /**
     * 
     * This class implements TrailParticles class and defines methods to draw particles
     * along the trail as the student moves around campus
     *
     */
    public class TrailParticles implements AnimatedObject {

        // The diameter of the ball, in pixels
        private static final int BALL_SIZE = 3;

        // The current x-coordinate of the ball from the left/right side of the
        // window
        private int x;

        // The current y-coordinate of the ball from the top/bottom of the window
        private int y;

        // The animation that this object is part of.
        private AbstractAnimation animation;

        // The shape of a particle
        private Ellipse2D particle;


        /**
         * This method draws the shape of the particles
         * 
         * @param animation an initialized abstract animation
         * @param x         the x-coordinate of the current location where the
         *                  particle should be drawn
         * @param y         the y-coordinate of the current location where the
         *                  particle should be drawn
         */
        public TrailParticles(AbstractAnimation animation, int x, int y) {
            this.animation = animation;
            particle = new Ellipse2D.Double(x, y, BALL_SIZE, BALL_SIZE);
            this.x = x;
            this.y = y;
        }

        /**
         * This method fills in the color of the particle
         * 
         * @param g the graphics to use/work on
         */
        public void paint(Graphics2D g) {
            // paints the particle yellow
            g.setColor(Color.YELLOW);
            g.fill(particle);
        }

        /**
         * This method sets the next frame of the particles so that they are in
         * constant motion on the screen
         */
        public void nextFrame() {
             particle.setFrame(x, y, BALL_SIZE, BALL_SIZE);
        }

        /**
         * This method makes the particles disappear when they get in contact with
         * viruses
         */
        public void disappear() {
            // Particles disappear from screen when the user starts a new journey
            // change the x and y coordinates to points off screen
            x = animation.getWidth() + 1;
            y = animation.getHeight() + 1;
        }

        /*
         * Returns the particle
         * 
         * @return particle the particle
         */
        public Shape getShape() {
            return particle;
        }

        /*
         * returns the current x coordinate of the particle
         * 
         * @return x the x coordinate of the point where the particle is
         */
        public int getX() {
            return x;
        }

        /**
         * sets x. FOR TESTING PURPOSES ONLY
         * 
         * @param newX the new value for the x coordinate of the point
         * 
         */
        public void setX(int newX) {
            x = newX;
        }

        /*
         * returns the current y position of the particle
         * 
         * @return y the current y position of the particle
         */
        public int getY() {
            return y;
        }


        /**
         * Gets the animation. FOR TESTING PURPOSES ONLY
         * 
         * @return animation the animation
         */
        public AbstractAnimation getAnimation() {
            return animation;
        }

}