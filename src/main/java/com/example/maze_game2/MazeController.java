package com.example.maze_game2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MazeController {

    @GetMapping("/")
    public String home(Model model) {
        int[][] maze = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };

        model.addAttribute("rows", maze.length);
        model.addAttribute("cols", maze[0].length);

        // JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String mazeJson = objectMapper.writeValueAsString(maze);
            model.addAttribute("maze", mazeJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "maze";
    }
}
