import 'package:flutter/material.dart';
import 'package:myapp/checklist_view.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  void _moveView() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => ChecklistView()),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.title)),
      body: Center(
        child: GestureDetector(
          onTap: _moveView,
          child: Container(
            width: 40,
            height: 40,
            child: const Text('Move'),
            decoration: BoxDecoration(border: Border.all(color: Colors.black)),
          ),
        ),
      ),
    );
  }
}
