package business

import org.junit.Assert
import org.junit.Test
import java.util.*

class `Oliver receives one question at a time - acceptance test` {

    @Test
    fun `there should be one current question when quiz starts`() {

        // Given there are questions in the quiz
        val first = Question(
                id = UUID.randomUUID().toString(),
                title = "How to create a method with spaces in Kotlin?")
        val second = Question(
                id = UUID.randomUUID().toString(),
                title = "How to create JUnit4 test function in Kotlin?")
        val questions = listOf(first, second)
        val quiz = Quiz(
                id = UUID.randomUUID().toString(),
                questions = questions)

        // When I start the quiz
        quiz.start()

        // Then I see only one question from that quiz
        val currentQuestion = quiz.currentQuestion
        Assert.assertTrue(questions.contains(currentQuestion))

    }

    @Test
    fun `can load quiz from storage, start it and respond with current question`() {

        // Given there is a Quiz with a few Questions in the Quiz Storage
        val first = Question(
                id = UUID.randomUUID().toString(),
                title = "How to create a method with spaces in Kotlin?")
        val second = Question(
                id = UUID.randomUUID().toString(),
                title = "How to create JUnit4 test function in Kotlin?")
        val questions = listOf(first, second)
        val quiz = Quiz(
                id = UUID.randomUUID().toString(),
                questions = questions)
        val quizStorage = TestOnlyQuizStorage(quizes = listOf(quiz))
        val startQuizService = StartQuizService(quizStorage)

        // When I start the Quiz
        val result = startQuizService.startQuiz(quiz.id)

        // Then I see a Question from that Quiz
        Assert.assertTrue(questions.contains(result))

    }
}

