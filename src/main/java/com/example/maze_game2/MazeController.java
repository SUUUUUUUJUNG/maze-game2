package com.example.maze_game2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;

@Controller
public class MazeController {

    @GetMapping("/")
    public String home(Model model) {
        int[][] maze = generateMaze(8, 8); // 동적으로 미로 생성
        model.addAttribute("rows", maze.length);
        model.addAttribute("cols", maze[0].length);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String mazeJson = objectMapper.writeValueAsString(maze);
            model.addAttribute("maze", mazeJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "maze";
    }

    // 랜덤 미로 생성 메서드
    private int[][] generateMaze(int rows, int cols) {
        Random random = new Random();
        int[][] maze = new int[rows][cols];

        // 기본 미로 구성 (벽으로 초기화)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = 1;
            }
        }

        // 길 생성 (단순한 알고리즘)
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                maze[i][j] = random.nextBoolean() ? 0 : 1;
            }
        }

        // 시작과 끝 경로 보장
        maze[1][1] = 0;
        maze[rows - 2][cols - 2] = 0;

        return maze;
    }
}
