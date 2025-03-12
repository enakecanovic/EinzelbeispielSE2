package at.aau.serg.controllers

import at.aau.serg.models.GameResult
import at.aau.serg.services.GameResultService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/leaderboard")
class LeaderboardController(
    private val gameResultService: GameResultService
) {

    @GetMapping
    fun getLeaderboard(): List<GameResult> =
        //gameResultService.getGameResults().sortedWith(compareBy({ -it.score }, { it.id }))

        // new sorting_by_time
        //gameResultService.getGameResults().sortedWith(compareBy({ -it.score }, { it.timeInSeconds }))

        // new sorting_by_time_2
        gameResultService.getGameResults().sortedWith(compareBy({ it.timeInSeconds }, { -it.score }))

}