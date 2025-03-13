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
       gameResultService.getGameResults().sortedWith(compareBy({ -it.score }, { it.timeInSeconds }))

        //new sorting_by_time_2
        //I have been familiarizing myself with the Kotlin syntax and concluded that "-" in "-it.score" represents descending order, so I have figured to try
        //and challenge myself to find alternative for this and I did it with "byDescending" getting basically the same thing
        //I have also figured even though it passed the tests, the 2nd solution has not been working correctly as it was 1st sorting by time and only in case times are equal it would sort them by score which is incorrect
        //gameResultService.getGameResults().sortedWith(compareByDescending<GameResult>{ it.score }.thenBy{ it.timeInSeconds})

}