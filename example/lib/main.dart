import 'package:flutter/material.dart';
import 'package:flutter_move_task_back/flutter_move_task_back.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'MoveTaskToBackTest',
      home: new FirstScreen(),
    );
  }
}

class FirstScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Plugin example app'),
      ),
      body: Center(
        child: ElevatedButton(
          child: Text('go to second screen'),
          onPressed: () {
            Navigator.of(context).push(MaterialPageRoute(builder: (context) {
              return SecondScreen();
            }));
          },
        ),
      ),
    );
  }
}

class SecondScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("SecondScreen"),
      ),
      body: Center(
        child: ElevatedButton(
          child: Text("Tap to MoveTaskToBack"),
          onPressed: test,
        ),
      ),
    );
  }

  Future<void> test() async {
    try {
      await FlutterMoveTaskBack.moveTaskToBack(nonRoot: false);
    } on Exception {
      print("something wrong...");
    }
  }
}
