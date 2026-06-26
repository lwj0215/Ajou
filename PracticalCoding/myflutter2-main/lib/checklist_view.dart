import 'package:flutter/material.dart';
import 'package:myapp/checklist_item.dart';

class ChecklistView extends StatefulWidget {
  @override
  State<ChecklistView> createState() => _ChecklistView();
}

class _ChecklistView extends State<ChecklistView> {
  List<String> todoList = ['코딩테스트 공부', 'A', 'B'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('checklist')),
      body: Container(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: List<Widget>.generate(todoList.length, (index) {
            return ChecklistItem(text: todoList[index]);
          }),
        ),
      ),
    );
  }
}
