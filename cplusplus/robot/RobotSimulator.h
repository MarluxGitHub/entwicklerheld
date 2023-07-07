#ifndef ROBOT_SIMULATOR_H
#define ROBOT_SIMULATOR_H

#include <stdbool.h>
#include <string.h>


enum BEARING {
    NORTH = 0,
    EAST = 1,
    SOUTH = 2,
    WEST = 3
};

struct Position {
    int x;
    int y;
};

struct Robot {
    struct Position position;
    enum BEARING bearing;
};

struct Position get_position(struct Robot robot) {
    return robot.position;
}

enum BEARING get_bearing(struct Robot robot) {
    return robot.bearing;
}

void turn_right(struct Robot *robot) {
    robot->bearing = static_cast<enum BEARING>((robot->bearing + 1) % 4);
}

void turn_left(struct Robot *robot) {
    robot->bearing = static_cast<enum BEARING>((robot->bearing + 3) % 4);
}

void advance(struct Robot *robot) {
    switch (robot->bearing) {
        case NORTH:
            robot->position.y++;
            break;
        case EAST:
            robot->position.x++;
            break;
        case SOUTH:
            robot->position.y--;
            break;
        case WEST:
            robot->position.x--;
            break;
        default:
            std::cerr << "Invalid bearing: " << robot->bearing << std::endl;
            break;
    }
}

void execute_sequence(struct Robot *robot, char *sequence) {
    for (int i = 0; i < strlen(sequence); i++) {
        switch (sequence[i]) {
            case 'R':
                turn_right(robot);
                break;
            case 'L':
                turn_left(robot);
                break;
            case 'A':
                advance(robot);
                break;
            default:
                std::cerr << "Invalid instruction: " << sequence[i] << std::endl;
                break;
        }
    }
}

#endif