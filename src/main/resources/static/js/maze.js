document.addEventListener("DOMContentLoaded", () => {
    const mazeContainer = document.getElementById("maze-container");
    const startButton = document.getElementById("start-button");

    let playerRow = 1;
    let playerCol = 1;

    mazeContainer.style.gridTemplateRows = `repeat(${rows}, 1fr)`;
    mazeContainer.style.gridTemplateColumns = `repeat(${cols}, 1fr)`;

    maze.forEach((row, rowIndex) => {
        row.forEach((cell, colIndex) => {
            const cellDiv = document.createElement("div");

            // Start와 End 텍스트 추가
            if (rowIndex === 1 && colIndex === 1) {
                cellDiv.className = "cell start";
                cellDiv.textContent = "Start"; // Start 텍스트 추가
            } else if (rowIndex === rows - 2 && colIndex === cols - 2) {
                cellDiv.className = "cell end";
                cellDiv.textContent = "End"; // End 텍스트 추가
            } else {
                cellDiv.className = cell === 1 ? "cell wall" : "cell path";
            }
            mazeContainer.appendChild(cellDiv);
        });
    });

    // 플레이어 위치 업데이트
    function updatePlayerPosition() {
        const cells = document.querySelectorAll(".cell");
        cells.forEach(cell => cell.classList.remove("player"));

        const playerIndex = playerRow * cols + playerCol;
        cells[playerIndex].classList.add("player");
    }

    // 시작 버튼 클릭 시 초기화
    startButton.addEventListener("click", () => {
        playerRow = 1;
        playerCol = 1;
        updatePlayerPosition();
    });

    // 방향키로 이동
    document.addEventListener("keydown", (event) => {
        let newRow = playerRow;
        let newCol = playerCol;

        if (event.key === "ArrowUp") newRow--;
        else if (event.key === "ArrowDown") newRow++;
        else if (event.key === "ArrowLeft") newCol--;
        else if (event.key === "ArrowRight") newCol++;

        if (maze[newRow][newCol] === 0) {
            playerRow = newRow;
            playerCol = newCol;
            updatePlayerPosition();
        }

        if (playerRow === rows - 2 && playerCol === cols - 2) {
            alert("축하합니다! 미로 탈출 성공!");
        }
    });

    updatePlayerPosition();
});
