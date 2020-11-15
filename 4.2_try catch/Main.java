package one;

public class Main {

    /**
     * unchecked exception thrown in case of connection failing
     */
    public static class RobotConnectionException extends RuntimeException {
        public RobotConnectionException (String message) {
            super(message);
        }

        public RobotConnectionException (String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * method that try connect to robot and move it, and throw exception if three attempts failed.
     * @param robotConnectionManager
     * @param toX point to move robot to
     * @param toY point to move robot to
     */
    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        //three attempts to connect and move robot
        for (int i = 0; i < 3; i++) {
            try (RobotConnection rcm = robotConnectionManager.getConnection()) {
                rcm.moveRobotTo(toX, toY);
                //attempt is successful - exit loop
                i = 3;
            }
            catch (RobotConnectionException r) {
                //if attempt number is three - throw exception
                if (i == 2) {
                    throw r;
                }
            }
            //catch any other exception
            catch (Exception e) {
                throw e;
            }
        }
    }


    public static void main(String[] args) {
    }
}
