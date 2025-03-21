package at.aau.serg.controllers

import at.aau.serg.models.GameResult
import at.aau.serg.services.GameResultService
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import org.mockito.Mockito.`when` as whenever // when is a reserved keyword in Kotlin

class LeaderboardControllerTests {

    private lateinit var mockedService: GameResultService
    private lateinit var controller: LeaderboardController

    @BeforeEach
    fun setup() {
        mockedService = mock<GameResultService>()
        controller = LeaderboardController(mockedService)
    }

 @Test
    fun test_getLeaderboard_correctScoreSorting() {
        val first = GameResult(1, "first", 20, 20.0) //highest score
        val second = GameResult(2, "second", 15, 10.0) //2nd highest score
        val third = GameResult(3, "third", 10, 15.0) //lowest score

        whenever(mockedService.getGameResults()).thenReturn(listOf(second, first, third))

        val res: List<GameResult> = controller.getLeaderboard()

        verify(mockedService).getGameResults()
        assertEquals(3, res.size)
        assertEquals(first, res[0]) //highest score 1s
        assertEquals(second, res[1]) //2nd highest score
        assertEquals(third, res[2]) //lowest score
    }

/* @Test
    fun test_getLeaderboard_correctScoreSorting() {
        val first = GameResult(1, "first", 20, 5.0) //fastest score
        val second = GameResult(2, "second", 20,10.0)//2nd score
        val third = GameResult(3, "third", 20, 15.0) //lowest score

        whenever(mockedService.getGameResults()).thenReturn(listOf(third, second, first))

        val res: List<GameResult> = controller.getLeaderboard()

        verify(mockedService).getGameResults()
        assertEquals(3, res.size)
        assertEquals(first, res[0]) //highest score 1st
        assertEquals(second, res[1]) //2nd highest score
        assertEquals(third, res[2]) //lowest score
    } */

    @Test
    fun test_getLeaderboard_sameScore_CorrectTimeSorting() {
        val first = GameResult(1, "first", 20, 10.0)
        val second = GameResult(2, "second", 20, 15.0)
        val third = GameResult(3, "third", 20, 20.0)

        whenever(mockedService.getGameResults()).thenReturn(listOf(third, first, second))

        val res: List<GameResult> = controller.getLeaderboard()

        verify(mockedService).getGameResults()
        assertEquals(3, res.size)
        assertEquals(first, res[0])
        assertEquals(second, res[1])
        assertEquals(third, res[2])
    }
/* @Test
    fun test_getLeaderboard_sameScore_CorrectTimeSorting() {
        val fastest=GameResult(1, "fastest",20,10.0) //best time
        val second = GameResult(2, "second", 20, 15.0) //2nd best time
        val slowest=GameResult(3, "slowest",20,20.0) //lowest time

        whenever(mockedService.getGameResults()).thenReturn(listOf(second, fastest, slowest))

        val res: List<GameResult> = controller.getLeaderboard()

        verify(mockedService).getGameResults()
        assertEquals(3, res.size)
        assertEquals(fastest, res[0])
        assertEquals(second, res[1])
        assertEquals(slowest, res[2])
    }*/
}
