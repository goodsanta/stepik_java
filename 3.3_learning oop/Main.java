public class Main {

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    //class was taken from comments for testing purposes
    public static class Robot {

        int x = 0;
        int y = 0;
        Direction direction = Direction.UP;

        public Direction getDirection() {
            return direction;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void turnLeft() {
            System.out.println("Поворот против часовой стрелки");
            if (direction == Direction.DOWN) {
                this.direction = Direction.RIGHT;
                return;
            }

            if (direction == Direction.UP) {
                this.direction = Direction.LEFT;
                return;
            }

            if (direction == Direction.LEFT) {
                this.direction = Direction.DOWN;
                return;
            }

            if (direction == Direction.RIGHT) {
                this.direction = Direction.UP;
                return;
            }
        }

        public void turnRight() {
            System.out.println("Поворот по часовой стрелке");
            if (this.direction == Direction.DOWN) {
                this.direction = Direction.LEFT;
                return;
            }

            if (this.direction == Direction.UP) {
                this.direction = Direction.RIGHT;
                return;
            }

            if (this.direction == Direction.LEFT) {
                this.direction = Direction.UP;
                return;
            }

            if (this.direction == Direction.RIGHT) {
                this.direction = Direction.DOWN;
                return;
            }
        }

        public void stepForward() {
            System.out.print("движение ");
            if (direction == Direction.DOWN) {
                System.out.println("вниз");
                this.y--;
            }

            if (direction == Direction.UP) {
                System.out.println("вверх");
                this.y++;
            }

            if (direction == Direction.LEFT) {
                System.out.println("налево");
                this.x--;
            }

            if (direction == Direction.RIGHT) {
                System.out.println("направо");
                this.x++;
            }
        }
    }

    /**
     * На игровом поле находится робот.<br>
     * Позиция робота на поле описывается двумя целочисленным координатами: X и Y.<br>
     * Ось X смотрит слева направо, ось Y — снизу вверх.<br>
     *
     * В начальный момент робот находится в некоторой позиции на поле.<br>
     * Также известно, куда робот смотрит: вверх, вниз, направо или налево.<br>
     * Ваша задача — привести робота в заданную точку игрового поля.<br>
     * @param robot
     * @param toX
     * @param toY
     */
    public static void moveRobot(Robot robot, int toX, int toY) {
        while (robot.getX() != toX | robot.getY() != toY) {
            if (robot.getX() < toX) {
                while (robot.getDirection() != Direction.RIGHT) {
                    robot.turnRight();
                }
                while (robot.getX() != toX) {
                    robot.stepForward();
                }
            }
            if (robot.getX() > toX) {
                while (robot.getDirection() != Direction.LEFT) {
                    robot.turnLeft();
                }
                while (robot.getX() != toX) {
                    robot.stepForward();
                }
            }
            if (robot.getY() < toY) {
                while (robot.getDirection() != Direction.UP) {
                    robot.turnRight();
                }
                while (robot.getY() != toY) {
                    robot.stepForward();
                }
            }
            if (robot.getY() > toY) {
                while (robot.getDirection() != Direction.DOWN) {
                    robot.turnLeft();
                }
                while (robot.getY() != toY) {
                    robot.stepForward();
                }
            }
        }
        System.out.println(robot.getX() + "," + robot.getY());
    }

    public static void main(String[] args) {
        moveRobot(new Robot(), 1, 3);
    }
}
