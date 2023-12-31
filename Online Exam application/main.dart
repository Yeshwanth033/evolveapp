import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Quiz App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: QuizScreen(),
    );
  }
}

class QuizScreen extends StatefulWidget {
  @override
  _QuizScreenState createState() => _QuizScreenState();
}

class _QuizScreenState extends State<QuizScreen> {
  int currentQuestionIndex = 0;
  int score = 0;

  List<Map<String, Object>> questions = [
    {
      'question': 'What is Flutter?',
      'options': ['Mobile development framework', 'Programming language', 'Design tool'],
      'correctAnswer': 'Mobile development framework',
    },
    {
      'question': 'Which programming language is Flutter based on?',
      'options': ['Java', 'Dart', 'Kotlin'],
      'correctAnswer': 'Dart',
    },
    {
      'question': 'What is the primary use of Dart language in Flutter?',
      'options': ['UI design', 'Backend development', 'Both UI design and Backend development'],
      'correctAnswer': 'Both UI design and Backend development',
    },
  ];

  void checkAnswer(String selectedAnswer) {
    String correctAnswer = questions[currentQuestionIndex]['correctAnswer'];

    if (selectedAnswer == correctAnswer) {
      score++;
    }

    setState(() {
      currentQuestionIndex++;

      if (currentQuestionIndex >= questions.length) {
        showResult();
      }
    });
  }

  void showResult() {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Quiz Completed'),
          content: Text('Your Score: $score / ${questions.length}'),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
                resetQuiz();
              },
              child: Text('Play Again'),
            ),
          ],
        );
      },
    );
  }

  void resetQuiz() {
    setState(() {
      currentQuestionIndex = 0;
      score = 0;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Quiz App'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Text(
              'Question ${currentQuestionIndex + 1}: ${questions[currentQuestionIndex]['question']}',
              style: TextStyle(fontSize: 18),
            ),
            SizedBox(height: 20),
            ...((questions[currentQuestionIndex]['options'] as List<String>).map((option) {
              return ElevatedButton(
                onPressed: () => checkAnswer(option),
                child: Text(option),
              );
            })),
          ],
        ),
      ),
    );
  }
}
